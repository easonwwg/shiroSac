package com.sac.shiro.listener;

import com.sac.shiro.cache.SessionCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;

/**
 * 实现SessionListener进行回话管理
 * Created by 99079 on 2017/10/23.
 */
public class CustomSessionListener implements SessionListener {

    /**
     * 这里的sessionDao是直接用的shiro提供的
     */
    private AbstractSessionDAO sessionDAO;

    public AbstractSessionDAO getSessionDAO() {
        return sessionDAO;
    }

    public void setSessionDAO(AbstractSessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public void onStart(Session session) {
        Serializable serializable= session.getId();
        System.out.println("会话开始"+serializable);
    }

    @Override
    public void onStop(Session session) {
        Serializable serializable= session.getId();
        SessionCache sessionCache=new SessionCache();
        //会话结束删除对应存放session-principalCollection的字典集合
        sessionCache.GetSessionCache().remove(serializable);
        System.out.println("会话结束"+serializable);
    }

    @Override
    public void onExpiration(Session session) {
        Serializable serializable= session.getId();
        System.out.println("会话截至"+serializable);
    }
}
