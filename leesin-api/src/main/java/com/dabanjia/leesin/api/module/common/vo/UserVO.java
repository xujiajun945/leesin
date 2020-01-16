package com.dabanjia.leesin.api.module.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xujiajun
 * @since 2019/12/25
 */
@Setter
@Getter
@ToString
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1113239436863504276L;

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
