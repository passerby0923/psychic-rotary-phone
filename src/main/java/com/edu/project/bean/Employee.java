package com.edu.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 员工信息实体类
 */
@Data
@NoArgsConstructor // 无参构造方法
@AllArgsConstructor // 全参构造方法
@TableName("employee") // 对应的数据库表名
public class Employee {
	@TableId(value = "employee_id", type = IdType.AUTO) // 指定数据库中的主键字段并使用 MyBatis-Plus 的自动递增策略
	private Integer employeeId; // 员工ID
	private String name; // 员工姓名
	private Integer age; // 员工年龄
	private Character gender; // 性别 ('M' 男, 'F' 女)
	private String department; // 部门
	private String position; // 职位
	@TableField("hire_date") // 对应数据库中的 hire_date 字段
	private Date hireDate; // 入职日期
	private BigDecimal salary; // 工资
	private String phone; // 联系电话
	private String email; // 电子邮件
}
