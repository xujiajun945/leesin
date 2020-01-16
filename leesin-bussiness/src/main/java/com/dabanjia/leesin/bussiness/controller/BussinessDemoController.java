package com.dabanjia.leesin.bussiness.controller;

import com.dabanjia.leesin.api.module.common.dto.UserDTO;
import com.dabanjia.leesin.module.common.util.LoginUtils;
import com.dabanjia.vayne.core.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xujiajun
 * @since 2020/1/13
 */
@RestController
@RequestMapping(value = "/bussiness_demo")
public class BussinessDemoController {

	@Autowired
	private LoginUtils loginUtils;

	@Autowired
	private HttpServletRequest request;

	@GetMapping(value = "/test_login")
	public ResponseData test() {
		Cookie[] cookies = request.getCookies();
		UserDTO user = loginUtils.getUser();
		return new ResponseData(user);
	}
}
