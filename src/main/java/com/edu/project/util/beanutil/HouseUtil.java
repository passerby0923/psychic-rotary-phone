package com.edu.project.util.beanutil;

import com.edu.project.bean.House;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 房屋信息获取与校验
 */
public class HouseUtil {

	// 封装房屋信息的方法
	public static House fromRequest(HttpServletRequest request) {
		House house = new House();

		// 获取并设置房屋各字段，带基本的校验
		house.setAddress(getStringParameter(request, "address"));
		house.setOwnerName(getStringParameter(request, "ownerName"));
		house.setHouseType(getStringParameter(request, "houseType"));
		house.setArea(getDecimalParameter(request, "area"));
		house.setBuildYear(getIntegerParameter(request, "buildYear"));

		return house;
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

	// 获取 BigDecimal 参数
	private static BigDecimal getDecimalParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty()) {
			return null; // 允许返回 null
		}
		try {
			return new BigDecimal(param);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("参数格式无效: " + paramName);
		}
	}
}