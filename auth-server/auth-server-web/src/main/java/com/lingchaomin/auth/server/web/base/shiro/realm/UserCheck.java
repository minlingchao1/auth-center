package com.lingchaomin.auth.server.web.base.shiro.realm;


import com.lingchaomin.auth.cas.shiro.common.realm.IUserCheck;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.remote.PermissionContext;
import com.lingchaomin.auth.server.common.utils.MobileUtil;
import com.lingchaomin.auth.server.core.user.entity.User;
import com.lingchaomin.auth.server.core.user.service.IUserService;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/1 下午5:18
 * @description
 */
@Service
public class UserCheck implements IUserCheck {

    @Autowired
    private IUserService userService;
    /**
     * 添加用户
     */
    @Override
    public void addUser(String client, String openId, String nick, String figureUrl) {
        userService.addUser(client,openId,nick,figureUrl);

    }

    /**
     * 检测用户是否合法
     */
    @Override
    public void checkUserIsLegal(String s, String s1) {
        userService.checkUserIsLegal(s,s1);

    }

    /**
     * 获取用户信息
     */
    @Override
    public UserDto getUserInfo(String key) {
        return userService.findByPrincipal(key);
    }

    /**
     * 获取权限
     */
    @Override
    public PermissionContext getPermissions(String appKey, String username) {
       // LOG.warn("appKey:{},userName:{}",appKey,username);
        PermissionContext permissionContext = new PermissionContext();

        boolean isMobile= MobileUtil.checkMobile(username);

        User user=userService.getUser(appKey,username,isMobile);

        List<String> roles=userService.getRole4QQLogin(appKey,user.getUserId());

        if(CollectionUtils.isNotEmpty(roles)){
            permissionContext.setRoles(new HashSet<String>(roles));
        }

        List<String> permissions=userService.getPermission4QQLogin(appKey,user.getUserId());

        if(CollectionUtils.isNotEmpty(permissions)){
            permissionContext.setPermissions(new HashSet<String>(permissions));
        }

        return permissionContext;
    }
}
