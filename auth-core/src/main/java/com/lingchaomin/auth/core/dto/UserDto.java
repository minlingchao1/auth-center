package com.lingchaomin.auth.core.dto;

import java.io.Serializable;


/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/20 下午9:20
 * @description  qq user 关联
 */
public class UserDto implements Serializable {

    private Long userId;

    private String figureUrl;

    private String nick;

    private String openId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFigureUrl() {
        return figureUrl;
    }

    public void setFigureUrl(String figureUrl) {
        this.figureUrl = figureUrl;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
