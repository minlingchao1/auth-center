package com.lingchaomin.auth.server.common.redis.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 反射工具类
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-10-11 13:26.
 */
public class ReflectUtil {

    private static final Logger LOG = LoggerFactory.getLogger("reflect_log");

    /**
     * 从obj获取属性field的值
     *
     * @param obj
     * @param field
     * @param <T>
     * @return
     */
    public static <T> String getFieldValue2Str(T obj, Field field) {
        String str = null;
        try {
            Object o = obj.getClass().getMethod("get" + StringUtils.capitalize(field.getName())).invoke(obj);

            if (o != null) {
                // 属性类型
                Class<?> fieldClazz = field.getType();

                if (String.class == fieldClazz) {
                    str = String.valueOf(o);
                } else if (long.class == fieldClazz || Long.class == fieldClazz) {
                    str = String.valueOf(o);
                } else if (int.class == fieldClazz || Integer.class == fieldClazz) {
                    str = String.valueOf(o);
                } else if (boolean.class == fieldClazz || Boolean.class == fieldClazz) {
                    str = String.valueOf(o);
                } else if (Date.class == fieldClazz) {
                    //转为时间戳
                    Date d = (Date) o;
                    str = String.valueOf(d.getTime());
                } else if (double.class == fieldClazz || Double.class == fieldClazz) {
                    str = String.valueOf(o);
                } else if (float.class == fieldClazz || Float.class == fieldClazz) {
                    str = String.valueOf(o);
                }
            }
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage(), e);
        }
        return str;
    }

    /**
     * 设置obj的field的属性值
     *
     * @param obj
     * @param field
     * @param value
     * @param <T>
     */
    public static <T> void setFieldValue4Str(T obj, Field field, String value) {
        if (value != null && (!"null".equals(value))) {
            Class<?> fieldClazz = field.getType();
            Object param = null;
            if (String.class == fieldClazz) {
                param = value;
            } else {
                if (long.class == fieldClazz || Long.class == fieldClazz) {
                    param = Long.valueOf(value);
                } else if (int.class == fieldClazz || Integer.class == fieldClazz) {
                    param = Integer.valueOf(value);
                } else if (boolean.class == fieldClazz || Boolean.class == fieldClazz) {
                    param = Boolean.valueOf(value);
                } else if (Date.class == fieldClazz) {
                    //转为时间戳
                    param = new Date(Long.valueOf(value));
                } else if (double.class == fieldClazz || Double.class == fieldClazz) {
                    param = Double.valueOf(value);
                } else if (float.class == fieldClazz || Float.class == fieldClazz) {
                    param = Float.valueOf(value);
                }
            }
            try {
                obj.getClass().getDeclaredMethod("set" + StringUtils.capitalize(field.getName()), fieldClazz).invoke(obj, param);
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                LOG.error(e.getMessage(), e);
            } catch (NoSuchMethodException e) {
                LOG.error(e.getMessage(), e);
            }
        }

    }
}
