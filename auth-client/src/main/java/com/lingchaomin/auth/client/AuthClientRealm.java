package com.lingchaomin.auth.client;

import com.yunbeitech.auth.core.remote.IRemoteService;
import com.yunbeitech.auth.core.remote.PermissionContext;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:26
 * @description  客户端调用
 */
public class AuthClientRealm extends AuthorizingRealm {

    private static final Logger LOG= LoggerFactory.getLogger(AuthClientRealm.class);

    private IRemoteService remoteService;

    private String appKey;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        LOG.debug("~~~~~~~~~~~~~~:{}"+principals.toString());

        String ticket = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PermissionContext context = remoteService.getPermissions(appKey, ticket);
        authorizationInfo.setRoles(context.getRoles());
        authorizationInfo.setStringPermissions(context.getPermissions());
        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //永远不会被调用
        throw new UnsupportedOperationException("永远不会被调用");
    }

    public void setRemoteService(IRemoteService remoteServiceInterface) {
        this.remoteService = remoteServiceInterface;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
