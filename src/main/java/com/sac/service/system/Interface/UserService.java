package com.sac.service.system.Interface;

import com.sac.pojo.system.User;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
public interface UserService {
    int add(User record);

    User getByWhere(String userName);

    List<User> userLists();

    /**
     * 根据用户的id查询所拥有的角色
     * @param uid
     * @return
     */
    List<String> listRoleByUserId(Integer uid);

    /**
     * 登陆验证
     * @param name
     * @return
     */
    User login(String name);
}
