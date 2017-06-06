package com.lingchaomin.auth.server.common.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识为 redis 哈希表,
 * key为哈希表的对应的值,
 * key可以设置变量参数进行替换,变量参数需要设置HashColumn的值
 *
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午9:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface Hash {

    /**
     * hash key
     * user:{userId}
     * @return
     */
    public abstract String key();

}
