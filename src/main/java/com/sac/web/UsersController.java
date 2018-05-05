package com.sac.web;

import com.sac.aop.aopservice.IMyMath;
import com.sac.aop.aopservice.impl.IMyMathImpl;
import com.sac.pojo.PojoValidate;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.executable.ExecutableValidator;
import java.util.*;

/**
 * Created by 99079 on 2017/9/21.
 */

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    /**
     * aop接口测试 基于jdk动态代理
     */
    @Autowired
    private IMyMath iMyMath;

    /**
     * aop类测试，基于cglib代理
     */
    @Autowired
    private IMyMathImpl iMyMath1;


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
    /**
     * 使用矩阵变量
     * http://localhost:8081/users/persons/nj;age=50/pets/21;q=22
     */
    @GetMapping("/goods/{country}/se/{age}")
    @ResponseBody
    public String findPet(
            @PathVariable String ownerId,
            @PathVariable String petId,
            @MatrixVariable(name = "q", pathVar = "ownerId") int q1,
            @MatrixVariable(name = "q", pathVar = "petId") int q2) {
        System.out.println("--> ownerId : " + ownerId);
        System.out.println("--> petId : " + petId);
        System.out.println("--> q1 : " + q1);
        System.out.println("--> q2 : " + q2);

        return "/examples/targets/test1";
    }

    /**
     * http://localhost:8081/users/owners/42/5
     *
     * @param ownerId
     * @param petId
     * @return
     */
    @GetMapping("/owners/{ownerId}/{petId}")
    @ResponseBody
    public String findPet1(
            @PathVariable String ownerId,
            @PathVariable String petId) {
        return "/examples/targets/test1";
    }

    //        http://localhost:8081/users/goods/mac;price=15400,20000;country=nj,bj/sales;top=300
    @GetMapping("/goods/{type}/{number}")
    @ResponseBody
    public String findPet2(
            @PathVariable String type,
            @MatrixVariable(name = "price", pathVar = "type") List<Integer> prices,
            @MatrixVariable(name = "country", pathVar = "type") List<String> country,
            @MatrixVariable(name = "top", pathVar = "number") List<String> top
    ) {
        return "/examples/targets/test1";
    }

    //http://localhost:8081/users/goods1/mac;price=15400;country=bj,nj
    @GetMapping("/goods1/{type}")
    @ResponseBody
    public String findPet21(
            @PathVariable String type,
            @MatrixVariable(pathVar = "type") Map<String, Object> petMatrixVars
    ) {
        return "/examples/targets/test1";
    }

    @RequiresRoles({"superAdmin"})
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public String list1() {
        return "main";
    }

    @RequestMapping(value = "/list1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> controllerTest(@RequestBody PojoValidate pojoValidate) {
        // ExecutableValidator
        Map<String, String> maps = new HashMap<>();
        maps.put("sd", pojoValidate.getUserName());
        // pojoValidate.setAge(20);
        int result = iMyMath.add(20);
        //int result = iMyMath1.add(pojoValidate);
        // System.out.println(result);
        return maps;
    }

}


