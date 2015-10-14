package com.pallasli.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jXmlFileUtils {

	/**
	 * 创建
	 * 
	 * @param path
	 * @return
	 */
	public static boolean create(String path) {

		Document document = DocumentHelper.createDocument();// 建立document对象，用来操作xml文件
		Element booksElement = document.addElement("knowledges");// 建立根节点
		booksElement.addComment("Thisis a test for dom4j ");// 加入一行注释
		Element bookElement = booksElement.addElement("maxId");
		bookElement.setText("0");

		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(path)));

			writer.write(document);

			writer.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return true;
	}

	public static Document load(String path) {
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {

			document = saxReader.read(path); // 读取XML文件,获得document对象

		} catch (Exception ex) {
			try {
				document = saxReader.read(new FileInputStream(path));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 读取XML文件,获得document对象
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return document;
	}

	public static void writeTo(Document doc, String encoding)
			throws UnsupportedEncodingException, IOException {

		OutputFormat format = OutputFormat.createPrettyPrint();

		format.setEncoding("gb2312");

		XMLWriter writer = new XMLWriter(System.out, format);

		writer.write(doc);

		writer.flush();

		return;

	}

	public static boolean doc2XmlFile(Document document, String path) {

		boolean flag = true;

		try {

			XMLWriter writer = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(path), "UTF-8"));

			writer.write(document);

			writer.close();

		} catch (Exception ex) {

			flag = false;

			ex.printStackTrace();

		}

		System.out.println(flag);

		return flag;

	}

	/**
	 * 通过读取XML文件，返回一个XML文档对象
	 * 
	 * @param pathXML
	 *            XML文件路径
	 * @return Document对象
	 * @throws DocumentException
	 *             获取Document对象时出现的异常
	 */
	public static Document getDocObjByFile(String pathXML)
			throws DocumentException {
		SAXReader reader = new SAXReader(); // SAXReader主要用于解析XML文件
		Document document = reader.read(new File(pathXML)); // 从指定的文件中读取Document
		return document;
	}

	/**
	 * 通过读取XML文本，返回一个XML文档对象
	 * 
	 * @param textXML
	 *            XML文本
	 * @return Document对象
	 * @throws DocumentException
	 *             获取Document对象时出现的异常
	 */
	public static Document getDocObjByText(String textXML)
			throws DocumentException {
		// DocumentHelper 是生成 XML 文档节点的 dom4j API 工厂类
		// 将textXML解析为一个XML文档并返回一个新创建的Document
		Document document = DocumentHelper.parseText(textXML);
		return document;
	}

	/**
	 * 自动创建一个Document对象,主要用于创建XML文件时使用
	 * 
	 * @return Document对象
	 */
	public static Document getDocObjByAuto() {
		// 创建一个Document对象
		Document document = DocumentHelper.createDocument();
		return document;
	}

	// 既然得到Dcument对象了，那么下来就是要读取各节点了，读取之前要获得根节点，否则无法读取：
	// Element root = document.getRootElement(); //获取根节点
	//
	// 以下是获取各节点的方法(一定要对照XML文件来看哟，否则可能会迷糊的！！！！！)：
	//
	// //遍历根节点下的子节点
	// for (Iterator it = root.elementIterator(); it.hasNext();) {
	// Element child = (Element) it.next();
	// String name = child.getName(); //获取节点的名字
	// String text = child.getText(); //读取节点的内容
	// System.out.println("节点名：" + name + "     内容：" + text);
	// }
	//
	// //遍历根节点下指定的子节点a
	// for (Iterator it = root.elementIterator("a"); it.hasNext();) {
	// Element child = (Element) it.next();
	// String name = child.getName(); //获取节点的名字
	// String text = child.getText(); //读取节点的内容
	// System.out.println("节点名：" + name + "     内容：" + text);
	// }
	//
	// //遍历根节点的子节点的属性id
	// for (Iterator it = root.elementIterator(); it.hasNext();) {
	// Element child = (Element) it.next();
	// String name = child.getName(); //获取节点的名字
	// Attribute att = child.attribute("id"); //获取属性id
	// String text1 = att.getText(); //读取属性的值
	// System.out.println("方法一     节点名：" + name + "     属性id:[ " + text1 +
	// " ]");
	// String text2 = child.attributeValue("id"); //读取属性id的值
	// System.out.println("方法二     节点名：" + name + "     属性id:[ " + text2 +
	// " ]");
	// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	// }
	//
	// //遍历f节点的所有属性
	// Element elf = root.element("f"); //获取指定的节点f
	// System.out.println("方法一：" + elf.getText()); //方法一：获取指定节点的内容
	// System.out.println("方法二：" + root.elementText("f")); //方法二：获取指定节点的内容
	// System.out.println("----------------------------------------------------");
	// for (Iterator it = elf.attributeIterator(); it.hasNext();) {
	// Attribute att = (Attribute) it.next();
	// String name = att.getName(); //获取属性名字
	// String text = att.getText(); ////获取属性值
	// System.out.println("属性名：" + name + "     属性值：" + text);
	// }
	//
	// //遍历d节点的所有子节点 方法一：
	// Element eld = root.element("d"); //获取指定的节点
	// for (Iterator it = eld.elementIterator(); it.hasNext();) {
	// Element el = (Element) it.next();
	// String name = el.getName();
	// String text = el.getText();
	// System.out.println("方法一     节点名：" + name + "     内容：" + text);
	// }
	//
	// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	//
	// //遍历d节点的所有子节点 方法二：
	// List eld1 = root.selectNodes("/root/d/*"); //获取指定的节点
	// for (Iterator it = eld1.iterator(); it.hasNext();) {
	// Node el = (Node) it.next();
	// String name = el.getName();
	// String text = el.getText();
	// System.out.println("方法二     节点名：" + name + "     内容：" + text);
	// }
	//
	// //遍历d节点中d2子节点的所有属性
	// Element eld2 = root.element("d").element("d2"); //获取指定的节点
	// for (Iterator it = eld2.attributeIterator(); it.hasNext();) {
	// Attribute att = (Attribute) it.next();
	// String name = att.getName();
	// String text = att.getText();
	// System.out.println("属性名：" + name + "     属性值：" + text);
	// }
	//
	// //遍历d1节点下的子节点d11 方法一：
	// List d11 = root.element("d").element("d1").elements("d11");
	// for (Iterator it = d11.iterator(); it.hasNext();) {
	// Element child = (Element) it.next();
	// String name = child.getName(); //获取节点的名字
	// String text = child.getText(); //读取节点的内容
	// System.out.println("方法一     节点名：" + name + "     内容：" + text);
	// }
	//
	// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	//
	// //遍历d1节点下的子节点d11 方法二：
	// List nodes = root.selectNodes("/root/d/d1/d11");
	// for (Iterator it = nodes.iterator(); it.hasNext();) {
	// Node child = (Node) it.next();
	// String name = child.getName(); //获取节点的名字
	// String text = child.getText(); //读取节点的内容
	// System.out.println("方法二     节点名：" + name + "     内容：" + text);
	// }
	//
	// //根据属性id遍历某节点
	// List elb2 = root.selectNodes("/root/b[@id='b2']");
	// for (Iterator it = elb2.iterator(); it.hasNext();) {
	// Node b2 = (Node) it.next();
	// String name = b2.getName();
	// String text = b2.getText();
	// String id = b2.valueOf("@id"); //得到属性id的值
	// System.out.println("节点名：" + name + "     内容：" + text + "    id：" + id);
	// }
	//
	// //根据属性id遍历某节点的子节点
	// List eldd = root.selectNodes("/root/d[@id='d1']/*");
	// for (Iterator it = eldd.iterator(); it.hasNext();) {
	// Node b2 = (Node) it.next();
	// String name = b2.getName();
	// String text = b2.getText();
	// String id = b2.valueOf("@id");
	// System.out.println("节点名：" + name + "     内容：" + text + "    id：" + id);
	// }

	@SuppressWarnings("unchecked")
	public static void modifyXml(String xmlFilePath) throws DocumentException,
			IOException {
		File file = new File(xmlFilePath);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		List<Element> list;

		/*
		 * 修改EntityProps
		 */
		list = doc.selectNodes("//Entity_Groups//Entity//EntityProps");
		System.out.println("需要修改的EntityProps一共有:" + list.size() + "处。");
		for (int i = 0; i < list.size(); i++) {
			Element elt = list.get(i);
			Node node = elt.selectSingleNode("Definition");
			if (node == null) {
				Element info = elt.addElement("Definition");
				info.addAttribute("xml:space", "preserve");
				info.addText(elt.selectSingleNode("Name").getText());
				// elt.selectSingleNode("Definition").setText(elt.selectSingleNode("Name").getText());
			} else {
				elt.selectSingleNode("Definition").setText(
						elt.selectSingleNode("Name").getText());
			}

		}

		/*
		 * 修改AttributeProps
		 */
		list = doc
				.selectNodes("//Entity_Groups//Entity//Attribute_Groups//Attribute//AttributeProps");
		System.out.println("需要修改的AttributeProps一共有:" + list.size() + "处。");
		for (int i = 0; i < list.size(); i++) {
			Element elt = list.get(i);
			Node node = elt.selectSingleNode("Definition");
			if (node == null) {
				// 增加节点
				Element info = elt.addElement("Definition");
				info.addAttribute("xml:space", "preserve");
				info.addText(elt.selectSingleNode("Name").getText());
				// elt.selectSingleNode("Definition").setText(elt.selectSingleNode("Name").getText());
			} else {
				elt.selectSingleNode("Definition").setText(
						elt.selectSingleNode("Name").getText());
			}
		}
		System.out.println("修改完毕！");

		XMLWriter writer = new XMLWriter(new FileOutputStream(file));
		writer.write(doc);
		writer.close();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("请输入程序所需的配置文件路径作为参数:");
			System.out.println("1、要修改的xml文件的名称。");
		} else {
			String xmlProperty = args[0];
			System.out.println(xmlProperty);
			try {
				Dom4jXmlFileUtils.modifyXml(xmlProperty);
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
