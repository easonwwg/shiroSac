package com.sac.shiro.core;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by EAISON on 2017/9/29.
 */
public class UrlPermisson implements Permission {

    private static final Logger logger = LoggerFactory.getLogger(UrlPermisson.class);

    private String url;

    /**
     * url指的是用户的权限
     * @param url
     */
    public UrlPermisson(String url) {
        this.url = url;
    }

    /**
     * 根据p可以获取用户当前访问的资源
     * @param p
     * @return
     */
    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof UrlPermisson)) {
            return false;
        }
        UrlPermisson urlPermisson = (UrlPermisson) p;
        PatternMatcher patternMatcher
                = new AntPathMatcher();
        logger.error("--------此次用户的权限资源是"+this.url+ "---用户访问的url是"+urlPermisson.url);
        boolean macth = patternMatcher.matches(this.url, urlPermisson.url);
        logger.error("--------用户的此权限结果是--------" + macth);
        return macth;
    }
}
