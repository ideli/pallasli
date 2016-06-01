package com.shineyue.htmldesign.html.component;

import com.shineyue.htmldesign.html.Component;

public class Cell extends Component {

	public String getType() {
		return COMPONENT_CELL;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		matchConfigByKey("1", "caption", String.class);
		matchConfigByKey("2", "name", String.class);

		return getHtml();
	}

}
