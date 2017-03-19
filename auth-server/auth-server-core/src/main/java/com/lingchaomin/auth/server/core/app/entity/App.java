package com.lingchaomin.auth.server.core.app.entity;

import com.lingchaomin.auth.server.core.base.entity.BaseModel;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午9:32
 * @description 应用信息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class App {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 名称
     */
    private String name;

    /**
     * appkey
     */
    private String appKey;

    /**
     * appSecret
     */
    private String appSecret;

    /**
     * 是否可用
     */
    private Boolean available;

    /**
     * 描述
     */
    private String descr;

}
