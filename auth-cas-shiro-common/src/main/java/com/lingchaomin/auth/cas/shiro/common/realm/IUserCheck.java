package com.lingchaomin.auth.cas.shiro.common.realm;

import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.exception.YunbeiOauthException;
import com.lingchaomin.auth.core.remote.PermissionContext;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/1 下午5:05
 * @description
 */
public interface IUserCheck {

    /**
     * 添加用户
     * @param openId
     * @param nick
     * @param figureUrl
     * @return
     */
    void addUser(String client, String openId, String nick, String figureUrl);

    /**
     * 检测用户是否合法
     * @param s
     * @param s1
     */
    void checkUserIsLegal(String s, String s1) throws YunbeiOauthException;

    /**
     * 获取用户信息
     * @param key
     * @return
     */
    UserDto getUserInfo(String key);


    /**
     * 获取权限
     * @param appKey
     * @param username
     * @return
     */
    PermissionContext getPermissions(String appKey, String username);
}

