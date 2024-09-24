package com.edu.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 居民信息
 */
@Data
@NoArgsConstructor//无参的构造方法
@AllArgsConstructor //全参的构造方法
public class Resident {
	private Integer residentId; // 居民ID
	private String name; // 居民姓名
	private String idCard; // 身份证号
	private Character gender; // 性别 ('M' 男, 'F' 女)
	private Integer age; // 年龄
	private String phone; // 联系电话
	private String address; // 住址


}

