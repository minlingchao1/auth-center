package com.lingchaomin.auth.server.web.base.shiro.realm;



import com.lingchaomin.auth.server.core.app.constant.ServerAppKey;
import com.lingchaomin.auth.server.core.user.service.IUserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/16 下午7:23
 * @description cas 单点登录继承类
 */
public class AuthServerCasRealm extends CasRealm {

    private static final Logger LOG=LoggerFactory.getLogger(AuthServerCasRealm.class);

    @Autowired
    private IUserService userService;


    /**
     * 权限验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String ticket = (String)principals.getPrimaryPrincipal();

        //可能是qq可能是mobile

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        LOG.warn("openId:{}",ticket);

        List<String> roles=userService.getRole4QQLogin(ServerAppKey.APP_KEY,ticket);

        if(org.apache.commons.collections4.CollectionUtils.isNotEmpty(roles)){
            LOG.warn("role:{}",roles);
            authorizationInfo.setRoles(new HashSet<String>(roles));
        }

        List<String> permissions=userService.getPermission4QQLogin(ServerAppKey.APP_KEY,ticket);

        if(org.apache.commons.collections4.CollectionUtils.isNotEmpty(permissions)){
            LOG.warn("permissions:{}",permissions);
            authorizationInfo.setStringPermissions(new HashSet<String>(permissions));
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
       LOG.debug("登录验证:{}",token);

        CasToken casToken = (CasToken)token;
        if(token == null) {
            return null;
        } else {
            String ticket = (String)casToken.getCredentials();
            if(!StringUtils.hasText(ticket)) {
                return null;
            } else {
                TicketValidator ticketValidator = this.ensureTicketValidator();

                try {
                    Assertion e = ticketValidator.validate(ticket, this.getCasService());
                    AttributePrincipal casPrincipal = e.getPrincipal();
                    String userId = casPrincipal.getName();
                    LOG.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{ticket, this.getCasServerUrlPrefix(), userId});


                    Map attributes = casPrincipal.getAttributes();
                    List principals = CollectionUtils.asList(new Object[]{userId, attributes});

                    LOG.debug("~~~~~~~~~~~~~attributes:{}",attributes.toString());

                    List<String> oauthClient=(List<String>) attributes.get("oauth_client");
                    List<String> openId=(List<String>)attributes.get("openid");

                    LOG.debug("oauthClient:{},openId:{}",oauthClient,openId);
                    userService.checkUserIsLegal(oauthClient.get(0),openId.get(0));


                    casToken.setUserId(userId);
                    String rememberMeAttributeName = this.getRememberMeAttributeName();
                    String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
                    boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
                    if(isRemembered) {
                        casToken.setRememberMe(true);
                    }

                    SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(principals, this.getName());
                    return new SimpleAuthenticationInfo(principalCollection, ticket);
                } catch (TicketValidationException var14) {
                    throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", var14);
                } catch (Exception e) {
                    LOG.debug(e.getMessage(),e);
                    throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
                }
            }
        }
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
