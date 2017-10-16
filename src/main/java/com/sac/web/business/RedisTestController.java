/*
package com.sac.web.business;

import com.sac.service.business.Interface.SessionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

*/
/**
 * Created by EAISON on 2017/10/16.
 *//*


@Controller
@RequestMapping(value = "/user")
public class RedisTestController {

    @Autowired
    private SessionTest sessionTest;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void list(Model model){
        // List<Role> roleList = roleService.list();
        // model.addAttribute("roleList",roleList);
        sessionTest.put("zh","111");
        System.out.print(sessionTest.get("zh"));
    }
}
*/
