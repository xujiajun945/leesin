package com.dabanjia.leesin.base.service.impl;

import com.dabanjia.leesin.api.model.User;
import com.dabanjia.leesin.api.vo.UserVO;
import com.dabanjia.leesin.base.dao.UserMapper;
import com.dabanjia.leesin.base.service.DemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xujiajun
 * @since 2019/12/24
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserVO getById(Long id) {
		return Optional.ofNullable(userMapper.getById(id))
			.map(this::buildUserVO)
			.orElse(null);
	}

	@Override
	public List<UserVO> listByIds(List<Long> ids) {
		return userMapper.listByIds(ids)
			.stream()
			.map(this::buildUserVO)
			.collect(Collectors.toList());
	}

	private UserVO buildUserVO(User user) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		return userVO;
	}
}
