package com.sac.shiro.listener;

import com.sac.shiro.cache.SessionCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;

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

    private OnlineService onlineService;

    public AbstractSessionDAO getSessionDAO() {
        return sessionDAO;
    }

    public void setSessionDAO(AbstractSessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    public OnlineService getOnlineService() {
        return onlineService;
    }

    public void setOnlineService(OnlineService onlineService) {
        this.onlineService = onlineService;
    }

    @Override
    public void onStart(Session session) {
        Serializable serializable= session.getId();
       // Serializable serializable1=   SecurityUtils.getSubject().getSession().getId();
        System.out.println("会话开始"+serializable);
       // System.out.println("会话开始"+serializable1);
    }

    @Override
    public void onStop(Session session) {
        //z这里要删除用户存放在redis里面的权限资源
        //这里的两个sessionid是一样的 可以借此做一些其它的操作
        Serializable serializable= session.getId();
        Serializable serializable1=   SecurityUtils.getSubject().getSession().getId();
        // Object obj=  SecurityUtils.getSubject().getPrincipal();这个会死循环
        SessionCache sessionCache=new SessionCache();
        PrincipalCollection principalCollection=sessionCache.getMyMap(serializable);
        sessionCache.GetSessionCache().remove(serializable);
        System.out.println("会话结束"+serializable);
        System.out.println("会话结束"+principalCollection);
    }

    @Override
    public void onExpiration(Session session) {
        Serializable serializable= session.getId();
        System.out.println("会话截至"+serializable);
    }
}
