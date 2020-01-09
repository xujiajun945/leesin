package com.dabanjia.leesin.base;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author xujiajun
 * @since 2019/12/24
 */
@RunWith(JUnit4.class)
public class JavaTest {

	@Test
	public void name() {
		String md5Hex = DigestUtils.md5Hex("123456");
		System.out.println(md5Hex);
	}
}
