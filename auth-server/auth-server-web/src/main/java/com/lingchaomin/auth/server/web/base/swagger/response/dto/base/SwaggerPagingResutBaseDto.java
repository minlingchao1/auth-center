package com.lingchaomin.auth.server.web.base.swagger.response.dto.base;

/**
 * Author:minlingchao
 * Date: 2016/11/9 13:34
 * Description: swagger分页返回基础类
 */
public class SwaggerPagingResutBaseDto {

    private Integer curPage;

    private Integer pageSize;

    private Long count;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
