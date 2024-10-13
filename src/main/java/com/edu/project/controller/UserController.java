
package com.edu.project.controller;

import com.edu.project.bean.Result;
import com.edu.project.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.edu.project.bean.User;
import com.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		// 检查用户是否已存在
		if (userService.findByUsername(user.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("用户已存在");
		}

		// 创建新用户并保存
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		userService.registerUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body("已添加");
	}

	/**
	 * 用户登录
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
		if (!password.equals(user.getPassword())) {
			model.addAttribute("error", "密码错误");
			return Result.fail("密码错误");
		}
		//生成token
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("id", user.getUserId());
		claims.put("username",user.getUsername());
		String token = JwtUtil.genToken(claims);
		System.out.println("================2"+token);

		return Result.success(token,"登录成功", user.getRole());
	}
}
