package com.dabanjia.leesin.gateway.filter;

import com.dabanjia.leesin.api.module.common.dto.UserDTO;
import com.dabanjia.leesin.module.common.util.LoginUtils;
import com.dabanjia.vayne.core.constant.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xujiajun
 * @since 2020/1/14
 */
@Slf4j
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

	@Autowired
	private LoginUtils loginUtils;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 判断当前用户是否登录
		UserDTO user = loginUtils.getUser();
		if (user == null) {
			log.error("login failed, reason was : ", ResultCode.LOGIN_FAILED.getMessage());
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}
}
