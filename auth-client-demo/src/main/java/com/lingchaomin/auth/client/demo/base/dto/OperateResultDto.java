package com.lingchaomin.auth.client.demo.base.dto;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Author:minlingchao
 * Date: 2016/10/8 17:14
 * Description: 操作返回结果
 */
public class OperateResultDto implements Serializable {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String msg = StringUtils.EMPTY;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 结果
     */
    private Object result;

    public OperateResultDto(Boolean success) {
        this.success = success;
    }

    public OperateResultDto(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public OperateResultDto(Boolean success, String msg, Object results) {
        this.success = success;
        this.msg = msg;
        this.result = results;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}