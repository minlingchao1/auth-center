package com.lingchaomin.auth.server.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 模型工具
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-09-14 11:16.
 */
public class ModelUtil {


    /**
     * list集合类型转换由 S->D
     * <p>
     * 接口列表转实现列表
     *
     * @param sList
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D extends S> List<D> formatIfac2Impl(List<S> sList) {
        if (CollectionUtils.isEmpty(sList)) {
            return Collections.emptyList();
        }

        List<D> dest = new ArrayList<D>();
        for (S s : sList) {
            dest.add((D) s);
        }
        return dest;
    }


    /**
     * 实现列表转接口列表
     *
     * @param sList
     * @param <D>
     * @param <S>
     * @return
     */
    public static <D, S extends D> List<D> formatImpl2Ifac(List<S> sList) {
        if (CollectionUtils.isEmpty(sList)) {
            return Collections.emptyList();
        }

        List<D> dest = new ArrayList<D>();
        for (S s : sList) {
            dest.add(s);
        }
        return dest;
    }
}
