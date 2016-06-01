package com.shineyue.htmldesign.html.component;

import com.shineyue.htmldesign.html.Component;

public class Radio extends Component {

	public String getType() {
		return COMPONENT_RADIO;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		replaceToken("1", "名称");
		replaceToken("2", "caption");
		return getHtml();
	}

}
