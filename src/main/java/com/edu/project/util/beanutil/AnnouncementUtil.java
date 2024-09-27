package com.edu.project.util.beanutil;

import com.edu.project.bean.Announcement;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * 公告信息获取与校验
 */
public class AnnouncementUtil {

	// 封装公告信息的方法
	public static Announcement fromRequest(HttpServletRequest request) {
		Announcement announcement = new Announcement();

		// 获取并设置公告各字段，带基本的校验
		announcement.setTitle(getStringParameter(request, "title"));
		announcement.setContent(getStringParameter(request, "content"));
		announcement.setPublishDate(getDateParameter(request, "publishDate"));

		return announcement;
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
		String param = getStringParameter(request, paramName);
		try {
			return Integer.valueOf(param);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("参数格式无效: " + paramName);
		}
	}

	// 获取 Date 参数
	private static Date getDateParameter(HttpServletRequest request, String paramName) {
		String param = getStringParameter(request, paramName);
		try {
			return Date.valueOf(param); // java.sql.Date 格式化
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("日期格式无效: " + paramName);
		}
	}
}
