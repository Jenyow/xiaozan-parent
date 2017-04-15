package com.xiaozan.web.xiaozan.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xiaozan.web.xiaozan.pojo.User;
import com.xiaozan.web.xiaozan.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/user", produces = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = String.class, notes = "根据用户名获取用户对象")
	public String getUserById(
			@ApiParam(required = true, name = "username", value = "用户名") @PathVariable("username") String username) {
		User user = userService.getUserById(username);
		String userStr = JSON.toJSONString(user);
		return userStr;
	}

}