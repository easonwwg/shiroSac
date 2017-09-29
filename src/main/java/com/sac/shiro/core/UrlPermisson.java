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

    public UrlPermisson(String url) {
        this.url = url;
    }
    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof UrlPermisson)) {
            return false;
        }
        UrlPermisson urlPermisson = (UrlPermisson) p;
        PatternMatcher patternMatcher
                = new AntPathMatcher();
        logger.debug("--------此次用户的权限资源是"+this.url+ "---用户访问的url是"+urlPermisson.url);
        boolean macth = patternMatcher.matches(this.url, urlPermisson.url);
        logger.debug("--------用户的此权限结果是--------" + macth);
        return macth;
    }
}
