package com.lingchaomin.auth.server.core.base.dao;

import java.util.List;

/**
 * IDao
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16-3-29.下午7:57
 */
public interface IDao<T> {

    Long countAll();

    T selectById(Long id);

    Long insert(T t);

    Long insertBatch(List<T> list);

    Long updateById(T t);

    Long updateBatch(List<T> list);

    Long deleteById(Long id);

}
