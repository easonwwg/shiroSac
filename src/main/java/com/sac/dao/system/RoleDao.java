package com.sac.dao.system;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
public interface RoleDao {

    /**
     * 角色對應的資源權限
     * @param roleName
     * @return
     */
    List<String> GetResourcesByRoleId(String roleName);
}
