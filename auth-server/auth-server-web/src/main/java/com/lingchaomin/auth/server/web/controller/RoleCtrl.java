package com.lingchaomin.auth.server.web.controller;



import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.role.dto.RoleListDto;
import com.lingchaomin.auth.server.core.role.dto.RoleSelectDto;
import com.lingchaomin.auth.server.core.role.dto.RoleTreeDto;
import com.lingchaomin.auth.server.core.role.service.IRoleService;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.RoleResultListDto;

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
 * @date 2017/2/22 下午9:09
 * @description 角色列表
 */
@Controller
@RequestMapping("role")
@Api(tags = "角色相关")
public class RoleCtrl {

    @Autowired
    private IRoleService roleService;

    //@RequiresPermissions("role:bindQQ")
    @RequestMapping(value = "add",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "新增",notes = "新增",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="role",value = "角色名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="descr",value = "角色描述",paramType ="query",required = true),
            @ApiImplicitParam(name ="resourceIds",value = "资源id",paramType ="query",required = true),
    })
    public Object add(Long appId,String role,String descr,String resourceIds){
        return roleService.add(appId,role,descr,resourceIds);
    }

    //@RequiresPermissions("role:modify")
    @RequestMapping(value = "modify",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "修改",notes = "修改",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="role",value = "角色名称",paramType ="query",required = true),
            @ApiImplicitParam(name ="descr",value = "角色描述",paramType ="query",required = true),
            @ApiImplicitParam(name ="resourceIds",value = "资源id",paramType ="query",required = true),
    })
    public Object modify(Long id,Long appId,String role,String descr,String resourceIds,String resourcesTree){
        return roleService.modify(id,appId,role,descr,resourceIds,resourcesTree);
    }

    //@RequiresPermissions("role:remove")
    @RequestMapping(value = "delete",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "删除",notes = "删除",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "角色id",paramType ="query",required = true),
    })
    public Object delete(Long id){
        return roleService.remove(id);
    }


   // @RequiresPermissions("role:setAvailable")
    @RequestMapping(value = "setAvailable",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "设置角色可用",notes = "设置角色可用",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "角色id",paramType ="query",required = true),
    })
    public Object setAvailable(Long id){
        return roleService.modifyAvailable(id);
    }

    //@RequiresPermissions("role:setUnAvailable")
    @RequestMapping(value = "setUnAvailable",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "设置角色不可用",notes = "设置角色不可用",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "角色id",paramType ="query",required = true),
    })
    public Object setUnAvailable(Long id){
        return roleService.modifyUnAvailable(id);
    }

    @RequestMapping(value = "select4Auth",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "用于授权获取",notes = "用于授权获取",response = RoleResultListDto.class)
    public Object select4Auth(Long appId){
        List<RoleSelectDto> roleSelectDtos=roleService.select4Auth(appId);
        return roleSelectDtos;
    }

    //@RequiresPermissions("role:list")
    @RequestMapping(value = "list",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "角色列表",notes = "角色列表",response = RoleResultListDto.class)
    public Object list(Model model){

        List<RoleListDto> roles=roleService.findAll();

        model.addAttribute("roles",roles);

        return "/role/role";
    }

    @RequestMapping(value = "getRoleTree",method = RequestMethod.GET)
    @ResponseBody
    public Object getRoleTree(){
        List<RoleTreeDto> roleTreeDtos=roleService.getRoleTreeDto();
        return ReqResultFormatter.formatOperSuccessDto(roleTreeDtos);
    }

    @RequestMapping(value = "getResourceTree",method = RequestMethod.GET)
    @ResponseBody
    public Object getResourceTree(Long id){
        String resourceTrees=roleService.getResourceTreeStr(id);

        return ReqResultFormatter.formatOperSuccessDto(resourceTrees);
    }
}
