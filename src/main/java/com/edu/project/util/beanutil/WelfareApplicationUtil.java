package com.edu.project.util.beanutil;

import com.edu.project.bean.WelfareApplication;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 福利申请信息获取与校验
 */
public class WelfareApplicationUtil {

	// 封装福利申请信息的方法
	public static WelfareApplication fromRequest(HttpServletRequest request) {
		WelfareApplication welfareApplication = new WelfareApplication();

		// 获取并设置申请各字段，带基本的校验
		welfareApplication.setApplicantName(getStringParameter(request, "applicantName"));
		welfareApplication.setWelfareType(getStringParameter(request, "welfareType"));
		welfareApplication.setApplyDate(getDateParameter(request, "applyDate"));
		welfareApplication.setStatus(getStringParameter(request, "status"));
		welfareApplication.setComments(getStringParameter(request, "comments"));
		welfareApplication.setFilePath(getStringParameter(request, "filePath"));

		return welfareApplication;
	}

	// 获取 String 参数
	private static String getStringParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty()) {
			throw new IllegalArgumentException("缺少或为空的参数: " + paramName);
		}
		return param;
	}

	// 获取 Date 参数
	private static Date getDateParameter(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param == null || param.isEmpty()) {
			return null; // 允许返回 null
		}
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(param);
		} catch (ParseException e) {
			throw new IllegalArgumentException("日期参数格式无效: " + paramName);
		}
	}
}
