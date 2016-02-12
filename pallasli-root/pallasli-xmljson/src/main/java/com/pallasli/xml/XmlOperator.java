package com.pallasli.xml;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

@SuppressWarnings("rawtypes")
public class XmlOperator {
	private Document document;
	private String filePath; // 文件所在的实际物理路径

	public static void main(String[] args) {
		String filepath = System.getProperty("user.dir")
				+ "/XmlFiles/LocalServicesConfig.xml";

		XmlOperator operator = new XmlOperator(filepath);
		operator.getXmlFile();
		// operator.addChild("item", "t1","services2", "");
		// operator.updateChild("item", "t1", "services2", "bb");
		// operator.deleteChild("item", "t1", "services2");
		String str = operator.getChild("item", "t3", "protocol");
		System.out.println(str);
	}

	public XmlOperator(String filepath) {
		this.document = null;
		this.filePath = filepath;
	}

	/**
	 * 创建XML文件
	 * 
	 * @param rootName
	 *            :根节点名称
	 */
	public void createXMLFile(String rootName) {
		if (!fileExist()) {
			this.document = DocumentHelper.createDocument();
			this.document.addElement(rootName);
			saveXMLFile(this.document);
		}
	}

	/**
	 * 获取已存在的XML文档
	 * 
	 * @return
	 */
	public Document getXmlFile() {
		if (fileExist()) {
			SAXReader reader = new SAXReader();
			try {
				this.document = reader.read(new File(filePath));
			} catch (DocumentException e) {
			} finally {
				reader = null;
			}
		} else {
			// 写日志
			System.exit(0);
		}
		return this.document;
	}

	/**
	 * 添加元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要添加的节点名称
	 * @param childValue
	 *            ：要添加的节点值
	 */
	public void addChild(String fatherNode, String fatherAttr,
			String childName, String childValue) {
		ChildOperator(fatherNode, fatherAttr, childName, childValue, "add");
	}

	/**
	 * 修改元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要修改的节点名称
	 * @param childValue
	 *            ：要修改成的节点值
	 */
	public void updateChild(String fatherNode, String fatherAttr,
			String childName, String childValue) {
		ChildOperator(fatherNode, fatherAttr, childName, childValue, "update");
	}

	/**
	 * 删除元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要删除的节点名称
	 */
	public void deleteChild(String fatherNode, String fatherAttr,
			String childName) {
		ChildOperator(fatherNode, fatherAttr, childName, "", "delete");
	}

	/**
	 * 获取某个元素数值
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要删除的节点名称
	 */
	public String getChild(String fatherNode, String fatherAttr,
			String childName) {
		String result = "";
		result = ChildOperator(fatherNode, fatherAttr, childName, "", "get");
		return result;
	}

	/**
	 * 子节点操作
	 * 
	 * @param fatherNode
	 *            :父节点名称
	 * @param fatherAttr
	 *            :父节点属性
	 * @param childName
	 *            ：要修改的节点
	 * @param childValue
	 *            ：修改后的节点值
	 * @param operator
	 *            : 要执行的操作名称
	 */
	private synchronized String ChildOperator(String fatherNode,
			String fatherAttr, String childName, String childValue,
			String operator) {
		String result = "";
		if (this.document == null) {
			// 写日志
			// String loginfo = "Has not get XML file, add err!";
			return "null";
		}
		Element root = this.document.getRootElement();// 获取根节点名称
		if (!root.getName().equals(fatherNode)) { // 如果不是在根节点下添加
			result = XmlElementOperator(root, fatherNode, fatherAttr,
					childName, childValue, operator);
		} else {
			if (operator.equals("add")) {
				Element childelement = root.addElement(childName);// 根节点不存在元素属性值
				childelement.setText(childValue);
				saveXMLFile(this.document);
			} else if (operator.equals("update")) {
				List childelements = root.elements(childName);
				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();
					everyone.setText(childValue); // 修改该元素值
				}
				saveXMLFile(this.document);
			} else if (operator.equals("delete")) {
				List childelements = root.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();
					root.remove(everyone);
				}
				saveXMLFile(this.document);
			} else if (operator.equals("get")) {
				List childelements = root.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();
					result = everyone.getText();
				}
				saveXMLFile(this.document);
			}
		}
		return result;
	}

	/**
	 * 递归元素操作
	 * 
	 * @param element
	 *            :要递归的元素
	 * @param fatherNode
	 *            :父节点名称
	 * @param fatherAttr
	 *            :父节点属性
	 * @param childName
	 *            ：要进行操作的节点
	 * @param childValue
	 *            ：操作后的节点值
	 * @param operator
	 *            : 要执行的操作名称
	 */
	private synchronized String XmlElementOperator(Element element,
			String fatherNode, String fatherAttr, String childName,
			String childValue, String operator) {
		String result = "";
		List elements = element.elements();
		for (Iterator it = elements.iterator(); it.hasNext();) {
			Element currentelement = (Element) it.next();
			if (!currentelement.getName().equals(fatherNode)) { // 当前元素并不是我们要查找的父元素时，继续查找
				XmlElementOperator(currentelement, fatherNode, fatherAttr,
						childName, childValue, operator);// 递归调用
			} else {
				if (currentelement.attributeCount() > 0) { // 当前元素存在属性值时
					for (Iterator list = currentelement.attributeIterator(); list
							.hasNext();) { // 遍历属性值
						Attribute attr = (Attribute) list.next(); // 获取属性值队列中的第一个元素
						if (attr.getValue().equals(fatherAttr)) {// 根据属性值确定惟一的父元素
							if (operator.equals("add")) {// 添加元素
								Element childelement = currentelement
										.addElement(childName); // 给当前元素添加一个子元素
								childelement.setText(childValue); // 设置子元素的数值
							} else if (operator.equals("update")) {// 修改某个元素
								List childelements = currentelement
										.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
								for (Iterator childs = childelements.iterator(); childs
										.hasNext();) {
									Element everyone = (Element) childs.next();
									everyone.setText(childValue); // 修改该元素值
								}
							} else if (operator.equals("delete")) { // 删除某个指定的元素
								List childelements = currentelement
										.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
								for (Iterator childs = childelements.iterator(); childs
										.hasNext();) {
									Element everyone = (Element) childs.next();
									currentelement.remove(everyone);
								}
							} else if (operator.equals("get")) {
								List childelements = currentelement
										.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
								for (Iterator childs = childelements.iterator(); childs
										.hasNext();) {
									Element everyone = (Element) childs.next();
									result = everyone.getText();
								}
							} else {
								// 写日志
								// String loginfo =
								// "XmlFile Operator not exists!";
							}
						}
					}
				}
			}
		}
		saveXMLFile(this.document);
		return result;
	}

	/**
	 * 保存XML文件
	 * 
	 * @param document
	 *            : XML文件名
	 */
	private void saveXMLFile(Document document) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(
					new FileWriter(new File(filePath)), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 判断XML文件是否存在.
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean fileExist() {
		java.io.File objFile = new java.io.File(this.filePath);
		if (objFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
