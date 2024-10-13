package com.edu.project.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

	// 定义密钥
	private static final String KEY = "street";

	// 生成token
	public static String genToken(Map<String, Object> claims) {
		return JWT.create()
				.withClaim("claims", claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 设置12小时过期时间
				.sign(Algorithm.HMAC256(KEY)); // 使用HMAC256算法进行签名
	}

	// 解析并验证token
	public static Map<String, Object> parseToken(String token) {
		System.out.println("============1"+token);
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build(); // 创建JWT验证器
		DecodedJWT jwt = verifier.verify(token); // 验证token并获取DecodedJWT对象
		return jwt.getClaim("claims").asMap(); // 提取并返回业务数据
	}
}
