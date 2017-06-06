package com.lingchaomin.auth.server.common.redis.interfaces;

import com.lingchaomin.auth.server.common.redis.exception.MissAnnotationException;
import com.lingchaomin.auth.server.common.redis.model.KeyMapPair;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lizhong.chen
 * @version V1.0
 * @data 2016-07-08下午1:42:59
 * @description hash
 */
public interface IRedisHashCommand extends IRedisBaseCommand {

    /**
     * 哈希-单属性设置哈希key的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value);


    /**
     * 哈希-获取哈希key的field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field);

    /**
     * 哈希-获取key的所有哈希值
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key);

    /**
     * 根据key和对象初始值,获取对象转换
     *
     * @param t
     * @param key
     * @param <T>
     * @return
     */
    public <T> T hgetAll(T t, String key);

    /**
     * 哈希-获取哈希表key的所有字段
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key);

    /**
     * 哈希-获取哈希表key的所有值
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key);

    /**
     * 哈希-获取哈希表中字段的数量
     *
     * @param key
     * @return
     */
    public Long hlen(String key);

    /**
     * 哈希-获取哈希表key给定字段的值
     *
     * @param key
     * @param fields
     * @return
     */
    public List<String> hmget(String key, String... fields);

    /**
     * 哈希-获取key,指定属性并返回对象
     *
     * @param t
     * @param key
     * @param fields
     * @param <T>
     * @return
     */
    public <T> T hmget(T t, String key, String... fields);

    /**
     * 哈希-多属性设置哈希的值
     * key为KeyMapPair-key
     * hash为KeyMapPair-map
     *
     * @param keyMapPair
     * @return
     */
    public String hmset(KeyMapPair keyMapPair);

    /**
     * 哈希-设置hash对象值hash表中
     *
     * @param t
     * @param <T>
     * @return
     * @throws MissAnnotationException
     */
    public <T> String hmset(T t) throws MissAnnotationException;

    /**
     * 缓存操作
     *
     * @param t
     * @param <T>
     * @return
     * @throws MissAnnotationException
     */
    public <T> String hmsetAndExpire(T t, int seconds) throws MissAnnotationException;

    /**
     * 哈希-多属性设置哈希key的值
     *
     * @param key
     * @param hash
     * @return
     */
    public String hmset(String key, Map<String, String> hash);

    /**
     * 哈希-只有在字段field不存在时,设置哈希表字段的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hsetnx(String key, String field, String value);

    /**
     * 哈希-为哈希表key指定的field字段加上增加值value
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrBy(String key, String field, long value);


    /**
     * 哈希-查询哈希表key中,指定的field是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key, String field);

    /**
     * 哈希表-删除一个或多个哈希表key的字段
     *
     * @param key
     * @param fields
     */
    public Long hdel(String key, String... fields);


}
