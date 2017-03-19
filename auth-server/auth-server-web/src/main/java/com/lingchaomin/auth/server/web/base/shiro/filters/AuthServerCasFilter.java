package com.lingchaomin.auth.server.web.base.shiro.filters;

import com.lingchaomin.auth.server.core.base.exception.ErrorCode;
import com.lingchaomin.auth.server.core.base.exception.YunbeiOauthException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午5:26
 * @description 登录跳转过滤器
 */
public class AuthServerCasFilter extends AuthenticatingFilter {
    private static Logger logger = LoggerFactory.getLogger(CasFilter.class);

    private static final String TICKET_PARAMETER = "ticket";
    private String failureUrl;

    /**
     * 锁定账户url
     */
    private String lockeAccountUrl;

    /**
     * 未知账户url
     */
    private String unknownAccountUrl;

    public AuthServerCasFilter() {
    }

    /**
     * 创建token
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String ticket = httpRequest.getParameter("ticket");
        return new CasToken(ticket);
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return this.executeLogin(request, response);
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 登录成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String callbackUrl = (String) getSubject(request, response).getSession().getAttribute("authc.callbackUrl");

        if(StringUtils.isEmpty(callbackUrl)) {
            callbackUrl = getSuccessUrl();
        }

        logger.debug("登录成功～～～:{},method:{},",callbackUrl,WebUtils.getSavedRequest(request).getMethod());

        WebUtils.issueRedirect(request,response,callbackUrl,null,true);
        return false;
    }


    /**
     * 登录失败
     * @param token
     * @param ae
     * @param request
     * @param response
     * @return
     */
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request, ServletResponse response) {
        Subject subject = this.getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {

            logger.debug("登录失败异常～～～～～～～～～:{},{}",ae.getCause(),ae.getMessage());

            try {
                Throwable exception=ae.getCause();
                if(exception instanceof YunbeiOauthException){
                    YunbeiOauthException yunbeiOauthException=(YunbeiOauthException)exception;
                    if(yunbeiOauthException.getErrorCode()== ErrorCode.USER_NOT_BIND){
                        Map queryParams=new HashMap();
                        queryParams.put("openId",yunbeiOauthException.getOpenId());
                        WebUtils.issueRedirect(request, response, this.unknownAccountUrl,queryParams);
                    }else if(yunbeiOauthException.getErrorCode()== ErrorCode.USER_HAS_LOCKED){
                        WebUtils.issueRedirect(request, response, this.lockeAccountUrl);
                    }
                }else{
                    WebUtils.issueRedirect(request, response, this.failureUrl);
                }
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }

        } else {
            try {
                logger.debug("callBackUrl:{}",request.getAttributeNames().toString());
                this.issueSuccessRedirect(request, response);
            } catch (Exception var8) {
                logger.error("Cannot redirect to the default success url", var8);
            }
        }

        return false;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public void setLockeAccountUrl(String lockeAccountUrl) {
        this.lockeAccountUrl = lockeAccountUrl;
    }

    public void setUnknownAccountUrl(String unknownAccountUrl) {
        this.unknownAccountUrl = unknownAccountUrl;
    }
}
