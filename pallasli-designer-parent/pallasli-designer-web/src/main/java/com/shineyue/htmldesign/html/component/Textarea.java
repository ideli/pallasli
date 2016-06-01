package com.shineyue.htmldesign.html.component;

import com.shineyue.htmldesign.html.Component;

public class Textarea extends Component {

	public String getType() {
		return COMPONENT_TEXTAREA;
	}

	public String buildHtml() {
		System.out.println("textarea:" + getBaseHtml());
		setHtml(getBaseHtml());
		replaceToken("1", "名称");
		replaceToken("2", "caption");
		return getHtml();
	}

}
