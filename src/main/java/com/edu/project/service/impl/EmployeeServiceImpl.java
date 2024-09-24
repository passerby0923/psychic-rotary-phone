package com.edu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.Employee;
import com.edu.project.dao.EmployeeMapper;
import com.edu.project.service.EmployeeService; // 导入接口
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl
		extends ServiceImpl<EmployeeMapper, Employee>
		implements EmployeeService {
	@Override
	public Employee getEmployeeByName(String name) {
		// 使用 MyBatis-Plus 提供的 lambda 查询
		// 返回符合条件的第一个员工
		return this.lambdaQuery().eq(Employee::getName, name).one();
	}


	@Override
	public boolean updateEmployeeByPhone(String phone, Employee employee) {
		// 使用 QueryWrapper 查找指定手机号的员工
		QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone); // 根据手机号查找员工

		// 检查员工是否存在
		if (getOne(queryWrapper) == null) {
			// 如果没有找到员工，返回 false 或抛出异常
			return false; // 或者可以选择抛出异常
		}

		// 确保更新的员工对象使用正确的手机号
		employee.setPhone(phone);
		return update(employee, queryWrapper); // 执行更新操作
	}


	@Override
	public boolean deleteEmployeeByPhone(String phone) {
		//使用 MyBatis-Plus 的 QueryWrapper 类来构建查询条件，以便根据员工的手机号查找特定的员工信息
		QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", phone);

		return remove(queryWrapper);
	}


}
