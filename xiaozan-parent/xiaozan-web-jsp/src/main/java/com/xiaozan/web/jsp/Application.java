package com.xiaozan.web.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.xiaozan.web.jsp.job.JobTest;

/*
 * @SpringBootApplication same as: 
 * @Configuration、@ComponentScan:是spring框架的语法,在spring 3.x就有了,用于代码方式创建配置信息和扫描包
 * @EnableAutoConfiguration:是spring boot语法，表示将使用自动配置
*/
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

	@Bean
	public JobTest geJobTest() {
		return new JobTest();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}