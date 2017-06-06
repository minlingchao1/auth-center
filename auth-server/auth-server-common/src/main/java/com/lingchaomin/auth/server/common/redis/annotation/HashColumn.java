package com.lingchaomin.auth.server.common.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis hash map
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午9:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface HashColumn {

    /**
     * hash map column 的名称
     *
     * @return
     */
    public abstract String name();

}
