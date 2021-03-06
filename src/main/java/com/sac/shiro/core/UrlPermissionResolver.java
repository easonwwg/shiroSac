package com.sac.shiro.core;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户的每一个权限和用户当前请求的权限都会进入到这个类里面进行匹配
 * 自定义shiro的权限匹配器 只有定义了这个才会匹配权限
 * Created by EAISON on 2017/9/29.
 */
public class UrlPermissionResolver implements PermissionResolver {

    private static final Logger logger = LoggerFactory.getLogger(UrlPermissionResolver.class);

    @Override
    public Permission resolvePermission(String permissionString) {
        logger.error("此次获取的权限资源是 " + permissionString);

        if(permissionString.startsWith("/")){
            //将参数传到UrlPermission中去判断
            return new UrlPermisson(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}

