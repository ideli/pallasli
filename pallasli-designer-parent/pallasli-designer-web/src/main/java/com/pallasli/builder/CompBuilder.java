package com.pallasli.builder;

import com.pallasli.comp.Checkbox;
import com.pallasli.comp.Comp;
import com.pallasli.comp.Form;
import com.pallasli.comp.Panel;
import com.pallasli.comp.Radio;
import com.pallasli.comp.TextArea;
import com.pallasli.comp.TextField;
import com.pallasli.utils.Constant;

import designer.bean.PageComp;

public class CompBuilder {
	public static String initHtml(PageComp pageComp, int parentLayout) {
		return initHtml(pageComp, parentLayout, 0);
	}

	public static String initHtml(PageComp pageComp, int parentLayout,
			int parentLevel) {
		Comp t = null;
		switch (pageComp.getF_type()) {
		case Constant.COMP_TYPE_PANEL: {
			t = new Panel(pageComp, parentLayout, parentLevel);
			break;
		}
		case Constant.COMP_TYPE_FORM: {
			t = new Form(pageComp, parentLayout, parentLevel);
			break;
		}
		case Constant.COMP_TYPE_CHECKBOX: {
			t = new Checkbox(pageComp, parentLayout, parentLevel);
			break;
		}
		case Constant.COMP_TYPE_RADIO: {
			t = new Radio(pageComp, parentLayout, parentLevel);
			break;
		}
		case Constant.COMP_TYPE_TEXTAREA: {
			t = new TextArea(pageComp, parentLayout, parentLevel);
			break;
		}
		case Constant.COMP_TYPE_TEXTFIELD: {
			t = new TextField(pageComp, parentLayout, parentLevel);
			break;
		}
		default:
			break;
		}
		String html = t.initHtml();
		return html;
	}
}
