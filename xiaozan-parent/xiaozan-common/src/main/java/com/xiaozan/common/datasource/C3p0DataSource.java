package com.xiaozan.common.datasource;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0DataSource {

	public static DataSource initDataSource() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(""); // 用户名
		dataSource.setPassword(""); // 密码
		dataSource.setInitialPoolSize(5); // 初始化连接池大小
		dataSource.setMinPoolSize(5); // 最少连接数
		dataSource.setMaxPoolSize(100); // 最大连接数
		dataSource.setMaxIdleTime(720);// 最大空闲时间,720秒内未使用则连接被丢弃。若为0则永不丢弃。Default:0
		dataSource.setAcquireIncrement(5); // 当连接池中的连接用完时，C3P0一次性创建新连接的数目
		dataSource.setIdleConnectionTestPeriod(360);// 测试连接有效的时间间隔
		dataSource.setAcquireRetryAttempts(30);//定义在从数据库获取新连接失败后重复尝试获取的次数，默认为30
		dataSource.setAcquireRetryDelay(1000);//两次连接中间隔时间，单位毫秒，默认为1000；
		dataSource.setBreakAfterAcquireFailure(false);
		dataSource.setTestConnectionOnCheckout(false);// 每次连接验证连接是否可用
		dataSource.setForceIgnoreUnresolvedTransactions(false);
		dataSource.setTestConnectionOnCheckin(true);
		dataSource.setNumHelperThreads(5);
		dataSource.setCheckoutTimeout(30000);
		// dataSource.setPreferredTestQuery("select count(1) from
		// P_NBSC_RES_AVAIL");
		return dataSource;
	}
}
