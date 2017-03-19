package com.lingchaomin.auth.server.core.user.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 上午9:37
 * @description qq 用户相关
 */
@Data
@Builder
public class QQUserRef  {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * openId
     */
    private String openId;

}
