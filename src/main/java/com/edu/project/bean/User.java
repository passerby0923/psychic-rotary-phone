package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 用户信息实体类
 */
@Data
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 全参构造方法
@TableName("user") // 对应的数据库表名
public class User {

	@TableId("user_id") // 对应数据库中的主键字段 user_id
	private Integer userId; // 用户ID
	private String username; // 用户名
	private String password; // 密码
	private String role; // 角色
	@TableField("resident_id") // 对应数据库中的 resident_id 字段
	private Integer residentId; // 居民ID (可为空)
	@TableField("created_at") // 对应数据库中的 created_at 字段
	private Timestamp createdAt; // 创建时间
}
