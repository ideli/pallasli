package designer.initial;

import com.pallasli.builder.CompBuilder;

import designer.bean.PageComp;

public class PageCompInitial {
	public static PageComp init(String page_comp_key) {
		return null;
	}

	public static String writeHtml(PageComp pageComp, int parentLayout) {
		return CompBuilder.initHtml(pageComp, parentLayout, 1);
	}
}
