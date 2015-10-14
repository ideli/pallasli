package com.pallasli.utils;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;

//import org.hibernate.lob.ClobImpl;

public class Dom4jXmlParseUtils {
	/**
	 * 反射设置实体不同类型字段的值 <暂时只支持 日期 字符串 boolean Integer值设置 待扩建>
	 * 
	 * @param field
	 * @param obj
	 * @param value
	 * @throws Exception
	 */
	public static void convertValue(Field field, Object obj, String value)
			throws Exception {

		String type = field.getGenericType().toString();
		System.out.println(field.getType().toString());
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (type.equals("class java.lang.Integer") || type.equals("int")) {
			field.set(obj, Integer.parseInt(value));
		} else if (type.equals("class java.lang.Long") || type.equals("long")) {
			field.set(obj, Long.parseLong(value));
		} else if (type.equals("class java.lang.Boolean")
				|| type.equals("boolean")) {
			field.set(obj, Boolean.parseBoolean(value));
		} else if (field.getGenericType().toString()
				.equals("class java.util.Date")) {
			field.set(obj, sim.parse(value));
		} else if (field.getGenericType().toString()
				.equals("interface java.sql.Clob")) {
			// field.set(obj, new ClobImpl(value));
		} else {
			field.set(obj, value);
		}
	}

