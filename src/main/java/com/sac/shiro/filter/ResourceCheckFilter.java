package com.sac.shiro.filter;

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

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        org.apache.shiro.subject.Subject subject
                = getSubject(servletRequest, servletResponse);
        String url = getPathWithinApplication(servletRequest);
        logger.debug("进入到了访问权限认证--当前用户正在访问的 url => " + url);
        boolean isPermitted=subject.isPermitted(url);
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
