package com.sac.rest.system;

import com.sac.service.system.Interface.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 99079 on 2017/10/10.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(method = RequestMethod.GET,value = "/getMenus")
    @ResponseBody
    public String GetMenuLists(){
        return  menuService.GetMenuList();
    }

}
