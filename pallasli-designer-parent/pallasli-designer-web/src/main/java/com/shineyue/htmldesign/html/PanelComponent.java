package com.shineyue.htmldesign.html;

import java.util.List;

public abstract class PanelComponent extends AbstractComponent {

	public List<AbstractComponent> children() {
		return null;
	}

	public abstract String buildHtml();

	public void initHtml() {
		List<AbstractComponent> list = children();
		for (AbstractComponent child : list) {
			appendChild(child);
		}
	}

	public void appendChild(AbstractComponent child) {

	}

}
