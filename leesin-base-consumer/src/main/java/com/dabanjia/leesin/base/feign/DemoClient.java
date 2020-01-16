package com.dabanjia.leesin.base.feign;

import com.dabanjia.leesin.api.module.common.vo.UserVO;
import com.dabanjia.leesin.base.feign.fallback.DemoClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/25
 */
@FeignClient(name = "${list-of-server.leesin-base-provider}", fallbackFactory = DemoClientFallbackFactory.class)
public interface DemoClient {

	/**
	 * 根据id 获取用户信息
	 *
	 * @param id id
	 * @return 用户信息
	 */
	@GetMapping(value = "/demo/user/{id}")
	UserVO getUserById(@PathVariable("id") Long id);

	/**
	 * 根据ids批量获取
	 *
	 * @param ids ids
	 * @return 用户列表
	 */
	@GetMapping(value = "/demo/user")
	List<UserVO> listByIds(@RequestBody List<Long> ids);
}
