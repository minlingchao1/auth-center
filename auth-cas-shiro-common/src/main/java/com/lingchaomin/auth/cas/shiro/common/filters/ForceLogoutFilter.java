package com.lingchaomin.auth.cas.shiro.common.filters;


import com.lingchaomin.auth.cas.shiro.common.constant.ShiroConstant;
import com.lingchaomin.auth.cas.shiro.common.dto.ShiroFilterResultDto;
import com.lingchaomin.auth.cas.shiro.common.utils.ShiroFilterUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/22 下午5:03
 * @description
 */
public class ForceLogoutFilter extends AccessControlFilter {


    private static final Logger LOG= LoggerFactory.getLogger(ForceLogoutFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {


        Session session=getSubject(servletRequest,servletResponse).getSession(false);

        if(session==null){
            LOG.warn("~~~~~~~~~~~~~~~~~~~~~session is null~~~~~~~");
            return true;
        }

        //判断是不是Ajax请求
        if (session.getAttribute(ShiroConstant.SESSION_FORCE_LOGOUT_KEY)!=null ) {

            LOG.warn(session.getId()+"  当前用户已经被踢");
            if(ShiroFilterUtils.isAjax(servletRequest)){
                ShiroFilterResultDto shiroFilterResultDto=new ShiroFilterResultDto();
                shiroFilterResultDto.setMsg("已被强制退出");
                shiroFilterResultDto.setSuccess(false);
                shiroFilterResultDto.setResult(3001);
                LOG.warn(session.getId()+"  当前用户已经被踢出，并且是Ajax请求！");

                out(servletResponse, shiroFilterResultDto);
            }
            return false;
        }

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        try {
            getSubject(servletRequest, servletResponse).logout();//强制退出
        } catch (Exception e) {/*ignore exception*/}

        //再重定向
        WebUtils.issueRedirect(servletRequest, servletResponse, "/logout");
        return false;
    }

    private void out(ServletResponse hresponse,ShiroFilterResultDto shiroFilterResultDto)throws IOException {
        hresponse.setCharacterEncoding("UTF-8");
        PrintWriter out = hresponse.getWriter();
        out.println(shiroFilterResultDto);
        out.flush();
        out.close();
    }

}
