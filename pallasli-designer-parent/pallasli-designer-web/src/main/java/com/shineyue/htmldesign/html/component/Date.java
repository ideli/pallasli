package com.shineyue.htmldesign.html.component;

import com.shineyue.htmldesign.html.Component;

public class Date extends Component {

	public String getType() {
		return COMPONENT_DATE;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		replaceToken("1", "名称");
		replaceToken("2", "caption");
		return getHtml();
	}

}
