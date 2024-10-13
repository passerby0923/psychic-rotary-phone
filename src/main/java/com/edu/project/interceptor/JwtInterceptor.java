package com.edu.project.interceptor;

import com.edu.project.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// 从请求头中获取 token
		String token = request.getHeader("Authorization");
		System.out.println("============3"+token);
		// 如果请求头中没有，则尝试从请求参数中获取 token
		if (token == null) {
			token = request.getParameter("Authorization");
			System.out.println("============4"+token);

		}
		try {
			// 验证 token 并提取其中的业务数据
			Map<String, Object> claims = JwtUtil.parseToken(token);
			// 将解析出的数据存入 request 属性中，供后续使用
			request.setAttribute("claims", claims);
			// 验证通过，放行请求
			return true;
		} catch (Exception e) {
			// 验证失败，返回 401 状态码，表示未授权
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
	}

}
