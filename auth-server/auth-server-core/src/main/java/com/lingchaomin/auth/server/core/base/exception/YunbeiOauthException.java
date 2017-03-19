package com.lingchaomin.auth.server.core.base.exception;


/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午3:44
 * @description
 */
public class YunbeiOauthException extends RuntimeException{


    private String openId;

    private ErrorCode errorCode;

    public YunbeiOauthException(ErrorCode apiCode, String msg,String openId) {
        super(msg);
        this.errorCode = apiCode;
        this.openId=openId;
    }

    /**
     * 获取错误码
     * @return
     */
    public ErrorCode getErrorCode(){
        return errorCode;
    }

    /**
     * openid
     * @return
     */
    public String getOpenId() {
        return openId;
    }


}