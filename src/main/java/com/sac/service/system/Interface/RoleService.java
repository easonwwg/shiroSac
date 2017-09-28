package com.sac.service.system.Interface;

import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
public interface RoleService {
    /**
     * 角色對應的資源權限
     * @param roleId
     * @return
     */
    List<String> GetResourcesByRoleId(int roleId);
}
