package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.User;
import com.edu.project.bean.WelfareApplication;

import java.util.List;

public interface WelfareApplicationService extends IService<WelfareApplication> {
	WelfareApplication getByApplicantName(String applicantName);

	List<WelfareApplication> getWelfareApplicationsByName(String applicantName);

	boolean updateApplicationStatus(String applicantName,String status);

	boolean deleteWelfareApplicationByName(String applicantName);
}
