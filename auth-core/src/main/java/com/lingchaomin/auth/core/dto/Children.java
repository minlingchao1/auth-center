package com.lingchaomin.auth.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/28 下午1:07
 * @description 子节点
 */
public class Children  implements Serializable{
    public List list=new ArrayList();

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getSize() {
        return list.size();
    }

    public void addChild(Node node) {
        list.add(node);
    }

    // 孩子节点排序

    public void sortChildren() {

        // 对本层节点进行排序
        // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
        Collections.sort(list, new NodeIdComparator());
        // 对每个节点的下一层节点进行排序
        for (Iterator it = list.iterator(); it.hasNext();) {
            ((Node) it.next()).sortChildren();
        }
    }

    // 拼接孩子节点的JSON字符串
    @Override
    public String toString() {

        String result = "[";

        for (Iterator it = list.iterator(); it.hasNext();) {
            result += ((Node) it.next()).toString();
            result += ",";
        }

        result = result.substring(0, result.length() - 1);
        result += "]";
        return result;

    }

}
