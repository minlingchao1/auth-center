package com.lingchaomin.auth.client.demo.controller;

import com.lingchaomin.auth.client.demo.base.shiro.realm.SessionConfigs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/2 上午9:54
 * @description
 */
@Controller
public class LoginCtrl {

    private static final Logger LOG= LoggerFactory.getLogger(LoginCtrl.class);
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("/error/account_unknown")
    public String accountUnKnown(String openId,String callBackUrl,Model model){
        model.addAttribute("openId",openId);

        Cookie openIdCookie = new Cookie(SessionConfigs.SESSION.SESSION_OPEN_ID, openId);
        response.addCookie(openIdCookie);


        return "redirect:http://localhost:8081/error/account_unknown?openId=" + openId + "&callBackUrl=" + callBackUrl;
    }
}
