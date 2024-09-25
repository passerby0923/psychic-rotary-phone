package com.edu.project.util.beanutil;

import com.edu.project.bean.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息获取与校验
 */
public class UserUtil {

	// 封装用户信息的方法
	public static User fromRequest(HttpServletRequest request) {
		User user = new User();

		// 获取并设置用户各字段，带基本的校验
		user.setUsername(getStringParameter(request, "username"));
		user.setPassword(getStringParameter(request, "password"));
		user.setRole(getStringParameter(request, "role"));

		return user;
	}

	// 获取 String 参数
	private static String getStringParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty()) {
			throw new IllegalArgumentException("Missing or empty parameter: " + paramName);
		}
		return param;
	}

	// 获取 Integer 参数
	private static Integer getIntegerParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty()) {
			return null; // 允许返回 null
		}
		try {
			return Integer.valueOf(param);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid number format for parameter: " + paramName);
		}
	}
}
