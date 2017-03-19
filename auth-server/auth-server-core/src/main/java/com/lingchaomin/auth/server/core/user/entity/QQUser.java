package com.lingchaomin.auth.server.core.user.entity;



import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午5:55
 * @description qq用户信息
 */
@Data
@Builder
public class QQUser  {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * openid
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String figureUrl;

}
