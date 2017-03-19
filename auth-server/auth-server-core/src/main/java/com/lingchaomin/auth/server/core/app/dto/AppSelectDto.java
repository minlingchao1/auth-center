package com.lingchaomin.auth.server.core.app.dto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/1 下午12:29
 * @description 应用select
 */
public class AppSelectDto {

    /**
     * id
     */
    private Long id;

    /**
     * text
     */
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
