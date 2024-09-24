package com.edu.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 公告
 */
@Data
@NoArgsConstructor//无参的构造方法
@AllArgsConstructor //全参的构造方法
public class Announcement {
	private Integer announcementId; // 公告ID
	private String title; // 公告标题
	private String content; // 公告内容
	private Date publishDate; // 发布日期

}

