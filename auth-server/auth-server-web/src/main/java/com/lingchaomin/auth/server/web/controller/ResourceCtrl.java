package com.lingchaomin.auth.server.web.controller;

import com.lingchaomin.auth.server.core.role.dto.MenuListDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceListDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceSelectDto;
import com.lingchaomin.auth.server.core.role.entity.Resource;
import com.lingchaomin.auth.server.core.role.service.IResourceService;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.ResourceListResultDto;
import com.yunbeitech.auth.common.dto.OperateResultDto;
import com.yunbeitech.auth.common.handler.ReqResultFormatter;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/22 下午9:10
 * @description 资源信息列表
 */
@Controller
@RequestMapping("resource")
@Api(tags = "资源相关")
public class ResourceCtrl {

    @Autowired
    private IResourceService resourceService;


    //@RequiresPermissions("resource:getById")
    @RequestMapping(value = "getById",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "根据id查找",notes = "根据id查找",response = ResourceListResultDto.class)
    public Object getById(Long id){

       Resource resource=resourceService.findById(id);

        return ReqResultFormatter.formatOperSuccessDto(resource);
    }

    //@RequiresPermissions("resource:modify")
    @RequestMapping(value = "modify",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "修改",notes = "修改",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="name",value = "资源名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="type",value = "资源类型",paramType ="query",required = true),
            @ApiImplicitParam(name ="priority",value = "优先级",paramType ="query",required = true),
            @ApiImplicitParam(name ="parenrtId",value = "父资源id",paramType ="query",required = true),
            @ApiImplicitParam(name ="permission",value = "权限",paramType ="query",required = true),
    })
    public Object modify(Long id,String name,Integer type,Integer priority,Long parentId,String permission,String url){
        return resourceService.modify(id,name,type,priority,parentId,permission,url);
    }

    //@RequiresPermissions("resource:bindQQ")
    @RequestMapping(value = "add",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "添加",notes = "添加",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="name",value = "资源名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="type",value = "资源类型",paramType ="query",required = true),
            @ApiImplicitParam(name ="priority",value = "优先级",paramType ="query",required = true),
            @ApiImplicitParam(name ="parenrtId",value = "父资源id",paramType ="query",required = true),
            @ApiImplicitParam(name ="permission",value = "权限",paramType ="query",required = true),
    })
    public Object add(String name,Integer type,Integer priority,Long appId,Long parentId,String parentIds,String permission,String url){
        return resourceService.add(name, type, priority,appId, parentId, permission, url);
    }

    //@RequiresPermissions("resource:remove")
    @RequestMapping(value = "delete",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "删除",notes = "删除",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "资源id",paramType ="query",required = true),
    })
    public Object delete(Long id){
        return resourceService.remove(id);

    }

    @RequestMapping(value = "getResourceByAppId",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据应用资源",notes = "根据应用资源",response = ResourceListResultDto.class)
    public Object getResourceByAppId(Long appId){

        List<ResourceSelectDto> resources=resourceService.findByAppId(appId);

        return ReqResultFormatter.formatOperSuccessDto(resources);
    }


    //@RequiresPermissions("resource:setAvailable")
    @RequestMapping(value = "setAvailable",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "设置资源可用",notes = "设置资源可用",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "角色id",paramType ="query",required = true),
    })
    public Object setAvailable(Long id){
        return resourceService.modifyAvailable(id);
    }

    //@RequiresPermissions("resource:setUnAvailable")
    @RequestMapping(value = "setUnAvailable",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "设置资源不可用",notes = "设置资源不可用",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "角色id",paramType ="query",required = true),
    })
    public Object setUnAvailable(Long id){
        return resourceService.modifyUnAvailable(id);
    }


    @RequestMapping(value = "menu",method = RequestMethod.GET)
    @ResponseBody
    public Object getMenuList(){
        List<MenuListDto> menuList=new ArrayList<>();




        MenuListDto userList=MenuListDto.builder().type(2).name("用户管理").id(4l).url("/user/list").build();
        MenuListDto authList=MenuListDto.builder().type(2).name("用户权限分配").url("/auth/list").id(4l).build();
        MenuListDto roleList=MenuListDto.builder().type(2).name("角色管理").url("/role/list").id(4l).build();
        MenuListDto resourceList=MenuListDto.builder().type(2).name("资源管理").url("/resource/list").id(4l).build();
        MenuListDto appList=MenuListDto.builder().type(2).name("应用管理").url("/app/list").id(4l).build();

        List<MenuListDto> userSubList=new ArrayList<>();
        List<MenuListDto> roleSubList=new ArrayList<>();
        List<MenuListDto> appSubList=new ArrayList<>();

        userSubList.add(userList);
        userSubList.add(authList);

        roleSubList.add(roleList);
        roleSubList.add(resourceList);

        appSubList.add(appList);


        MenuListDto user=MenuListDto.builder().type(1).name("用户").id(1l).list(userSubList).build();
        MenuListDto role=MenuListDto.builder().type(1).name("权限").id(2l).list(roleSubList).build();
        MenuListDto app=MenuListDto.builder().type(1).name("应用").id(3l).list(appSubList).build();

        menuList.add(user);
        menuList.add(role);
        menuList.add(app);


        return ReqResultFormatter.formatOperSuccessDto(menuList);
    }


//    @RequiresPermissions("resource:list")
    @RequestMapping(value = "list",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "资源列表",notes = "资源列表",response = ResourceListResultDto.class)
    public Object list(Model model){

        List<ResourceListDto> resources=resourceService.findAll();

        model.addAttribute("resources",resources);

        return "/role/resource";
    }

}
