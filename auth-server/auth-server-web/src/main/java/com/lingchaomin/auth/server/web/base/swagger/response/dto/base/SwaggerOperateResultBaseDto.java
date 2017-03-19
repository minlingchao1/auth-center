package com.lingchaomin.auth.server.web.base.swagger.response.dto.base;

import org.apache.commons.lang3.StringUtils;

/**
 * Author:minlingchao
 * Date: 2016/11/9 13:44
 * Description: 操作返回基础类
 */
public class SwaggerOperateResultBaseDto {

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
