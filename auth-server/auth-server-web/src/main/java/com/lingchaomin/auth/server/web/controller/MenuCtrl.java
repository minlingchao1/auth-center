package com.lingchaomin.auth.server.web.controller;

import com.lingchaomin.auth.core.dto.Node;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.app.constant.ServerAppKey;
import com.lingchaomin.auth.server.core.user.service.IUserService;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/28 下午4:07
 * @description
 */
@RestController
@RequestMapping("menu")
public class MenuCtrl{

    private static final Logger LOG= LoggerFactory.getLogger(MenuCtrl.class);
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Object getMenuList(){


        String key = (String) SecurityUtils.getSubject().getPrincipal();


        LOG.warn("appKey:{},key:{}", ServerAppKey.APP_KEY,key);


        List<Node> nodes=userService.getMenuList(ServerAppKey.APP_KEY,key);

        return ReqResultFormatter.formatOperSuccessDto(nodes);
    }
}
