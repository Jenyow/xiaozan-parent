package com.xiaozan.web.xiaozan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaozan.web.xiaozan.mapper.UserMapper;
import com.xiaozan.web.xiaozan.pojo.User;
import com.xiaozan.web.xiaozan.service.IUserService;

@Component
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(String id) {
		// UserExample example=new UserExample();
		// example.createCriteria().andIdEqualTo(id);
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}
}