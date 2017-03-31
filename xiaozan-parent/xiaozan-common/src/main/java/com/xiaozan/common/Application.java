package com.xiaozan.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
public class Application {

	/*
	@Bean
	public JobTest geJobTest() {
		return new JobTest();
	}
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
//		SpringApplication.run(Application.class, args);
//		System.exit(SpringApplication.exit(SpringApplication.run(BatchConfiguration.class, args)));
	}
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(){
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}