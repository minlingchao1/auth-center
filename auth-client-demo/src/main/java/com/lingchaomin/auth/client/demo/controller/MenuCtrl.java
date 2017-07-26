package com.lingchaomin.auth.client.demo.controller;


import com.lingchaomin.auth.client.demo.base.handler.ReqResultFormatter;
import com.lingchaomin.auth.core.dto.Node;
import com.lingchaomin.auth.core.remote.IRemoteService;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MenuCtrl {

    private static final Logger LOG= LoggerFactory.getLogger(MenuCtrl.class);

    @Autowired
    private IRemoteService remoteService;


    @Value("${cas.app.key}")
    private String appKey;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Object getMenuList(){

        String key = (String) SecurityUtils.getSubject().getPrincipal();

        LOG.warn("appKey:{},key:{}",appKey,key);

        List<Node> nodes=remoteService.getMenuList(appKey,key);

        return ReqResultFormatter.formatOperSuccessDto(nodes);
    }
}
