package com.sac.service.system.Impl;
import com.sac.dao.system.MenuDao;
import com.sac.pojo.system.Menu;
import com.sac.service.system.Interface.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 99079 on 2017/10/10.
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public Menu GetMenuList() {
        List<Menu> rootMenus= menuDao.GetMenuList();
        //找到父节点
        Menu menu=rootMenus.stream().filter(s->s.getParentCode().equals("0")).findFirst().get();
        //开始递归遍历
        menu.setChildMenus(getChild(menu.getCode(), rootMenus));
        return menu;
    }

    private List<Menu> getChild(String code,List<Menu> rootMenu){
        //子菜单
        List<Menu> childList=new ArrayList<>();
        // 遍历所有节点，将每个节点父菜单code与传过来的code比较
        for (Menu menu:rootMenu) {
            if (menu.getParentCode().equals(code)){
                childList.add(menu);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {
            // 如果子菜单没有url，说明该子菜单下还有子菜单
            if (menu.getMenuUrl()==null) {
                // 递归
                menu.setChildMenus(getChild(menu.getCode(), rootMenu));
            }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
