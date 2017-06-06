package com.lingchaomin.auth.cas.shiro.common.realm;


import com.lingchaomin.auth.core.dto.AuthorizationDto;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
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

import java.util.List;
import java.util.Map;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/16 下午7:23
 * @description cas 单点登录继承类
 */
public class YunbeiCasRealm extends CasRealm {

    private static final Logger LOG=LoggerFactory.getLogger(YunbeiCasRealm.class);

    private String appKey;

    private IUserCheck userCheck;



    /**
     * 登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
       LOG.warn("登录验证:{}",token);

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

                    LOG.warn("~~~~~~~~~~~~~attributes:{}",attributes.toString());

                    List<String> oauthClient=(List<String>) attributes.get("oauth_client");

                    //用户名／密码
                    if(!CollectionUtils.isEmpty(oauthClient)){
                        List<String> openId=(List<String>)attributes.get("openid");
                        List<String> nickName=(List<String>)attributes.get("nickname");
                        List<String> figureUrl=(List<String>)attributes.get("figureurl");
                        userCheck.addUser(oauthClient.get(0),openId.get(0),nickName.get(0),figureUrl.get(0));
                        userCheck.checkUserIsLegal(oauthClient.get(0),openId.get(0));
                    }

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

    public void addUser(String client,String openId,String nick,String figureUrl){

    }


    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        return genKey(principals);
    }

    public AuthorizationDto getPermissions(PrincipalCollection principals){
        Cache<Object,AuthorizationInfo> authorizationInfoCache=getAuthorizationCache();

        AuthorizationInfo authorizationInfo=null;
        if(authorizationInfoCache.get(genKey(principals))==null){
            authorizationInfo=  doGetAuthorizationInfo(principals);
            authorizationInfoCache.put(genKey(principals),authorizationInfo);
        }else {
            authorizationInfo=authorizationInfoCache.get(genKey(principals));
        }

        AuthorizationDto authorizationDto=new AuthorizationDto();
        authorizationDto.setPermissions(authorizationInfo.getStringPermissions());
        authorizationDto.setRoles(authorizationInfo.getRoles());
        return authorizationDto;
    }

    private String genKey(PrincipalCollection principals){
        return appKey+":"+principals.getPrimaryPrincipal();
    }


    public IUserCheck getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(IUserCheck userCheck) {
        this.userCheck = userCheck;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
