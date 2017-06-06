package com.lingchaomin.auth.server.core.role.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/30 下午2:21
 * @description
 */
@Data
@Builder
public class ResourceTreeDto {

    /**
     * app名称
     */
    private String appName;

    /**
     * appId
     */
    private Long appId;

    /**
     * treeNodeDto
     */
    private List<TreeNodeDto> treeNodeDtos;

}
