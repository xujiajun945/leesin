package com.dabanjia.leesin.base.feign.fallback;

import com.dabanjia.leesin.api.module.common.vo.UserVO;
import com.dabanjia.leesin.base.feign.DemoClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/26
 */
@Component
@Slf4j
public class DemoClientFallbackFactory implements FallbackFactory<DemoClient> {

	@Override
	public DemoClient create(Throwable throwable) {
		return new DemoClient() {

			@Override
			public UserVO getUserById(Long id) {
				log.error("fallback, reason was: ", throwable);
				UserVO userVO = new UserVO();
				userVO.setName("测试熔断-factory");
				return userVO;
			}

			@Override
			public List<UserVO> listByIds(List<Long> ids) {
				return null;
			}

		};
	}
}
