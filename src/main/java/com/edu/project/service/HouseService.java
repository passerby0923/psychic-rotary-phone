package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.House;

public interface HouseService extends IService<House> {
	House findByName(String ownerName);
	boolean updateByOwnerName(String ownerName, House updatedHouse);
	boolean deleteByOwnerName(String ownerName);
}
