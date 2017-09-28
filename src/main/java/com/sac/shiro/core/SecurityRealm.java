package com.sac.shiro.core;

import com.sac.pojo.system.User;
import com.sac.service.system.Interface.RoleService;
import com.sac.service.system.Interface.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by EAISON on 2017/9/28.
 */
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String passwd = new String((char[]) token.getCredentials());
        User user= userService.login(username, passwd);
        if (user==null)
            throw  new AuthenticationException("用户名或密码错误.");
        SimpleAuthenticationInfo  authenticationInfo=
                new SimpleAuthenticationInfo(username, passwd, getName());
        return authenticationInfo;
    }
}
