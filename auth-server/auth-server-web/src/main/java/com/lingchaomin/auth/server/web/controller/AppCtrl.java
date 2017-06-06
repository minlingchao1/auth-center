package com.lingchaomin.auth.server.web.controller;


import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.app.dto.AppSelectDto;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.core.app.service.IAppService;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.AppListResultDto;

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
 * @date 2017/2/22 下午9:10
 * @description 应用信息相关
 */
@Controller
@RequestMapping("app")
@Api(tags = "应用信息相关")
public class AppCtrl {

    @Autowired
    private IAppService appService;


    @RequestMapping(value = "getAppInfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取应用信息",notes = "获取应用信息",response =OperateResultDto.class)
    public Object getAppInfo(){
        List<AppSelectDto> apps=appService.selectAll4Resource();
        return ReqResultFormatter.formatOperSuccessDto(apps);
    }

    @RequestMapping(value = "count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取应用数量",notes = "获取应用数量",response =OperateResultDto.class)
    public Object count(){
        long count=appService.count(null);
        return ReqResultFormatter.formatOperSuccessDto(count);
    }

    //@RequiresPermissions("app:modify")
    @RequestMapping(value = "modify",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "应用信息修改",notes = "应用信息修改",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="name",value = "应用名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="descr",value = "应用描述",paramType ="query",required = true),
    })
    public Object modify(Long id,String name,String descr){
        return appService.modify(id,name,descr);
    }

    //@RequiresPermissions("app:bindQQ")
    @RequestMapping(value = "add",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "应用信息添加",notes = "应用信息添加",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="name",value = "应用名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="descr",value = "应用描述",paramType ="query",required = true),
    })
    public Object add(String name,String descr){
        return appService.add(name,descr);
    }

    //@RequiresPermissions("app:remove")
    @RequestMapping(value = "delete",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "应用删除",notes = "应用删除",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "应用id",paramType ="query",required = true),
    })
    public Object delete(Long id){
        return appService.remove(id);
    }

   // @RequiresPermissions("app:setAvailable")
    @RequestMapping(value = "setAvailable",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "设置应用可用",notes = "设置应用可用",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "应用id",paramType ="query",required = true),
    })
    public Object setAvailable(Long id){
        return appService.modifyAvailable(id);
    }

    //@RequiresPermissions("app:setUnAvailable")
    @RequestMapping(value = "setUnAvailable",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "设置应用不可用",notes = "设置应用不可用",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "应用id",paramType ="query",required = true),
    })
    public Object setUnAvailable(Long id){
        return appService.modifyUnAvailable(id);
    }


    //@RequiresPermissions("app:list")
    @RequestMapping(value = "list",method = {RequestMethod.GET})
    @ApiOperation(value = "应用列表",notes = "应用列表",response = AppListResultDto.class)
    public String list(Model model){

        List<App> apps=appService.list(null);
        model.addAttribute("apps",apps);
        return "/app/app";
    }

}
