package com.edu.project.controller;

import com.edu.project.bean.House;
import com.edu.project.service.HouseService;
import com.edu.project.util.beanutil.HouseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService houseService;

	// 创建房屋
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> createHouse(HttpServletRequest request) {
		House house = HouseUtil.fromRequest(request);
		boolean save = houseService.save(house);
		Map<String, String> response = new HashMap<>();
		if (save) {
			response.put("message", "房屋添加成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "房屋添加失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	// 获取所有房屋
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<House>> listHouses() {
		List<House> list = houseService.list(); // 获取所有房屋
		return ResponseEntity.ok(list); // 返回房屋列表的 JSON 数据
	}

	// 根据业主名字获取房屋
	@GetMapping("/byname")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getHouseByOwnerName(@PathVariable String ownerName) {
		House house = houseService.findByName(ownerName);
		Map<String, Object> response = new HashMap<>();
		if (house != null) {
			response.put("message", "查询成功");
			response.put("house", house); // 将查询到的房屋信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该房屋");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	// 更新房屋信息
//	@PostMapping("/updateById")
//	@ResponseBody
//	public ResponseEntity<Map<String, String>> updateHouse(@RequestParam Integer houseId, HttpServletRequest request) {
//		House updatedHouse = HouseUtil.fromRequest(request);
//		boolean isUpdated = houseService.updateById(houseId, updatedHouse);
//		Map<String, String> response = new HashMap<>();
//		if (isUpdated) {
//			response.put("status", "success");
//			response.put("message", "房屋更新成功");
//			return ResponseEntity.ok(response);
//		} else {
//			response.put("status", "error");
//			response.put("message", "房屋更新失败");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//		}
//	}

	// 删除房屋
//	@PostMapping("/deleteById")
//	@ResponseBody
//	public ResponseEntity<Map<String, String>> deleteHouse(@RequestParam Integer houseId) {
//		boolean isDelHouse = houseService.deleteById(houseId);
//		Map<String, String> response = new HashMap<>();
//		if (isDelHouse) {
//			response.put("status", "success");
//			response.put("message", "删除成功");
//			return ResponseEntity.ok(response);
//		} else {
//			response.put("status", "error");
//			response.put("message", "删除失败");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//		}
//	}
}