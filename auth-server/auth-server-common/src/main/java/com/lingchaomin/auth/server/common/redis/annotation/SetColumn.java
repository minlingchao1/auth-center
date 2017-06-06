package com.lingchaomin.auth.server.common.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识为集合的列
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-10-11 11:43.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SetColumn {
    /**
     * 集合列名
     *
     * @return
     */
    String name() default "";
}
