package com.lingchaomin.auth.server.core.role.service;


import com.lingchaomin.auth.server.core.role.entity.Authorization;
import com.lingchaomin.auth.server.core.user.dto.AuthorizationListDto;
import com.yunbeitech.auth.common.dto.OperateResultDto;

import java.util.List;
import java.util.Set;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:53
 * @description
 */
public interface IAuthorizationService {

    /**
     * 查找角色
     * @param appKey
     * @param userId
     * @return
     */
    Set<String> findRoles(String appKey, Long userId);

    /**
     * 查找权限
     * @return
     */
    Set<String> findPermissions(Set<String> roles);

    /**
     * 根据userId和appId查找
     * @param userId
     * @param appId
     * @return
     */
    Authorization findByUserIdAndAppId(Long userId, Long appId);

    /**
     * 添加
     * @param
     * @return
     */
    OperateResultDto add(Long appId,String userId,String roleIds);

    /**
     * 修改
     * @param
     * @return
     */
    OperateResultDto modify(Long id,Long appId,String roleIds);



    /**
     * 统计数量
     * @return
     */
    OperateResultDto count(String searchValue);

    /**
     * 删除
     * @param id
     * @return
     */
    OperateResultDto remove(Long id);

    /**
     * 根据要删除的角色更新授权信息
     * @param id
     * @return
     */
    OperateResultDto updateAuthByRole(Long id);

    /**
     * 根据userID删除
     * @param userId
     */
    OperateResultDto deleteByUserId(Long userId);

    /**
     * 根据应用删除
     * @param id
     */
    OperateResultDto deleteByAppId(Long id);

    /**
     * 查找全部
     * @return
     */
    List<AuthorizationListDto> findAll();
}
