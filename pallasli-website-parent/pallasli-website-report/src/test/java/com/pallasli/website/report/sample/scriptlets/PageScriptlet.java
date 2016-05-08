package com.pallasli.website.report.sample.scriptlets;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

/**
 * @author weigq
 */
public class PageScriptlet extends JRDefaultScriptlet {

	private int pageSumId;

	public void afterPageInit() throws JRScriptletException {
		System.out.println("afterPageInit");
		pageSumId = 0;
	}

	public void afterDetailEval() throws JRScriptletException {
		
		Integer id = (Integer) this.getFieldValue("id");
		System.out.println("afterDetailEval. id:" + id);
		pageSumId += id;
		System.out.println("pageSumId:" + pageSumId);
	}

	public int getPageSumId() {
		return pageSumId;
	}

}
