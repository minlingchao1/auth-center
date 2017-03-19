package com.lingchaomin.auth.server.core.role.entity;


import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午6:02
 * @description 资源表
 */
@Data
@Builder
public class Resource  {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 父资源id
     */
    private Long parentId;

    /**
     * 父资源ids
     */
    private String parentIds;

    /**
     * 所需要的权限
     */
    private String permission;

    /**
     * 是否可获取
     */
    private Boolean available;

    /**
     * 所在应用
     */
    private Long appId;

    /**
     * url
     *
     */
    private String url;
}
