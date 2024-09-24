package com.edu.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 留言板
 */
@Data
@NoArgsConstructor//无参的构造方法
@AllArgsConstructor //全参的构造方法
public class Message {
	private Integer messageId; // 留言ID
	private Integer residentId; // 居民ID
	private String content; // 留言内容
	private Timestamp createTime; // 留言时间
	private String reply; // 回复内容
	private Timestamp replyTime; // 回复时间

}
