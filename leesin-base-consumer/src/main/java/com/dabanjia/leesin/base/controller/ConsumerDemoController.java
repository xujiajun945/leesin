package com.dabanjia.leesin.base.controller;

import com.dabanjia.leesin.api.module.common.vo.UserVO;
import com.dabanjia.leesin.base.feign.DemoClient;
import com.dabanjia.vayne.core.entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/25
 */
@RestController
@RequestMapping(value = "/consumer/demo")
public class ConsumerDemoController {

	@Autowired
	private DemoClient demoClient;

	@GetMapping(value = "/user/{id}")
	public ResponseData test(@PathVariable Long id) {
		UserVO userVO = demoClient.getUserById(id);
		return new ResponseData(userVO);
	}

	@GetMapping(value = "/user/test")
	public ResponseData test2() {
		List<Long> ids = Arrays.asList(11L, 21L);
		List<UserVO> userVOList = demoClient.listByIds(ids);
		return new ResponseData(userVOList);
	}
}
