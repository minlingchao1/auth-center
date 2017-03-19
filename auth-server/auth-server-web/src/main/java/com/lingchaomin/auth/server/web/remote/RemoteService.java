package com.lingchaomin.auth.server.web.remote;


import com.lingchaomin.auth.core.remote.IRemoteService;
import com.lingchaomin.auth.core.remote.PermissionContext;
import com.lingchaomin.auth.server.core.user.service.IUserService;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:50
 * @description
 */
@Service
public class RemoteService implements IRemoteService {

    private static final Logger LOG= LoggerFactory.getLogger(RemoteService.class);

    @Autowired
    private IUserService userService;


    @Autowired
    private SessionDAO sessionDAO;

    public Session getSession(String appKey, Serializable sessionId) {

        if(sessionDAO==null){
            LOG.debug("sessionDao is null~~~~~");
        }

        Session session=sessionDAO.readSession(sessionId);
        if(session==null){
            return null;
        }
        return  session;
    }

    public Serializable createSession(Session session) {
        return sessionDAO.create(session);
    }


    public void updateSession(String appKey, Session session) {
        sessionDAO.update(session);
    }


    public void deleteSession(String appKey, Session session) {
        sessionDAO.delete(session);
    }

    public PermissionContext getPermissions(String appKey, String username) {
//        LOG.debug("appKey:{},userName:{}",appKey,username);
//        PermissionContext permissionContext = new PermissionContext();
//        List<String> roles=userService.getRole4QQLogin(appKey,username);
//
//        if(CollectionUtils.isNotEmpty(roles)){
//            permissionContext.setRoles(new HashSet<String>(roles));
//        }
//
//        List<String> permissions=userService.getPermission4QQLogin(appKey,username);
//
//        if(CollectionUtils.isNotEmpty(permissions)){
//            permissionContext.setPermissions(new HashSet<String>(permissions));
//        }

        return null;

        //return permissionContext;
    }
}
