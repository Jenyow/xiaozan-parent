package com.xiaozan.druid.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.xiaozan.druid.util.ValidateUtil;

@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig implements EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

	private Environment environment;
	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}

	/**
	 * 注册dataSource
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() throws SQLException {

		String url = propertyResolver.getProperty("url");
		String username = propertyResolver.getProperty("username");
		String password = propertyResolver.getProperty("password");

		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			logger.info("Your database connection pool configuration is incorrect!"
					+ " Please check your Spring profile, current profiles are:"
					+ Arrays.toString(environment.getActiveProfiles()));
			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}

		String name = propertyResolver.getProperty("name");
		String driverClassName = propertyResolver.getProperty("driver-class-name");
		String initialSize = propertyResolver.getProperty("initialSize");
		String maxActive = propertyResolver.getProperty("maxActive");
		String minIdle = propertyResolver.getProperty("minIdle");
		String maxWait = propertyResolver.getProperty("maxWait");
		String poolPreparedStatements = propertyResolver.getProperty("poolPreparedStatements");
		String maxPoolPreparedStatementPerConnectionSize = propertyResolver
				.getProperty("maxPoolPreparedStatementPerConnectionSize");
		String validationQuery = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("validationQuery"),
				"select 'x'");
		String validationQueryTimeout = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("validationQueryTimeout"), "15");
		String testOnBorrow = propertyResolver.getProperty("testOnBorrow");
		String testOnReturn = propertyResolver.getProperty("testOnReturn");
		String testWhileIdle = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("testWhileIdle"),
				"true");
		String keepAlive = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("keepAlive"), "true");
		String timeBetweenEvictionRunsMillis = propertyResolver.getProperty("timeBetweenEvictionRunsMillis");
		String minEvictableIdleTimeMillis = propertyResolver.getProperty("minEvictableIdleTimeMillis");
		String filters = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("filters"),
				"stat,wall,slf4j,config");
		String useGlobalDataSourceStat = propertyResolver.getProperty("useGlobalDataSourceStat");

		String timeBetweenLogStatsMillis = propertyResolver.getProperty("timeBetweenLogStatsMillis");
		String removeAbandoned = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("removeAbandoned"),
				"false");
		String removeAbandonedTimeout = propertyResolver.getProperty("removeAbandonedTimeout");
		String logAbandoned = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("logAbandoned"), "true");
		String decrypt = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("config.decrypt"), "false");
		String key = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("config.decrypt.key"), "");
		String mergeSql = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("druid.stat.mergeSql"),
				"true");
		String logSlowSql = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("druid.stat.logSlowSql"),
				"true");
		String slowSqlMillis = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.stat.slowSqlMillis"), "5000");

		DruidDataSource druidDataSource = new DruidDataSource();
		if (!StringUtils.isEmpty(name)) {
			druidDataSource.setName(name);
		}
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		if (!StringUtils.isEmpty(driverClassName)) {
			druidDataSource.setDriverClassName(driverClassName);
		}
		if (!StringUtils.isEmpty(initialSize)) {
			druidDataSource.setInitialSize(Integer.parseInt(initialSize));
		}
		if (!StringUtils.isEmpty(maxActive)) {
			druidDataSource.setMaxActive(Integer.parseInt(maxActive));
		}
		if (!StringUtils.isEmpty(minIdle)) {
			druidDataSource.setMinIdle(Integer.parseInt(minIdle));
		}
		if (!StringUtils.isEmpty(maxWait)) {
			druidDataSource.setMaxWait(Integer.parseInt(maxWait));
		}
		if (!StringUtils.isEmpty(poolPreparedStatements)) {
			// 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
			// 默认false
			druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(poolPreparedStatements));
		}
		if (!StringUtils.isEmpty(maxPoolPreparedStatementPerConnectionSize)) {
			// 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
			// 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
			druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(
					Integer.parseInt(maxPoolPreparedStatementPerConnectionSize));
		}
		druidDataSource.setValidationQuery(validationQuery);
		druidDataSource.setValidationQueryTimeout(Integer.parseInt(validationQueryTimeout));
		if (!StringUtils.isEmpty(testOnBorrow)) {
			druidDataSource.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		}
		if (!StringUtils.isEmpty(testOnReturn)) {
			druidDataSource.setTestOnReturn(Boolean.parseBoolean(testOnReturn));
		}
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(testWhileIdle));
		druidDataSource.setKeepAlive(Boolean.parseBoolean(keepAlive));
		if (!StringUtils.isEmpty(timeBetweenEvictionRunsMillis)) {
			druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
		}

		if (!StringUtils.isEmpty(minEvictableIdleTimeMillis)) {
			druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(minEvictableIdleTimeMillis));
		}
		druidDataSource.setFilters(filters);
		if (!StringUtils.isEmpty(useGlobalDataSourceStat)) {
			// 默认false
			druidDataSource.setUseGlobalDataSourceStat(Boolean.parseBoolean(useGlobalDataSourceStat));
		}
		if (!StringUtils.isEmpty(timeBetweenLogStatsMillis)) {
			// 当useGlobalDataSourceStat设置为true,不能设置该属性
			druidDataSource.setTimeBetweenLogStatsMillis(Integer.parseInt(timeBetweenLogStatsMillis));
		}
		druidDataSource.setRemoveAbandoned(Boolean.parseBoolean(removeAbandoned));
		if (!StringUtils.isEmpty(removeAbandonedTimeout)) {
			druidDataSource.setRemoveAbandonedTimeout(Integer.parseInt(removeAbandonedTimeout));
		}
		druidDataSource.setLogAbandoned(Boolean.parseBoolean(logAbandoned));

		Properties properties = new Properties();
		properties.setProperty("config.decrypt", decrypt);
		properties.setProperty("config.decrypt.key", key);
		properties.setProperty("druid.stat.mergeSql", mergeSql);
		properties.setProperty("druid.stat.logSlowSql", logSlowSql);
		properties.setProperty("druid.stat.slowSqlMillis", slowSqlMillis);
		druidDataSource.setConnectProperties(properties);

		return druidDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * 配置druid监控 Servlet
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean DruidStatViewServle() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");

		String allow = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("druid.servlet.allow"), "");
		String deny = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("druid.servlet.deny"), "");
		String loginUsername = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.servlet.loginUsername"), "admin");
		String loginPassword = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.servlet.loginPassword"), "admin1234");
		String resetEnable = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.servlet.resetEnable"), "false");

		servletRegistrationBean.addInitParameter("allow", allow);
		servletRegistrationBean.addInitParameter("deny", deny);
		servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
		servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
		servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
		return servletRegistrationBean;
	}

	/**
	 * 配置 druid监控 Filter
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");

		String exclusions = ValidateUtil.getValueOrDefaultValue(propertyResolver.getProperty("druid.filter.exclusions"),
				"'*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*'");
		String sessionStatMaxCount = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.filter.sessionStatMaxCount"), "1000");
		String sessionStatEnable = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.filter.sessionStatEnable"), "true");
		String principalSessionName = ValidateUtil.getValueOrDefaultValue(
				propertyResolver.getProperty("druid.filter.principalSessionName"), "user.username");
		String profileEnable = ValidateUtil
				.getValueOrDefaultValue(propertyResolver.getProperty("druid.filter.profileEnable"), "true");

		filterRegistrationBean.addInitParameter("exclusions", exclusions);
		filterRegistrationBean.addInitParameter("sessionStatMaxCount", sessionStatMaxCount);
		filterRegistrationBean.addInitParameter("sessionStatEnable", sessionStatEnable);
		filterRegistrationBean.addInitParameter("principalSessionName", principalSessionName);
		filterRegistrationBean.addInitParameter("profileEnable", profileEnable);
		return filterRegistrationBean;

	}
}