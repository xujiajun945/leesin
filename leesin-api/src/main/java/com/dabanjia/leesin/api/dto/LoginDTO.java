package com.dabanjia.leesin.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Setter
@Getter
@ToString
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = -4867819126775937708L;

	/**
	 * 用户phone
	 */
	private String phone;

	/**
	 * 密码
	 */
	private String password;
}
