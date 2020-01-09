package com.dabanjia.leesin.module.common.util;

import com.dabanjia.leesin.api.dto.UserDTO;
import com.dabanjia.vayne.core.constant.ResponseCodeEnum;
import com.dabanjia.vayne.core.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Slf4j
public class LoginUtils {

	protected void setUser(UserDTO userDTO) {
		HttpServletRequest httpServletRequest = this.getHttpServletRequest();
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute(session.getId(), userDTO);
	}

	public UserDTO getUser() {
		HttpServletRequest httpServletRequest = this.getHttpServletRequest();
		HttpSession session = httpServletRequest.getSession();
		return (UserDTO) session.getAttribute(session.getId());
	}

	protected void removeUser() {
		HttpServletRequest httpServletRequest = this.getHttpServletRequest();
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute(session.getId());
	}

	public Long getUserId() {
		return Optional.ofNullable(this.getUser()).map(UserDTO::getId).orElse(null);
	}

	private HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (servletRequestAttributes == null) {
			log.error("cannot get HttpServletRequest!");
			throw new ResponseException(ResponseCodeEnum.BAD_REQUEST);
		}
		return servletRequestAttributes.getRequest();
	}

}
