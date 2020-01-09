package com.dabanjia.leesin.base.controller;

import com.dabanjia.leesin.api.dto.LoginDTO;
import com.dabanjia.leesin.api.dto.UserDTO;
import com.dabanjia.leesin.base.service.LoginService;
import com.dabanjia.leesin.util.LoginUtils;
import com.dabanjia.vayne.core.entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoginUtils loginUtils;

	/**
	 * 登录
	 *
	 * @param loginDTO 登录信息
	 * @return 响应
	 */
	@PostMapping
	public ResponseData login(@RequestBody LoginDTO loginDTO) {
		UserDTO userDTO = loginService.login(loginDTO);
		loginUtils.setUser(userDTO);
		return new ResponseData();
	}

	/**
	 * 获取用户登录信息
	 *
	 * @return 用户登录信息
	 */
	@GetMapping(value = "/user_info")
	public ResponseData getUserInfo() {
		UserDTO user = loginUtils.getUser();
		return new ResponseData(user);
	}

	/**
	 * 登出
	 *
	 * @return 响应
	 */
	@DeleteMapping
	public ResponseData logout() {
		loginUtils.removeUser();
		return new ResponseData();
	}
}