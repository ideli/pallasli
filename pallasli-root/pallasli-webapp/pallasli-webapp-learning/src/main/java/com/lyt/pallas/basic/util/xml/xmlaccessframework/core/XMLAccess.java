package com.lyt.pallas.basic.util.xml.xmlaccessframework.core;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lyt.pallas.basic.util.xml.xmlaccessframework.util.DocumentTool;
import com.lyt.pallas.basic.util.xml.xmlaccessframework.util.ElementTool;
import com.lyt.pallas.basic.util.xml.xmlaccessframework.util.StringTool;

@SuppressWarnings("rawtypes")
public class XMLAccess {
	String tablename;
	String pkName;
	int pkValue;
	int pkCount;
	int pkInit;
	int pkIncrement;

	Object bean;
	Document doc;

	public XMLAccess(String tablename, Object bean)
			throws ParserConfigurationException, XPathExpressionException,
			TransformerException {
		this.tablename = tablename;
		this.bean = bean;
		this.readXML();
		this.initPk();
		this.writeXML();
	}

	public void readXML() {
		String Path = XMLSetting.getXMLSetting().readXMLPath() + tablename
				+ ".xml";
		doc = DocumentTool.createDoc(Path);
	}

	public void writeXML() throws TransformerException {
		String path = XMLSetting.getXMLSetting().readXMLPath() + tablename
				+ ".xml";
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer tf = null;
		tf = tfactory.newTransformer();

		tf.transform(new DOMSource(doc), new StreamResult(new File(path)));

	}

