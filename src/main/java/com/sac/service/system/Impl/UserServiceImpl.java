package com.sac.service.system.Impl;

import com.sac.dao.generic.GenericDao;
import com.sac.dao.system.UserDao;
import com.sac.pojo.system.User;
import com.sac.service.generic.GenericServiceImpl;
import com.sac.service.system.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public GenericDao<User, String> GetDao() {
        return userDao;
    }

    @Override
    public List<String> listRoleByUserId(Integer uid) {
        return userDao.listRoleByUserId(uid);
    }

    @Override
    public User login(String name) {
        return userDao.login(name);
    }
}

