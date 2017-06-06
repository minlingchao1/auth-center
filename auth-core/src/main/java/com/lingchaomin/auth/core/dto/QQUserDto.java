package com.lingchaomin.auth.core.dto;

import java.io.Serializable;


/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/5 上午11:51
 * @description qq用户id
 */

public class QQUserDto implements Serializable {


    /**
     * qq openid
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 权限ids
     */
    private String roleIds;

    /**
     * 用户id
     */
    private Long userId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
