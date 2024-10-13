package com.edu.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
	private int code;       // 响应状态码
	private String message; // 响应消息
	private T data;         // 泛型类型的数据

	// 只包含状态码和消息的构造方法
	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	// 成功的静态工厂方法，返回带数据的结果
	public static <T> Result<T> success(String message, T data) {
		return new Result<>(200, message, data); // 200 表示成功
	}

	// 成功的静态工厂方法，不带数据
	public static Result<Void> success(String message) {
		return new Result<>(200, message);
	}

	// 失败的静态工厂方法
	public static Result<Void> fail(String message) {
		return new Result<>(400, message); // 400 表示请求错误
	}

	// 新增：支持带 token 和 role 的成功方法
	public static Result<Map<String, String>> success(String token, String message, String role) {
		Map<String, String> data = new HashMap<>();
		data.put("token", token);
		data.put("role", role);
		return new Result<>(200, message, data);
	}
}
