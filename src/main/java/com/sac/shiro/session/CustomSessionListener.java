package com.sac.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**自定义session监听
 * Created by EAISON on 2017/10/18.
 */
public class CustomSessionListener implements SessionListener {

   private  ShrioSessionDao shrioSessionDao=new ShrioSessionDao();

    @Override
    public void onStart(Session session) {
        System.out.println("on start");
    }

    @Override
    public void onStop(Session session) {
        System.out.println("on stop");
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("on 删除session");
        shrioSessionDao.delete(session);
    }
}
