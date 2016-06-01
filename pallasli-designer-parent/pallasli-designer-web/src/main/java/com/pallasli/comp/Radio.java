package com.pallasli.comp;

import designer.bean.PageComp;

public class Radio extends Input {

	public Radio(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
	}

	@Override
	public void initCompProperties() {
		html.append(" type=\"radio\"");

	}

}
