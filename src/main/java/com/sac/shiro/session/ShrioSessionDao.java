package com.sac.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * 自定义session的会话管理
 * Created by EAISON on 2017/10/17.
 */
public class ShrioSessionDao  extends CachingSessionDAO{


    @Override
    protected void doUpdate(Session session) {
        //super.doUpdate(session);
            System.out.println("doUpdate");
    }

    @Override
    protected void doDelete(Session session) {
        super.delete(session);
        System.out.println("doDelete");

    }

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("doCreate");
        System.out.println(getSessionIdGenerator().generateId(session));
        return getSessionIdGenerator().generateId(session);
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("doReadSession");
        System.out.println(super.readSession(sessionId));
        return super.readSession(sessionId);
    }
}
