package com.edu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.House;
import com.edu.project.dao.HouseMapper;
import com.edu.project.service.HouseService;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl extends
		ServiceImpl<HouseMapper, House>
		implements HouseService {
	@Override
	public House findByName(String ownerName) {
		return this.lambdaQuery().eq(House::getOwnerName, ownerName).one();
	}

	@Override
	public boolean updateByOwnerName(String ownerName, House updatedHouse) {
		// 使用 UpdateWrapper 查找指定业主名的房屋
		UpdateWrapper<House> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("owner_name", ownerName); // 根据业主名查找房屋
		// 检查房屋是否存在
		House existingHouse = this.getOne(updateWrapper);
		if (existingHouse == null) {
			// 如果没有找到房屋，返回 false
			return false; // 或者可以选择抛出异常
		}
		// 设置要更新的字段，这里假设除了业主名之外的所有字段都可以更新
		updatedHouse.setOwnerName(existingHouse.getOwnerName());
		// 执行更新操作
		return this.update(updatedHouse, updateWrapper);
	}

	@Override
	public boolean deleteByOwnerName(String ownerName) {
		// 使用 QueryWrapper 查找指定业主名的房屋
		QueryWrapper<House> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("owner_name", ownerName); // 根据业主名查找房屋

		// 执行删除操作
		return this.remove(queryWrapper);
	}
}

