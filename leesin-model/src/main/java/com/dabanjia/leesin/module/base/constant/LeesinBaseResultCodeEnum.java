package com.dabanjia.leesin.module.base.constant;

import lombok.Getter;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Getter
public enum LeesinBaseResultCodeEnum {

	/** 用户权限相关 */
	USER_NOT_EXISTS(10000, "用户不存在"),
	USER_DELETED(10001, "用户被冻结或已删除"),
	PASSWORD_ERROR(10002, "密码错误");

	private Integer code;

	private String message;

	LeesinBaseResultCodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
