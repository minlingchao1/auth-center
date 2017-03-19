package com.lingchaomin.auth.client;

import com.yunbeitech.auth.core.remote.IRemoteService;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午10:34
 * @description
 */
public class AuthClientSessionDao extends CachingSessionDAO {

    private static final Logger LOG= LoggerFactory.getLogger(AuthClientSessionDao.class);
    /**
     *
     */
    private IRemoteService remoteService;
    private String appKey;

    public void setRemoteService(IRemoteService remoteService) {
        this.remoteService = remoteService;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    @Override
    protected void doDelete(Session session) {
        remoteService.deleteSession(appKey, session);
    }

    @Override
    protected void doUpdate(Session session) {
        remoteService.updateSession(appKey, session);
    }


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = remoteService.createSession(session);
        LOG.debug("remoteService,sessionId:{}",sessionId);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        LOG.debug("remoteService sessionId:{}",sessionId);
        return remoteService.getSession(appKey, sessionId);
    }
}
