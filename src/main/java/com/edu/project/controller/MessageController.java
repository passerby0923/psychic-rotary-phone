package com.edu.project.controller;

import com.edu.project.bean.Message;
import com.edu.project.bean.User;
import com.edu.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin //跨域请求
@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@PostMapping("/createadd")
	@ResponseBody
	public String CreateAdd(@RequestParam("nickName") String nickName,
	                     @RequestParam("content") String content){
		Message message = new Message();
		message.setNickName(nickName);
		message.setContent(content);
		message.setCreateTime(new Timestamp(System.currentTimeMillis()));
		boolean savecreat = messageService.save(message);
		if (savecreat) {
			return "留言上传成功";
		} else {
			return "留言上传成功";
		}
	}


	@PostMapping("/replyadd")
	@ResponseBody
	public String replyAdd(@RequestParam("nickName") String nickName,
	                       @RequestParam("replyContent") String replyContent) {
		// 根据昵称查询 Message 对象
		Message existingMessage = messageService.findByNickName(nickName);

		if (existingMessage != null) {
			// 如果记录存在，更新回复内容和时间
			existingMessage.setReplyContent(replyContent);
			existingMessage.setReplyTime(new Timestamp(System.currentTimeMillis()));

			// 更新记录
			boolean updateSuccess = messageService.updateMessage(existingMessage);
			return updateSuccess ? "留言更新成功" : "留言更新失败";
		} else {
			// 如果记录不存在，创建新留言
			Message newMessage = new Message();
			newMessage.setNickName(nickName);
			newMessage.setReplyContent(replyContent);
			newMessage.setReplyTime(new Timestamp(System.currentTimeMillis()));

			// 保存新记录
			boolean saveSuccess = messageService.save(newMessage);
			return saveSuccess ? "留言上传成功" : "留言上传失败";
		}
	}


	/**
	 * 获取所有信息
	 *
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Message>> listUsers() {
		List<Message> list = messageService.list(); // 获取所有用户
		return ResponseEntity.ok(list); // 返回用户列表的 JSON 数据
	}

	// 根据昵称查询信息
	@GetMapping("/query/{nickName}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<Map<String, Object>> getMessageByNickName(@PathVariable String nickName) {
		// 根据昵称查询留言信息
		Message message = messageService.findBynickName(nickName);
		Map<String, Object> response = new HashMap<>();

		if (message != null) {
			response.put("message", "查询成功");
			response.put("messageInfo", message); // 将查询到的留言信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该留言");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	//删除
	@DeleteMapping("/delete/{nickName}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteMessage(@PathVariable String nickName) {
		boolean isDelMessage = messageService.deleteByNickName(nickName);
		Map<String, String> response = new HashMap<>();
		if (isDelMessage) {
			response.put("status", "success");
			response.put("message", "留言删除成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "留言删除失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}


}
