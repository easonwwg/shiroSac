package com.sac.service.system.Interface;

import com.sac.dao.generic.GenericDao;
import com.sac.pojo.system.Menu;

/**
 * Created by 99079 on 2017/10/10.
 */
public interface MenuService extends GenericDao<Menu,String>{

    String GetMenuList();
}
