package com.lingchaomin.auth.server.core.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/23 上午10:51
 * @description
 */
@Getter
@Setter
@ToString
@Builder
public class SessionDto {

    /**
     * ip地址
     */

    private String sessionId;

    private String ip;

    private String nick;

    private String startTime;

    private String lastLoginTime;

    /**
     * 状态
     */
    private Integer status;

}
