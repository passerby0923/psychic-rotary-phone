package com.edu.project;


import com.edu.project.bean.User;
import com.edu.project.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ApplicationTest {
	@Resource
	private UserMapper userMapper;

	@Test
	public void UserMappertest(){
		User user = userMapper.selectById(2);
		System.out.println("user:"+user);
	}
}
