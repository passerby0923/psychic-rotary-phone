package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 居民信息
 */
import com.baomidou.mybatisplus.annotation.TableField;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("resident")
public class Resident {
	@TableId(value = "resident_id", type = IdType.AUTO)
	private Integer residentId; // 居民ID
	private String name; // 居民姓名

	@TableField("id_card") // 指定数据库中的列名
	private String idCard; // 身份证号

	private Character gender; // 性别 ('M' 男, 'F' 女)
	private Integer age; // 年龄
	private String phone; // 联系电话
	private String address; // 住址
}
