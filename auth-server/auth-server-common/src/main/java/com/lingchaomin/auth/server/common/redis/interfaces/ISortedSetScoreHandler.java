package com.lingchaomin.auth.server.common.redis.interfaces;

/**
 * 有序集合分数处理器
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-10-11 17:18.
 */
public interface ISortedSetScoreHandler<T> {

    /**
     * 根据对象获取排序分数
     *
     * @param obj
     * @return
     */
    public double getScore(T obj);
}
