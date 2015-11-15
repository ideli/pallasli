package com.pallasli.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;

public class XmlUtils {

	/**
	 * 读取XML文件转化成list对象
	 * 
	 * @param path
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> parseXmlToList(String path, Class<T> cls)
			throws Exception {
		List<T> list = new ArrayList<T>();
		Document doc = null;
		SAXReader reader = new SAXReader();
		doc = reader.read(new File(path));
		Element rootElt = doc.getRootElement();
		Iterator<?> iter = rootElt.elementIterator();
		while (iter.hasNext()) {
			T obj = cls.newInstance();
			Element recordEle = (Element) iter.next();

			Iterator<?> attrIt = recordEle.elementIterator();
			while (attrIt.hasNext()) {
				Element attr = (Element) attrIt.next();
				String fieldSetter = attr.getName();
				fieldSetter = ("" + fieldSetter.charAt(0)).toUpperCase()
						+ fieldSetter.substring(1);
				fieldSetter = "set" + fieldSetter;
				Class[] paraTypes = new Class[] { String.class };
				try {
					// Method method = cls.getMethod(fieldSetter, paraTypes);
					// String[] paraValues = new String[] {
					// attr.getStringValue() };
					// method.invoke(obj, paraValues);
					// Class<?> cl = Class.forName(cls.getName());
					Map<String, Field> fieldMap = Dom4jXmlParseUtils
							.getAllFields(cls);
					Field field = fieldMap.get(attr.getName());
					field.setAccessible(true);
					Dom4jXmlParseUtils.convertValue(field, obj,
							attr.getStringValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
		}
		return list;
	}

	/**
	 * 读取XML文件转化成list对象
	 * 
	 * @param path
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> getObjFromXml(String path, Class<T> cls)
			throws Exception {
		List<T> list = new ArrayList<T>();
		Document doc = null;
		SAXReader reader = new SAXReader();
		doc = reader.read(new File(path));
		Element rootElt = doc.getRootElement();
		Iterator<?> iter = rootElt.elementIterator();
		while (iter.hasNext()) {
			T obj = cls.newInstance();
			Element recordEle = (Element) iter.next();
			Iterator<?> attrIt = recordEle.attributeIterator();
			while (attrIt.hasNext()) {
				DefaultAttribute attr = (DefaultAttribute) attrIt.next();
				String fieldSetter = attr.getName();
				fieldSetter = ("" + fieldSetter.charAt(0)).toUpperCase()
						+ fieldSetter.substring(1);
				fieldSetter = "set" + fieldSetter;
				Class[] paraTypes = new Class[] { String.class };
				try {
					Method method = cls.getMethod(fieldSetter, paraTypes);
					String[] paraValues = new String[] { attr.getStringValue() };
					method.invoke(obj, paraValues);
				} catch (Exception e) {
				}
			}
			list.add(obj);
		}
		return list;
	}

	public void readStringXml(String xml) {
		Document doc = null;
		try {

			// ��ȡ������XML�ĵ�
			// SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������
			//
			// SAXReader reader = new SAXReader();
			// //User.hbm.xml��ʾ��Ҫ������xml�ĵ�
			// Document document = reader.read(new File("User.hbm.xml"));
			// �������ͨ�����xml�ַ��
			doc = DocumentHelper.parseText(xml); // ���ַ�תΪXML

			Element rootElt = doc.getRootElement(); // ��ȡ��ڵ�
			System.out.println("��ڵ㣺" + rootElt.getName()); // �õ���ڵ�����

			Iterator<?> iter = rootElt.elementIterator("head"); // ��ȡ��ڵ��µ��ӽڵ�head

			// ����head�ڵ�
			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("title"); // �õ�head�ڵ��µ��ӽڵ�titleֵ
				System.out.println("title:" + title);

				Iterator<?> iters = recordEle.elementIterator("script"); // ��ȡ�ӽڵ�head�µ��ӽڵ�script

				// ����Header�ڵ��µ�Response�ڵ�
				while (iters.hasNext()) {

					Element itemEle = (Element) iters.next();

					String username = itemEle.elementTextTrim("username"); // �õ�head�µ��ӽڵ�script�µ��ֽڵ�username��ֵ
					String password = itemEle.elementTextTrim("password");

					System.out.println("username:" + username);
					System.out.println("password:" + password);
				}
			}
			Iterator<?> iterss = rootElt.elementIterator("body"); // /��ȡ��ڵ��µ��ӽڵ�body
			// ����body�ڵ�
			while (iterss.hasNext()) {

				Element recordEless = (Element) iterss.next();
				String result = recordEless.elementTextTrim("result"); // �õ�body�ڵ��µ��ӽڵ�resultֵ
				System.out.println("result:" + result);

				Iterator<?> itersElIterator = recordEless
						.elementIterator("form"); // ��ȡ�ӽڵ�body�µ��ӽڵ�form
				// ����Header�ڵ��µ�Response�ڵ�
				while (itersElIterator.hasNext()) {

					Element itemEle = (Element) itersElIterator.next();

					String banlce = itemEle.elementTextTrim("banlce"); // �õ�body�µ��ӽڵ�form�µ��ֽڵ�banlce��ֵ
					String subID = itemEle.elementTextTrim("subID");

					System.out.println("banlce:" + banlce);
					System.out.println("subID:" + subID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void readFileXml(String xmlPath) {
		Document doc = null;
		try {

			// ��ȡ������XML�ĵ�
			// SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������
			//
			SAXReader reader = new SAXReader(); // User.hbm.xml��ʾ��Ҫ������xml�ĵ�
			doc = reader.read(new File(xmlPath));
			// �������ͨ�����xml�ַ��
			// doc = DocumentHelper.parseText(xml); // ���ַ�תΪXML

			Element rootElt = doc.getRootElement(); // ��ȡ��ڵ�
			System.out.println("��ڵ㣺" + rootElt.getName()); // �õ���ڵ�����

			Iterator<?> iter = rootElt.elementIterator("head"); // ��ȡ��ڵ��µ��ӽڵ�head

			// ����head�ڵ�
			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				String title = recordEle.elementTextTrim("title"); // �õ�head�ڵ��µ��ӽڵ�titleֵ
				System.out.println("title:" + title);

				Iterator<?> iters = recordEle.elementIterator("script"); // ��ȡ�ӽڵ�head�µ��ӽڵ�script

				// ����Header�ڵ��µ�Response�ڵ�
				while (iters.hasNext()) {

					Element itemEle = (Element) iters.next();

					String username = itemEle.elementTextTrim("username"); // �õ�head�µ��ӽڵ�script�µ��ֽڵ�username��ֵ
					String password = itemEle.elementTextTrim("password");

					System.out.println("username:" + username);
					System.out.println("password:" + password);
				}
			}
			Iterator<?> iterss = rootElt.elementIterator("body"); // /��ȡ��ڵ��µ��ӽڵ�body
			// ����body�ڵ�
			while (iterss.hasNext()) {

				Element recordEless = (Element) iterss.next();
				String result = recordEless.elementTextTrim("result"); // �õ�body�ڵ��µ��ӽڵ�resultֵ
				System.out.println("result:" + result);

				Iterator<?> itersElIterator = recordEless
						.elementIterator("form"); // ��ȡ�ӽڵ�body�µ��ӽڵ�form
				// ����Header�ڵ��µ�Response�ڵ�
				while (itersElIterator.hasNext()) {

					Element itemEle = (Element) itersElIterator.next();

					String banlce = itemEle.elementTextTrim("banlce"); // �õ�body�µ��ӽڵ�form�µ��ֽڵ�banlce��ֵ
					String subID = itemEle.elementTextTrim("subID");

					System.out.println("banlce:" + banlce);
					System.out.println("subID:" + subID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// private Properties props;
	//
	// public Properties getProps() {
	// return this.props;
	// }
	//
	// public void parse(String filename) throws Exception {
	// ConfigParser handler = new ConfigParser();
	// SAXParserFactory factory = SAXParserFactory.newInstance();
	// factory.setNamespaceAware(false);
	// factory.setValidating(false);
	//
	// SAXParser parser = factory.newSAXParser();
	// try {
	// FileInputStream is = new FileInputStream(new File(filename));
	// parser.parse(is, handler);
	// // parser.parse(filename, handler);
	// props = handler.getProps();
	// } catch (Exception e) {
	// } finally {
	// factory = null;
	// parser = null;
	// handler = null;
	// }
	// }

	public String transFormer(String xmlFileName, String xslFileName) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		String outStr = "";
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(
					xslFileName));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			transformer.transform(new StreamSource(xmlFileName),
					new StreamResult(baos));
			outStr = baos.toString("UTF-8");
		} catch (Exception e) {
		}
		return outStr;
	}
}
