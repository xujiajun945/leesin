package com.dabanjia.leesin.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujiajun
 * @since 2019/12/23
 */
@ServletComponentScan
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LeesinBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeesinBaseApplication.class, args);
	}
}
