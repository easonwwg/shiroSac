package com.sac.dao.system;

import com.sac.pojo.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
public interface UserDao {

    int add(User record);

   /* int update(User record);

    int delete(Integer id);

    Integer batchDelete(@Param("ids") List<Integer> ids);*/

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
    User login(@Param("name")String name);

}
