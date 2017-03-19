package com.lingchaomin.auth.server.core.role.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/1 下午5:11
 * @description 资源listdto
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceListDto {

    private Long id;

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
    private String parentName;


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

    private String appName;

    /**
     * url
     *
     */
    private String url;
}
