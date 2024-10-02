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
 * 居民福利申请信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("welfare_application")
public class WelfareApplication {
	@TableId(value = "application_id", type = IdType.AUTO)
	private Integer applicationId; // 申请ID
	private String applicantName; // 申请人姓名
	private String welfareType; // 福利类型
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date applyDate; // 申请日期
	private String status; // 申请状态
	private String comments; // 备注
	private String filePath; // 文件存储路径
}
