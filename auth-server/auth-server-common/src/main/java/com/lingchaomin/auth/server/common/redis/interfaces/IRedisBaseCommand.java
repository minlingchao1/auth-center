package com.lingchaomin.auth.server.common.redis.interfaces;

/**
 * @author lizhong.chen
 * @version V1.0
 * @data 2016-07-08下午1:41:10
 * @description TODO
 */
public interface IRedisBaseCommand {
    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 设置key值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value);

    /**
     * set nx px key
     *
     * @param key
     * @param value
     * @param time
     */
    public void setNxPx(String key, String value, long time);

    /**
     * 删除keys的对象
     *
     * @param keys
     * @return
     */
    public Long del(String... keys);

    /**
     * 设置key的过期时间为seconds
     *
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key, int seconds);

    /**
     * 设置key的过期时间为unixTime
     *
     * @param key
     * @param unixTime
     * @return
     */
    Long expireAt(String key, long unixTime);
}
