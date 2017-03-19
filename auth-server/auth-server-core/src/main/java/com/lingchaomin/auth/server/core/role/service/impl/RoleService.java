package com.lingchaomin.auth.server.core.role.service.impl;

import com.lingchaomin.auth.server.core.role.constant.RoleStatus;
import com.lingchaomin.auth.server.core.role.dao.RoleDao;
import com.lingchaomin.auth.server.core.role.dto.RoleListDto;
import com.lingchaomin.auth.server.core.role.dto.RoleSelectDto;
import com.lingchaomin.auth.server.core.role.entity.Role;
import com.lingchaomin.auth.server.core.role.service.IRoleService;
import com.yunbeitech.auth.common.dto.OperErrorCode;
import com.yunbeitech.auth.common.dto.OperateResultDto;
import com.yunbeitech.auth.common.handler.ReqResultFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.NonNull;
import scala.actors.threadpool.Arrays;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午10:54
 * @description 角色信息
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleDao roleDao;


    /**
     * 统计数量
     */
    public long count(String searchValue) {
        return roleDao.count(searchValue);
    }

    /**
     * 添加
     */
    public OperateResultDto add(@NonNull Long appId, @NonNull String role, String descr, String resourceIds) {

        Role r=Role.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .appId(appId)
                .roleName(role)
                .descr(descr)
                .resourceIds(resourceIds)
                .build();

        long ret=roleDao.insert(r);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 修改
     */
    public OperateResultDto modify(@NonNull Long id,@NonNull Long appId,@NonNull String role,String descr,String resourceIds) {
        Role r=Role.builder()
                .appId(appId)
                .roleName(role)
                .descr(descr)
                .resourceIds(resourceIds)
                .build();

        long ret=roleDao.update(r);
        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 删除
     */
    public OperateResultDto remove(@NonNull Long id) {

        long ret= roleDao.deleteById(id);
        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);

    }



    /**
     * 根据appId查找
     */
    public long deleteByAppId(Long appId) {
        return roleDao.deleteByAppId(appId);
    }

    /**
     * 根据roleIds查找
     */
    public List<String> findByRoleIds(String rolesIds) {
        List<String> roleIdList= Arrays.asList(rolesIds.split(","));

        if(org.apache.commons.collections4.CollectionUtils.isEmpty(roleIdList)){
            return new ArrayList<String>();
        }
        return roleDao.selectByRoleIds(roleIdList);
    }

    /**
     * 查找资源
     */
    public List<String> findResourceByRoleIds(String rolesIds) {
        List<String> roleIdList= Arrays.asList(rolesIds.split(","));

        if(org.apache.commons.collections4.CollectionUtils.isEmpty(roleIdList)){
            return new ArrayList<String>();
        }

        return roleDao.selectResourceByRoleIds(roleIdList) ;
    }

    /**
     * 更新可用
     */
    public OperateResultDto modifyAvailable(Long id) {
        long ret=roleDao.updateStatus(id, RoleStatus.AVAILABLE);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 更新不可用
     */
    public OperateResultDto modifyUnAvailable(Long id) {
        long ret=roleDao.updateStatus(id, RoleStatus.UNAVAILABLE);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }

        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 查找全部
     */
    public List<RoleListDto> findAll() {
        List<RoleListDto> roles=roleDao.selectAll();

        return roles;
    }

    public List<RoleSelectDto> select4Auth(Long appId) {
        return  roleDao.select4Auth(appId);
    }
}
