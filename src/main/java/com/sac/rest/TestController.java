package com.sac.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "接口测试")
@RequestMapping(value = "/sac")
public class TestController {

	@ApiOperation(value = "获取用户列表", notes = "测试做的")
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public List<String> getUserList() {
		List<String> r = new ArrayList<String>();
		r.add("ss");
		return r;
	}

	@ApiOperation(value = "改变用户", notes = "根据User对象改变用户")
	@ApiImplicitParam(name = "stu", value = "用户详细实体user", required = true, dataType = "Stu")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Stu postUser(@RequestBody Stu stu) {
		stu.setAge(1000);
		stu.setName("wwgwwgwwg");
		return stu;
	}
}

class Stu {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
