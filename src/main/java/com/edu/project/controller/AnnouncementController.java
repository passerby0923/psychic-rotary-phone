package com.edu.project.controller;

import com.edu.project.bean.Announcement;
import com.edu.project.service.AnnouncementService;
import com.edu.project.util.beanutil.AnnouncementUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080") // 指定前端的源
@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	/**
	 * 创建公告
	 * @param announcement
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, String>> addAnnouncement(@RequestBody Announcement announcement) {
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


	/**
	 * 获取所有公告
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Announcement>> listAnnouncements() {
		List<Announcement> list = announcementService.list();
		System.out.println("Fetched announcements: " + list);
		return ResponseEntity.ok(list);
	}


	/**
	 * 获取单个公告
	 * @param title
	 * @return
	 */
	@GetMapping("/query/{title}")
	@ResponseBody // 返回值自动转换为 JSON
	public ResponseEntity<Map<String, Object>> getAnnouncementById(@PathVariable String title) {
		List<Announcement> announcements = announcementService.getBytitle(title);
		Map<String, Object> response = new HashMap<>();
		if (!announcements.isEmpty()) {
			response.put("message", "查询成功");
			response.put("announcements", announcements); // 将查询到的公告列表返回
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "未找到该公告");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}


	/**
	 * 更新公告信息
	 * @param announcement
	 * @return
	 */

	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity updateAnnouncement(@RequestBody Announcement announcement) {
		boolean isUpdated = announcementService.updateBytitle(announcement);
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
	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/**
	 * 删除公告
	 * @param title
	 * @return
	 */
	@DeleteMapping("/delete/{title}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> deleteAnnouncement(@PathVariable String title) {
		boolean isDeleted = announcementService.removeBytitle(title);
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
