package com.lingchaomin.auth.server.core.role.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/30 下午4:06
 * @description
 */
@Data
@Builder
public class RoleTreeDto {

    private Long appId;

    private String appName;

    private List<TreeNodeDto> treeNodeDtos;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<TreeNodeDto> getTreeNodeDtos() {
        return treeNodeDtos;
    }

    public void setTreeNodeDtos(List<TreeNodeDto> treeNodeDtos) {
        this.treeNodeDtos = treeNodeDtos;
    }
}
