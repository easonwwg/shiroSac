package com.sac.web.system;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.data.redis.core.RedisTemplate;
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


   // @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        redisTemplate.opsForValue().set("wwg","2622");
        System.out.print(redisTemplate.opsForValue().get("wwg"));
        return "Menu/index";
    }
}

