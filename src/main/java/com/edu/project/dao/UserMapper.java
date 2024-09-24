package com.edu.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.project.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	public User findByUsername(String username);

}
