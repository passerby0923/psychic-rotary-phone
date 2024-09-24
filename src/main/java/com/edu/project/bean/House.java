package com.edu.project.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 房屋信息
 */
@Data
@NoArgsConstructor//无参的构造方法
@AllArgsConstructor //全参的构造方法
public class House {
	private Integer houseId; // 房屋ID
	private String address; // 房屋地址
	private Integer ownerId; // 业主ID（居民）
	private String houseType; // 房屋类型（住宅、商业等）
	private BigDecimal area; // 房屋面积
	private Integer buildYear; // 建造年份

}
