package com.lingchaomin.auth.server.core.verify.constant;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/13 下午1:29
 * @description 发送结果
 */
public class SendResult {

    private Integer code;

    private String msg;

    private String result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
