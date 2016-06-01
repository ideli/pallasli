package com.pallasli.comp;

import designer.bean.PageComp;

public class Input extends Comp {
	public Input(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
		isSingleTag = true;
		this.htmlTag = "input";
	}

	@Override
	public void initCompProperties() throws Exception {
		throw new Exception();
	}
}
