package com.xiaozan.common.redis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	public static RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	private static JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setUsePool(true);
		factory.setHostName("127.0.0.1");
		factory.setPassword("");
		factory.setPort(6379);
		JedisPoolConfig config = new JedisPoolConfig();
		config.setJmxEnabled(true);
		config.setMaxTotal(10);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(60000);
		config.setTestOnBorrow(true);
		factory.setPoolConfig(config);
		factory.setDatabase(0);// 0-15
		factory.afterPropertiesSet();
		return factory;
	}

}
