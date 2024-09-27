package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.Announcement;
import com.edu.project.bean.Employee;

import java.util.List;

public interface AnnouncementService extends IService<Announcement> {
	List<Announcement> getBytitle(String title);

	boolean updateBytitle(Announcement updatedAnnouncement);

	boolean removeBytitle(String title);

}
