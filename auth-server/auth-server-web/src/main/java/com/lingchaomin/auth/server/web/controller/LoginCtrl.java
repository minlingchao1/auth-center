package com.lingchaomin.auth.server.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/21 下午3:10
 * @description 登录注册相关
 */
@Controller
public class LoginCtrl {

    private static final Logger LOG= LoggerFactory.getLogger(LoginCtrl.class);
    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/error/account_unknown")
    public String accountUnKnown(String openId,String callBackUrl,Model model){
        model.addAttribute("openId",openId);
        model.addAttribute("callBackUrl", callBackUrl);
        return "/error/account_unknown";
    }
}
