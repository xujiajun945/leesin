package com.dabanjia.leesin.base.service;

import com.dabanjia.leesin.api.module.common.vo.UserVO;

import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/24
 */
public interface DemoService {

	/**
	 * 根据id获取用户信息
	 *
	 * @param id id
	 * @return 用户信息
	 */
	UserVO getById(Long id);

	/**
	 * 根据ids批量获取
	 *
	 * @param ids ids
	 * @return 用户信息列表
	 */
	List<UserVO> listByIds(List<Long> ids);
}
