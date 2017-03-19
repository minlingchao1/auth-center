package com.lingchaomin.auth.server.core.role.dao;



import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.role.dto.ResourceListDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceSelectDto;
import com.lingchaomin.auth.server.core.role.entity.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 上午9:40
 * @description 资源相关
 */
@Repository
public interface ResourceDao extends IDao<Resource> {


    /**
     * 统计
     * @param searchValue
     * @return
     */
    long count(@Param("searchValue") String searchValue);
    /**
     * 更新
     * @param resource
     * @return
     */
    long update(Resource resource);

    /**
     * 更新状态
     * @param id
     * @param available
     * @return
     */
    long updateStatus(@Param("id") Long id, @Param("available") Boolean available);

    /**
     * 根据角色查找
     * @param role
     * @return
     */
    String selectByRoleId(@Param("role") String role);


    /**
     * 根据appId查找
     * @param appId
     * @return
     */
    long deleteByAppId(Long appId);

    /**
     * 查找权限
     * @param reseources
     * @return
     */
    List<String> selectPermissionsByResourceIds(@Param("resourceIds") List<String> reseources);

    List<ResourceListDto> selectAll();

    List<ResourceSelectDto> selectByAppId(Long appId);
}
