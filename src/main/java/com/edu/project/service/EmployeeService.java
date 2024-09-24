package com.edu.project.service;

import com.edu.project.bean.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

public interface EmployeeService extends IService<Employee> {
	Employee getEmployeeByName(String name); // 根据姓名获取员工


	boolean updateEmployeeByPhone(String phone, Employee employee);//手机号获取员工信息

	boolean deleteEmployeeByPhone(String phone);//手机号删除员工信息
}
