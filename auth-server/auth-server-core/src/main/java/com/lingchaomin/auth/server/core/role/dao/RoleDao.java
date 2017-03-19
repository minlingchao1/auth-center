package com.lingchaomin.auth.server.core.role.dao;



import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.role.dto.RoleListDto;
import com.lingchaomin.auth.server.core.role.dto.RoleSelectDto;
import com.lingchaomin.auth.server.core.role.entity.Role;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 上午9:39
 * @description 角色相关
 */
@Repository
public interface RoleDao extends IDao<Role> {


    /**
     * 统计
     * @param searchValue
     * @return
     */
    long count(@Param("searchValue") String searchValue);

    /**
     * 更新
     * @param role
     * @return
     */
    long update(Role role);

    /**
     * 更新状态
     * @param id
     * @param available
     * @return
     */
    long updateStatus(@Param("id") Long id, @Param("available") Boolean available);

    /**
     * 查找角色名
     * @param roleIds
     * @return
     */
    List<String>  findRoles(@Param("roleIds") Set<String> roleIds);


    /**
     * 根据appId删除
     * @param appId
     * @return
     */
    long deleteByAppId(Long appId);

    /**
     * 根据roleids查找
     * @param roleIds
     * @return
     */
    List<String> selectByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 查找资源
     * @param roleIdList
     * @return
     */
    List<String> selectResourceByRoleIds(@Param("roleIds") List<String> roleIdList);

    /**
     * 查找全部
     * @return
     */
    List<RoleListDto> selectAll();

    List<RoleSelectDto> select4Auth(Long appId);
}
