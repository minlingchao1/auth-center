package com.lingchaomin.auth.server.core.role.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/1 下午11:46
 * @description 角色list
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleListDto {

    private Long id;

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

    private String appName;

}
