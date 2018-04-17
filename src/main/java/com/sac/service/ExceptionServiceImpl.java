package com.sac.service;

import com.sac.dao.system.UserDao;
import com.sac.exception.BusinessException;
import com.sac.pojo.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Date;
import java.sql.SQLException;

/**
 * @author:eason
 * @Description
 * @Date: 20:22,2018/4/17
 * @ModifiedBy
 */
@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    private UserDao userDao;

    @Override
    public void test() {
        int a = 0;
        throw new BusinessException("测试错误");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test1() {
        try {
            userDao.deleteUser("wa1");
            User user = new User();
            user.setId(1212121L);
            user.setCreateTime(new java.util.Date());
            user.setLastLoginTime(new java.util.Date());
            user.setEmail("xxx");
            user.setPswd("xsxsxs");
            user.setNickName("wa");
            user.setStatus(Integer.parseInt("1xx"));
            userDao.insertModel(user);
        } catch (Exception ex) {
            throw ex;
        }

    }
}
