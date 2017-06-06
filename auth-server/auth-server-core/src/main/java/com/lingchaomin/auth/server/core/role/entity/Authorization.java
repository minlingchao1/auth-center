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
 * @date 2017/2/20 下午9:35
 * @description 授权信息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authorization  implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 权限ids
     */
    private String roleIds;
}
