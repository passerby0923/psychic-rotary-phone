package com.edu.project.util;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // 只返回非空字段
public class ApiResponse<T> {
	private boolean success;
	private String message;
	private T data;

	public ApiResponse(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, "操作成功", data);
	}

	public static ApiResponse<Object> failure(String message) {
		return new ApiResponse<>(false, message, null);
	}

	// Getters and Setters
	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
}
