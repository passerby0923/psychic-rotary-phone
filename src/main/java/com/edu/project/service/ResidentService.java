package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.Employee;
import com.edu.project.bean.Resident;

public interface ResidentService extends IService<Resident> {
	Resident findByIdCard(String idCard);
	boolean updateByIdCard(String idCard,Resident updatedResident);
	boolean deleteByIdCard(String idCard);
}
