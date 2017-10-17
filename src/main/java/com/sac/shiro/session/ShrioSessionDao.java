package com.sac.shiro.session;

import com.sac.cache.RedisManager;
import com.sac.commons.SerializerUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义session的会话管理
 * Created by EAISON on 2017/10/17.
 */
public class ShrioSessionDao  extends AbstractSessionDAO{

    @Resource(name = "redisManager")
    private  RedisManager redisManager;

    /**
     * The Redis key prefix for the sessions
     */
    private static final String KEY_PREFIX = "shiro_redis_session:";

    /**
     * 更新session
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("进入到update");
        this.saveSession(session);
    }

    /**
     * 删除session
     * @param session
     */
    @Override
    public void delete(Session session) {
        System.out.println("进入到session");
        if (session == null || session.getId() == null) {
            return;
        }
        redisManager.del(KEY_PREFIX + session.getId());
    }

    /**
     * 获取活动的session
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        System.out.println("进入到getActiveSessions");
        Set<Session> sessions = new HashSet<Session>();
        Set<byte[]> keys = redisManager.keys(KEY_PREFIX + "*");
        if(keys != null && keys.size()>0){
            for(byte[] key : keys){
                Session s = (Session)SerializerUtil.deserialize(redisManager.get(SerializerUtil.deserialize(key)));
                sessions.add(s);
            }
        }
        return sessions;
    }

    /**
     * 创建session
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("进入到doCreate");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    /**
     * 获取session
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("进入到doReadSession");
        if(sessionId == null){
            return null;
        }
        Session s = (Session)redisManager.get(KEY_PREFIX + sessionId);
        return s;
    }

    /**
     * 保存session到redis
     * @param session
     * @throws UnknownSessionException
     */
    private void saveSession(Session session) throws UnknownSessionException{

        if (session == null || session.getId() == null) {
            return;
        }
        //设置过期时间
        long expireTime = 1800000l;
        session.setTimeout(expireTime);
        redisManager.setEx(KEY_PREFIX + session.getId(), session, expireTime);
    }
}
