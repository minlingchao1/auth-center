package com.lingchaomin.auth.server.core.base.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/17 下午5:55
 * @description
 */
@Data
@Builder
public class BaseModel {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;
}
