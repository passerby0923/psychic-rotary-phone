package com.edu.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.project.bean.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
	// 自定义查询方法，通过nickName获取Message
	@Select("SELECT * FROM message WHERE nick_name = #{nickName}")
	Message findByNickName(String nickName);

	// 根据昵称查询留言
	@Select("SELECT * FROM message WHERE nick_name = #{nickName}")
	Message findBynickName(String nickName);

	// 根据昵称删除留言
	@Delete("DELETE FROM message WHERE nick_name = #{nickName}")
	int deleteByNickName(String nickName);
}
