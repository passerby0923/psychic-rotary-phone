package com.edu.project.service.impl;

import com.edu.project.bean.Message;
import com.edu.project.dao.MessageMapper;
import com.edu.project.service.MessageService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class MessageServiceImpl extends
		ServiceImpl<MessageMapper, Message>
		implements MessageService {
	@Override
	public Message findByNickName(String nickName) {
		// 调用自定义的Mapper方法，根据昵称查询Message记录
		return this.baseMapper.findByNickName(nickName);
	}

	@Override
	public boolean updateMessage(Message message) {
		// 使用MyBatis-Plus提供的updateById方法更新记录
		return this.updateById(message);
	}

	@Override
	public boolean save(Message message) {
		// 使用MyBatis-Plus提供的save方法保存新记录
		return super.save(message);
	}

	@Override
	public Message findBynickName(String nickName) {
		// 使用 Mapper 自定义查询逻辑
		return this.baseMapper.findBynickName(nickName);
	}

	@Override
	public boolean deleteByNickName(String nickName) {
		// 根据昵称删除留言
		return this.baseMapper.deleteByNickName(nickName) > 0;
	}
}
