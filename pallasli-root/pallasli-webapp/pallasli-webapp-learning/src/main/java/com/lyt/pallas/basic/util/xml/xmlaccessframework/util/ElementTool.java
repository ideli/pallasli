package com.lyt.pallas.basic.util.xml.xmlaccessframework.util;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ElementTool {

	public static Element find(String expression, Document doc)
			throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		XPathExpression expr;
		Element result = null;
		expr = xPath.compile(expression);
		result = (Element) expr.evaluate(doc, XPathConstants.NODE);
		return result;

	}

	public static NodeList findAll(String expression, Document doc)
			throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		XPathExpression expr;
		NodeList result = null;
		expr = xPath.compile(expression);
		result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return result;
	}

}
