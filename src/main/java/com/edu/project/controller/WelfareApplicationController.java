package com.edu.project.controller;

import com.edu.project.bean.WelfareApplication;
import com.edu.project.service.WelfareApplicationService;
import com.edu.project.util.beanutil.WelfareApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/welfare")
public class WelfareApplicationController {

	@Autowired
	private WelfareApplicationService welfareApplicationService;

	/**
	 * 获取所有福利申请
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<WelfareApplication>> listWelfareApplications() {
		List<WelfareApplication> list = welfareApplicationService.list();
		return ResponseEntity.ok(list); // 返回申请列表的 JSON 数据
	}

	/**
	 * 获取单个福利申请
	 * @param applicantName
	 * @return
	 */
	@GetMapping("/qruey/{applicantName}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<Map<String, Object>> getWelfareApplicationsByName(@PathVariable String applicantName) {
		List<WelfareApplication> welfareApplications = welfareApplicationService.getWelfareApplicationsByName(applicantName);
		Map<String, Object> response = new HashMap<>();
		if (!welfareApplications.isEmpty()) {
			response.put("message", "查询成功");
			response.put("applications", welfareApplications); // 返回查询到的申请列表
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该申请");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	/**
	 * 修改状态
	 * @param applicantName
	 * @param requestBody
	 * @return
	 */
	@PostMapping("/update/{applicantName}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<String> updateStatus(
			@PathVariable String applicantName,
			@RequestBody Map<String, String> requestBody) {
		String status = requestBody.get("status");
		boolean success = welfareApplicationService.updateApplicationStatus(applicantName, status);
		if (success) {
			return ResponseEntity.ok("状态更新成功");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到申请记录");
		}
	}


	/**
	 * 删除福利申请
	 * @param applicantName
	 * @return
	 */
	@DeleteMapping("/delete/{applicantName}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteWelfareApplication(@PathVariable String applicantName) {
		boolean isDeleted = welfareApplicationService.deleteWelfareApplicationByName(applicantName);
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
