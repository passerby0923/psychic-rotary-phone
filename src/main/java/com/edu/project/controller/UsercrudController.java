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

	// 创建用户
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> createUser(HttpServletRequest request) {
		User user = UserUtil.fromRequest(request);
		boolean save = userService.save(user);
		Map<String, String> response = new HashMap<>();
		if (save) {
			response.put("message", "用户添加成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "用户添加失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	// 获取所有用户
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<User>> listUsers() {
		List<User> list = userService.list(); // 获取所有用户
		return ResponseEntity.ok(list); // 返回用户列表的 JSON 数据
	}


	// 根据用户名获取用户
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



	// 更新用户信息
	@PostMapping("/updateByUsername")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateUser(@RequestParam String username, HttpServletRequest request) {
		User updatedUser = UserUtil.fromRequest(request);
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


	// 删除用户
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
