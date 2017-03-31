package com.xiaozan.common.redis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

	private @Value("${redis.hostname}") String redisHostName;
	private @Value("${redis.password}") String redisPwd;
	private @Value("${redis.port}") int redisPort;
	private @Value("${redis.pool.maxTotal}") int redisMaxTotal;
	private @Value("${redis.pool.maxIdle}") int redisMaxIdle;
	private @Value("${redis.pool.maxWaitMillis}") int redisMaxWait;
	private @Value("${redis.db.index}") int index = 1;
	private @Value("${redis.timeout}") int timeout = 20;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setUsePool(true);
		factory.setHostName(redisHostName);
		factory.setPassword(redisPwd);
		factory.setPort(redisPort);
		factory.setTimeout(timeout * 1000);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setJmxEnabled(true);
		config.setMaxTotal(redisMaxTotal);
		config.setMaxIdle(redisMaxIdle);
		config.setMaxWaitMillis(redisMaxWait);
		config.setTestOnBorrow(true);
		factory.setPoolConfig(config);
		factory.setDatabase(index);
		factory.afterPropertiesSet();
		return factory;
	}
}