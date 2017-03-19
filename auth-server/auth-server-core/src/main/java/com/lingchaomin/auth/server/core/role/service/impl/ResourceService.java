package com.lingchaomin.auth.server.core.role.service.impl;

import com.lingchaomin.auth.server.core.role.constant.ResourceStatus;
import com.lingchaomin.auth.server.core.role.dao.ResourceDao;
import com.lingchaomin.auth.server.core.role.dto.ResourceListDto;
import com.lingchaomin.auth.server.core.role.dto.ResourceSelectDto;
import com.lingchaomin.auth.server.core.role.entity.Resource;
import com.lingchaomin.auth.server.core.role.service.IResourceService;
import com.yunbeitech.auth.common.dto.OperErrorCode;
import com.yunbeitech.auth.common.dto.OperateResultDto;
import com.yunbeitech.auth.common.handler.ReqResultFormatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.NonNull;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午10:40
 * @description 资源信息
 */
@Service
public class ResourceService implements IResourceService {

    private static final Logger LOG= LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private ResourceDao resourceDao;


    /**
     * 统计数量
     */
    public OperateResultDto count(String searchValue) {
        long ret= resourceDao.count(searchValue);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 添加
     */
    public OperateResultDto add(@NonNull String name, @NonNull Integer type, Integer priority,@NonNull Long appId, Long parentId, String permission, String url) {

        if(StringUtils.isBlank(name)||type==null){
            return ReqResultFormatter.formatFailDto(OperErrorCode.LACK_PARAMS);
        }

        if(parentId==null){
            parentId=0l;
        }

        StringBuilder parentIds=new StringBuilder();

        if(parentId!=0){
            Resource parentResource=resourceDao.selectById(parentId);
            parentIds.append(parentResource.getParentIds()).append("/").append(parentId);
        }else {
            parentIds.append(parentId);
        }

        Resource resource=Resource.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .name(name)
                .type(type)
                .priority(priority)
                .appId(appId)
                .parentId(parentId)
                .parentIds(parentIds.toString())
                .permission(permission)
                .url(url)
                .build();

        long ret=resourceDao.insert(resource);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }

        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 修改
     */
    public OperateResultDto modify(@NonNull Long id,@NonNull String name,@NonNull Integer type,Integer priority,Long parentId,String permission,String url) {
        Resource resource=Resource.builder()
                .id(id)
                .name(name)
                .type(type)
                .priority(priority)
                .parentId(parentId)
                .permission(permission)
                .url(url)
                .build();
        long ret= resourceDao.update(resource);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 删除
     */
    public OperateResultDto remove(Long id) {
        long ret= resourceDao.deleteById(id);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }


    /**
     * 根据id查找
     */
    public Resource findById(Long id) {
        return resourceDao.selectById(id);
    }


    /**
     * 根据应用删除
     */
    public OperateResultDto deleteByAppId(Long appId) {

        long ret= resourceDao.deleteByAppId(appId);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 根据resourceIds查找
     */
    public List<String> findPermissionsByResourceIds(List<String> resources) {

        String allResources= StringUtils.join(resources,",");

        LOG.warn("allResources:{}",allResources);

        return resourceDao.selectPermissionsByResourceIds(Arrays.asList(allResources.split(",")));
    }

    public OperateResultDto modifyAvailable(@NonNull Long id) {
        long ret= resourceDao.updateStatus(id, ResourceStatus.AVAILABLE);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }

        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    public OperateResultDto modifyUnAvailable(@NonNull Long id) {
        long ret= resourceDao.updateStatus(id, ResourceStatus.UNAVAILABLE);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }

        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 查找全部
     */
    public List<ResourceListDto> findAll() {
        return resourceDao.selectAll();
    }

    public List<ResourceSelectDto> findByAppId(Long appId) {
        return resourceDao.selectByAppId(appId);
    }
}
