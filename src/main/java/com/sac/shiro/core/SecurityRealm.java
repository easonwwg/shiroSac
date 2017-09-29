package com.sac.shiro.core;

import com.sac.pojo.system.User;
import com.sac.service.system.Interface.RoleService;
import com.sac.service.system.Interface.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

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
        UsernamePasswordToken upToken=(UsernamePasswordToken)token;
        String username = upToken.getUsername();
        User user= userService.login(username);
        if (user==null)
            throw  new AuthenticationException("用户不存在");
        //這裏的密码是加盐的 盐值是用户的创建时间
        String salt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(user.getCreateTime());
        SimpleAuthenticationInfo  authenticationInfo=
                new SimpleAuthenticationInfo(user.getNickName(), user.getPswd(),
                        ByteSource.Util.bytes(salt),getName());
        return authenticationInfo;
    }
}
