package com.lingchaomin.auth.server.core.role.dto;

import java.util.Comparator;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/30 下午2:51
 * @description
 */
public class ResourceIdComparator  implements Comparator{

    // 按照节点编号排序
    public int compare(Object o1, Object o2) {

        Long j1 =(((TreeNodeDto) o1).getId());
        Long j2 = ((TreeNodeDto) o2).getId();
        return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
    }
}
