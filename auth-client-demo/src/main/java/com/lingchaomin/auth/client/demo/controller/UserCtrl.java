package com.lingchaomin.auth.client.demo.controller;

import com.lingchaomin.auth.client.demo.base.handler.ReqResultFormatter;
import com.lingchaomin.auth.client.demo.base.velocity.constant.CacheConstant;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.remote.IRemoteService;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/21 下午3:51
 * @description
 */
@Controller
@RequestMapping("user")
public class UserCtrl {

    private static final Logger LOG= LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    private IRemoteService remoteService;

    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public Object getUserInfo(){

        String key = (String) SecurityUtils.getSubject().getPrincipal();

        UserDto userDto=null;
        if(StringUtils.isNotBlank(key)){
            userDto= CacheConstant.USER_CACHE.getIfPresent(key);

            if(userDto==null){
                userDto=remoteService.getUserInfo(key);
                CacheConstant.USER_CACHE.put(key,userDto);
            }
        }


       return ReqResultFormatter.formatOperSuccessDto(userDto);
    }
}
