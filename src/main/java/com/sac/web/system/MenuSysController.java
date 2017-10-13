package com.sac.web.system;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by EAISON on 2017/10/13.
 */

@Controller
@RequestMapping("/menus")
public class MenuSysController {
    /**
     * 测试页面1
     *
     * @return
     */
    @RequiresRoles({"SuperAdmin"})
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex() {
        return "Menu/index";
    }
}

