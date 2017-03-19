package com.lingchaomin.auth.server.core.verify.service;

import com.yunbeitech.auth.common.dto.OperateResultDto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/13 上午11:55
 * @description 发送验证码相关
 */
public interface IVerifyService {

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    OperateResultDto sendCode(String mobile);

    /**
     * 检测验证码输入是否正确
     * @param code
     * @return
     */
    OperateResultDto checkVerify(String mobile, String code);
}
