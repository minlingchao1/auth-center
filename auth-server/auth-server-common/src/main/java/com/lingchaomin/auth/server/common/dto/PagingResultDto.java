package com.lingchaomin.auth.server.common.dto;


import java.io.Serializable;

/**
 *
 */
public class PagingResultDto implements Serializable {
    /**
     * 当前页
     */
    private Integer draw;
    /**
     * 页尺寸
     */
    private Integer pageSize;
    /**
     * 总数
     */
    private Long count;

    /**
     * 分页结果
     */
    private Object data;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    private Object result;

    public PagingResultDto() {
        init();
    }

    public PagingResultDto(Integer curPage, Integer pageSize) {
        this.draw = curPage;
        this.pageSize = pageSize;

        init();
    }

    private void init() {
        if ((this.draw == null) || (this.draw <= 0)) {
            this.draw = 1;
        }

        if ((this.pageSize == null) || (this.pageSize <= 0)) {
            this.pageSize = 10;
        }
    }

    public Integer getDraw() {
        return draw;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getBegin() {
        return 1L * (this.draw - 1) * this.pageSize;
    }

    public Integer getBeginInt() {
        return (this.draw - 1) * this.pageSize;
    }

    public Integer getPageCount() {
        int mod = ((this.count % this.pageSize) == 0) ? 0 : 1;
        int divide = (int) (this.count / this.pageSize);

        return divide + mod;
    }

    public Long getCount() {
        return count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
