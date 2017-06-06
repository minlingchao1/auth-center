package com.lingchaomin.auth.core.dto;

import java.util.Comparator;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/28 下午1:10
 * @description
 */
public class NodeIdComparator implements Comparator {

    // 按照节点编号排序
    public int compare(Object o1, Object o2) {

        Long j1 =(((Node) o1).getId());
        Long j2 = ((Node) o2).getId();
        return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
    }

}

