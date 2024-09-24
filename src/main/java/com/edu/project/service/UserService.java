package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.User;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {

	User findByUsername(String username);

	void registerUser(User newUser);
}
