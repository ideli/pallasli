package com.pallasli.comp;

import java.util.List;

import com.pallasli.builder.CompBuilder;
import com.pallasli.builder.LayoutBuilder;

import designer.bean.PageComp;

public abstract class Comp {

	@SuppressWarnings("unused")
	private Comp() {

	}

	PageComp comp;
	int level;
	int parentLayout;
	StringBuffer html;
	boolean isSingleTag;
	String htmlTag;

	public Comp(PageComp pageComp, int parentLayout) {
		this(pageComp, parentLayout, 0);
	}

	public Comp(PageComp pageComp, int parentLayout, int parentLevel) {
		this.comp = pageComp;
		this.parentLayout = parentLayout;
		this.level = parentLevel + 1;
		isSingleTag = false;
		this.html = new StringBuffer();
	}

	public abstract void initCompProperties() throws Exception;

	public void initLayot() {
		html.append(" class=\"" + LayoutBuilder.getLayout(comp, parentLayout)
				+ "\"");
	}

	public void initHtmlChildren() {
		List<PageComp> l = comp.getF_children();
		if (l == null)
			return;
		for (PageComp pc : l) {
			html.append(CompBuilder.initHtml(pc, comp.getF_layout(), level));
		}
	}

	private void getHtmlBegin() {
		for (int i = 0; i < level; i++) {
			html.append("    ");
		}
		html.append("<");
		html.append(htmlTag);
		html.append(" id=\"" + comp.getF_key() + "\"");
		try {

			initCompProperties();

		} catch (Exception e) {
			e.printStackTrace();
		}
		initLayot();
		if (isSingleTag)
			html.append("/");
		html.append(">\r\n");
	}

	private void getHtmlEnd() {
		if (isSingleTag)
			return;

		for (int i = 0; i < level; i++) {
			html.append("    ");
		}
		html.append("</");
		html.append(htmlTag);
		html.append(">\r\n");
	}

	public String initHtml() {
		getHtmlBegin();
		initHtmlChildren();
		getHtmlEnd();
		return html.toString();
	}
}
