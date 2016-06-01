package com.pallasli.comp;

import designer.bean.PageComp;

public class Panel extends Comp {

	public Panel(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
		this.htmlTag = "div";
	}

	@Override
	public void initCompProperties() {

	}

}
