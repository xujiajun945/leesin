package com.dabanjia.leesin.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xujiajun
 * @since 2019/12/23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LeesinBaseConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeesinBaseConsumerApplication.class, args);
	}
}
