package com.edu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.Employee;
import com.edu.project.bean.User;
import com.edu.project.dao.UserMapper;
import com.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.memory.UserAttribute;
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

	@Override
	public boolean updateUserByUsername(String username, User updatedUser) {
		// 使用 QueryWrapper 查找指定用户名的用户
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username); // 根据用户名查找用户

		// 检查用户是否存在
		User existingUser = userMapper.selectOne(queryWrapper);
		if (existingUser == null) {
			// 如果没有找到用户，返回 false
			return false; // 或者可以选择抛出异常
		}
		// 确保更新的员工对象使用正确的手机号
		updatedUser.setUsername(username);
		return update(updatedUser, queryWrapper); // 执行更新操作
		// 可以继续设置其他字段
	}

	@Override
	public boolean deleteUseByUsername(String username) {
		//使用 MyBatis-Plus 的 QueryWrapper 类来构建查询条件，以便根据员工的手机号查找特定的员工信息
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);

		return remove(queryWrapper);
	}


}
