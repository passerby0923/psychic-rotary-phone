package com.edu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.WelfareApplication;
import com.edu.project.dao.WelfareApplicationMapper;
import com.edu.project.service.WelfareApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WelfareApplicationServiceImpl extends
		ServiceImpl<WelfareApplicationMapper, WelfareApplication>
		implements WelfareApplicationService {

	@Autowired
	private WelfareApplicationMapper welfareApplicationMapper;

	@Override
	public WelfareApplication getByApplicantName(String applicantName) {
		return this.lambdaQuery()
				.eq(WelfareApplication::getApplicantName, applicantName)
				.one(); // 返回匹配的第一条记录
	}

	@Override
	public List<WelfareApplication> getWelfareApplicationsByName(String applicantName) {
		return this.lambdaQuery()
				.eq(WelfareApplication::getApplicantName, applicantName)
				.list();
	}

	@Override
	public boolean updateApplicationStatus(String applicantName, String status) {
		UpdateWrapper<WelfareApplication> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("applicant_name", applicantName)
				.set("status", status);

		// 通过实例调用 mapper 的 update 方法
		return welfareApplicationMapper.update(null, updateWrapper) > 0; // 返回更新是否成功
	}

	@Override
	public boolean deleteWelfareApplicationByName(String applicantName) {
		// 使用 QueryWrapper 来构建查询条件
		QueryWrapper<WelfareApplication> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("applicant_name", applicantName);

		return remove(queryWrapper); // 执行删除操作
	}
}
