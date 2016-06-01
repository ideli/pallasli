package com.pallasli.comp;

import designer.bean.PageComp;

public class TextField extends Input {

	public TextField(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
	}

	@Override
	public void initCompProperties() {
		html.append(" type=\"text\"");
	}

}
