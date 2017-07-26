package com.lingchaomin.auth.client.demo.base.shiro.realm;


import com.lingchaomin.auth.cas.shiro.common.realm.YunbeiCasRealm;
import com.lingchaomin.auth.core.remote.PermissionContext;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/16 下午7:23
 * @description cas 单点登录继承类
 */
public class AuthServerCasRealm extends YunbeiCasRealm {

    private static final Logger LOG=LoggerFactory.getLogger(AuthServerCasRealm.class);

    /**
     * 权限验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        LOG.warn("~~~~~~~~~~~~~~:{}"+principals.toString());

        String ticket = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PermissionContext context = getUserCheck().getPermissions(getAppKey(), ticket);
        authorizationInfo.setRoles(context.getRoles());
        authorizationInfo.setStringPermissions(context.getPermissions());
        return authorizationInfo;
    }
}
