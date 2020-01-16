package com.dabanjia.leesin.module.common.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xujiajun
 * @since 2020/1/13
 */
@Setter
@Getter
@ToString
public class StandaloneRedisConfig {

	/**
	 * host
	 */
	private String host;

	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 密码
	 */
	private String password;
}
