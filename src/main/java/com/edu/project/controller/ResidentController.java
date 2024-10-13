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

@CrossOrigin // 跨域请求
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
	public ResponseEntity<Map<String, String>> createResident(@RequestBody Resident resident) {
		Map<String, String> response = new HashMap<>();
		boolean save = residentService.save(resident);
		if (save) {
			response.put("message", "居民添加成功");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
	public ResponseEntity<Map<String, Object>> listResidents() {
		List<Resident> list = residentService.list(); // 获取所有居民

		Map<String, Object> response = new HashMap<>();
		response.put("code", 200); // 设置返回状态码
		response.put("message", "查询成功"); // 设置返回信息
		response.put("data", list); // 设置返回的数据

		return ResponseEntity.ok(response); // 返回带有状态码的 JSON 数据
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
			response.put("code", 200); // 状态码 200 表示查询成功
			response.put("message", "查询成功");
			response.put("resident", resident); // 将查询到的居民信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("code", 404); // 状态码 404 表示未找到
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
	public ResponseEntity<Map<String, Object>> updateResident(@RequestParam String idCard, HttpServletRequest request) {
		Resident updatedResident = ResidentUtil.fromRequest(request);
		boolean isUpdated = residentService.updateByIdCard(idCard, updatedResident);
		Map<String, Object> response = new HashMap<>();
		if (isUpdated) {
			response.put("code", 200); // 状态码 200 表示更新成功
			response.put("message", "居民更新成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("code", 400); // 状态码 400 表示请求错误
			response.put("message", "居民更新失败");
			return ResponseEntity.badRequest().body(response);
		}
	}

	/**
	 * 删除居民
	 * @param idCard
	 * @return
	 */
	@DeleteMapping("/delete/{idCard}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteResident(@PathVariable String idCard) {
		boolean isDelResident = residentService.deleteByIdCard(idCard);
		Map<String, Object> response = new HashMap<>();
		if (isDelResident) {
			response.put("code", 200); // 状态码 200 表示删除成功
			response.put("message", "删除成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("code", 400); // 状态码 400 表示请求错误
			response.put("message", "删除失败");
			return ResponseEntity.badRequest().body(response);
		}
	}
}
