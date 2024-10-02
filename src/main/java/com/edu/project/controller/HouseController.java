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

@CrossOrigin
@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService houseService;

	/**
	 * 创建房屋信息
	 * @param request
	 * @return
	 */
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

	/**
	 * 获取房屋信息
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<House>> listHouses() {
		List<House> list = houseService.list(); // 获取所有房屋
		return ResponseEntity.ok(list); // 返回房屋列表的 JSON 数据
	}

	/**
	 * 根据业主名字获取房屋
	 * @param ownerName
	 * @return
	 */
	@GetMapping("/byname/{ownerName}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<House> getHouseByOwnerName(@PathVariable String ownerName) {
		House house = houseService.findByName(ownerName);
		if (house != null) {
			return ResponseEntity.ok(house);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * 根据业主名更新房屋信息
	 * @param ownerName
	 * @param request
	 * @return
	 */
	@PostMapping("/updateByOwnerName")
	@ResponseBody
	public ResponseEntity<Map<String, String>> updateHouse(@RequestParam String ownerName, HttpServletRequest request) {
		House updatedHouse = HouseUtil.fromRequest(request);
		boolean isUpdated = houseService.updateByOwnerName(ownerName, updatedHouse);
		Map<String, String> response = new HashMap<>();
		if (isUpdated) {
			response.put("status", "success");
			response.put("message", "房屋更新成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("status", "error");
			response.put("message", "房屋更新失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	/**
	 * 根据业主名删除房屋
	 * @param ownerName
	 * @return
	 */
	@DeleteMapping("/delete/{ownerName}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteHouse(@PathVariable String ownerName) {
		boolean isDelHouse = houseService.deleteByOwnerName(ownerName);
		Map<String, String> response = new HashMap<>();
		if (isDelHouse) {
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