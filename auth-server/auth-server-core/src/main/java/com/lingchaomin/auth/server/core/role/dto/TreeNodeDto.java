package com.lingchaomin.auth.server.core.role.dto;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/30 下午2:22
 * @description js tree dto
 */
@Data
@Builder
public class TreeNodeDto {

    private Long id;

    private String text;

    private String icon="none";

    private Long parentId;

    private List<TreeNodeDto> children;

    public void sortChildren() {

        // 对本层节点进行排序
        // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
        Collections.sort(children, new ResourceIdComparator());
        // 对每个节点的下一层节点进行排序
        for (Iterator it = children.iterator(); it.hasNext();) {
            ((TreeNodeDto) it.next()).sortChildren();
        }
    }
}
