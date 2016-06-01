package com.shineyue.htmldesign.html.component;

import com.shineyue.htmldesign.html.Component;

public class Node extends Component {

	public String getType() {
		return COMPONENT_NUMBER;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		replaceToken("1", "名称");
		replaceToken("2", "caption");
		return getHtml();
	}

}
