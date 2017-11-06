package com.sac.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.BitSet;

/**
 * Created by 99079 on 2017/9/21.
 */

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private static  final Logger logger= LoggerFactory.getLogger(UsersController.class);

    /**
     * 跳转到查询所有角色的页面
     * http://localhost:8081/users/list.form?typer=23 可以访问
     * http://localhost:8081/users/list.form  可以访问
     * http://localhost:8081/users/list.do  可以访问
     * http://localhost:8081/users/list.action  可以访问
     * http://localhost:8081/users/list.action  不可以访问
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
       // List<Role> roleList = roleService.list();
       // model.addAttribute("roleList",roleList);
        System.out.print("ok");
        return "main";
    }


}
