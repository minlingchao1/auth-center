package com.lingchaomin.auth.server.common.redis.interfaces;


import com.lingchaomin.auth.server.common.redis.RedisVisitorBaseFacade;
import com.lingchaomin.auth.server.common.redis.model.SyncResult;

/**
 * 数据检测
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/29 下午2:25
 */
public interface IRedisSync {

    /**
     * 获取redis访问器
     *
     * @return
     */
    RedisVisitorBaseFacade getRedisVisitor();

    /**
     * 是否存在于redis
     *
     * @param key
     * @return
     */
    boolean isInRedis(String key);

    /**
     * 同步
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> SyncResult<T> sync(String key);

    /**
     * 检测是否存在于redis
     * 若存在则不做操作
     * 若不存在进行数据同步
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> SyncResult<T> checkAndSync(String key);

    /**
     * 重新同步
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> SyncResult<T> resync(String key);
}
