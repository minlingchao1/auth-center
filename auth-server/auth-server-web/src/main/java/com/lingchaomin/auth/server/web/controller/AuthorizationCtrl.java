package com.lingchaomin.auth.server.web.controller;


import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.core.role.service.IAuthorizationService;
import com.lingchaomin.auth.server.core.user.dto.AuthorizationListDto;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.AuthorizationListResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/22 下午9:33
 * @description 授权相关
 */
@Controller
@RequestMapping("auth")
@Api(tags = "授权相关")
public class AuthorizationCtrl {

    @Autowired
    private IAuthorizationService authorizationService;

    //@RequiresPermissions("auth:bindQQ")
    @RequestMapping(value = "auth",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "应用信息修改",notes = "应用信息修改",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="appId",value = "应用名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="userId",value = "用户id",paramType ="query",required = true),
            @ApiImplicitParam(name ="roleIds",value = "角色ids",paramType ="query",required = true),
    })
    public Object auth(Long appId,String userId,String roleIds){
        return authorizationService.add(appId,userId,roleIds);
    }


    //@RequiresPermissions("auth:modify")
    @RequestMapping(value = "modify",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "授权信息修改",notes = "授权信息修改",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="appId",value = "应用名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="roleIds",value = "角色ids",paramType ="query",required = true),
    })
    public Object modify(Long id,Long appId,String roleIds){
        return authorizationService.modify(id,appId,roleIds);
    }

    //@RequiresPermissions("auth:remove")
    @RequestMapping(value = "delete",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "应用信息删除",notes = "应用信息删除",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "应用信息Id",paramType ="query",required = true),
    })
    public Object delete(Long id){
        return authorizationService.remove(id);
    }


    ////////页面

   // @RequiresPermissions("auth:list")
    @RequestMapping(value = "list",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "授权信息列表",notes = "授权信息列表",response = AuthorizationListResultDto.class)
    public Object list(Model model){

        List<AuthorizationListDto> auths=authorizationService.findAll();
        model.addAttribute("auths",auths);

        return "/user/auth";
    }


}
