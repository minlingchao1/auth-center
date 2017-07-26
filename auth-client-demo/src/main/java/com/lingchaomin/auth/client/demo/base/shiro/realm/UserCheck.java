package com.lingchaomin.auth.client.demo.base.shiro.realm;

import com.lingchaomin.auth.cas.shiro.common.realm.IUserCheck;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.exception.YunbeiOauthException;
import com.lingchaomin.auth.core.remote.IRemoteService;
import com.lingchaomin.auth.core.remote.PermissionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/1 下午5:43
 * @description 远程调用接口获取用户信息
 */
@Service
public class UserCheck implements IUserCheck {

    @Autowired
    private IRemoteService remoteService;

    @Override
    public void addUser(String s, String s1, String s2, String s3) {
         remoteService.addUser(s,s1,s2,s3);
    }

    @Override
    public void checkUserIsLegal(String s, String s1) throws YunbeiOauthException {
         remoteService.checkUserIsLegal(s,s1);
    }

    @Override
    public UserDto getUserInfo(String s) {
        return remoteService.getUserInfo(s);
    }

    @Override
    public PermissionContext getPermissions(String s, String s1) {
        return remoteService.getPermissions(s,s1);
    }
}
