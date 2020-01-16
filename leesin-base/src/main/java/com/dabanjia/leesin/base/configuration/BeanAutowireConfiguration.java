package com.dabanjia.leesin.base.configuration;

import com.dabanjia.leesin.module.common.config.StandaloneRedisConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujiajun
 * @since 2020/1/10
 */
@Configuration
public class BeanAutowireConfiguration {

	@Bean(value = "standaloneRedisConfig")
	@ConfigurationProperties(prefix = "leesin.redis")
	public StandaloneRedisConfig standaloneRedisConfig() {
		return new StandaloneRedisConfig();
	}
}
