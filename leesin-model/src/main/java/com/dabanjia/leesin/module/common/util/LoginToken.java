package com.dabanjia.leesin.module.common.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xujiajun
 * @since 2020/1/14
 */
@Setter
@Getter
@ToString
public class LoginToken implements Serializable {

	private static final long serialVersionUID = 8552464269672247259L;

	private String token;

	private String value;

	private Long expireTime;
}
