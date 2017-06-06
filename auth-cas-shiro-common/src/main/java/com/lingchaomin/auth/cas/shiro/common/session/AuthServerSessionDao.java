package com.lingchaomin.auth.cas.shiro.common.session;


import com.lingchaomin.auth.cas.shiro.common.utils.SerializableUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.crazycake.shiro.RedisManager;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/21 下午4:23
 * @description session重写
 */
public class AuthServerSessionDao extends EnterpriseCacheSessionDAO {

    private RedisManager redisManager;

    /**
     * The Redis key prefix for the sessions
     */
    private String keyPrefix = "shiro_session:";

    @Override
    protected void doUpdate(Session session) {
//        super.doUpdate(session);

        redisManager.set(getByteKey(session.getId()), SerializableUtils.serialize(session).getBytes());
    }

    @Override
    protected void doDelete(Session session) {
       // super.doDelete(session);
        redisManager.del(getByteKey(session.getId()));
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisManager.set(getByteKey(session.getId()), SerializableUtils.serialize(session).getBytes());
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {

        // 先从缓存中获取session，如果没有再去数据库中获取
//        try{
//            Session session = super.doReadSession(sessionId);
//            if(session == null){
//                byte[] sessionByte = redisManager.get(getByteKey(sessionId));
//
//                if(sessionByte==null){
//                    return  null;
//                }
//                return SerializableUtils.deserialize(new String(sessionByte));
//            }
//            return session;
//        }catch (Exception e){
//           return null;
//        }

        byte[] sessionByte = redisManager.get(getByteKey(sessionId));

        if(sessionByte==null){
            return  null;
        }
        return SerializableUtils.deserialize(new String(sessionByte));

    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();

        Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
        if(keys != null && keys.size()>0){
            for(byte[] key:keys){
                Session s = (Session)SerializableUtils.deserialize(new String(redisManager.get(key)));
                sessions.add(s);
            }
        }

        return sessions;
    }

    /**
     * 获得byte[]型的key
     * @param sessionId
     * @return
     */
    private byte[] getByteKey(Serializable sessionId){
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }
}
