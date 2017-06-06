package com.lingchaomin.auth.server.web.remote;


import com.google.common.collect.Lists;

import com.lingchaomin.auth.core.dto.Node;
import com.lingchaomin.auth.core.dto.QQUserDto;
import com.lingchaomin.auth.core.dto.UserDto;
import com.lingchaomin.auth.core.remote.IRemoteService;
import com.lingchaomin.auth.core.remote.PermissionContext;
import com.lingchaomin.auth.server.common.utils.MobileUtil;
import com.lingchaomin.auth.server.core.user.entity.User;
import com.lingchaomin.auth.server.core.user.service.IUserService;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:50
 * @description 远程调用
 */
@Service
public class RemoteService implements IRemoteService {

    private static final Logger LOG= LoggerFactory.getLogger(RemoteService.class);

    @Autowired
    private IUserService userService;


    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 获取用户信息
     * @param key
     * @return
     */
    public UserDto getUserInfo(String key){
       UserDto userDto=null;
        if(StringUtils.isNotBlank(key)){
          //  LOG.warn("userName:{}",key);
            userDto= userService.findByPrincipal(key);
        }else {
            LOG.warn("userName is null~~~~~~~");
        }

        return userDto ;
    }

    /**
     * 获取菜单列表
     */
    public List<Node> getMenuList(String appKey, String userName) {

        List<Node> nodes= Lists.newArrayList();

        nodes=userService.getMenuList(appKey,userName);

        return nodes;
    }

    /**
     * 添加用户
     */
    @Override
    public void addUser(String client, String openId, String nick, String figureUrl) {
        userService.addUser(client, openId, nick, figureUrl);
    }

    /**
     * 检测用户是否合法
     */
    @Override
    public void checkUserIsLegal(String s, String s1) {
        userService.checkUserIsLegal(s,s1);
    }

    /**
     * 获取客服部门人员
     */
    @Override
    public List<QQUserDto> getCustomerService(String appKey) {
        return userService.getCustomerService(appKey);
    }

    /**
     * 获取权限
     * @param appKey
     * @param username
     * @return
     */
    public PermissionContext getPermissions(String appKey, String username) {
      //  LOG.warn("appKey:{},userName:{}",appKey,username);
        PermissionContext permissionContext = new PermissionContext();

        boolean isMobile= MobileUtil.checkMobile(username);

        User user=userService.getUser(appKey,username,isMobile);

        List<String> roles=userService.getRole4QQLogin(appKey,user.getUserId());

        if(CollectionUtils.isNotEmpty(roles)){
            permissionContext.setRoles(new HashSet<String>(roles));
        }

        List<String> permissions=userService.getPermission4QQLogin(appKey,user.getUserId());

        if(CollectionUtils.isNotEmpty(permissions)){
            permissionContext.setPermissions(new HashSet<String>(permissions));
        }

        return permissionContext;
    }

}
