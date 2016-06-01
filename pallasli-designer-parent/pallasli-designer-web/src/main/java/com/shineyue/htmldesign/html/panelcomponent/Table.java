package com.shineyue.htmldesign.html.panelcomponent;

import com.shineyue.htmldesign.html.PanelComponent;

public class Table extends PanelComponent {

	public String getType() {
		return COMPONENT_TABLE;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		replaceToken("1", "名称");
		replaceToken("2", "caption");
		return getHtml();
	}

}
