package com.lingchaomin.auth.server.web.base.swagger.response.dto.impl;


import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.web.base.swagger.response.dto.base.SwaggerPagingResutBaseDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/24 下午1:27
 * @description 应用列表dto
 */
public class AppListResultDto  extends SwaggerPagingResutBaseDto {

    private List<App> result;

    public List<App> getResult() {
        return result;
    }

    public void setResult(List<App> result) {
        this.result = result;
    }
}
