package com.dabanjia.leesin.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	/**
	 * 结论: fastJson在进行序列化和反序列化的时候依赖java对象的setter和getter方法
	 */
	@Test
	public void testFastJson() {
		JsonTest jsonTest = new JsonTest();
		jsonTest.id = 1L;
		jsonTest.name = "testSerialized";

		String s = JSON.toJSONString(jsonTest);
		System.out.println(s);

		JsonTest object = JSON.parseObject(s, JsonTest.class);
	}

	@Test
	public void testGson() {
		JsonTest jsonTest = new JsonTest();
		jsonTest.id = 1L;
		jsonTest.name = "testSerialized";
	}

	@Test
	public void testJackson() {
		JsonTest jsonTest = new JsonTest();
		jsonTest.id = 1L;
		jsonTest.name = "testSerialized";

	}

	@Test
	public void testJsonArray() {
		JsonTest jsonTest = new JsonTest();
		jsonTest.id = 1L;
		jsonTest.name = "testSerialized";

		JSONObject object1 = new JSONObject();
		object1.put("id", 1L);
		object1.put("name", "objectName1");
		List<JSONObject> childrenList1 = new ArrayList<>();
		JSONObject child1 = new JSONObject();
		child1.put("id", 11L);
		child1.put("name", "childName1");
		childrenList1.add(child1);
		object1.put("children", childrenList1);

		JSONObject object2 = new JSONObject();
		object2.put("id", 2L);
		object2.put("name", "objectName2");
		List<JSONObject> childrenList2 = new ArrayList<>();
		JSONObject child2 = new JSONObject();
		child2.put("id", 21L);
		child2.put("name", "childName2");
		childrenList2.add(child2);
		object2.put("children", childrenList2);

		List<JSONObject> objectList = new ArrayList<>();
		Collections.addAll(objectList, object1, object2);
		jsonTest.children = objectList;

		System.out.println(jsonTest);

		List<JSONObject> children = jsonTest.children;
		for (JSONObject jsonObject : children) {
			List<JSONObject> list = (List<JSONObject>) jsonObject.get("children");
			JSONArray array = jsonObject.getJSONArray("children");
			JSONObject jsonObject1 = list.get(0);
			System.out.println(list);
			System.out.println(jsonObject1);
		}
	}

	@Test
	public void testDecimal() {
		List<Integer> list = new ArrayList<>();
		Integer one = 1;
		Integer two = 2;
		Collections.addAll(list, one, two);

		JSONArray array = JSON.parseArray(JSON.toJSONString(list));
	}

	@Setter
	@Getter
	public static class JsonTest {

		private String name;

		private Long id;

		private List<JSONObject> children;
	}
}
