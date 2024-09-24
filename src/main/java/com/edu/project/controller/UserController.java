package com.edu.project.controller;

import com.edu.project.bean.Result;
import org.springframework.ui.Model;
import com.edu.project.bean.User;
import com.edu.project.service.UserService;
import com.edu.project.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;



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
		// 检查用户名是否已存在
		if (userService.findByUsername(username)!=null) {
			return "用户已存在";
		}
		// 创建新用户
		User newUser = new User();
		newUser.setUsername(username);
		// 使用 PasswordUtil 加密密码
		newUser.setPassword(PasswordUtil.encryptPassword(password));
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
			return Result.fail("用户不存在"); // 返回登录页面
		}
		if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
			model.addAttribute("error", "密码错误");
			return Result.fail("密码错误"); // 返回登录页面
		}
		// 登录成功，返回用户角色和成功状态
		return Result.success("登录成功", user.getRole());
	}

}
