package com.sac.shiro.core;

import com.sac.pojo.system.User;
import com.sac.service.system.Interface.RoleService;
import com.sac.service.system.Interface.UserService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
        System.out.println("角色是------------------------" + roles);
        System.out.println("權限是------------------------" + resources);
        return info;
    }

}
