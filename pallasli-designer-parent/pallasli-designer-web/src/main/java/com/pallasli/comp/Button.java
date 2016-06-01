package com.pallasli.comp;

import designer.bean.PageComp;

public class Button extends Input {

	public Button(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
	}

	@Override
	public void initCompProperties() {
		html.append("input type=\"button\"");
	}

}
