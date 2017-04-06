package com.xiaozan.web.xiaozan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xiaozan.web.xiaozan.pojo.User;
import com.xiaozan.web.xiaozan.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUserById(String id) {
		User user = userService.getUserById(id);
		String userStr = JSON.toJSONString(user);
		return userStr;
	}
}