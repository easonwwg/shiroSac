package com.sac.service.system.Interface;
import com.sac.pojo.system.Role;
import com.sac.service.generic.GenericService;
import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
public interface RoleService extends GenericService<Role,String> {
    /**
     * 角色對應的資源權限
     * @param roleName
     * @return
     */
    List<String> GetResourcesByRoleId(String roleName);
}
