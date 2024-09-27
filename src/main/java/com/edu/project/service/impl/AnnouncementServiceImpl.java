package com.edu.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.Announcement;
import com.edu.project.dao.AnnouncementMapper;
import com.edu.project.service.AnnouncementService;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl extends
		ServiceImpl<AnnouncementMapper, Announcement>
		implements AnnouncementService {
	@Override
	public Announcement getBytitle(String title) {
		return null;
	}
}
