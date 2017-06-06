package com.lingchaomin.auth.server.common.dto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午9:55
 * @description 错误代码
 */
public enum OperErrorCode {

    /**
     * 成功
     */
    SUCCESS(200,"success","成功"),

    /**
     * 失败
     */
    FAIL(9999,"fail","失败"),

    /**
     * 缺少参数
     */
    LACK_PARAMS(1001,"lack_params","缺少参数"),

    /**
     * 用户已经注册过
     */
    USER_HAS_REGISTERED(2001,"user_has_registered","用户已经注册过"),

    /**
     * 密码为空
     */
    PASSWORD_IS_EMPTY(2002,"password_is_password","密码为空"),

    /**
     * 用户已经绑定
     */
    USER_HAS_BEEN_BINDED(2003,"user_has_been_binded","该用户已经绑定"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(2004,"user_not_exist","用户不存在"),

    /**
     * 不是手机号
     */
    NOT_MOBILE(2005,"not_mobile","手机号"),

    /**
     * 手机验证码发送超过次数
     */
    VERIFY_CODE_SEND_LIMIT(2006,"verify_code_send_limit","手机验证码发送限制"),

    FORCE_LOGOUT(3001,"force_logout","强制退出")
    ;

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 英文信息
     */
    private String valueEn;

    /**
     * 中文信息
     */
    private String valueZn;

    OperErrorCode(Integer code, String valueEn, String valueZn) {
        this.code = code;
        this.valueEn = valueEn;
        this.valueZn = valueZn;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValueEn() {
        return valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    public String getValueZn() {
        return valueZn;
    }

    public void setValueZn(String valueZn) {
        this.valueZn = valueZn;
    }
}
