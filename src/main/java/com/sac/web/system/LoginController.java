package com.sac.web.system;

import com.sac.service.system.Interface.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EAISON on 2017/9/28.
 */

@Controller
@RequestMapping("/user")
public class LoginController {
    public  static  final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;


    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public  String toLogin(){
        return "login";
    }
    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public  String toMainIndex(){
        return  "main";
    }

    /**
     * 未授权
     * @return
     */
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public  String toErrorIndex(){
        return  "error";
    }


    /**
     * 登陆按钮
     * @param userName
     * @param passwd
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/checklogin", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public String login(String userName,String passwd, Model model,
                        HttpServletRequest request) {

        Subject currentSubject = SecurityUtils.getSubject();
        //已经没有被认证
        if (!currentSubject.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken(userName,passwd);
             try {
                 currentSubject.login(token);
             }
             catch (AuthenticationException e)
             {
                model.addAttribute("errormsg", "用户名或者密码错误");
                 return "login";
             }
            //跳转到首页
             return  "main";
        }
        //直接跳转到首页
        return  "main";
    }

}
