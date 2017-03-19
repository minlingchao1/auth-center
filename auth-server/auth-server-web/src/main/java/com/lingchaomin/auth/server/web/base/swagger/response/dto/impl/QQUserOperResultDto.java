package com.lingchaomin.auth.server.web.base.swagger.response.dto.impl;


import com.lingchaomin.auth.server.core.user.entity.QQUser;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.base.SwaggerOperateResultBaseDto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/24 下午1:34
 * @description qq用户返回结果
 */
public class QQUserOperResultDto extends SwaggerOperateResultBaseDto {
    private QQUser result;

    public QQUser getResult() {
        return result;
    }

    public void setResult(QQUser result) {
        this.result = result;
    }
}
