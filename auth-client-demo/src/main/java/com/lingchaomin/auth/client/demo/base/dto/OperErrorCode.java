package com.lingchaomin.auth.client.demo.base.dto;

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
     * 部署信息不存在
     */
    DEPLOY_INFO_NOT_EXIST(3001,"deploy_info_not_exist","部署信息不存在"),

    /**
     * tomcat上传失败
     */
    TOMCAT_UPLOAD_FAIL(3002,"tomcat_upload_fail","tomcat上传失败"),

    /**
     * tomcat不存在
     */
    TOMCAT_NOT_EXIST(3003,"tomcat_not_exist","tomcat不存在"),

    /**
     * shell脚本文件上传失败
     */
    SHELL_FILE_UPLOAD_FAIL(3004,"shell_file_upload_fail","shell脚步文件上传失败"),

    /**
     * shell脚本已经存在
     */
    SHELL_FILE_HAS_EXISTED(3005,"shell_file_has_existed","shell脚本已经存在"),

    FORCE_LOGOUT(3001,"force_logout","强制退出"),

    TPL_PARENT_SAME(4001,"tpl_parent_same","父模版与自模版相同")
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
