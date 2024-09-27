package com.edu.project.controller;

import com.edu.project.bean.Announcement;
import com.edu.project.service.AnnouncementService;
import com.edu.project.util.beanutil.AnnouncementUtil;
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
@RequestMapping("/announcements")
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	// 创建公告
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> addAnnouncement(HttpServletRequest request) {
		Announcement announcement = AnnouncementUtil.fromRequest(request);
		boolean save = announcementService.save(announcement);
		Map<String, String> response = new HashMap<>();
		if (save) {
			response.put("message", "公告添加成功");
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "公告添加失败");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	// 获取所有公告
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Announcement>> listAnnouncements() {
		List<Announcement> list = announcementService.list();
		System.out.println("Fetched announcements: " + list);
		return ResponseEntity.ok(list);
	}


	// 获取单个公告
	@GetMapping("/{title}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<Map<String, Object>> getAnnouncementById(@PathVariable String title) {
		Announcement announcement = announcementService.getBytitle(title);
		Map<String, Object> response = new HashMap<>();
		if (announcement != null) {
			response.put("message", "查询成功");
			response.put("announcement", announcement); // 将查询到的公告信息返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该公告");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	// 更新公告信息
//	@PostMapping("/update")
//	@ResponseBody
//	public ResponseEntity<Map<String, String>> updateAnnouncement(@PathVariable String title, HttpServletRequest request) {
//		Announcement updatedAnnouncement = AnnouncementUtil.fromRequest(request);
//		boolean isUpdated = announcementService.updateBytitle(title, updatedAnnouncement);
//		Map<String, String> response = new HashMap<>();
//		if (isUpdated) {
//			response.put("status", "success");
//			response.put("message", "更新成功");
//			return ResponseEntity.ok(response);
//		} else {
//			response.put("status", "error");
//			response.put("message", "更新失败");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//		}
//	}

	// 删除公告
//	@DeleteMapping("/delete/{id}")
//	@ResponseBody
//	public ResponseEntity<Map<String, String>> deleteAnnouncement(@PathVariable String title) {
//		boolean isDeleted = announcementService.removeBytitle(title);
//		Map<String, String> response = new HashMap<>();
//		if (isDeleted) {
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
