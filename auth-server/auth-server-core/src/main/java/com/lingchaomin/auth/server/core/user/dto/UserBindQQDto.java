package com.lingchaomin.auth.server.core.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/5 下午2:09
 * @description 绑定qq dto
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserBindQQDto {

    /**
     * qq openid
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 权限ids
     */
    private String roleIds;

    /**
     * 用户id
     */
    private Long userId;
}
