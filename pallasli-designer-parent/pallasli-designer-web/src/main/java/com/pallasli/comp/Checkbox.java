package com.pallasli.comp;

import designer.bean.PageComp;

public class Checkbox extends Input {

	public Checkbox(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
	}

	@Override
	public void initCompProperties() {
		html.append(" type=\"checkbox\"");

	}

}
