package com.lingchaomin.auth.server.core.role.dao;


import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.role.entity.Authorization;
import com.lingchaomin.auth.server.core.user.dto.AuthorizationListDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午9:44
 * @description 授权信息
 */
@Repository
public interface AuthorizationDao extends IDao<Authorization> {

    /**
     * 查找角色
     * @param appKey
     * @param userId
     * @return
     */
    String findRoles(@Param("appKey") String appKey, @Param("userId") Long userId);

    /**
     * 查找权限
     * @param appKey
     * @param userId
     * @return
     */
    String findPermissions(@Param("appKey") String appKey, @Param("userId") Long userId);

    /**
     * 根据用户id和appid查找
     * @param userId
     * @param appId
     * @return
     */
    Authorization findByUserIdAndAppId(@Param("userId") Long userId, @Param("appId") Long appId);


    /**
     * 统计
     * @param searchValue
     * @return
     */
    long count(@Param("searchValue") String searchValue);

    long update(Authorization authorization);

    /**
     * 根据角色查找
     * @param id
     * @return
     */
    List<Authorization> selectByRoleId(Long id);

    /**
     * 根据userID删除
     * @param userId
     * @return
     */
    long deleteByUserId(Long userId);

    /**
     * 根据appId删除
     * @param appId
     */
    long deleteByAppId(Long appId);

    /**
     * 查找全部
     * @return
     */
    List<AuthorizationListDto> selectAll();
}
