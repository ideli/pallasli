package com.pallasli.xml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class Dom4jUtils {
	public static void main(String[] args) throws DocumentException {

		SAXReader saxReader = new SAXReader();
		File file = new File("./test.xml");
		Document document = saxReader.read(file);

		Map map = new HashMap();
		map.put("design", "http://www.eclipse.org/birt/2005/design");
		XPath x = document.createXPath("//design:list-property");
		x.setNamespaceURIs(map);
		List nodelist = x.selectNodes(document);
		System.out.println(nodelist.size());

	}
}
