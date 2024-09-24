package com.edu.project;

import com.edu.project.util.EncryptExistingPasswords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	//@Autowired
	private EncryptExistingPasswords encryptExistingPasswords;

	@Override
	public void run(String... args) throws Exception {
		// 应用启动后，自动加密未加密的密码
		encryptExistingPasswords.encryptPasswords();
	}
}
