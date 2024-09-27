package com.edu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.project.bean.Announcement;

public interface AnnouncementService extends IService<Announcement> {
	Announcement getBytitle(String title);

}
