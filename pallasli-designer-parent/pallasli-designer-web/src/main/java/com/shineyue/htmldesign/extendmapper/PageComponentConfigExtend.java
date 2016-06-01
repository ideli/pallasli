package com.shineyue.htmldesign.extendmapper;

import java.io.Serializable;

import com.shineyue.htmldesign.model.PageComponentConfig;

public class PageComponentConfigExtend extends PageComponentConfig implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2745748578285493594L;

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
