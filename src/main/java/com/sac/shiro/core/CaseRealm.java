package com.sac.shiro.core;

import com.sac.pojo.system.User;
import com.sac.service.system.Interface.RoleService;
import com.sac.service.system.Interface.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author:eason
 * @Description：
 * @Date: 10:49,2017/12/7
 * @ModifiedBy
 */
public class CaseRealm extends CasRealm {

    private static final Logger logger = LoggerFactory.getLogger(CaseRealm.class);

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
        logger.debug("进入到授权方法" + user.getNickName());
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
        System.out.println("角色是------------------------" + roles);
        System.out.println("權限是------------------------" + resources);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        } else {
            String ticket = (String) casToken.getCredentials();
            if (!StringUtils.hasText(ticket)) {
                return null;
            } else {
                TicketValidator ticketValidator = this.ensureTicketValidator();
                try {
                    Assertion e = ticketValidator.validate(ticket, this.getCasService());
                    AttributePrincipal casPrincipal = e.getPrincipal();
                    String userId = casPrincipal.getName();
                    User user = userService.login(userId);
                    if (user == null)
                        throw new AuthenticationException("用户不存在");
                    if (user.getStatus() == 0) {
                        throw new DisabledAccountException("用户禁止被登陆");
                    }
                    Map attributes = casPrincipal.getAttributes();
                    casToken.setUserId(userId);
                    String rememberMeAttributeName = this.getRememberMeAttributeName();
                    String rememberMeStringValue = (String) attributes.get(rememberMeAttributeName);
                    boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
                    if (isRemembered) {
                        casToken.setRememberMe(true);
                    }
                    SimplePrincipalCollection principalCollection = new SimplePrincipalCollection(user, this.getName());
                    return new SimpleAuthenticationInfo(principalCollection, ticket);
                } catch (TicketValidationException var14) {
                    throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", var14);
                }
            }
        }
    }

    /**
     * 清楚认证的缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清楚权限的缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
}

