package com.shineyue.htmldesign.html.component;

import com.shineyue.htmldesign.html.Component;

public class Datetime extends Component {

	public String getType() {
		return COMPONENT_DATETIME;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		replaceToken("1", "名称");
		replaceToken("2", "caption");
		return getHtml();
	}

}
