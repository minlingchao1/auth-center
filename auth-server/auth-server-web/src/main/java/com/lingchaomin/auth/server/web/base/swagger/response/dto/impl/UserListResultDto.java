package com.lingchaomin.auth.server.web.base.swagger.response.dto.impl;


import com.lingchaomin.auth.server.core.user.entity.User;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.base.SwaggerPagingResutBaseDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/24 下午1:31
 * @description 用户分页返回结果
 */
public class UserListResultDto extends SwaggerPagingResutBaseDto {


    private List<User> result;

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
