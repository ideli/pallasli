package com.lyt.pallas.basic.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lyt.pallas.basic.util.xml.xmlaccessframework.util.StringTool;
import com.pallasli.utils.StringUtils;

public class XMLUtil {
	public List<? extends Object> getModelFromXML(String relativePath,
			Class<?> objClass) {

		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = objClass.getDeclaredMethods();
		for (Method m : methods) {
			map.put(m.getName(), m);
		}
		List<Object> rtnList = new ArrayList<Object>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ relativePath;
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			NodeList nodeList = document.getElementsByTagName("data");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element selElement = (Element) nodeList.item(i);
				NodeList childList = selElement.getChildNodes();
				Object bean = objClass.newInstance();
				Method method = bean.getClass().getDeclaredMethod("setId",
						new Class<?>[] { Integer.class });
				method.invoke(
						bean,
						new Object[] { new Integer(selElement
								.getAttribute("id")) });

				for (int j = 0; j < childList.getLength(); j++) {
					Node childElement = childList.item(j);
					if (childElement.getNodeType() == Node.ELEMENT_NODE) {
						String beanVarName = childElement.getNodeName();
						String methodname = StringTool
								.parseToSetField(beanVarName);
						method = map.get(methodname);

						// 如何实现未知类型强转？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
						// 如何实现未知参数个数的激发？？？？？？？？？？？？？？？？？？？？？？？？？？？？
						Type[] types = method.getGenericParameterTypes();
						if (types.length == 1) {
							// String value = childElement.getTextContent();
							// if (value != null) {
							// value.replaceAll(",",
							// SpecialSymbolConstant.COMMA);
							// Object v = value;
							// Class cls = (Class) types[0];
							//
							// method.invoke(bean,
							// new Object[] { cls.cast(v) });
							// }

							if (types[0].equals(String.class)) {
								method.invoke(bean, new Object[] { childElement
										.getTextContent() });
							} else {
								if (types[0].equals(Integer.class)) {
									method.invoke(
											bean,
											new Object[] { new Integer(
													childElement
															.getTextContent()) });
								} else {
									if (types[0].equals(Double.class)) {
										method.invoke(
												bean,
												new Object[] { new Double(
														childElement
																.getTextContent()) });
									} else {
										if (types[0].equals(Boolean.class)) {
											method.invoke(
													bean,
													new Object[] { new Boolean(
															childElement
																	.getTextContent()) });
										}
									}
								}
							}
						}

					}

				}
				rtnList.add(bean);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return rtnList;
	}

	public Boolean save(Object bu, String relativePath) {
		boolean flag = false;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;

		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ relativePath;
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			NodeList nodeList = document.getElementsByTagName("data");

			Map<String, Method> map = new HashMap<String, Method>();
			Method[] methods = bu.getClass().getDeclaredMethods();
			for (Method m : methods) {
				map.put(m.getName(), m);
			}

			Method getId = map.get("getId");
			Object objId = getId.invoke(bu, new Object[] {});

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element selElement = (Element) nodeList.item(i);
				String id = selElement.getAttribute("id");

				if (id.equals(objId.toString())) {

					Set<String> keySet = map.keySet();
					Iterator<String> it = keySet.iterator();
					while (it.hasNext()) {
						String key = it.next();
						if (key.startsWith("get") && !key.equals("getId")) {
							Method getM = map.get(key);
							NodeList dataChildList = selElement
									.getElementsByTagName(StringUtils
											.removePreGetOrSet(key));
							if (dataChildList.getLength() == 1) {
								dataChildList.item(0).setTextContent(
										(String) getM.invoke(bu,
												new Object[] {}));
							}
							if (dataChildList.getLength() == 0) {
								Element element = document
										.createElement(StringUtils
												.removePreGetOrSet(key));
								Object value = getM.invoke(bu, new Object[] {});
								if (value != null) {
									element.setTextContent(value.toString());
									selElement.appendChild(element);
								}
							}
						}
					}

					// NodeList childList = selElement.getChildNodes();
					// for (int j = 0; j < childList.getLength(); j++) {
					// Node childElement = childList.item(j);
					//
					// if (childElement.getNodeType() == Node.ELEMENT_NODE) {
					// String beanVarName = childElement.getNodeName();
					// String methodname = StringTool
					// .parseToGetField(beanVarName);
					// Method method = null;
					// method = bu.getClass().getDeclaredMethod(
					// methodname, new Class<?>[] {});
					//
					// childElement.setTextContent((String) method.invoke(
					// bu, new Object[] {}));
					// }
					// }
				}
			}
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.transform(new DOMSource(document),
					new StreamResult(new FileOutputStream(new File(this
							.getClass().getResource("/").getPath()
							+ "basicUnitssss.xml"))));
			flag = true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return flag;

	}

	public String getTableMappingFromXML(Class<?> objClass) {
		String tableName = null;
		String objName = objClass.getName();
		String packageName = null;
		if (objName != null) {
			int index = objName.lastIndexOf(".");
			packageName = objName.substring(0, index);
			if (index < objName.length()) {
				objName = objName.substring(index + 1);
			}
		}
		String xmlName = StringUtils.alterFirstCharToLower(objName);
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = objClass.getDeclaredMethods();
		for (Method m : methods) {
			map.put(m.getName(), m);
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ "sql/gto/" + xmlName + ".gto.xml";
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			NodeList nodeList = document.getElementsByTagName("package");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Element selElement = (Element) nodeList.item(i);
				if (packageName.equals(selElement.getAttribute("name"))) {
					NodeList childList = selElement
							.getElementsByTagName("class");
					for (int j = 0; j < childList.getLength(); j++) {
						Element childElement = (Element) childList.item(j);
						if (objName.equals(childElement.getAttribute("name"))) {
							tableName = childElement.getAttribute("table");
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return tableName;
	}
}
