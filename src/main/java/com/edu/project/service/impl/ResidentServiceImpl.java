package com.edu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.edu.project.bean.Resident;
import com.edu.project.dao.ResidentMapper;
import com.edu.project.service.ResidentService;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl extends
		ServiceImpl<ResidentMapper, Resident>
		implements ResidentService {

	@Override
	public Resident findByIdCard(String idCard) {
		// 使用 MyBatis-Plus 提供的 lambda 查询
		// 返回符合条件的第一个员工
		return this.lambdaQuery().eq(Resident::getIdCard, idCard).one();
	}

	@Override
	public boolean updateByIdCard(String idCard, Resident updatedResident) {
		// 使用 QueryWrapper 查找指定的居民
		QueryWrapper<Resident> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id_card", idCard); // 修改为 id_card
		// 检查居民是否存在
		if (getOne(queryWrapper) == null) {
			return false; // 如果没有找到居民，返回 false
		}
		// 确保更新的居民对象使用正确的手机号（这可能需要根据实际需求进行调整）
		updatedResident.setPhone(idCard);
		return update(updatedResident, queryWrapper); // 执行更新操作
	}

	@Override
	public boolean deleteByIdCard(String idCard) {
		// 使用 QueryWrapper 查找指定的居民
		QueryWrapper<Resident> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id_card", idCard); // 使用正确的列名 id_card

		// 调用 MyBatis-Plus 的 remove 方法进行删除
		return remove(queryWrapper); // 返回删除操作的结果
	}


}
