package com.edu.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.User;
import com.edu.project.dao.UserMapper;
import com.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends
		ServiceImpl<UserMapper, User>
		implements UserService {

	//	注入的属性
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		User byUsername = userMapper.findByUsername(username);
		return byUsername;
	}

	@Override
	public void registerUser(User newUser) {
		userMapper.insert(newUser);
	}
}
