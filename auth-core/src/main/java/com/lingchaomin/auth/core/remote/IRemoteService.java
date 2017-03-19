package com.lingchaomin.auth.core.remote;

import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:29
 * @description
 */
public interface IRemoteService {
    PermissionContext getPermissions(String appKey, String username);

    void deleteSession(String appKey, Session session);

    void updateSession(String appKey, Session session);

    Serializable createSession(Session session);

    Session getSession(String appKey, Serializable sessionId);
}
