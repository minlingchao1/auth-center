package com.lingchaomin.auth.server.core.user.dto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/2 上午9:42
 * @description
 */
public class UserSelectDto {

    /**
     * userId
     */
    private String id;

    /**
     * 用户昵称
     */
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
