package com.lingchaomin.auth.server.web.controller;

import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.core.verify.service.IVerifyService;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.impl.UserListResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/13 上午11:53
 * @description 验证码相关
 */
@RestController
@RequestMapping("verify")
public class VerifyCtrl {

    @Autowired
    private IVerifyService verifyService;

    @RequestMapping(value = "sendCode",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "发送验证码",notes = "发送验证码",response = UserListResultDto.class)
    public Object sendCode(String mobile){
        OperateResultDto operateResultDto= verifyService.sendCode(mobile);
        return operateResultDto;
    }

    @RequestMapping(value = "checkVerify",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "验证验证码",notes = "验证验证码",response = UserListResultDto.class)
    public Object checkVerify(String mobile,String code){
        OperateResultDto operateResultDto=verifyService.checkVerify(mobile,code);

        if(operateResultDto.getSuccess()){
            return true;
        }

        return false;
    }

}
