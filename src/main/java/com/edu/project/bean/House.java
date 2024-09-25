package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 全参构造方法
public class House {
	@TableId(value = "house_id", type = IdType.AUTO) // 指定数据库中的主键字段并使用 MyBatis-Plus 的自动递增策略
	private Integer houseId; // 房屋ID
	private String address; // 房屋地址
	private String ownerName; // 业主名
	private String houseType; // 房屋类型（住宅、商业等）
	private BigDecimal area; // 房屋面积
	private Integer buildYear; // 建造年份
}
