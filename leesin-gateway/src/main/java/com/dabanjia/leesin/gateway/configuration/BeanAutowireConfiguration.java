package com.dabanjia.leesin.gateway.configuration;

import com.dabanjia.leesin.module.common.config.StandaloneRedisConfig;
import com.dabanjia.leesin.module.common.util.LoginUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujiajun
 * @since 2020/1/10
 */
@Configuration
public class BeanAutowireConfiguration {

	@Bean(value = "loginUtils")
	public LoginUtils loginUtils() {
		return new LoginUtils();
	}

	@Bean(value = "standaloneRedisConfig")
	@ConfigurationProperties(prefix = "gateway.redis")
	public StandaloneRedisConfig standaloneRedisConfig() {
		return new StandaloneRedisConfig();
	}
}
