package com.lingchaomin.auth.server.web.base.shiro.realm;

import com.lingchaomin.auth.cas.shiro.common.realm.YunbeiCasRealm;
import com.lingchaomin.auth.core.remote.PermissionContext;
import com.lingchaomin.auth.server.common.utils.MobileUtil;

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

        String ticket = (String)principals.getPrimaryPrincipal();

        //可能是qq可能是mobile

        Boolean isMobile=false;
        if(MobileUtil.checkMobile(ticket)){
           isMobile=true;
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        LOG.warn("openId:{}",ticket);

         PermissionContext permissionContext=getUserCheck().getPermissions(getAppKey(),ticket);
        return authorizationInfo;
    }
}
