package com.lingchaomin.auth.cas.shiro.common.utils;



import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/23 下午11:08
 * @description
 */
public class ShiroFilterUtils {

    final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;

    /**
     * 是否是Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * response 输出JSON
     * @param resultMap
     */
    public static void out(ServletResponse response, Map<String, String> resultMap){

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JsonUtil.toJson(resultMap).toString());
        } catch (Exception e) {
            //LoggerUtils.fmtError(CLAZZ, e, "输出JSON报错。");
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }
}
