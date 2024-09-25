package com.edu.project.util.beanutil;

import com.edu.project.bean.Employee;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 员工信息获取与校验
 */
public class EmployeeUtil {

	// 封装员工信息的方法
	public static Employee fromRequest(HttpServletRequest request) {
		Employee employee = new Employee();

		// 获取并设置员工各字段，带基本的校验
		employee.setName(getStringParameter(request, "name"));
		employee.setAge(getIntegerParameter(request, "age"));
		employee.setGender(getCharParameter(request, "gender"));
		employee.setDepartment(getStringParameter(request, "department"));
		employee.setPosition(getStringParameter(request, "position"));
		employee.setHireDate(getDateParameter(request, "hireDate"));
		employee.setSalary(getBigDecimalParameter(request, "salary"));
		employee.setPhone(getStringParameter(request, "phone"));
		employee.setEmail(getStringParameter(request, "email"));

		return employee;
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
		String param = getStringParameter(request, paramName);
		try {
			return Integer.valueOf(param);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid number format for parameter: " + paramName);
		}
	}

	// 获取单字符参数
	private static char getCharParameter(HttpServletRequest request, String paramName) {
		String param = getStringParameter(request, paramName);
		if (param.length() != 1) {
			throw new IllegalArgumentException("Invalid character format for parameter: " + paramName);
		}
		return param.charAt(0);
	}

	// 获取 BigDecimal 参数
	private static BigDecimal getBigDecimalParameter(HttpServletRequest request, String paramName) {
		String param = getStringParameter(request, paramName);
		try {
			return new BigDecimal(param);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid number format for parameter: " + paramName);
		}
	}

	// 获取 Date 参数
	private static Date getDateParameter(HttpServletRequest request, String paramName) {
		String param = getStringParameter(request, paramName);
		try {
			return Date.valueOf(param); // java.sql.Date 格式化
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid date format for parameter: " + paramName);
		}
	}
}
