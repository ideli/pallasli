package com.pallasli.layout;

import com.pallasli.utils.Constant;

import designer.bean.PageComp;

public abstract class Layout {

	@SuppressWarnings("unused")
	private Layout() {

	}

	PageComp comp;
	int parentLayout;
	String cls;

	public Layout(PageComp pageComp, int parentLayout) {
		this.comp = pageComp;
		this.parentLayout = parentLayout;
		this.cls = "";
	}

	public String getLayoutCls() {

		if (parentLayout == Constant.LAYOUT_TAB) {
			switch (comp.getF_layout()) {
			case Constant.LAYOUT_FORM:
				cls = "form";
				break;
			case Constant.LAYOUT_TAB:
				cls = "tab";
				break;
			default:
				break;
			}
		}
		return cls;
	};

}
