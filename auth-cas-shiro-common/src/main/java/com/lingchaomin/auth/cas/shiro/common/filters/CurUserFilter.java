package com.lingchaomin.auth.cas.shiro.common.filters;


import com.lingchaomin.auth.cas.shiro.common.constant.ShiroConstant;
import com.lingchaomin.auth.cas.shiro.common.realm.IUserCheck;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/20 下午8:56
 * @description 获取当前用户
 */
public class CurUserFilter extends PathMatchingFilter {

    private static final Logger LOG= LoggerFactory.getLogger(CurUserFilter.class);

    private IUserCheck userCheck;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String key = (String) SecurityUtils.getSubject().getPrincipal();

        if(StringUtils.isNotBlank(key)){
            LOG.warn("userName:{}",key);
            request.setAttribute(ShiroConstant.CURRENT_USER, userCheck.getUserInfo(key));
        }
        return true;
    }

    public IUserCheck getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(IUserCheck userCheck) {
        this.userCheck = userCheck;
    }
}
