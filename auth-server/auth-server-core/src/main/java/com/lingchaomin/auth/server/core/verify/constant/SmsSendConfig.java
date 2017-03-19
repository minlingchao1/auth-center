package com.lingchaomin.auth.server.core.verify.constant;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/13 下午1:12
 * @description
 */
public class SmsSendConfig {

    /**
     * 验证码发送地址
     */
    public static final String VERIFY_URL="https://api.dingdongcloud.com/v1/sms/sendyzm";

    /**
     * api key
     */
    public static final String API_KEY="d0c0642ba453e92e00f57aaa21930ce0";

    /**
     * 发送短信内容
     */
    public static final String CONTENT="【叮咚云】您的验证码是：%s";
}