	@SuppressWarnings("unchecked")
	public List selectAll() throws XPathExpressionException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, DOMException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		List list = new ArrayList();
		NodeList selElements = ElementTool.findAll(
				"//rowdata/descendant-or-self::*", doc);
		for (int i = 0; i < selElements.getLength(); i++) {
			Element selElement = (Element) selElements.item(i);
			if (selElement.getNodeType() == Node.ELEMENT_NODE) {
				if (!selElement.getNodeName().equals("rowdata")) {
					String beanVarName = selElement.getNodeName();
					String methodname = StringTool.parseToSetField(beanVarName);
					Method method = null;
					method = bean.getClass().getDeclaredMethod(methodname,
							new Class<?>[] { new String().getClass() });
					method.invoke(bean,
							new Object[] { selElement.getTextContent() });

				} else {
					bean = bean.getClass().newInstance();
					list.add(bean);

				}
			}
		}
		return list;
	}

	public Object selectById(int id) throws XPathExpressionException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			DOMException, IllegalAccessException, InvocationTargetException {
		NodeList selElements = ElementTool.findAll("//rowdata[" + pkName + "="
				+ id + "]/*", doc);
		for (int i = 0; i < selElements.getLength(); i++) {
			Element selElement = (Element) selElements.item(i);
			if (selElement.getNodeType() == Node.ELEMENT_NODE) {
				String beanVarName = selElement.getNodeName();
				String methodname = StringTool.parseToSetField(beanVarName);
				Method method;
				method = bean.getClass().getDeclaredMethod(methodname,
						new Class<?>[] { new String().getClass() });
				method.invoke(bean, selElement.getTextContent());

			}
		}
		return bean;
	}

	@SuppressWarnings({ "unchecked" })
	public List selectByWhere(String where) throws XPathExpressionException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			DOMException, IllegalAccessException, InvocationTargetException,
			InstantiationException {
		List list = new ArrayList();
		NodeList selElements = ElementTool.findAll("//rowdata[" + where
				+ "]/descendant-or-self::*", doc);
		for (int i = 0; i < selElements.getLength(); i++) {
			Element selElement = (Element) selElements.item(i);
			if (selElement.getNodeType() == Node.ELEMENT_NODE) {
				if (!selElement.getNodeName().equals("rowdata")) {
					String beanVarName = selElement.getNodeName();
					String methodname = StringTool.parseToSetField(beanVarName);
					Method method = null;
					method = bean.getClass().getDeclaredMethod(methodname,
							new Class<?>[] { new String().getClass() });
					method.invoke(bean, selElement.getTextContent());
				} else {
					bean = bean.getClass().newInstance();
					list.add(bean);
				}
			}
		}
		return list;
	}

	public void save() throws DOMException, XPathExpressionException,
			SecurityException, IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException,
			TransformerException {
		Element xmlroot = doc.getDocumentElement();
		Element rowroot = doc.createElement("rowdata");
		Field[] fieldarray = bean.getClass().getDeclaredFields();
		int fieldcount = fieldarray.length;
		for (int i = 0; i < fieldcount; i++) {
			String elementname = fieldarray[i].getName();
			Element childelement = doc.createElement(elementname);
			if (elementname.equals(this.pkName)) {
				childelement.setTextContent(this.createPK());
			} else {
				childelement.setTextContent(this.getBeanValue(elementname));
			}
			rowroot.appendChild(childelement);
		}
		xmlroot.appendChild(rowroot);
		this.writeXML();
	}

	private String getBeanValue(String elementname) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String methodname = StringTool.parseToGetField(elementname);
		Method method;
		String result = null;
		method = bean.getClass().getDeclaredMethod(methodname,
				new Class<?>[] {});
		result = (String) method.invoke(bean, new Object[] {});

		return result;

	}

	public void update(Object bean) throws XPathExpressionException,
			SecurityException, NoSuchMethodException, DOMException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, TransformerException {
		String id = this.getId();
		Element updateElement = ElementTool.find("//rowdata[" + pkName + "="
				+ id + "]", doc);
		Field[] field = bean.getClass().getDeclaredFields();
		for (Field f : field) {
			String childElement = f.getName();
			Element child = (Element) updateElement.getElementsByTagName(
					childElement).item(0);
			if (childElement.equals(pkName)) {
			} else {
				String methodname = StringTool.parseToGetField(childElement);
				Method method;
				method = bean.getClass().getDeclaredMethod(methodname,
						new Class<?>[] {});
				child.setTextContent((String) method.invoke(bean,
						new Object[] {}));
			}
		}
		writeXML();
	}

	private String createPK() throws XPathExpressionException {
		Element element = ElementTool
				.find("//primarykey/primarykey-count", doc);
		int pkvalue = this.pkCount + this.pkIncrement;
		if (pkvalue < this.pkInit) {
			pkvalue = this.pkInit;
		}
		element.setTextContent(new Integer(pkvalue).toString());
		return new Integer(pkvalue).toString();
	}

	public void delete() throws XPathExpressionException, SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException,
			TransformerException {
		String id = getId();
		Element delElement = ElementTool.find("//rowdata[" + pkName + "=" + id
				+ "]", doc);
		delElement.getParentNode().removeChild(delElement);
		writeXML();
	}

	public String getId() throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String methodname = StringTool.parseToGetField(pkName);
		Method method = null;
		String id = null;
		method = bean.getClass().getDeclaredMethod(methodname,
				new Class<?>[] {});
		id = (String) method.invoke(bean, new Object[] {});
		return id;
	}

	public void initPk() throws XPathExpressionException {
		NodeList nodelist = ElementTool.findAll("//primarykey/*", doc);
		for (int i = 0; i < nodelist.getLength(); i++) {
			Element element = (Element) nodelist.item(i);
			if (element.getTagName().equals("primarykey-name")) {
				this.pkName = element.getTextContent();
			}
			if (element.getTagName().equals("primarykey-init")) {
				pkInit = Integer.parseInt(element.getTextContent());
			}
			if (element.getTagName().equals("primarykey-increment")) {
				this.pkIncrement = Integer.parseInt(element.getTextContent());
			}
			if (element.getTagName().equals("primarykey-count")) {
				this.pkCount = Integer.parseInt(element.getTextContent());
			}
		}

	}

}