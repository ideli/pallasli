package com.pallasli.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

public class FastJsonUtilTest {
	@Test
	public void fromJsonArrayStr() {
		TestBean b = new TestBean();
		b.setName("name");
		Map<String, TestBean> mapb = new HashMap<String, TestBean>();
		mapb.put("a", b);
		mapb.put("b", b);
		mapb.put("c", b);
		String json = JsonUtil.object2PrettyStr(mapb);
		System.out.println(json);

		String jsonArrayStr = "[{name:\"name\",date:\"2012-02-03\"},{name:\"name\"}]";
		String jsonMapStr = "{\"a\":{\"name\":\"name\"},\"b\":{\"name\":\"name\"}}";
		String jsonStr = "{name:\"name\"}";
		List<TestBean> list;
		list = JsonUtil.str2List(jsonArrayStr, TestBean.class);
		TestBean bean = JsonUtil.str2Object(jsonStr, TestBean.class);
		Assert.assertEquals("name", bean.getName());
		for (TestBean bar : list) {
			Assert.assertEquals("name", bar.getName());
			System.out.println(bar.getDate());
		}
		JSONArray jsonArray = JsonUtil.list2JsonArray(list);

		list = JsonUtil.json2List(jsonArray, TestBean.class);
		for (TestBean bar : list) {
			Assert.assertEquals("name", bar.getName());
		}
		Map<String, TestBean> map = JsonUtil
				.str2Map(jsonMapStr, TestBean.class);
		Assert.assertEquals("name", map.get("a").getName());

	}
}
