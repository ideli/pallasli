/**
 * 河北省神玥软件科技有限公司 版权所有
 * @file 文件:LoginUser.java
 * @date 创建时间:2015-12-4
 * @author 创建人:魏广强
 * @Description TODO
 */
package com.pallasli.website.report;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class WebappDataSource extends AbstDataSource {

	private int i = -1;
	private List<? extends Object> list;

	public WebappDataSource(List<? extends Object> items) {
		if (items == null) {
			items = java.util.Collections.emptyList();
		}
		this.list = items;
	}

	@Override
	public boolean next() throws JRException {
		i++;
		return i < list.size();
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		String fieldName = jrField.getName();
		Object line = list.get(i);
		if (line != null) {
			try {
				return PropertyUtils.getProperty(line, fieldName);
			} catch (Exception e) {
				throw new JRException(e);
			}
		}
		return null;
	}

}
