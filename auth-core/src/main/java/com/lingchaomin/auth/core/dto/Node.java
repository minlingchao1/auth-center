package com.lingchaomin.auth.core.dto;

import java.io.Serializable;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/28 下午1:05
 * @description 节点
 */
public class Node  implements Serializable{

    private Long id;

    private String name;

    private String url;

    private Long parentId;

    private Children children=new Children();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    // 对子节点进行横向排序

    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    @Override
    public String toString() {
        String result = "{" + "id : '" + id + "'" + ", name : '" + name + "'"+"parentId:'"+parentId+"'";

        if (children != null && children.getSize() != 0) {

            result += ", children : " + children.toString();

        } else {
            result += ", leaf : true";
        }
        return result + "}";
    }
}
