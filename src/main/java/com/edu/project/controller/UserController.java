package com.edu.project.controller;

import com.edu.project.bean.Result;
import org.springframework.ui.Model;
import com.edu.project.bean.User;
import com.edu.project.service.UserService;
import com.edu.project.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@CrossOrigin //跨域请求
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	@PostMapping("/register")
	public String registerUser(@RequestParam String username,
	                           @RequestParam String password,
	                           @RequestParam String role) {
		if (userService.findByUsername(username) != null) {
			return "用户已存在";
		}
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password); // 存储明文密码
		newUser.setRole(role);
		newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		userService.registerUser(newUser);
		return "已添加";
	}

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	public Result login(@RequestParam String username, @RequestParam String password, Model model) {
		User user = userService.findByUsername(username);
		if (user == null) {
			model.addAttribute("error", "用户不存在");
			return Result.fail("用户不存在");
		}
		if (!password.equals(user.getPassword())) { // 直接比较明文密码
			model.addAttribute("error", "密码错误");
			return Result.fail("密码错误");
		}
		return Result.success("登录成功", user.getRole());
	}
}
