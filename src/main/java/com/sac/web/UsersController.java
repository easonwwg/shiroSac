package com.sac.web;

import com.sac.aop.aopservice.IMyMath;
import com.sac.pojo.PojoValidate;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 99079 on 2017/9/21.
 */

@Controller
@RequestMapping(value = "/user")
public class UsersController {

    @Autowired
    private IMyMath iMyMath;
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    /**
     * 跳转到查询所有角色的页面
     * http://localhost:8081/users/list.form?typer=23 可以访问
     * http://localhost:8081/users/list.form  可以访问
     * http://localhost:8081/users/list.do  可以访问
     * http://localhost:8081/users/list.action  可以访问
     * http://localhost:8081/users/list.action  不可以访问
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        // List<Role> roleList = roleService.list();
        // model.addAttribute("roleList",roleList);
        System.out.print("ok");
        return "main";
    }

    @RequiresRoles({"superAdmin"})
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public String list1() {
        return "main";
    }

    @RequestMapping(value = "/list1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> testValidate(@RequestBody PojoValidate pojoValidate) {
       Map<String, String> maps = new HashMap<>();
        /* if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                System.out.println(objectError.getDefaultMessage());
                maps.put("x", objectError.getDefaultMessage());
            }
            return maps;
        }*/
        maps.put("sd", pojoValidate.getUserName());
        int result = iMyMath.add(pojoValidate);
        System.out.println(result);
        return maps;
    }

}
