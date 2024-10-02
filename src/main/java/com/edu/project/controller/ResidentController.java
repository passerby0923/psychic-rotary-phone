package com.edu.project.controller;

import com.edu.project.bean.Resident;
import com.edu.project.service.ResidentService;
import com.edu.project.util.beanutil.ResidentUtil;
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
@RequestMapping("/resident")
public class ResidentController {

	@Autowired
	private ResidentService residentService;

	/**
	 * 创建居民
	 * @param request
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> createResident(HttpServletRequest request) {
		Resident resident = ResidentUtil.fromRequest(request);
		boolean save = residentService.save(resident);
		Map<String, String> response = new HashMap<>();
		if (save) {
			response.put("message", "居民添加成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "居民添加失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	/**
	 * 获取所有居民
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Resident>> listResidents() {
		List<Resident> list = residentService.list(); // 获取所有居民
		return ResponseEntity.ok(list); // 返回居民列表的 JSON 数据
	}

	/**
	 * 根据身份证号获取居民
	 * @param idCard
	 * @return
	 */
	@GetMapping("/updata/{idCard}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getResidentByIdCard(@PathVariable String idCard) {
		Resident resident = residentService.findByIdCard(idCard);
		Map<String, Object> response = new HashMap<>();
		if (resident != null) {
			response.put("message", "查询成功");
			response.put("resident", resident); // 将查询到的居民信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该居民");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	/**
	 * 更新居民信息
	 * @param idCard
	 * @param request
	 * @return
	 */
	@PostMapping("/updateByIdCard")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateResident(@RequestParam String idCard, HttpServletRequest request) {
		Resident updatedResident = ResidentUtil.fromRequest(request);
		boolean isUpdated = residentService.updateByIdCard(idCard, updatedResident);
		Map<String, String> response = new HashMap<>();
		if (isUpdated) {
			response.put("status", "success");
			response.put("message", "居民更新成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "居民更新失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	/**
	 * 删除居民
	 * @param idCard
	 * @return
	 */
	@DeleteMapping("/delete/{idCard}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteResident(@PathVariable String idCard) {
		boolean isDelResident = residentService.deleteByIdCard(idCard);
		Map<String, String> response = new HashMap<>();
		if (isDelResident) {
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
