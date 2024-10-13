//package com.edu.project.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.csrf().disable() // 禁用 CSRF 保护
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不使用 session
//				.and()
//				.authorizeRequests()
//				.antMatchers("/user/login").permitAll() // 允许所有用户访问登录接口
//				.anyRequest().authenticated() // 所有其他请求都需要认证
//				.and()
//				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加 JWT 过滤器
//	}
//
//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter(); // 自定义 JWT 过滤器
//	}
//}
