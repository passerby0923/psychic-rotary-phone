package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/**
 * 留言板
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("message")
public class Message {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;                    // 留言ID
	private String nickName;            // 昵称
	private String content;             // 留言内容
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp createTime;       // 留言时间
	private String replyContent;        // 回复内容
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp replyTime;        // 回复时间
}
