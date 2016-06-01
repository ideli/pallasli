package designer.initial;

import java.util.List;

import com.google.gson.Gson;
import com.pallasli.utils.Constant;
import com.pallasli.utils.FileUtils;

import designer.bean.Page;
import designer.bean.PageComp;

public class PageInitial {

	public static Page init(String page_key) {

		String pageString = FileLoader
				.loadFile("J:\\pallasli@icloud.com\\designer\\WebContent\\WEB-INF\\design\\testPage.json");
		Page page = new Gson().fromJson(pageString, Page.class);

		return page;
	}

	private static void initHtmlBegin(StringBuffer html, Page page) {
		switch (page.getF_layout()) {
		case Constant.LAYOUT_TAB: {
			html.append("    <div class=\"page-content\">\r\n");
			break;
		}
		default:
			break;
		}
	}

	private static void initHtmlChildren(StringBuffer html, Page page) {

		if ("tab".equals(page.getF_layout())) {

		}

		List<PageComp> l = page.getF_children();
		if (l == null)
			return;
		for (PageComp pc : l) {
			html.append(PageCompInitial.writeHtml(pc, page.getF_layout()));
		}

	}

	private static void initHtmlEnd(StringBuffer html, Page page) {

		switch (page.getF_layout()) {
		case Constant.LAYOUT_TAB: {
			html.append("    </div>\r\n");
			break;
		}
		default:
			break;
		}
	}

	public static String writeHtml(Page page) {
		StringBuffer html = new StringBuffer();
		initHtmlBegin(html, page);
		initHtmlChildren(html, page);
		initHtmlEnd(html, page);
		String header = FileLoader
				.loadFile("J:\\pallasli@icloud.com\\designer\\WebContent\\WEB-INF\\design\\htmlHeader.txt");
		String footer = FileLoader
				.loadFile("J:\\pallasli@icloud.com\\designer\\WebContent\\WEB-INF\\design\\htmlFooter.txt");
		FileUtils.createFile(
				"J:\\pallasli@icloud.com\\designer\\WebContent\\pages", true);
		FileUtils.createFile(
				"J:\\pallasli@icloud.com\\designer\\WebContent\\pages\\test",
				true);
		FileUtils
				.createFile(
						"J:\\pallasli@icloud.com\\designer\\WebContent\\pages\\test\\test.html",
						false);
		FileUtils
				.writeFile(
						"J:\\pallasli@icloud.com\\designer\\WebContent\\pages\\test\\test.html",
						header + "\r\n" + html.toString() + "\r\n" + footer);
		return html.toString();
	}

	public static void main(String[] a) {
		System.out.println(PageInitial.writeHtml(PageInitial.init("")));
	}
}
