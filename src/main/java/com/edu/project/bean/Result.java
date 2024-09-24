package com.edu.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
	private int code;
	private String message;
	private T data;

	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static <T> Result<T> success(String message, T data) {
		return new Result<>(200, message, data);
	}

	public static Result<Void> success(String message) {
		return new Result<>(200, message);
	}

	public static Result<Void> fail(String message) {
		return new Result<>(400, message);
	}
}
