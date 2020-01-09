package com.dabanjia.leesin.base.configuration;

import com.dabanjia.leesin.util.LoginUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Configuration
public class BeanAutowireConfiguration {

	@Bean(value = "loginUtils")
	public LoginUtils loginUtils() {
		return new LoginUtils();
	}
}
