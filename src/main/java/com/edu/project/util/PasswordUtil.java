package com.edu.project.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

	// 加密密码
	public static String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	// 验证密码
	public static boolean verifyPassword(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
}
