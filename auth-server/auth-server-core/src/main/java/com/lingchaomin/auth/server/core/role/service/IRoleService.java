package com.lingchaomin.auth.server.core.role.service;

import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.core.role.dto.RoleListDto;
import com.lingchaomin.auth.server.core.role.dto.RoleSelectDto;
import com.lingchaomin.auth.server.core.role.dto.RoleTreeDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午10:54
 * @description 角色信息
 */
public interface IRoleService {


    /**
     * 统计数量
     * @return
     */
    long count(String searchValue);

    /**
     * 添加
     * @param role
     * @return
     */
    OperateResultDto add(Long appId, String role, String descr, String resourceIds);

    /**
     * 修改
     * @param role
     * @return
     */
    OperateResultDto modify(Long id, Long appId, String role, String descr, String resourceIds, String resourceIdsTree);

    /**
     * 删除
     * @param id
     * @return
     */
    OperateResultDto remove(Long id);


    /**
     * 根据appId查找
     * @param id
     * @return
     */
    long deleteByAppId(Long id);

    /**
     * 根据roleIds查找
     * @param rolesIds
     * @return
     */
    List<String> findByRoleIds(String rolesIds);

    /**
     * 查找资源
     * @param rolesIds
     * @return
     */
    List<String> findResourceByRoleIds(String rolesIds);

    /**
     * 更新可用
     * @param id
     * @return
     */
    OperateResultDto modifyAvailable(Long id);

    /**
     * 更新不可用
     * @param id
     * @return
     */
    OperateResultDto modifyUnAvailable(Long id);

    /**
     * 查找全部
     * @return
     */
    List<RoleListDto> findAll();


    List<RoleSelectDto> select4Auth(Long appId);

    /**
     * 获取角色树
     * @return
     */
    List<RoleTreeDto> getRoleTreeDto();

    /**
     * 获取resource tree 字符串
     * @param id
     * @return
     */
    String getResourceTreeStr(Long id);
}
