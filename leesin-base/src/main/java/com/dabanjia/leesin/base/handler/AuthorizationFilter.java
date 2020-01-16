package com.dabanjia.leesin.base.handler;

import com.alibaba.fastjson.JSON;
import com.dabanjia.leesin.api.module.common.dto.UserDTO;
import com.dabanjia.leesin.module.common.util.LoginUtils;
import com.dabanjia.vayne.core.constant.ResultCode;
import com.dabanjia.vayne.core.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xujiajun
 * @since 2020/1/14
 */
@Slf4j
@WebFilter(urlPatterns = {"/bussiness_demo/*", "/demo/*"})
public class AuthorizationFilter implements Filter {

	@Autowired
	private LoginUtils loginUtils;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// 判断当前用户是否登录
		UserDTO user = loginUtils.getUser();
		if (user == null) {
			this.writeFailed(servletResponse);
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private void writeFailed(ServletResponse servletResponse) throws IOException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(JSON.toJSONString(new ResponseException(ResultCode.LOGIN_FAILED)));
		writer.flush();
		writer.close();
	}

}
