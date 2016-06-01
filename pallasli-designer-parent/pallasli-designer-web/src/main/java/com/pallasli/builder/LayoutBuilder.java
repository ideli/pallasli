package com.pallasli.builder;

import com.pallasli.layout.Fit;
import com.pallasli.layout.Form;
import com.pallasli.layout.Layout;
import com.pallasli.layout.Tab;
import com.pallasli.utils.Constant;

import designer.bean.PageComp;

public class LayoutBuilder {
	public static String getLayout(PageComp pageComp, int parentLayout) {
		Layout t = null;
		switch (parentLayout) {
		case Constant.LAYOUT_FIT: {
			t = new Fit(pageComp, parentLayout);
			break;
		}
		case Constant.LAYOUT_TAB: {
			t = new Tab(pageComp, parentLayout);
			break;
		}
		case Constant.LAYOUT_FORM: {
			t = new Form(pageComp, parentLayout);
			break;
		}
		case Constant.LAYOUT_TABLE: {
			break;
		}
		default:
			break;
		}
		String cls = t.getLayoutCls();
		return cls;
	}
}
