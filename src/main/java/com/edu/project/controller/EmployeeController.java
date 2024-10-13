package com.edu.project.controller;

import com.edu.project.bean.Employee;
import com.edu.project.service.EmployeeService;
import com.edu.project.util.beanutil.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin //跨域请求
@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 添加员工
	 * @param employee
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> addEmployee(@RequestBody Employee employee) {
		boolean save = employeeService.save(employee);
		Map<String, String> response = new HashMap<>();

		if (save) {
			response.put("message", "用户添加成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "用户添加失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}


	/**
	 * 获取所有员工
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Employee>> listEmployees() {
		List<Employee> list = employeeService.list();
		return ResponseEntity.ok(list); // 返回员工列表的 JSON 数据
	}

	/**
	 * 获取单个员工
	 * @param name
	 * @return
	 */
	@GetMapping("/updata/{name}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<Map<String, Object>> getEmployeeByName(@PathVariable String name) {
		Employee employee = employeeService.getEmployeeByName(name);
		Map<String, Object> response = new HashMap<>();
		if (employee != null) {
			response.put("message", "查询成功");
			response.put("employee", employee); // 将查询到的员工信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该员工");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	/**
	 * 更新员工信息
	 * @return
	 */
	@PostMapping("/updateByPhone")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateEmployee(@RequestBody Employee employee) {
		// 从 Employee 对象中获取 phone 属性
		String phone = employee.getPhone(); // 确保 Employee 类中有这个字段
		boolean isUpdated = employeeService.updateEmployeeByPhone(phone, employee);

		Map<String, String> response = new HashMap<>();
		if (isUpdated) {
			response.put("status", "success");
			response.put("message", "更新成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "更新失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}



	/**
	 * 删除员工
	 * @param phone
	 * @return
	 */
	@DeleteMapping("/delete/{phone}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String phone) {
		boolean isDeleted = employeeService.deleteEmployeeByPhone(phone);
		Map<String, String> response = new HashMap<>();
		if (isDeleted) {
			response.put("status", "success");
			response.put("message", "删除成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "删除失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
