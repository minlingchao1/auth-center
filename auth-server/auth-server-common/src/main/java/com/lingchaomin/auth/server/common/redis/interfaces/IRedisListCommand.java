package com.lingchaomin.auth.server.common.redis.interfaces;



import com.lingchaomin.auth.server.common.redis.exception.MissAnnotationException;

import java.util.List;

/**
 * @author lizhong.chen
 * @version V1.0
 * @data 2016-07-08下午1:44:38
 * @description redis 列表
 */
public interface IRedisListCommand extends IRedisBaseCommand {

    /**
     * 列表-在key列表左侧插入元素values
     *
     * @param key
     * @param values
     * @return
     */
    public Long lpush(String key, String... values);

    /**
     * 列表-在obj的key列表左侧插入obj的元素values
     *
     * @param obj
     * @param <T>
     * @return
     */
    public <T> Long lpush(T obj) throws MissAnnotationException;

    /**
     * 列表-key列表从左侧取出一个值
     *
     * @param key
     * @return
     */
    public String lpop(String key);

    /**
     * 列表-key列表从左侧取出一个值，并转换为T
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T lpop(T obj, String key);

    /**
     * 列表-在key列表右侧插入元素values
     *
     * @param key
     * @param values
     * @return
     */
    public Long rpush(String key, String... values);

    /**
     * 列表-key列表从右侧取出一个值
     *
     * @param obj
     * @param <T>
     * @return
     */
    public <T> Long rpush(T obj) throws MissAnnotationException;

    /**
     * 列表-key列表重最右边取出一个数
     *
     * @param key
     * @return
     */
    public String rpop(String key);

    /**
     * 列表-key从右侧取出一个值，并转换为T
     *
     * @param obj
     * @param key
     * @param <T>
     * @return
     */
    public <T> T rpop(T obj, String key);

    /**
     * 列表-获取列表key区间start-end的值
     * start从0开始
     * end=-1,表示取出所有值,end值大于列表值不会出现越界错误,获取所有值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end);

    /**
     * 列表-获取列表key区间start-end的值
     * start从0开始
     * end=-1,表示取出所有值
     *
     * @param t
     * @param key
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public <T> List<T> lrange(T t, String key, long start, long end);

    /**
     * 列表-获取列表key的长度
     *
     * @param key
     * @return
     */
    public Long llen(String key);

    /**
     * 列表-从列表key中获取index的值
     *
     * @param key
     * @param index
     * @return
     */
    public String lindex(String key, long index);

    /**
     * 列表-删除列表key区间value的值
     * count>0时:从表头开始向表尾搜索,移除与value值相等的元素,数量为value
     * count<0时:从表尾开始向表头搜索,移除与value值相等的元素,数量为value
     * count=0时:移除表中所有与value相等的值
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long lrem(String key, long count, String value);

    /**
     * 修剪列表
     * Ltrim 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * 下标 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param start
     * @param stop
     * @return
     */
    String ltrim(String key, long start, long stop);

}
