package com.pallasli.comp;

import designer.bean.PageComp;

public class TextArea extends Input {

	public TextArea(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
	}

	@Override
	public void initCompProperties() {
		html.append(" type=\"text\"");
	}

}
