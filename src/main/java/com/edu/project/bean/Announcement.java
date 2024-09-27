package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 公告
 */
@Data
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 全参构造方法
@TableName("announcement") // 对应的数据库表名
public class Announcement {
	@TableId(value = "announcement_id", type = IdType.AUTO) // 指定数据库中的主键字段并使用 MyBatis-Plus 的自动递增策略
	private Integer announcementId; // 公告ID
	private String title; // 公告标题
	private String content; // 公告内容
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date publishDate; // 发布日期
}
