package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
public class LoginController {
	
	
	@Autowired
	private UserRepository ur;
	
	@ApiOperation(value = "登录",notes = "登录系统")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
		@ApiImplicitParam(name = "password", value = "用户密码", required =true, dataType = "String")
	})
	//@RequestMapping(value = "/login", method = RequestMethod.POST)
	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
	public String update(@PathVariable String username, @PathVariable String password) {
		
		User user = ur.findByUsername(username);
		if(user.getPassword() == password) {
			return "登录成功！";
		}
		else {
			return "登录失败";
		}
		
	}
}
