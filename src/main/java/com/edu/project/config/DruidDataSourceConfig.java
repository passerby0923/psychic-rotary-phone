package com.edu.project.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Druid配置类
 * 数据源头更换成Druid
 */
@Configuration
public class DruidDataSourceConfig {
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}
}
