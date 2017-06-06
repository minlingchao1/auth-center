package com.lingchaomin.auth.server.core.user.entity;

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
 * @date 2017/2/20 上午9:37
 * @description qq 用户相关
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QQUserRef  implements Serializable {

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
