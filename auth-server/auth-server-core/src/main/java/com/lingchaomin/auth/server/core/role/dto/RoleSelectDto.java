package com.lingchaomin.auth.server.core.role.dto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/2 上午9:52
 * @description
 */
public class RoleSelectDto {

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
