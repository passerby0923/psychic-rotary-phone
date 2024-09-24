package com.edu.project.controller;

import com.edu.project.bean.Employee;
import com.edu.project.service.EmployeeService;
import com.edu.project.util.EmployeeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// 创建员工
	@PostMapping("/add")
	@ResponseBody
	public String addEmployee(HttpServletRequest request) {
		Employee employee = EmployeeUtil.fromRequest(request);
		boolean save = employeeService.save(employee);
		if (save) {
			return "用户添加成功";
		} else {
			return "用户添加失败";
		}
	}

	// 获取所有员工
	@GetMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> list = employeeService.list();
		model.addAttribute("employees", list);
		return "employee/list"; // 返回员工列表视图
	}

	// 获取单个员工
	@GetMapping("/{name}")
	@ResponseBody // 返回值自动转换为 JSON
	public Map<String, Object> getEmployeeByName(@PathVariable String name) {
		// 获取员工信息
		Employee employee = employeeService.getEmployeeByName(name);
		Map<String, Object> response = new HashMap<>();
		if (employee != null) {
			response.put("message", "查询成功");
			response.put("employee", employee); // 将查询到的员工信息返回
		} else {
			response.put("message", "未找到该员工");
		}
		return response;
	}



	// 更新员工信息
	@PostMapping("/updateByPhone")
	public String updateEmployee(@RequestParam String phone, HttpServletRequest request) {
		// 使用工具类从请求中获取员工信息
		Employee updatedEmployee = EmployeeUtil.fromRequest(request);

		// 调用服务方法更新员工
		boolean isUpdated = employeeService.updateEmployeeByPhone(phone, updatedEmployee);

		if (isUpdated) {
			return "redirect:/employees/list"; // 更新成功，重定向到员工列表
		} else {
			return "error"; // 更新失败，返回错误页面
		}
	}


	// 删除员工
	@PostMapping("/deleteByPhone")
	public String deleteEmployee(@RequestParam String phone) {
		boolean isDeleted = employeeService.deleteEmployeeByPhone(phone);

		if (isDeleted) {
			return "redirect:/employees/list"; // 删除成功，重定向到员工列表
		} else {
			return "error"; // 删除失败，返回错误页面
		}
	}

}
