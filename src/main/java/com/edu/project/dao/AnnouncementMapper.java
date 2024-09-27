package com.edu.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.project.bean.Announcement;
import com.edu.project.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
}
