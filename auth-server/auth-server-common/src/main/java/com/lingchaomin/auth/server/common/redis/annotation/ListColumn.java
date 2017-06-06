package com.lingchaomin.auth.server.common.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis list - json串的列名
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-10-11 11:35.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ListColumn {
    String name() default "";
}
