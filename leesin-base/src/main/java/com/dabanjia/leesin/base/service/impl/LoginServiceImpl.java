package com.dabanjia.leesin.base.service.impl;

import com.dabanjia.leesin.api.dto.LoginDTO;
import com.dabanjia.leesin.api.dto.UserDTO;
import com.dabanjia.leesin.api.model.User;
import com.dabanjia.leesin.base.dao.UserMapper;
import com.dabanjia.leesin.base.entity.constant.LeesinBaseResultCodeEnum;
import com.dabanjia.leesin.base.entity.exception.PermissionException;
import com.dabanjia.leesin.base.service.LoginService;
import com.dabanjia.vayne.core.constant.ResponseCodeEnum;
import com.dabanjia.vayne.core.exception.ResponseException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujiajun
 * @since 2020/1/8
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO login(LoginDTO loginDTO) {
		String phone = loginDTO.getPhone();
		if (phone == null || phone.isEmpty()) {
			throw new ResponseException(ResponseCodeEnum.BAD_REQUEST);
		}
		// 验证当前手机号是否存在
		User user = userMapper.getByPhone(phone);
		if (user == null) {
			throw new PermissionException(LeesinBaseResultCodeEnum.USER_NOT_EXISTS);
		}
		if (user.getDeleted()) {
			throw new PermissionException(LeesinBaseResultCodeEnum.USER_DELETED);
		}
		// 验证用户密码是否正确
		String password = user.getPassword();
		String md5Hex = DigestUtils.md5Hex(loginDTO.getPassword() + user.getSalt());
		if (!md5Hex.equals(password)) {
			throw new PermissionException(LeesinBaseResultCodeEnum.PASSWORD_ERROR);
		}
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
}
