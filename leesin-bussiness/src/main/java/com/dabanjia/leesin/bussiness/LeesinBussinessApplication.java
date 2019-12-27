package com.dabanjia.leesin.bussiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujiajun
 * @since 2019/12/27
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LeesinBussinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeesinBussinessApplication.class, args);
	}
}
