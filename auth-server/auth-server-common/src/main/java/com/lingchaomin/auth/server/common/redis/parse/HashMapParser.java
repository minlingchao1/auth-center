package com.lingchaomin.auth.server.common.redis.parse;




import com.lingchaomin.auth.server.common.redis.annotation.Hash;
import com.lingchaomin.auth.server.common.redis.annotation.HashColumn;
import com.lingchaomin.auth.server.common.redis.exception.InvalidParameterException;
import com.lingchaomin.auth.server.common.redis.exception.MissAnnotationException;
import com.lingchaomin.auth.server.common.redis.model.KeyMapPair;
import com.lingchaomin.auth.server.common.redis.util.ReflectUtil;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * hash map parser 解析器
 * <p/>
 * v1:支持基本类型+date类型的转换
 * TODO clz v2:支持引用类型属性的转换,使用新hash作为处理原则
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午9:52
 */
public final class HashMapParser {

    public static final Logger LOG = LoggerFactory.getLogger("redis_hashmap_parser");

    /**
     * key参数前缀
     */
    public static final String KEY_PARAM_PREFIX = "{";

    public static final String KEY_PARAM_PREFIX_REPLACE = "\\{";

    /**
     * key参数后缀
     */
    public static final String KEY_PARAM_SUFFIX = "}";

    public static final String KEY_PARAM_SUFFIX_REPLACE = "\\}";


    /**
     * key map pair 转换为对象
     *
     * @param t    需要转换的实例对象
     * @param hash
     * @param <T>
     * @return
     */
    public static <T> T hash2Obj(T t, Map<String, String> hash) throws InvalidParameterException {

        if (t == null) {
            throw new InvalidParameterException("format obj can not null");
        }
        if (MapUtils.isEmpty(hash)) {
            throw new InvalidParameterException("hash can not null");
        }

        Class<?> clazz = t.getClass();

        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isEmpty(fields)) {
            return null;
        }

        for (Field field : fields) {
            HashColumn hashColumn = field.getAnnotation(HashColumn.class);

            if (hashColumn != null) {
                //列名
                String columnName = getColumnName(field, hashColumn);

                if (hash.containsKey(columnName)) {
                    String value = hash.get(columnName);
                    ReflectUtil.setFieldValue4Str(t, field, value);
                }
            }
        }

        return t;
    }

    /**
     * 对象 转换为 redis的map
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> KeyMapPair obj2Hash(T obj) throws MissAnnotationException {
        if (null == obj) {
            return null;
        }

        Class<?> clazz = obj.getClass();

        //hashMap annotation
        Hash hashAnnotation = clazz.getAnnotation(Hash.class);
        if (null == hashAnnotation) {
            throw new MissAnnotationException(clazz.getCanonicalName(), Hash.class.getCanonicalName());
        }

        //属性
        Field[] fields = clazz.getDeclaredFields();

        Map<String, String> hash = new HashMap<String, String>();
        if (ArrayUtils.isNotEmpty(fields)) {
            for (Field field : fields) {
                //hash map 列名
                HashColumn hashColumn = field.getAnnotation(HashColumn.class);

                if (hashColumn != null) {
                    String val = ReflectUtil.getFieldValue2Str(obj, field);
                    if (StringUtils.isNotEmpty(val)) {
                        String columnName = getColumnName(field, hashColumn);
                        //解析列
                        hash.put(columnName, val);
                    }
                }
            }
        }

        String key = parseKey(clazz, hashAnnotation.key(), hash);

        KeyMapPair keyMapPair = new KeyMapPair();
        keyMapPair.setKey(key);
        keyMapPair.setMap(hash);

        return keyMapPair;
    }

    /**
     * 解析hash的key
     *
     * @param clazz
     * @param annoKey
     * @param hash
     * @return
     */
    protected static String parseKey(Class<?> clazz, String annoKey, Map<String, String> hash) throws MissAnnotationException {

        if ("".equals(annoKey.trim())) {
            throw new MissAnnotationException("class:[" + clazz.getCanonicalName() + "] hash annotation key can not be empty");
        }

        if (annoKey.contains(KEY_PARAM_PREFIX) && MapUtils.isEmpty(hash)) {
            throw new MissAnnotationException("class:[" + clazz.getCanonicalName() + "] hash annotation key:[" + annoKey + "]has param key,but column annos is null");
        }

        String key = annoKey;
        //key参数
        while (key.contains(KEY_PARAM_PREFIX)) {
            //开始
            int begin = key.indexOf(KEY_PARAM_PREFIX);
            //结束
            int end = key.indexOf(KEY_PARAM_SUFFIX);
            //检测符号对
            if (end < begin) {
                //key参数符号对异常
                throw new MissAnnotationException("class:[" + clazz.getCanonicalName() + "] hash annotation key param fix error");
            }

            String oneKey = key.substring(begin + 1, end);

            String value = hash.get(oneKey);
            if (StringUtils.isEmpty(value)) {
                //value不存在则设置为
                value = "";
            }

            //替换key参数
            key = key.replaceAll(KEY_PARAM_PREFIX_REPLACE + oneKey + KEY_PARAM_SUFFIX_REPLACE, value);

        }

//        LOG.info(key);
        return key;
    }

    public static String getColumnName(Field field, HashColumn hashColumn) {
        String annoHashColumn = hashColumn.name();

        return StringUtils.isNotEmpty(annoHashColumn) ? annoHashColumn : field.getName();
    }

}
