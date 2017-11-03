package com.sac.service.system.Interface;

import com.sac.pojo.system.User;
import com.sac.service.generic.GenericService;

import java.util.List;

/**
 * 每一个业务的接口继承业务通用接口
 * 还可以定义自己的独有接口
 * Created by EAISON on 2017/9/28.
 */
public interface UserService  extends GenericService<User,String>{

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
