package com.dabanjia.leesin.base;

import com.dabanjia.leesin.base.configuration.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujiajun
 * @since 2019/12/23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class LeesinBaseConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeesinBaseConsumerApplication.class, args);
	}
}
