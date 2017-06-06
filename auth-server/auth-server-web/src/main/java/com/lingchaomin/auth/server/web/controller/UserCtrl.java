package com.lingchaomin.auth.server.web.controller;


import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.user.dto.UserSelectDto;
import com.lingchaomin.auth.server.core.user.entity.User;
import com.lingchaomin.auth.server.core.user.service.IUserService;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.UserListResultDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2017/2/22 下午5:30
 * @description  用户相关
 */
@Controller
@RequestMapping("user")
@Api(tags = "用户相关接口")
public class UserCtrl {

    private static final Logger LOG= LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "register",method = {RequestMethod.POST,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "用户注册",notes = "用户注册",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="nick",value = "用户昵称",paramType ="query",required = true),
            @ApiImplicitParam(name ="mobile",value = "用户手机号",paramType ="query",required = true),
            @ApiImplicitParam(name ="descr",value = "用户备注信息",paramType ="query",required = true),
            @ApiImplicitParam(name ="password",value = "用户密码",paramType ="query",required = true),
    })
    public Object register(String mobile,String nick,String descr,String password,String openId){
        return userService.register(mobile,nick,descr,password,openId);
    }


    @RequestMapping(value = "bindQQ",method = {RequestMethod.POST,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "用户绑定qq",notes = "用户绑定qq",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="openId",value = "用户昵称",paramType ="query",required = true),
            @ApiImplicitParam(name ="mobile",value = "用户手机号",paramType ="query",required = true),
    })
    public Object bindQQ(String openId,String mobile){
        return userService.bindQQ(openId,mobile);
    }


    @RequestMapping(value = "check",method = {RequestMethod.POST,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "用户检测",notes = "用户检测",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="mobile",value = "用户手机号",paramType ="query",required = true),
    })
    public Object check(String mobile){
        return userService.check(mobile);
    }




    //@RequiresPermissions("user:remove")
    @RequestMapping(value = "delete",method = {RequestMethod.POST,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "删除用户",notes = "删除用户",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "用户id",paramType ="query",required = true)
    })
    public Object delete(Long id){
        return userService.remove(id);
    }


    //@RequiresPermissions("user:lock")
    @RequestMapping(value = "lock",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value ="锁定用户",notes = "锁定用户",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "用户id",paramType ="query",required = true)
    })
    public Object lock(Long id){
        return userService.modifyLock(id);
    }

    //@RequiresPermissions("user:unlock")
    @RequestMapping(value = "unlock",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value ="解锁用户",notes = "解锁用户",response = OperateResultDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name ="id",value = "用户id",paramType ="query",required = true)
    })
    public Object unlock(Long id){
        return userService.modifyUnLock(id);
    }


    @RequestMapping(value = "select4Auth",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "用于授权",notes = "用于授权",response = UserListResultDto.class)
    public Object select4Auth(){
        List<UserSelectDto> userSelectDtos=userService.select4Auth();
        return ReqResultFormatter.formatOperSuccessDto(userSelectDtos);
    }

    @RequestMapping(value = "getUserInfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getUserInfo(){
        String key = (String) SecurityUtils.getSubject().getPrincipal();

        UserDto userDto=null;
        if(StringUtils.isNotBlank(key)){
            LOG.warn("userName:{}",key);
            userDto= userService.findByPrincipal(key);
        }

        return ReqResultFormatter.formatOperSuccessDto(userDto);
    }

    @RequestMapping("modifyById")
    @ResponseBody
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="pk",value = "数据库id",paramType ="query",required = true),
            @ApiImplicitParam(name ="name",value = "列名",paramType ="query",required = true),
            @ApiImplicitParam(name ="value",value = "值",paramType ="query",required = true),
    })
    public Object modifyById(Long pk, String name, String value) {
        return userService.modify(pk,name,value);
    }

    @RequestMapping("list")
    public String listAllUser(Model model){
        List<User> users=userService.list(null);

        model.addAttribute("users",users);
        return "/user/user";
    }


}
