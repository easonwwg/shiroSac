package com.sac.dao.system;
import com.sac.dao.generic.GenericDao;
import com.sac.pojo.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每一个dao继承父接口的方法，另外可以封装自己的独有业务的方法
 * Created by EAISON on 2017/9/28.
 */
public interface UserDao extends GenericDao<User, String> {

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
    User login(@Param("name") String name);

    int deleteUser(@Param("name") String name);

}

