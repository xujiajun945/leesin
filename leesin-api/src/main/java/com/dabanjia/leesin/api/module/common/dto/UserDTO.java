package com.dabanjia.leesin.api.module.common.dto;

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
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -174334341752145737L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 姓名
	 */
	private String name;
}
