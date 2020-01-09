package com.dabanjia.leesin.base.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dabanjia.leesin.api.vo.UserVO;
import com.dabanjia.leesin.base.service.DemoService;
import com.dabanjia.leesin.module.common.util.LoginUtils;
import com.dabanjia.vayne.core.entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xujiajun
 * @since 2019/12/24
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoController {

	@Autowired
	private DemoService demoService;

	@Autowired
	private LoginUtils loginUtils;

	private int num = 0;

	/**
	 * 根据id获取用户信息
	 *
	 * @param id id
	 * @return 用户信息
	 */
	@GetMapping(value = "/user/{id}")
	public ResponseData test(@PathVariable Long id) {
		UserVO userVO = demoService.getById(id);
		return new ResponseData(userVO);
	}

	@GetMapping(value = "/user")
	@SentinelResource(value = "sentinel-test")
	public ResponseData test2(@RequestBody List<Long> ids) {
		List<UserVO> userVOList = demoService.listByIds(ids);
		return new ResponseData(userVOList);
	}

	@GetMapping(value = "/thread_local")
	public ResponseData testThreadLocal() {
		return new ResponseData(num++);
	}

	@GetMapping(value = "/login_user_id")
	public ResponseData testLoginUtils() {
		Long userId = loginUtils.getUserId();
		return new ResponseData(userId);
	}
}
