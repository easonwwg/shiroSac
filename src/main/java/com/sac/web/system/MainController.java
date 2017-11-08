package com.sac.web.system;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 特殊处理 防止用户登陆后，再次打开首页，出现404找不到错误，直接暴力跳转到首页
 * Created by EAISON on 2017/10/13.
 */

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex()
    {
        return "redirect:/user/main";
    }

}

