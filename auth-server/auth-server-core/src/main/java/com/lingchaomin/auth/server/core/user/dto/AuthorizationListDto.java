package com.lingchaomin.auth.server.core.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/2 上午12:37
 * @description 返回结果
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationListDto {

    private Long id;

    private Long appId;

    private String userId;

    private String roleIds;

    private String appName;

    private String userNick;

    private List<String> roleNames;
}
