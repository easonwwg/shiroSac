package com.sac.dao.system;

import com.sac.dao.generic.GenericDao;
import com.sac.pojo.system.Menu;

import java.util.List;

/**
 * Created by EAISON on 2017/10/10.
 */
public interface MenuDao extends GenericDao<Menu, String> {

    List<Menu> GetMenuList();
}
