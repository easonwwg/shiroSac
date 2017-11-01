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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 99079 on 2017/10/25.
 */
public class KickoutSessionFilter extends AccessControlFilter {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
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
                deleteSession.setAttribute("kickout",true);
               //sessionDAO.delete(deleteSession);
               //删除map的key 防止再次找到删除session找不到
              // sessionCache.GetSessionCache().remove(serializable);
            }
        }

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println("第二个过滤器权限验证失败");
        Subject subject=getSubject(servletRequest,servletResponse);
        subject.logout();
        return false;
    }
}
