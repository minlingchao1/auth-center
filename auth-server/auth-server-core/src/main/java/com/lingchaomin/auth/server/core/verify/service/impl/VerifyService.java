package com.lingchaomin.auth.server.core.verify.service.impl;

import com.lingchaomin.auth.server.core.verify.constant.SendResult;
import com.lingchaomin.auth.server.core.verify.constant.SmsSendConfig;
import com.lingchaomin.auth.server.core.verify.dao.VerifyDao;
import com.lingchaomin.auth.server.core.verify.entity.VerifyCode;
import com.lingchaomin.auth.server.core.verify.service.IVerifyService;
import com.yunbeitech.auth.common.dto.OperErrorCode;
import com.yunbeitech.auth.common.dto.OperateResultDto;
import com.yunbeitech.auth.common.handler.ReqResultFormatter;
import com.yunbeitech.auth.common.utils.DateUtil;
import com.yunbeitech.auth.common.utils.JsonUtil;
import com.yunbeitech.auth.common.utils.MobileUtil;
import com.yunbeitech.auth.common.utils.RandomUtil;
import com.yunbeitech.auth.common.utils.WebUtil;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/16 下午3:34
 * @description
 */
@Service
public class VerifyService implements IVerifyService {

    private static final Logger LOG= LoggerFactory.getLogger(VerifyService.class);

    @Autowired
    private VerifyDao verifyDao;

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    public OperateResultDto sendCode(String mobile){

        //判断手机号是否合法
        if(!MobileUtil.checkMobile(mobile)){
            return ReqResultFormatter.formatFailDto(OperErrorCode.NOT_MOBILE);
        }


        long count=checkMobileLimit(mobile);

        if(count>5){
            return ReqResultFormatter.formatFailDto(OperErrorCode.VERIFY_CODE_SEND_LIMIT);
        }

        //发送验证码
        String code= RandomUtil.getRandStr(6);

        SendResult sendResult=sendVerify(code,mobile);

        if(sendResult==null||sendResult.getCode()!=1) {
            return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
        }

        long ret=addVerifyCode(code,mobile);

        if(ret>0){
            //  addVerifyCode2Redis(mobile,code);
        }else {
            return ReqResultFormatter.formatOperSuccessDto(OperErrorCode.SUCCESS);
        }


        return ReqResultFormatter.formatOperSuccessDto(OperErrorCode.SUCCESS);
    }

    /**
     * 添加验证码
     * @param code
     * @param mobile
     * @return
     */
    private long addVerifyCode(String code,String mobile){
        VerifyCode verifyCode=VerifyCode.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .code(code)
                .mobile(mobile)
                .build();
       return verifyDao.insert(verifyCode);
    }


    private long checkMobileLimit(String mobile) {
        Long now=System.currentTimeMillis();

        Long beginTs=now- DateUtil.HOUR_MILLIS;
        long count=verifyDao.selectByMobileAndSendTs(mobile,beginTs,now);
        return count;
    }

    /**
     * 检测验证码输入是否正确
     */
    public OperateResultDto checkVerify(String mobile,String code) {

        String realCode= verifyDao.selectByMobile(mobile);

        if(realCode.equals(code)){
            return ReqResultFormatter.formatOperSuccessDto(null);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }


    /**
     * 发送验证码
     * @param code
     * @param mobile
     * @return
     */
    private SendResult sendVerify(String code,String mobile){

        String content=String.format(SmsSendConfig.CONTENT,code);

        NameValuePair[] data=new NameValuePair[]{
                new NameValuePair("apikey",SmsSendConfig.API_KEY),
                new NameValuePair("mobile",mobile),
                new NameValuePair("content",content)
        };

        try {
            String result= WebUtil.doPost(SmsSendConfig.VERIFY_URL,data);
            SendResult sendResult= JsonUtil.fromJson(result,SendResult.class);
            return sendResult;
        } catch (HttpException e) {
            LOG.error(e.getMessage(),e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}