	/**
	 * 解析xml文件返回保存cls的List集合，如果返回码resultCode=1时则返回List为null
	 * 
	 * @param xml
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static List<?> parseXml2List(String xml, Class<?> cls,
			String selectRegWithotRoot) throws Exception {
		Document doc = DocumentHelper.parseText(xml);
		return parseXml2List(doc, cls, selectRegWithotRoot);
	}

	public static Map<String, Field> getAllFields(Class<?> cl) {
		Map<String, Field> retmap = new HashMap<String, Field>();
		Field[] fields = cl.getDeclaredFields();
		for (Field f : fields) {
			retmap.put(f.getName(), f);
		}
		while (!cl.getSuperclass().equals(Object.class)) {
			cl = cl.getSuperclass();
			Field[] tmpfields = cl.getDeclaredFields();
			for (Field f : tmpfields) {
				retmap.put(f.getName(), f);
			}
		}

		return retmap;
	}

	@SuppressWarnings("unchecked")
	public static List<?> parseXml2List(List<Element> selectNodeLists,
			Class<?> cls) throws Exception {
		List<Object> lists = null;
		if (selectNodeLists != null && !selectNodeLists.isEmpty()) {
			lists = new ArrayList<Object>();
			for (Element e : selectNodeLists) {
				Class<?> cl = Class.forName(cls.getName());
				Map<String, Field> fieldMap = getAllFields(cls);
				Object ob = cl.newInstance();
				Node idNode = e.getParent().selectSingleNode("id");
				if (idNode != null) {
					String id = idNode.getText();
					try {

						Field field = fieldMap.get("parentId");
						if (field != null) {
							field.setAccessible(true);
							convertValue(field, ob, id);
						}
					} catch (Exception ep) {
						ep.printStackTrace();

					}
				}
				List<Element> li = e.elements();
				for (Element element2 : li) {
					String name = element2.getName();

					String value = element2.getText();
					try {
						Field field = fieldMap.get(name);

						if (field != null) {
							field.setAccessible(true);
							convertValue(field, ob, value);
						}
					} catch (Exception ep) {
						ep.printStackTrace();
					}

				}
				lists.add(ob);
			}
		}

		return lists;
	}

	@SuppressWarnings("unchecked")
	public static List<Element> getSelectNodeList(Document doc,
			String selectRegWithotRoot) {
		Node n = getSelectNode(doc, selectRegWithotRoot);
		List<Element> selectNodeLists = n.selectNodes("node");
		return selectNodeLists;
	}

	public static Node getSelectNode(Document doc, String selectRegWithotRoot) {
		// Element et = doc.getRootElement();
		// String root = et.getName();
		return doc.selectSingleNode("//" + selectRegWithotRoot);
	}

	public static List<?> parseXml2List(Document doc, Class<?> cls,
			String selectRegWithotRoot) throws Exception {
		return parseXml2List(getSelectNodeList(doc, selectRegWithotRoot), cls);

	}

	public static Element addElement(DOMElement parent, String childName) {
		DOMElement message = new DOMElement(childName);
		if (parent.hasChildNodes()) {
			org.w3c.dom.Node firstNode = parent.getFirstChild();
			parent.insertBefore(message, firstNode);
		} else {
			parent.add(message);
		}
		return message;
	}

	/**
	 * 解析xml文件返回保存cls的List集合，如果返回码resultCode=1时则返回List为null
	 * 
	 * @param url
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXml2List(URL url, Class<?> cls) throws Exception {
		List<Object> lists = null;
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(url);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<Element> list = doc.selectNodes("//" + root + "/resultCode");
		if (!list.isEmpty() && list.size() > 0) {
			Element element = list.get(0);
			String returnResult = element.getText();
			if (returnResult.equals("0")) {
				List<Element> father = doc.selectNodes("//" + root + "/"
						+ cls.getSimpleName() + "s");
				// 判断对象父节点是否有包含数据
				if (father != null && !father.isEmpty() && father.size() == 1) {
					List<Element> userLists = father.get(0).elements();
					if (userLists != null && !list.isEmpty()) {
						lists = new ArrayList<Object>();
						for (Element e : userLists) {
							List<Element> li = e.elements();
							Class<?> cl = Class.forName(cls.getName());
							Object ob = cl.newInstance();
							for (Element element2 : li) {
								String name = element2.getName();
								String value = element2.getText();
								Field field = ob.getClass().getDeclaredField(
										name);
								field.setAccessible(true);
								convertValue(field, ob, value);
							}
							lists.add(ob);
						}
					}
				}
			}

		}
		return lists;
	}

	/**
	 * 解析xml文件返回保存Map的集合，map中可能包含key值为returnCode、desc、totalCount等单字段.
	 * 也可能包含存储对象为List<cls>的集合值. 获取List值key cls_List
	 * 
	 * @param requestPath
	 * @param cls
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseXml2Map(String requestPath,
			Class<?> cls) throws Exception {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		// Document doc = saxReader.read(new File(requestPath));
		Document doc = saxReader.read(new URL(requestPath));
		Element et = doc.getRootElement();
		// 标记List是否为空
		// boolean bool = true ;
		// 根节点名字
		List<Element> rList = et.elements();
		for (Element element : rList) {
			List<Element> rLists = element.elements();
			if (!rLists.isEmpty() && rLists.size() > 0) {
				// bool = false;
				// 判断二级节点
				for (Element e : rLists) {
					List<Element> li = e.elements();
					Class<?> cl = Class.forName(cls.getName());
					Object ob = cl.newInstance();
					for (Element element2 : li) {
						String name = element2.getName();
						String value = element2.getText();
						Field field = ob.getClass().getDeclaredField(name);
						field.setAccessible(true);
						convertValue(field, ob, value);
					}
					lists.add(ob);
				}
			} else {
				maps.put(element.getName(), element.getText());
			}
			maps.put(cls.getSimpleName() + "_List", lists);
		}
		return maps;
	}

	/**
	 * 只获取返回码0为保存成功(true)1为保存失败(false)
	 */
	@SuppressWarnings("unchecked")
	public static boolean parseXmlReturnCode(String xml) {
		boolean bool = false;
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element et = doc.getRootElement();
			String root = et.getName();
			// 查看返回码是否为真.
			List<Element> list = doc.selectNodes("//" + root + "/resultCode");
			if (!list.isEmpty() && list.size() > 0) {
				Element element = list.get(0);
				String returnResult = element.getText();
				if (returnResult.equals("0")) {
					bool = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
}
