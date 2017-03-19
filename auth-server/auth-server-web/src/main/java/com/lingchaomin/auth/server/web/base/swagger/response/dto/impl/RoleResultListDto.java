package com.lingchaomin.auth.server.web.base.swagger.response.dto.impl;


import com.lingchaomin.auth.server.core.role.entity.Role;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.base.SwaggerPagingResutBaseDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/24 下午1:30
 * @description 角色分页返回结果
 */
public class RoleResultListDto extends SwaggerPagingResutBaseDto {

    private List<Role> result;

    public List<Role> getResult() {
        return result;
    }

    public void setResult(List<Role> result) {
        this.result = result;
    }
}
