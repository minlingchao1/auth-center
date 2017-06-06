package com.lingchaomin.auth.core.remote;


import com.lingchaomin.auth.core.dto.Node;
import com.lingchaomin.auth.core.dto.QQUserDto;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.exception.YunbeiOauthException;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:29
 * @description
 */
public interface IRemoteService {

    /**
     * 获取权限
     * @param appKey
     * @param username
     * @return
     */
    PermissionContext getPermissions(String appKey, String username);

    /**
     * 获取用户信息
     * @param key
     * @return
     */
    UserDto getUserInfo(String key);

    /**
     * 获取菜单列表
     * @return
     */
    List<Node> getMenuList(String appKey, String userName);

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
     * 获取客服部门人员
     * @param appKey
     * @return
     */
    List<QQUserDto> getCustomerService(String appKey);
}
