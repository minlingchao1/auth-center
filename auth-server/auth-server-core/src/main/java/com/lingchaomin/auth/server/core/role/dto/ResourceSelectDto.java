package com.lingchaomin.auth.server.core.role.dto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/1 下午1:33
 * @description 用于select显示
 */
public class ResourceSelectDto {

    private Long id;

    private String text;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
