package com.dabanjia.leesin.module.common.util;

import com.alibaba.fastjson.JSON;
import com.dabanjia.leesin.api.module.common.dto.UserDTO;
import com.dabanjia.vayne.core.constant.ResultCode;
import com.dabanjia.vayne.core.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Slf4j
public class LoginUtils {

	@Value(value = "${sso.cookie-name}")
	private String cookieName;

	@Value(value = "${sso.expire-time}")
	private Long expireTime;

	@Value(value = "${sso.domain}")
	private String domain;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	protected void setUser(UserDTO userDTO) {
		ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();

		String sessionKey = this.createCookie();
		LoginToken token = this.createToken(sessionKey, JSON.toJSONString(userDTO));
		stringObjectValueOperations.set(sessionKey, token, expireTime, TimeUnit.SECONDS);
	}

	public UserDTO getUser() {
		ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
		String sessionKey = this.getSessionKey();
		return Optional.ofNullable(JSON.parseObject(JSON.toJSONString(stringObjectValueOperations.get(sessionKey)), LoginToken.class))
			.map(loginToken -> JSON.parseObject(loginToken.getValue(), UserDTO.class))
			.orElse(null);
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
			throw new ResponseException(ResultCode.BAD_REQUEST);
		}
		return servletRequestAttributes.getRequest();
	}

	private HttpServletResponse getHttpServletResponse() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (servletRequestAttributes == null) {
			log.error("cannot get HttpServletRequest!");
			throw new ResponseException(ResultCode.BAD_REQUEST);
		}
		return servletRequestAttributes.getResponse();
	}

	private String getSessionKey() {
		HttpServletRequest httpServletRequest = this.getHttpServletRequest();
		return Optional.ofNullable(WebUtils.getCookie(httpServletRequest, cookieName)).map(Cookie::getValue).orElse("");
	}

	private String createCookie() {
		HttpServletRequest httpServletRequest = this.getHttpServletRequest();
		return Optional.ofNullable(WebUtils.getCookie(httpServletRequest, cookieName))
			.map(Cookie::getValue)
			.orElseGet(() -> {
				String jSessionId = Optional.ofNullable(WebUtils.getCookie(httpServletRequest, "JSESSIONID"))
					.map(Cookie::getValue).orElse(UUID.randomUUID().toString());
				Cookie cookie = new Cookie(cookieName, jSessionId);
				cookie.setDomain(domain);
				HttpServletResponse httpServletResponse = this.getHttpServletResponse();
				httpServletResponse.addCookie(cookie);
				return jSessionId;
			});
	}

	private LoginToken createToken(String token, String value) {
		LoginToken loginToken = new LoginToken();
		loginToken.setToken(token);
		loginToken.setValue(value);
		loginToken.setExpireTime(expireTime);
		return loginToken;
	}

}
