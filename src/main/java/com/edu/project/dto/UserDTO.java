package com.edu.project.dto;

public class UserDTO {
	private String username; // 用户名
	private String password; // 密码
	private String role; // 角色

	// 构造函数
	public UserDTO(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	// Getter 方法
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
}
