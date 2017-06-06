package com.lingchaomin.auth.server.common.redis.interfaces;



import com.lingchaomin.auth.server.common.redis.exception.MissAnnotationException;

import java.util.List;
import java.util.Set;

/**
 * @author lizhong.chen
 * @version V1.0
 * @data 2016-07-08下午1:45:59
 * @description 集合
 */
public interface IRedisCollectionCommand extends IRedisBaseCommand {
    /**
     * 集合-加入数据至集合
     *
     * @param key
     * @param members
     * @return
     */
    public Long sadd(String key, String... members);

    /**
     * 集合添加T元素
     *
     * @param t
     * @param <T>
     * @return
     * @throws MissAnnotationException
     */
    public <T> Long sadd(T t) throws MissAnnotationException;

    /**
     * 集合-获取集合的成员数
     *
     * @param key
     * @return
     */
    public Long scard(String key);

    /**
     * 集合-列出集合的元素
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key);

    /**
     * 集合-列出集合的元素
     *
     * @param t
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> smembers(T t, String key);

    /**
     * 集合-判断member是否是集合key的成员
     *
     * @param key
     * @param member
     * @return
     */
    public boolean sismember(String key, String member);

    /**
     * 集合-返回给定所有集合的差集 以第一个集合为参照物
     *
     * @param keys
     * @return
     */
    public Set<String> sdiff(String... keys);

    /**
     * 集合-返回给定所有集合的交集
     *
     * @param keys
     * @return
     */
    public Set<String> sinter(String... keys);

    /**
     * 集合-返回给定所有集合的并集
     *
     * @param keys
     * @return
     */
    public Set<String> sunion(String... keys);

    /**
     * 集合-移除并返回key集合的的一个随机元素
     *
     * @param key
     * @return
     */
    public String spop(String key);

    /**
     * 集合-移除并返回key集合的的count个随机元素
     *
     * @param key
     * @param count
     * @return
     */
    public Set<String> spop(String key, long count);

    /**
     * 集合-返回集合中一个随机元素
     *
     * @param key
     * @return
     */
    public String srandmember(String key);

    /**
     * 集合-返回集合中count个随机元素
     *
     * @param key
     * @param count
     * @return
     */
    public List<String> srandmember(String key, int count);

    /**
     * 集合-移除key集合指定元素
     *
     * @param key
     * @param members
     * @return
     */
    public Long srem(String key, String... members);

}
