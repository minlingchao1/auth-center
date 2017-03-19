package com.lingchaomin.auth.server.core.verify.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/13 下午12:04
 * @description 验证码基础类
 */
@Data
@Builder
public class VerifyCode {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;


    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 发送时间
     */
    private Long sendTs;

}
