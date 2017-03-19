package com.lingchaomin.auth.server.core.role.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午5:59
 * @description 角色表
 */
@Data
@Builder
public class Role  {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 描述
     */
    private String descr;

    /**
     * 资源id
     */
    private String resourceIds;

    /**
     * 是否可以使用
     */
    private Boolean available;

}
