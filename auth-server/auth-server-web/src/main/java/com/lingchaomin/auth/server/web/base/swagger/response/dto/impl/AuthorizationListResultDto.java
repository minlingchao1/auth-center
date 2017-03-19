package com.lingchaomin.auth.server.web.base.swagger.response.dto.impl;


import com.lingchaomin.auth.server.core.role.entity.Authorization;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.base.SwaggerPagingResutBaseDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/24 下午1:29
 * @description 授权信息分页返回结果
 */
public class AuthorizationListResultDto extends SwaggerPagingResutBaseDto {

    private List<Authorization> result;

    public List<Authorization> getResult() {
        return result;
    }

    public void setResult(List<Authorization> result) {
        this.result = result;
    }
}
