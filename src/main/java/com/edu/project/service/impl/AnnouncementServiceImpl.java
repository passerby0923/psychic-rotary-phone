package com.edu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.project.bean.Announcement;
import com.edu.project.bean.Employee;
import com.edu.project.dao.AnnouncementMapper;
import com.edu.project.service.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl extends
		ServiceImpl<AnnouncementMapper, Announcement>
		implements AnnouncementService {
	@Override
	public List<Announcement> getBytitle(String title) {
		return this.lambdaQuery().eq(Announcement::getTitle, title).list();
	}

	@Override
	public boolean updateBytitle(Announcement updatedAnnouncement) {
		QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("title", updatedAnnouncement.getTitle());

		updatedAnnouncement.setTitle(updatedAnnouncement.getTitle());
		return update(updatedAnnouncement, queryWrapper);
	}

	@Override
	public boolean removeBytitle(String title) {
		//使用 MyBatis-Plus 的 QueryWrapper 类来构建查询条件，以便根据员工的手机号查找特定的员工信息
		QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("title", title);

		return remove(queryWrapper);
	}


}
