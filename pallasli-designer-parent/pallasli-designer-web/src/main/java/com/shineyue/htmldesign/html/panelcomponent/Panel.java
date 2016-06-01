package com.shineyue.htmldesign.html.panelcomponent;

import java.io.StringWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.shineyue.htmldesign.html.AbstractComponent;
import com.shineyue.htmldesign.html.PanelComponent;

public class Panel extends PanelComponent {
	public String test(String html) throws Exception {
		StringWriter sw = new StringWriter();
		// String html = "<form><input name=\"text\"/></form>";
		Document document = DocumentHelper.parseText(html);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		XMLWriter writer = new XMLWriter(sw, format);
		writer.write(document);
		writer.close();
		System.err.println(sw.toString());
		return "";
	}

	public String formatHtml(String html) throws Exception {
		// test(str);
		// if (true) {
		// return "";
		// }
		// return test(html);
		Document document = DocumentHelper.parseText(html);
		StringWriter sw = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setXHTML(true);
		format.setEncoding("utf-8");
		HTMLWriter writer = new HTMLWriter(sw, format);
		writer.write(document);
		writer.close();
		System.out.println(sw.toString());
		return sw.toString();
		// OutputFormat format = OutputFormat.createPrettyPrint();
		// format.setEncoding("utf-8");
		// StringWriter writer = new StringWriter();
		// HTMLWriter htmlWriter = new HTMLWriter(writer, format);
		// Document document = DocumentHelper.parseText(html);
		// html = htmlWriter.prettyPrintXHTML(html);
		// System.out.println(html);
		// htmlWriter.close();
		// return html;

		// CloseableHttpClient client = HttpClients.createDefault();
		// HttpGet get = new HttpGet("http://book.douban.com/latest");
		// CloseableHttpResponse response = (CloseableHttpResponse) client
		// .execute(get);
		// HttpEntity entity = response.getEntity();
		// String content = EntityUtils.toString(entity);
		// Source source = new Source(str);
		// List<Element> lis = source.getAllElements("li");
		// System.out.println("总书数目：" + lis.size());
		// List<Element> childList = null;
		// for (Element em : lis) {
		// childList = em.getChildElements();
		// if (childList.size() == 2
		// && "div".equals(childList.get(0).getName())
		// && "a".equals(childList.get(1).getName())) {
		// Element divElement = childList.get(0);
		// String title = divElement.getChildElements().get(0)
		// .getTextExtractor().toString();
		// String description = divElement.getChildElements().get(1)
		// .getTextExtractor().toString();
		// String summary = divElement.getChildElements().get(2)
		// .getTextExtractor().toString();
		// System.out.println("标题:" + title);
		// System.out.println("描述:" + description);
		// System.out.println("简介:" + summary);
		//
		// Element aelement = childList.get(1);
		// String iconpath = aelement.getChildElements().get(0)
		// .getAttributeValue("src");
		// System.out.println("图片路径:" + iconpath);
		// System.out
		// .println("=======================================================================");
		// }
		// }

	}

	public String getType() {
		return COMPONENT_PANRL;
	}

	public String buildHtml() {
		setHtml(getBaseHtml());
		List<AbstractComponent> children = getChildren();
		StringBuffer sb = new StringBuffer();
		for (AbstractComponent child : children) {
			System.out.println("type" + child.getType());
			String cstr = child.buildHtml();
			System.out.println("cstr:" + cstr);
			sb.append(cstr);
		}
		replaceToken("1", sb.toString());
		String html = "";
		try {
			html = getHtml();
			System.out.println("html:" + html);
			html = formatHtml(html);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}

}
