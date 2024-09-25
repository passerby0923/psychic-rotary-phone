package com.edu.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.Employee;
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
}
