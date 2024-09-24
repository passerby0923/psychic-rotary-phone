package com.edu.project.util;

import com.edu.project.bean.User;
import com.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import java.util.List;

@Component  // 这个类要被 Spring 管理，所以加上 @Component 注解
public class EncryptExistingPasswords {

	@Autowired
	private UserService userService;

	public void encryptPasswords() {
		// 获取所有用户信息
		List<User> userList = userService.list();  // 获取所有用户

		// 遍历所有用户
		for (User user : userList) {
			String currentPassword = user.getPassword(); // 读取现有的密码

			// 判断密码是否已经加密，BCrypt 加密后的密码通常以 $2a$ 或 $2b$ 开头，长度为 60 个字符
			if (currentPassword.length() < 60 || !currentPassword.startsWith("$2a$")) {
				// 如果密码未加密，对其进行加密
				String encryptedPassword = BCrypt.hashpw(currentPassword, BCrypt.gensalt());

				// 更新用户密码
				user.setPassword(encryptedPassword);
				userService.updateById(user);  // 更新用户密码到数据库
			}
		}
	}
}
