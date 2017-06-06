package com.lingchaomin.auth.server.core.user.entity;



import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午5:55
 * @description qq用户信息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class QQUser  implements Serializable {

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
