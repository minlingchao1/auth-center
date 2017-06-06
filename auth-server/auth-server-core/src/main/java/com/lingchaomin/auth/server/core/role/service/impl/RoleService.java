package com.lingchaomin.auth.server.core.role.service.impl;

import com.lingchaomin.auth.server.common.dto.OperErrorCode;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.app.dao.AppDao;
import com.lingchaomin.auth.server.core.app.entity.App;
import com.lingchaomin.auth.server.core.role.constant.RoleStatus;
import com.lingchaomin.auth.server.core.role.dao.RoleDao;
import com.lingchaomin.auth.server.core.role.dto.RoleListDto;
import com.lingchaomin.auth.server.core.role.dto.RoleSelectDto;
import com.lingchaomin.auth.server.core.role.dto.RoleTreeDto;
import com.lingchaomin.auth.server.core.role.dto.TreeNodeDto;
import com.lingchaomin.auth.server.core.role.entity.Role;
import com.lingchaomin.auth.server.core.role.service.IRoleService;

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

    @Autowired
    private AppDao appDao;

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
                .available(RoleStatus.AVAILABLE)
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
    public OperateResultDto modify(@NonNull Long id,@NonNull Long appId, String role,String descr,String resourceIds,String resourceIdsTree) {
        Role r=Role.builder()
                .id(id)
                .appId(appId)
                .roleName(role)
                .descr(descr)
                .resourceIds(resourceIds)
                .resourceIdsTree(resourceIdsTree)
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

    /**
     * 获取角色树
     */
    public List<RoleTreeDto> getRoleTreeDto() {

        List<App> apps=appDao.selectAll(null);

        List<RoleTreeDto> roleTreeDtos=new ArrayList<RoleTreeDto>();

        for(App app:apps){

            List<Role> roles=roleDao.selectByAppId(app.getId());

            List<TreeNodeDto> treeNodeDtos=new ArrayList<TreeNodeDto>();

            for(Role role:roles){
                TreeNodeDto treeNodeDto=TreeNodeDto.builder()
                        .text(role.getRoleName())
                        .id(role.getId())
                        .icon("none")
                        .build();
                treeNodeDtos.add(treeNodeDto);
            }


            RoleTreeDto roleTreeDto=RoleTreeDto.builder()
                    .appId(app.getId())
                    .appName(app.getName())
                    .treeNodeDtos(treeNodeDtos)
                    .build();
            roleTreeDtos.add(roleTreeDto);
        }
        return roleTreeDtos;
    }

    /**
     * 获取resource tree 字符串
     */
    public String getResourceTreeStr(Long id) {
        String resourceTrees=roleDao.selectResourceTrees(id);
        return resourceTrees;
    }
}
