package com.lingchaomin.auth.server.web.controller;


import com.lingchaomin.auth.server.core.user.service.IQQUserService;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.QQUserOperResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/22 下午9:06
 * @description   qq用户信息
 */
@RestController
@RequestMapping("qquser")
@Api(tags = "QQ用户相关接口")
public class QQUserCtrl {

    @Autowired
    private IQQUserService qqUserService;

    //@RequiresPermissions("qquser:get")
    @RequestMapping(value = "getByUserId",method = {RequestMethod.POST,RequestMethod.POST})
    @ApiOperation(value = "获取用户qq绑定信息",notes = "获取用户qq绑定信息",response = QQUserOperResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="userId",value = "用户id",paramType ="query",required = true),
    })
    public Object getByUserId(Long userId){
        return qqUserService.getByUserId(userId);
    }
}
