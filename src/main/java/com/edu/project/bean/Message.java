package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 留言板
 */
@Data
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 全参构造方法
public class Message {
	@TableId(value = "message_id", type = IdType.AUTO) // 指定数据库中的主键字段并使用 MyBatis-Plus 的自动递增策略
	private Integer messageId; // 留言ID
	private Integer residentId; // 居民ID
	private String content; // 留言内容
	private Timestamp createTime; // 留言时间
	private String reply; // 回复内容
	private Timestamp replyTime; // 回复时间
}
