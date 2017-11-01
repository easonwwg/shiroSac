package com.sac.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 次过滤器在shiro的realm注册 先调用
 * Created by EAISON on 2017/9/29.
 */
public class ResourceCheckFilter extends AccessControlFilter {

    private String errorUrl;
    private static final Logger logger = LoggerFactory.getLogger(ResourceCheckFilter.class);

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    /**
     * 验证每一个资源的权限
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        if (null==SecurityUtils.getSubject().getSession()){
            System.out.println("——————————————————————此次请求的session为空");
        }

        System.out.println("---------------------------进入到第一个过滤器判断权限开始");
        org.apache.shiro.subject.Subject subject
                = getSubject(servletRequest, servletResponse);
        String url = getPathWithinApplication(servletRequest);
        logger.debug("进入到了访问权限认证--当前用户正在访问的 url => " + url);
        boolean isPermitted=subject.isPermitted(url);//这个方法会不断的将url与用户的权限进行匹配
        System.out.println("-----验证结果是"+isPermitted);
        System.out.println("---------------------------进入到第一个过滤器判断权限结束");
       return isPermitted;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.debug("当 isAccessAllowed 返回 false 的时候，才会执行 method onAccessDenied ");
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + this.errorUrl);
        return false;
    }
}
