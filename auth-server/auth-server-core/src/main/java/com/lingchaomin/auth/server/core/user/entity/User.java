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
 * @date 2017/2/17 下午5:58
 * @description 用户表
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User  implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密密码
     */
    private String entryPassword;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 描述
     */
    private String descr;

    /**
     *
     */
    private String salt;
}
