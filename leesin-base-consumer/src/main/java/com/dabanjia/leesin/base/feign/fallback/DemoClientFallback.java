package com.dabanjia.leesin.base.feign.fallback;

import com.dabanjia.leesin.api.module.common.vo.UserVO;
import com.dabanjia.leesin.base.feign.DemoClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/25
 */
@Component
public class DemoClientFallback implements DemoClient {

	@Override
	public UserVO getUserById(Long id) {
		UserVO userVO = new UserVO();
		userVO.setName("测试熔断");
		return userVO;
	}

	@Override
	public List<UserVO> listByIds(List<Long> ids) {
		return null;
	}
}
