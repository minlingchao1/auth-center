package com.lingchaomin.auth.server.core.role.service.impl;

import com.lingchaomin.auth.server.common.dto.OperErrorCode;
import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.common.handler.ReqResultFormatter;
import com.lingchaomin.auth.server.core.role.dao.AuthorizationDao;
import com.lingchaomin.auth.server.core.role.dao.ResourceDao;
import com.lingchaomin.auth.server.core.role.dao.RoleDao;
import com.lingchaomin.auth.server.core.role.entity.Authorization;
import com.lingchaomin.auth.server.core.role.entity.Resource;
import com.lingchaomin.auth.server.core.role.entity.Role;
import com.lingchaomin.auth.server.core.role.service.IAuthorizationService;
import com.lingchaomin.auth.server.core.user.dto.AuthorizationListDto;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:53
 * @description 授权相关
 */
@Service
public class AuthorizationService implements IAuthorizationService {

    private static final Logger LOG= LoggerFactory.getLogger(AuthorizationService.class);

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 查找角色
     */
    public Set<String> findRoles(String appKey, Long  userId) {

        Set<String> rolesSet=new HashSet<String>();

        String roles=authorizationDao.findRoles(appKey,userId);

        if(StringUtils.isBlank(roles)){
            return rolesSet;
        }

        String[] rolesArr=roles.split(",");

        for(String r:rolesArr){
            rolesSet.add(r);
        }

        return rolesSet;
    }

    /**
     * 查找权限
     */
    public Set<String> findPermissions(Set<String> roles) {

        Set<String> resourceIdSet=new HashSet<String>();

        Set<String> permissionSet=new HashSet<String>();

        if(CollectionUtils.isEmpty(roles)){
            return permissionSet;
        }
        for(String role:roles){
            Role r =roleDao.selectById(Long.valueOf(role));

            String resourcesIds=r.getResourceIds();

            if(StringUtils.isBlank(resourcesIds)){
                continue;
            }

            List<String> resourceIdList= Arrays.asList(resourcesIds.split(","));
            resourceIdSet.addAll(resourceIdList);

        }

        if(CollectionUtils.isEmpty(resourceIdSet)){
            return permissionSet;
        }

        for(String resource:resourceIdSet){
            Resource r=resourceDao.selectById(Long.valueOf(resource));

            if(r==null){
                continue;
            }
            String permissions=r.getPermission();

            if(StringUtils.isBlank(permissions)){
                continue;
            }
            List<String> permissionList=Arrays.asList(permissions.split(","));
            permissionSet.addAll(permissionList);

        }

        return permissionSet;
    }

    /**
     * 根据userId和appId查找
     */
    public Authorization findByUserIdAndAppId(Long userId, Long appId) {
        return authorizationDao.findByUserIdAndAppId(userId,appId);
    }

    /**
     * 添加
     */
    public OperateResultDto add(Long appId, String userId, String roleIds) {
        Authorization authorization=Authorization.builder()
                .gmtCreate(new Date())
                .gmtModified(new Date())
                .appId(appId)
                .userId(Long.valueOf(userId))
                .roleIds(roleIds)
                .build();

        long ret= authorizationDao.insert(authorization);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 修改
     */
    public OperateResultDto modify(Long id,Long appId,String roleIds) {

        Authorization authorization=Authorization.builder()
                .id(id)
                .appId(appId)
                .roleIds(roleIds)
                .build();

        long ret= authorizationDao.update(authorization);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 统计数量
     */
    public OperateResultDto count(String searchValue) {
        long ret= authorizationDao.count(searchValue);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 删除
     */
    public OperateResultDto remove(Long id) {
        long ret= authorizationDao.deleteById(id);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 根据要删除的角色更新授权信息
     */
    public OperateResultDto updateAuthByRole(Long id) {

        List<Authorization> authorizations=authorizationDao.selectByRoleId(id);

        for(Authorization authorization:authorizations){

            List<String> roleIds=Arrays.asList(authorization.getRoleIds().split(","));
            roleIds.remove(String.valueOf(id));
            authorization.setRoleIds(StringUtils.join(roleIds,","));
        }

       long ret= authorizationDao.updateBatch(authorizations);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 根据userID删除
     */
    public OperateResultDto deleteByUserId(Long userId) {
       long ret= authorizationDao.deleteByUserId(userId);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 根据应用删除
     */
    public OperateResultDto deleteByAppId(Long appId) {
        long ret= authorizationDao.deleteByAppId(appId);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }

    /**
     * 查找全部
     */
    public List<AuthorizationListDto> findAll() {

        List<AuthorizationListDto> authorizationListDtos=authorizationDao.selectAll();

        for(AuthorizationListDto authorizationListDto:authorizationListDtos){
            String roleIds=authorizationListDto.getRoleIds();
            List<String> roleIdsList=Arrays.asList(roleIds.split(","));
            Set<String> roleIdsSet=new HashSet<String>();
            roleIdsSet.addAll(roleIdsList);

            List<String> roleNames=roleDao.findRoles(roleIdsSet);

            LOG.warn("~~~~~~~~~~~~~~~~~~roleNames:{}",roleNames);
            authorizationListDto.setRoleNames(roleNames);
        }

        return authorizationListDtos;
    }
}
