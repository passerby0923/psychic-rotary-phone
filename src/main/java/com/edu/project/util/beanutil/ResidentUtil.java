package com.edu.project.util.beanutil;

import com.edu.project.bean.Resident;

import javax.servlet.http.HttpServletRequest;

/**
 * 居民信息获取与校验
 */
public class ResidentUtil {

	// 封装居民信息的方法
	public static Resident fromRequest(HttpServletRequest request) {
		Resident resident = new Resident();

		// 获取并设置居民各字段，带基本的校验
		resident.setName(getStringParameter(request, "name"));
		resident.setIdCard(getStringParameter(request, "idCard"));
		resident.setGender(getCharacterParameter(request, "gender"));
		resident.setAge(getIntegerParameter(request, "age"));
		resident.setPhone(getStringParameter(request, "phone"));
		resident.setAddress(getStringParameter(request, "address"));

		return resident;
	}

	// 获取 String 参数
	private static String getStringParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty()) {
			throw new IllegalArgumentException("缺少或为空的参数: " + paramName);
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
			throw new IllegalArgumentException("参数格式无效: " + paramName);
		}
	}

	// 获取 Character 参数
	private static Character getCharacterParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty() || param.length() != 1) {
			throw new IllegalArgumentException("缺少或格式无效的参数: " + paramName);
		}
		return param.charAt(0);
	}
}
