package com.dabanjia.leesin.base.service;

import com.dabanjia.leesin.api.module.common.dto.LoginDTO;
import com.dabanjia.leesin.api.module.common.dto.UserDTO;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
public interface LoginService {

	/**
	 * 用户登录
	 *
	 * @param loginDTO 登录信息dto
	 * @return userDTO 用户的信息
	 */
	UserDTO login(LoginDTO loginDTO);
}
