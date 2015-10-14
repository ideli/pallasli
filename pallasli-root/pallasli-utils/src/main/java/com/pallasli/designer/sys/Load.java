package com.pallasli.designer.sys;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public class Load {
	/**
	 * 初始化组件html
	 */
	@Test
	public void init() {
		CompHtml.compInitMap.clear();

		Properties p = FileUtils.getProperties("inithtml.properties");

		Set<Object> keys = p.keySet();
		Iterator<Object> it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			CompHtml.compInitMap.put(key, (String) p.get(key));
		}
		System.out.println(CompHtml.compInitMap);
	}
}
