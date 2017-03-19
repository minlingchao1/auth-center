package com.lingchaomin.auth.server.web.base.shiro.session;

import com.lingchaomin.auth.server.web.base.shiro.utils.SerializableUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.crazycake.shiro.RedisManager;

import java.io.Serializable;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/21 下午4:23
 * @description session重写
 */
public class AuthServerSessionDao extends CachingSessionDAO {

    private RedisManager redisManager;

    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }

        redisManager.set(session.getId().toString().getBytes(), SerializableUtils.serialize(session).getBytes());
    }

    @Override
    protected void doDelete(Session session) {
        redisManager.del(session.getId().toString().getBytes());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisManager.set(session.getId().toString().getBytes(), SerializableUtils.serialize(session).getBytes());
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        byte[] sessionByte = redisManager.get(sessionId.toString().getBytes());

        if(sessionByte==null){
            return  null;
        }
        return SerializableUtils.deserialize(new String(sessionByte));
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }
}
