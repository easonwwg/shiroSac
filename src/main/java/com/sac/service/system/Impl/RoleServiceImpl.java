package com.sac.service.system.Impl;

import com.sac.dao.system.RoleDao;
import com.sac.service.system.Interface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<String> GetResourcesByRoleId(int roleId) {
        return roleDao.GetResourcesByRoleId(roleId);
    }
}
