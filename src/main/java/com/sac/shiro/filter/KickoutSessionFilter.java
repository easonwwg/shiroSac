package com.sac.shiro.filter;

import com.sac.shiro.cache.SessionCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by 99079 on 2017/10/25.
 */
public class KickoutSessionFilter extends AccessControlFilter {

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * iframe的权限跳转控制代码
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("进入到第二个过滤器判断权限");
        SessionCache sessionCache = new SessionCache();
      /*如果是div则需要一下代码  如果是iframe则注释
      if (SecurityUtils.getSubject().getSession().getAttribute("kickout")!=null){
            Map<String, String> resultMap = new HashMap<String, String>();
            if (ShiroFilterUtils.isAjax(servletRequest) ) {
                resultMap.put("errorMsg", "您已经在其他地方登录，请重新登录！");
                ShiroFilterUtils.out(servletResponse,resultMap);
            }
            return  false;
        }*/
        //当前用户的session
        Serializable serializableId = SecurityUtils.getSubject().getSession().getId();
        //获取当前用户的权限
        String principalCollection = sessionCache.getMyMap(serializableId).toString();
        //遍历map
        Map<Serializable, PrincipalCollection> tempSession = sessionCache.GetSessionCache();
        for (Serializable serializable : sessionCache.GetSessionCache().keySet()) {
            String pricpalStr = sessionCache.getMyMap(serializable).toString();
            if (!serializableId.equals(serializable) && pricpalStr.equals(principalCollection)) {
                //删除此session
                Session deleteSession = sessionDAO.readSession(serializable);
                //如果是div则需要一下代码  如果是iframe则注释
                // deleteSession.setAttribute("kickout",true);
                //如果是iframe则需要一下两行代码  如果是div则注释
                //把之前登陆用户的session删除
                sessionDAO.delete(deleteSession);
                //删除map的key 防止再次找到删除session找不到
                sessionCache.GetSessionCache().remove(serializable);
            }
        }

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("第二个过滤器权限验证失败");
        Subject subject = getSubject(servletRequest, servletResponse);
        subject.logout();
        return false;
    }


   /* div的权限跳转代码
    protected boolean isAccessAllowed1(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("进入到第二个过滤器判断权限");
        SessionCache sessionCache = new SessionCache();
      if (SecurityUtils.getSubject().getSession().getAttribute("kickout")!=null){
            Map<String, String> resultMap = new HashMap<String, String>();
            if (ShiroFilterUtils.isAjax(servletRequest) ) {
                resultMap.put("errorMsg", "您已经在其他地方登录，请重新登录！");
                ShiroFilterUtils.out(servletResponse,resultMap);
            }
            return  false;
        }
        //当前用户的session
        Serializable serializableId = SecurityUtils.getSubject().getSession().getId();
        //获取当前用户的权限
        String principalCollection = sessionCache.getMyMap(serializableId).toString();
        //遍历map
        Map<Serializable,PrincipalCollection> tempSession= sessionCache.GetSessionCache();
        for (Serializable serializable : sessionCache.GetSessionCache().keySet()) {
            String pricpalStr = sessionCache.getMyMap(serializable).toString();
            if (!serializableId.equals(serializable) && pricpalStr.equals(principalCollection)) {
                //删除此session
                Session deleteSession= sessionDAO.readSession(serializable);
                //如果是div则需要一下代码  如果是iframe则注释
                deleteSession.setAttribute("kickout",true);
                //如果是iframe则需要一下两行代码  如果是div则注释
*//*                sessionDAO.delete(deleteSession);
                //删除map的key 防止再次找到删除session找不到
                sessionCache.GetSessionCache().remove(serializable);*//*
            }
        }

        return true;
    }
*/

}
