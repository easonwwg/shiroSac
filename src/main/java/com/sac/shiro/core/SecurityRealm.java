package com.sac.shiro.core;

import com.sac.pojo.system.User;
import com.sac.service.system.Interface.RoleService;
import com.sac.service.system.Interface.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by EAISON on 2017/9/28.
 */
public class SecurityRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        logger.debug("进入到授权方法");
        //用户的角色
        List<String> roles =
                userService.listRoleByUserId(new Long(user.getId()).intValue());
        //用户的权限
        //现在做的是一个用户只有一个权限
        String roleName = roles.get(0);
        List<String> resources
                = roleService.GetResourcesByRoleId(roleName);
        //设置权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles.stream().collect(Collectors.toSet()));
        info.setStringPermissions(resources.stream().collect(Collectors.toSet()));
       System.out.print("sql查询");
        System.out.println("角色是------------------------"+roles);
        System.out.println("權限是------------------------"+resources);
        return info;
    }

    /**
     * 登陆认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        User user = userService.login(username);//这里的user前台shiro标签可以访问
        if (user == null)
            throw new AuthenticationException("用户不存在");
        if (user.getStatus() == 0) {
            throw new DisabledAccountException("用户禁止被登陆");
        }
        //這裏的密码是加盐的 盐值是用户的创建时间
        String salt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(user.getCreateTime());
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(user, user.getPswd(),
                        ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
    }

    /**
     * 清除授权
     * @param principals
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除认证
     * @param principals
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

}
