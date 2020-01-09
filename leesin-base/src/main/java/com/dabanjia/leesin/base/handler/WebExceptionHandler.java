package com.dabanjia.leesin.base.handler;

import com.dabanjia.leesin.module.common.exception.PermissionException;
import com.dabanjia.vayne.core.entity.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xujiajun
 * @since 2019/12/23
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionHandler {

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = PermissionException.class)
	public ResponseData handlePermissionException(PermissionException exception) {
		log.error("permission error:", exception.getMessage());
		return new ResponseData(exception.getCode(), exception.getMessage());
	}
}
