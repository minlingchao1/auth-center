package com.lingchaomin.auth.server.web.base.swagger.response.dto.impl;

import com.lingchaomin.auth.server.core.role.entity.Resource;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.base.SwaggerPagingResutBaseDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/24 下午1:30
 * @description 资源分页返回结果
 */
public class ResourceListResultDto extends SwaggerPagingResutBaseDto {

    private List<Resource> result;

    public List<Resource> getResult() {
        return result;
    }

    public void setResult(List<Resource> result) {
        this.result = result;
    }
}
