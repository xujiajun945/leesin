package com.dabanjia.leesin.base.service.impl;

import com.dabanjia.leesin.api.module.common.dto.LoginDTO;
import com.dabanjia.leesin.api.module.common.dto.UserDTO;
import com.dabanjia.leesin.api.module.common.model.User;
import com.dabanjia.leesin.base.dao.UserMapper;
import com.dabanjia.leesin.base.service.LoginService;
import com.dabanjia.leesin.module.base.constant.LeesinBaseResultCodeEnum;
import com.dabanjia.leesin.module.common.exception.PermissionException;
import com.dabanjia.vayne.core.constant.ResultCode;
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
			throw new ResponseException(ResultCode.BAD_REQUEST);
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
		String loginPassword = DigestUtils.md5Hex(loginDTO.getPassword() + user.getSalt());
		if (!loginPassword.equals(password)) {
			throw new PermissionException(LeesinBaseResultCodeEnum.PASSWORD_ERROR);
		}
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
}
