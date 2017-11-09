package com.sac.web.system;

import com.sac.exception.BusinessException;
import com.sac.service.system.Interface.UserService;
import com.sac.shiro.cache.SessionCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by EAISON on 2017/9/28.
 */

@Controller
@RequestMapping("/user")
public class LoginController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        //  SecurityUtils.getSubject().getSession().stop();
        return "index";
    }

    /**
     * 新增用戶
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toIndex() {
      //  Serializable beforeSerializable=   SecurityUtils.getSubject().getSession().getId();
      //  SecurityUtils.getSubject().getSession().stop();

      //  Serializable afterSerializable=   SecurityUtils.getSubject().getSession().getId();
        return "add";
    }

    /**
     * 刪除用戶
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String toIndex1() {
      //  SecurityUtils.getSubject().getSession().stop();
        if (5>4){
            throw  new BusinessException("超出最大字节数");
        }
        return "delete";
    }


    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }



    /**
     * 跳转到首页
     *
     * @return
     */
    /*@RequiresRoles({"SuperAdmin"})*/ //注解测试
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String toMainIndex() {
        return "main";
    }

    /**
     * 未授权
     *
     * @return
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String toErrorIndex() {
        return "error";
    }


    /**
     * 登陆按钮
     *
     * @param userName
     * @param passwd
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/checklogin", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String login( HttpServletRequest request,String userName, String passwd, Model model) {
        Subject currentSubject = SecurityUtils.getSubject();
        if(currentSubject.isRemembered()){
            System.out.print("用户已经记住了我");
        }
        //没有被认证
        if (!currentSubject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passwd);
           //  token.setRememberMe(true);
            try {
                currentSubject.login(token);
                PrincipalCollection principalCollection=
                        SecurityUtils.getSubject().getPrincipals();
                Serializable serializable= SecurityUtils.getSubject().getSession().getId();
                System.out.println("用户登陆时候的sessionId是"+serializable);
                SessionCache sessionCache=new SessionCache();
                sessionCache.setMyMap(serializable,principalCollection);
               // int a=0;
            } catch (DisabledAccountException e) {
                model.addAttribute("errormsg", e.getMessage());
                return "login";
            } catch (AuthenticationException e) {
                model.addAttribute("errormsg", "用户名或者密码错误");
                return "login";
            }
            //跳转到首页 但是需要验证用户是否有访问首页的权限
            return "redirect:/user/main";
        }
        //直接跳转到首页
        return "redirect:/user/main";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/toLogOut", method = RequestMethod.GET)
    public String toLogOut(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return "login";
    }


    /**
     * 用户异常页面
     *
     * @return
     */
    @RequestMapping(value = "/sysError", method = RequestMethod.GET)
    public String sysError(RedirectAttributes redirectAttributes) {
        return "sysError";
    }

}
