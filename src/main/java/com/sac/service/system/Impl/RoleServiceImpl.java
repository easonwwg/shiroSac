package com.sac.service.system.Impl;

import com.sac.dao.generic.GenericDao;
import com.sac.dao.system.RoleDao;
import com.sac.pojo.system.Role;
import com.sac.service.generic.GenericServiceImpl;
import com.sac.service.system.Interface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, String> implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public GenericDao<Role, String> GetDao() {
        return roleDao;
    }

    @Override
    public List<String> GetResourcesByRoleId(String roleName) {
        return roleDao.GetResourcesByRoleId(roleName);
    }
}
