package com.edu.project.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *  居民福利申请信息
 */
@Data
@NoArgsConstructor//无参的构造方法
@AllArgsConstructor //全参的构造方法
public class WelfareApplication {
	private Integer applicationId; // 申请ID
	private Integer residentId; // 居民ID
	private String welfareType; // 福利类型
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date applyDate; // 申请日期
	private String status; // 申请状态
	private String comments; // 备注
	private String filePath; // 文件存储路径

}

