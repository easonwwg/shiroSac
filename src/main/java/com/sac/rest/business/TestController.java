package com.sac.rest.business;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Title: UserController.java Description: Copyright: Copyright (c) 2017
 * Company: SAC
 *
 * @author eason-wang
 * @version 1.0
 * @date 2017年12月12日
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Api(value = "user", description = "用户管理接口")
public class TestController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(notes = "get", httpMethod = "GET", value = "获取用户名")
    public List<String> save() {
        return Arrays.asList("wwg", "sk");
    }
}
