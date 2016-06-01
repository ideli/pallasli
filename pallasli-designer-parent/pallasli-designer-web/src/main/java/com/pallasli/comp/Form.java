package com.pallasli.comp;

import designer.bean.PageComp;

public class Form extends Comp {

	public Form(PageComp pageComp, int parentLayout, int parentLevel) {
		super(pageComp, parentLayout, parentLevel);
		this.htmlTag = "form";
	}

	@Override
	public void initCompProperties() {
	}

}