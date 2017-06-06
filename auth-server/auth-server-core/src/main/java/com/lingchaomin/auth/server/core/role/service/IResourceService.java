package com.lingchaomin.auth.server.core.role.service;

import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceListDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceSelectDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceTreeDto;
import com.lingchaomin.auth.server.core.role.entity.Resource;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午10:39
 * @description 资源信息
 */
public interface IResourceService {


    /**
     * 统计数量
     * @return
     */
    OperateResultDto count(String searchValue);

    /**
     * 添加
     * @param
     * @return
     */
    OperateResultDto add(String name, Integer type, Integer priority, Long appId, Long parentId, String permission, String url);

    /**
     * 修改
     * @param
     * @return
     */
    OperateResultDto modify(Long id, String name, Integer type, Integer priority, Long parentId, String permission, String url);

    /**
     * 删除
     * @param id
     * @return
     */
    OperateResultDto remove(Long id);


    /**
     * 根据id查找
     * @param id
     * @return
     */
    Resource findById(Long id);


    /**
     * 根据应用删除
     * @param id
     */
    OperateResultDto deleteByAppId(Long id);

    /**
     * 根据resourceIds查找
     * @param resources
     * @return
     */
    List<String> findPermissionsByResourceIds(List<String> resources);

    OperateResultDto modifyAvailable(Long id);

    OperateResultDto modifyUnAvailable(Long id);

    /**
     * 查找全部
     * @return
     */
    List<ResourceListDto> findAll();

    /**
     * 根据appId查找
     * @param appId
     * @return
     */
    List<ResourceSelectDto> findByAppId(Long appId);

    /**
     * 获取jstreedto
     * @return
     */
    List<ResourceTreeDto> getResourceTreeDto();


}
