package com.sac.rest.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by EAISON on 2017/9/22.
 */

@RestController
@Api(description = "接口测试")
@RequestMapping(value = "/test")
public class TestQueryController {

    @Autowired
    private ITestQueryService service;

    @ApiOperation(value = "获取用户列表", notes = "测试做的")
    @RequestMapping(value ="/names" , method = RequestMethod.GET)
    public List<String> getUserList() {
        return service.GetAllUserName();
    }

   /*post测试
    @ApiOperation(value = "改变用户", notes = "根据User对象改变用户")
    @ApiImplicitParam(name = "stu", value = "用户详细实体user", required = true, dataType = "Stu")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Stu postUser(@RequestBody Stu stu) {
        stu.setAge(1000);
        stu.setName("wwgwwgwwg");
        return stu;
    }*/
}
