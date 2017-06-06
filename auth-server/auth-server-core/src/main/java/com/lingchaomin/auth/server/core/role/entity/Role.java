package com.lingchaomin.auth.server.core.role.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午5:59
 * @description 角色表
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role  implements Serializable {

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

    private String resourceIdsTree;

}
