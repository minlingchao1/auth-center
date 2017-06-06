package com.lingchaomin.auth.cas.shiro.common.dto;


import org.apache.commons.lang.StringUtils;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/1 下午4:54
 * @description
 */
public class ShiroFilterResultDto {

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
