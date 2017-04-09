package com.xiaozan.web.xiaozan.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageInterceptor;

@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.xiaozan.web.xiaozan.mapper")
public class DruidDataSourceConfig implements EnvironmentAware {

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
		if (StringUtils.isEmpty(propertyResolver.getProperty("url"))) {
			System.out.println("Your database connection pool configuration is incorrect!"
					+ " Please check your Spring profile, current profiles are:"
					+ Arrays.toString(environment.getActiveProfiles()));
			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setName(propertyResolver.getProperty("name"));
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));
		druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));

		druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));
		druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));
		druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));
		druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait")));

		druidDataSource.setPoolPreparedStatements(
				Boolean.parseBoolean(propertyResolver.getProperty("poolPreparedStatements")));
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));

		druidDataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
		druidDataSource
				.setValidationQueryTimeout(Integer.parseInt(propertyResolver.getProperty("validationQueryTimeout")));
		druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow")));
		druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty("testOnReturn")));
		druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty("testWhileIdle")));
		druidDataSource.setKeepAlive(Boolean.parseBoolean(propertyResolver.getProperty("keepAlive")));

		druidDataSource.setTimeBetweenEvictionRunsMillis(
				Long.parseLong(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
		druidDataSource.setMinEvictableIdleTimeMillis(
				Long.parseLong(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		druidDataSource.setFilters(propertyResolver.getProperty("filters"));

		druidDataSource.setUseGlobalDataSourceStat(
				Boolean.parseBoolean(propertyResolver.getProperty("useGlobalDataSourceStat")));

		// druidDataSource.setTimeBetweenLogStatsMillis(Integer.parseInt(propertyResolver.getProperty("timeBetweenLogStatsMillis")));

		druidDataSource.setRemoveAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("removeAbandoned")));
		druidDataSource
				.setRemoveAbandonedTimeout(Integer.parseInt(propertyResolver.getProperty("removeAbandonedTimeout")));
		druidDataSource.setLogAbandoned(Boolean.parseBoolean(propertyResolver.getProperty("logAbandoned")));

		Properties properties = new Properties();
		properties.setProperty("config.decrypt", propertyResolver.getProperty("config.decrypt"));
		properties.setProperty("config.decrypt.key", propertyResolver.getProperty("config.decrypt.key"));

		properties.setProperty("druid.stat.mergeSql", propertyResolver.getProperty("druid.stat.mergeSql"));
		properties.setProperty("druid.stat.logSlowSql", propertyResolver.getProperty("druid.stat.logSlowSql"));
		properties.setProperty("druid.stat.slowSqlMillis", propertyResolver.getProperty("druid.stat.slowSqlMillis"));
		druidDataSource.setConnectProperties(properties);
		return druidDataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		// mybatis分页
		PageInterceptor pageHelper = new PageInterceptor();
		Properties props = new Properties();
		props.setProperty("reasonable", "true");
		props.setProperty("supportMethodsArguments", "true");
		props.setProperty("returnPageInfo", "check");
		props.setProperty("params", "count=countSql");
		pageHelper.setProperties(props); // 添加插件
		Interceptor[] plugins = new Interceptor[] { pageHelper };
		sqlSessionFactoryBean.setPlugins(plugins);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean
				.setMapperLocations(resolver.getResources("classpath:com/xiaozan/web/xiaozan/sqlmap/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * 配置druid Servlet
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean DruidStatViewServle() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		servletRegistrationBean.addInitParameter("allow", propertyResolver.getProperty("druid.servlet.allow"));
		servletRegistrationBean.addInitParameter("deny", propertyResolver.getProperty("druid.servlet.deny"));
		servletRegistrationBean.addInitParameter("loginUsername",
				propertyResolver.getProperty("druid.servlet.loginUsername"));
		servletRegistrationBean.addInitParameter("loginPassword",
				propertyResolver.getProperty("druid.servlet.loginPassword"));
		servletRegistrationBean.addInitParameter("resetEnable",
				propertyResolver.getProperty("druid.servlet.resetEnable"));
		return servletRegistrationBean;
	}

	/**
	 * 配置 druid Filter
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", propertyResolver.getProperty("druid.filter.exclusions"));
		filterRegistrationBean.addInitParameter("sessionStatMaxCount",
				propertyResolver.getProperty("druid.filter.sessionStatMaxCount"));
		filterRegistrationBean.addInitParameter("sessionStatEnable",
				propertyResolver.getProperty("druid.filter.sessionStatEnable"));
		filterRegistrationBean.addInitParameter("principalSessionName",
				propertyResolver.getProperty("druid.filter.principalSessionName"));
		filterRegistrationBean.addInitParameter("profileEnable",
				propertyResolver.getProperty("druid.filter.profileEnable"));
		return filterRegistrationBean;

	}
}