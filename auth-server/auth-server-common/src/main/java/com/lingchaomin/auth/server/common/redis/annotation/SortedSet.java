package com.lingchaomin.auth.server.common.redis.annotation;


import com.lingchaomin.auth.server.common.redis.interfaces.ISortedSetScoreHandler;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识为redis set集合对象
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-10-11 11:39.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface SortedSet {

    /**
     * set集合的key
     *
     * @return
     */
    String key();

    /**
     * 分数处理器
     *
     * @return
     */
    Class<? extends ISortedSetScoreHandler> scoreHandler();
}
