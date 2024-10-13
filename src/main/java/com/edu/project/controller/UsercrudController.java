package com.edu.project.controller;

import com.edu.project.bean.User;

import com.edu.project.service.UserService;
import com.edu.project.util.ApiResponse;
import com.edu.project.util.beanutil.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin //跨域请求
@RestController
@RequestMapping("/users")
public class UsercrudController {

	@Autowired
	private UserService userService;

	/**
	 * 创建用户
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> createUser(@RequestBody User user) {
		// 尝试保存用户信息
		boolean save = userService.save(user);

		// 创建响应消息
		Map<String, String> response = new HashMap<>();
		if (save) {
			response.put("message", "用户添加成功");
			return ResponseEntity.ok(response); // 返回成功响应
		} else {
			response.put("message", "用户添加失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 返回失败响应
		}
	}


	/**
	 * 获取所有用户
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listUsers() {
		List<User> list = userService.list(); // 获取所有用户
		Map<String, Object> response = new HashMap<>();
		response.put("code", 200); // 添加状态码，200 表示成功
		response.put("data", list); // 返回用户列表数据

		return ResponseEntity.ok(response); // 返回封装后的响应数据
	}



	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	@GetMapping("/updata/{username}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		Map<String, Object> response = new HashMap<>();
		if (user != null) {
			response.put("message", "查询成功");
			response.put("user", user); // 将查询到的用户信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该用户");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}


	/**
	 * 更新用户信息
	 * @param username
	 * @param request
	 * @return
	 */
	@PostMapping("/updateByUsername")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody User updatedUser) {
		String username = updatedUser.getUsername(); // 从更新的用户对象中获取用户名
		boolean isUpdated = userService.updateUserByUsername(username, updatedUser);

		Map<String, String> response = new HashMap<>();
		if (isUpdated) {
			response.put("status", "success");
			response.put("message", "用户更新成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "用户更新失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	/**
	 * 删除用户
	 * @param username
	 * @return
	 */
	@DeleteMapping("/delete/{username}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String username) {
		boolean isDeluser = userService.deleteUseByUsername(username);
		Map<String, String> response = new HashMap<>();
		if (isDeluser) {
			response.put("status", "success");
			response.put("message", "删除成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "删除失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
