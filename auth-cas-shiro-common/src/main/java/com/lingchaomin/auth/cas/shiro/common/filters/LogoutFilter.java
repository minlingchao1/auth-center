package com.lingchaomin.auth.cas.shiro.common.filters;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/24 下午1:34
 * @description
 */
public class LogoutFilter extends AdviceFilter {

    private static final Logger log = LoggerFactory.getLogger(org.apache.shiro.web.filter.authc.LogoutFilter.class);
    public static final String DEFAULT_REDIRECT_URL = "/";
    private String redirectUrl = "/";

    public LogoutFilter() {
    }

    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        String redirectUrl = this.getRedirectUrl(request, response, subject);

        try {
            subject.logout();
        } catch (SessionException var6) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
        }

        Map<String, String[]> parameterNames=request.getParameterMap();


        List<String> attr= Collections.list(request.getAttributeNames());

        if(StringUtils.isNotBlank(request.getParameter("service"))){

            String[] redirectArr=redirectUrl.split("service=");

            StringBuilder sb=new StringBuilder();

            sb.append(redirectArr[0]);
            sb.append("service=");
            sb.append(request.getParameter("service"));

            redirectUrl=sb.toString();
            //this.redirectUrl=sb.toString();
        }

        //log.warn("~~~~~~~~~~~~~~~~~params:{},attr:{},redirectUrl:{}",new Object[]{request.getParameter("service"),attr.toString(),newRedirectUrl});
        this.issueRedirect(request, response, redirectUrl);
        return false;
    }

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        log.warn("~~~~~~~~~~~~~~~~~redirectUrl:{}",redirectUrl);
        WebUtils.issueRedirect(request, response, redirectUrl);
    }

    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        return this.getRedirectUrl();
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }



}
