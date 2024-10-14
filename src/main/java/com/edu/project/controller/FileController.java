package com.edu.project.controller;

import com.edu.project.bean.WelfareApplication;
import com.edu.project.service.WelfareApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin // 跨域请求
@Controller
@Slf4j
@RequestMapping("/feil")
public class FileController {

	@Autowired
	private WelfareApplicationService welfareApplicationService;

	/**
	 *
	 * 文件上传功能
	 *
	 * @param applicantName
	 * @param welfareType
	 * @param applyDate
	 * @param status
	 * @param comments
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@PostMapping("/add")
	@ResponseBody
	public String upload(@RequestParam("applicantName") String applicantName,
	                     @RequestParam("welfareType") String welfareType,
	                     @RequestParam("applyDate") String applyDate,
	                     @RequestParam("status") String status,
	                     @RequestParam("comments") String comments,
	                     @RequestParam("file") MultipartFile file)
			throws IOException, ParseException {

		// 文件保存路径
		String path = ResourceUtils.getURL("resources").getPath();
		File fileDir = new File(path + "/stasicfile");

		// 创建目录
		if (!fileDir.exists() && !fileDir.mkdirs()) {
			log.error("文件目录创建失败");
			return "文件目录创建失败";
		}

		// 保存文件
		String filePath = saveFile(file, fileDir);
		if (filePath == null) {
			return "文件上传失败";
		}

		// 创建并保存福利申请对象
		WelfareApplication welfareApplication = new WelfareApplication();
		welfareApplication.setApplicantName(applicantName);
		welfareApplication.setWelfareType(welfareType);
		welfareApplication.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(applyDate));
		welfareApplication.setStatus(status);
		welfareApplication.setComments(comments);
		welfareApplication.setFilePath(filePath);
		boolean saveSuccess = welfareApplicationService.save(welfareApplication);
		if (saveSuccess) {
			return "注册用户成功/文件上传成功";
		} else {
			return "保存申请信息失败";
		}
	}

	/**
	 * 文件下载功能
	 * @param applicantName
	 * @return
	 */
	@GetMapping("/downloadByApplicant/{applicantName}")
	public ResponseEntity<Map<String,String>> downloadFileByApplicant(@PathVariable String applicantName) {
		log.info("收到下载请求，申请人姓名: {}", applicantName);

		// 根据申请人姓名查询福利申请信息
		WelfareApplication welfareApplication = welfareApplicationService.getByApplicantName(applicantName);

		// 检查是否找到对应的申请
		if (welfareApplication == null) {
			log.warn("未找到申请信息，申请人姓名: {}", applicantName);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		// 获取文件路径
		String filePath = welfareApplication.getFilePath();
		if (filePath == null) {
			log.warn("申请信息中未找到文件路径，申请人姓名: {}", applicantName);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		File file = new File(filePath);

		// 检查文件是否存在
		if (!file.exists()) {
			log.warn("文件不存在，文件路径: {}", filePath);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		Resource resource = new FileSystemResource(file);
		String contentType = "application/octet-stream"; // 可根据文件类型进行调整

		// 获取文件扩展名
		String fileName = file.getName();
		String fileExtension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")+1) : "";
		System.out.println("文件扩展名"+fileExtension);
		// 设置响应头
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
		headers.add("fileExtension", fileExtension); // 添加扩展名信息到响应头

		Map<String, String> response = new HashMap<>();
		response.put("extension", fileExtension);
		log.info("文件下载成功，文件名: {}", fileName);

		return ResponseEntity.status(HttpStatus.OK).body(response);


	}


	/**
	 * 文件路径
	 * @param file
	 * @param fileDir
	 * @return
	 * @throws IOException
	 */
	private String saveFile(MultipartFile file, File fileDir) throws IOException {
		if (file.isEmpty()) {
			return null;
		}
		// 获取文件原始名称
		String originalFilename = file.getOriginalFilename();
		// 生成唯一的文件名
		String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
		// 保存文件
		File saveFile = new File(fileDir, uniqueFilename);
		file.transferTo(saveFile);
		log.info("文件已保存至: {}", saveFile.getAbsolutePath());
		return "resources/stasicfile/" + uniqueFilename; // 返回相对路径
	}
}
