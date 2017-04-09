package com.xiaozan.web.xiaozan;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*
 * @SpringBootApplication same as: 
 * @Configuration、@ComponentScan:是spring框架的语法,在spring 3.x就有了,用于代码方式创建配置信息和扫描包
 * @EnableAutoConfiguration:是spring boot语法，表示将使用自动配置
*/
@SpringBootApplication
/*
 * @ServletComponentScan使spring能够扫描到我们自己编写的servlet和filter
 * */
@ServletComponentScan
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(WebApplication.class);
		/*
		 * "console":控制台输出; "log":日志输出; "off":不输出。
		 */
		springApplication.setBannerMode(Mode.CONSOLE);
		springApplication.run(args);

	}
}