package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private Long id;
	private String nickName;
	private String ip;
	private String content;
	private Timestamp createTime ;
	@Builder.Default
	private Boolean replied = false;
	private String replyName;
	private String replyContent;
	private Timestamp replyTime ;
	@JsonIgnore
	@Builder.Default
	@TableLogic
	private Boolean enabled = true;
}
