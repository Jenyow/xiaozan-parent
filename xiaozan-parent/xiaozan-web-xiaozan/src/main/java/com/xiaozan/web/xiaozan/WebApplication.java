package com.xiaozan.web.xiaozan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication same as: 
 * @Configuration、@ComponentScan:是spring框架的语法,在spring 3.x就有了,用于代码方式创建配置信息和扫描包
 * @EnableAutoConfiguration:是spring boot语法，表示将使用自动配置
*/
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WebApplication.class, args);
	}
}