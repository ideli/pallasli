package com.lyt.pallas.basic.util.xml.xmlaccessframework.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DocumentTool {

	public static Document createDoc(String path) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;

		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(path));
		} catch (IOException e) {
			String tablename = path.substring(path.lastIndexOf("/") + 1);
			String tbname = tablename.substring(0, tablename.lastIndexOf("."));
			document = builder.newDocument();
			Element table = document.createElement(tbname);
			Element pk = document.createElement("primarykey");
			Element pkname = document.createElement("primarykey-name");
			Element pkinit = document.createElement("primarykey-init");
			Element pkincrement = document
					.createElement("primarykey-increment");
			Element pkcount = document.createElement("primarykey-count");
			document.appendChild(table);
			table.appendChild(pk);
			pk.appendChild(pkname);
			pk.appendChild(pkinit);
			pk.appendChild(pkincrement);
			pk.appendChild(pkcount);
			pkname.setTextContent(tbname.substring(2).concat("_id"));
			pkinit.setTextContent("1");
			pkincrement.setTextContent("1");
			pkcount.setTextContent("1");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	public static void saveDoc(String path, Document doc)
			throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = null;
		transformer = factory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult(new File(
				path)));
	}

}
