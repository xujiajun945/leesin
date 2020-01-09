package com.dabanjia.leesin.module.common.exception;

import com.dabanjia.leesin.module.base.constant.LeesinBaseResultCodeEnum;
import com.dabanjia.vayne.core.constant.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Setter
@Getter
@ToString
public class PermissionException extends RuntimeException {

	private static final long serialVersionUID = -7950879217053249837L;

	private int code;

	public PermissionException() {
		super();
	}

	public PermissionException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public PermissionException(ResponseCodeEnum codeEnum) {
		super(codeEnum.getMessage());
		this.code = codeEnum.getCode();
	}

	public PermissionException(LeesinBaseResultCodeEnum codeEnum) {
		super(codeEnum.getMessage());
		this.code = codeEnum.getCode();
	}
}
