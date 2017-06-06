package com.lingchaomin.auth.server.common.redis.interfaces;


import com.lingchaomin.auth.server.common.redis.exception.MissAnnotationException;

import java.util.List;

/**
 * @author lizhong.chen
 * @version V1.0
 * @data 2016-07-08下午1:47:02
 * @description 有序列表
 */
public interface IRedisSortedListCommand extends IRedisBaseCommand {

    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     * <p>
     * redis正是通过分数来为集合中的成员进行从小到大的排序
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Long zadd(String key, double score, String member);


    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> Long zadd(T t) throws MissAnnotationException;

    /**
     * 获取有序集合的成员数
     *
     * @param key
     * @return
     */
    public Long zcard(String key);


    /**
     * 通过索引区间返回有序集合成指定区间内的成员
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public List<String> zrange(String key, Long start, Long stop);


    /**
     * 获取有序集合的所有成员
     *
     * @param key
     * @return
     */
    public List<String> zrangeAll(String key);

    /**
     * 获取有序集合的所有成员-转换为对象
     *
     * @param t
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> zrangeAll4Obj(T t, String key);

}
