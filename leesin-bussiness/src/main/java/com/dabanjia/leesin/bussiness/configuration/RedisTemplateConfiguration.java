package com.dabanjia.leesin.bussiness.configuration;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.dabanjia.leesin.module.common.config.StandaloneRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author xujiajun
 * @since 2020/1/13
 */
@Configuration
@ConditionalOnBean(value = {StandaloneRedisConfig.class})
public class RedisTemplateConfiguration {

	@Autowired
	private StandaloneRedisConfig standaloneRedisConfig;

	/**
	 * redis连接工厂
	 */
	@Bean(value = "redisConnectionFactory")
	public RedisConnectionFactory standaloneRedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(standaloneRedisConfig.getHost());
		redisStandaloneConfiguration.setPort(standaloneRedisConfig.getPort());
		redisStandaloneConfiguration.setPassword(standaloneRedisConfig.getPassword());
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}

	/**
	 * redisTemplate配置
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		// 配置redisTemplate
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		// 设置序列化
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();

		// key序列化
		redisTemplate.setKeySerializer(stringSerializer);
		// value序列化
		redisTemplate.setValueSerializer(fastJsonRedisSerializer);
		// Hash key序列化
		redisTemplate.setHashKeySerializer(stringSerializer);
		// Hash value序列化
		redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
