package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.House;
import com.edu.project.bean.Message;

public interface MessageService extends IService<Message> {
	Message findByNickName(String nickName);  // 根据昵称查询留言
	boolean updateMessage(Message message);   // 更新留言
	boolean save(Message message);            // 保存新留言
	Message findBynickName(String nickName);

	boolean deleteByNickName(String nickName); // 根据昵称删除留言
}
