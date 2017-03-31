package com.xiaozan.common.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidDataSource {

	
	public DataSource dataSource() throws Exception {
		Map<String, String> properties = new HashMap<>();
		DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
		return dataSource;
	}
	
}
