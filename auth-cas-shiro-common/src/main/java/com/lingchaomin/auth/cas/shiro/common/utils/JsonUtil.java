package com.lingchaomin.auth.cas.shiro.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * json util
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16-4-8.下午2:23
 */
public final class JsonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    /**
     * 将POJO转为JSON
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOG.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON转为POJO
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOG.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

    public static <T> T fromJson(String json,Class<?> collectionClass,Class<?>elementClasses) {
        T pojo;
        try {
            JavaType javaType=OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass,elementClasses);
            pojo= OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            LOG.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

    /**
     * json字符串转Map
     * 2015年4月3日上午10:41:25
     * auther:shijing
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static Map<String, Object> parseMap(String jsonStr) throws IOException {
        Map<String, Object> map = OBJECT_MAPPER.readValue(jsonStr, Map.class);
        return map;
    }

}

