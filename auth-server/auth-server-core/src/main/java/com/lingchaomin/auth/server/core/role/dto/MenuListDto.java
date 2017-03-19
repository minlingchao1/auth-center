package com.lingchaomin.auth.server.core.role.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/16 下午4:26
 * @description
 */
@Data
@Builder
public class MenuListDto {

    private Long id;

    private String name;

    private List<MenuListDto> list;

    private Integer type;

    private String url;

}
