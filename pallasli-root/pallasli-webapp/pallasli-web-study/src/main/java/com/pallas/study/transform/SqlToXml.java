package com.pallas.study.transform;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.pallas.knowledge.bean.KnowledgeType;
import com.pallas.knowledge.dao.impl.KnowledgeTypeDAOImpl;
import com.pallasli.constant.SystemConstant;
import com.pallasli.utils.Dom4jXmlFileUtils;

public class SqlToXml {

	public static void main(String[] a) {
		new SqlToXml().generateKnowledgeTypes();
	}

	public String getPath(Element node) {
		String dir = SystemConstant.WEB_ROOT + "test/学习总结/";
		new File(dir).mkdir();
		String path = "";
		Node parentnode = node.getParent();
		while (parentnode != null && parentnode.selectSingleNode("text") != null) {
			path += parentnode.selectSingleNode("text").getText() + "/";
			parentnode = parentnode.getParent();
		}
		path = dir + path + node.selectSingleNode("text").getText();
		return path;
	}

	public void generateChildKnowledgeTypes(Element nodes) {
		Node idNode = nodes.selectSingleNode("id");
		String id = "0";
		if (idNode != null)
			id = idNode.getText();
		List<KnowledgeType> list = new KnowledgeTypeDAOImpl().selectChildrenByParentId(Long.parseLong(id));
		for (KnowledgeType k : list) {
			Element node = nodes.addElement("node");
			node.addElement("id").setText(k.getId() + "");
			node.addElement("text").setText(k.getText());
			node.addElement("leaf").setText(k.isLeaf() + "");
			node.addElement("expanded").setText(k.isExpanded() + "");
			String path = getPath(node);
			if (k.isLeaf()) {
				Dom4jXmlFileUtils.create(path + ".xml");

			} else {
				new File(path).mkdir();
			}
			generateChildKnowledgeTypes(node);
		}
	}

	public void generateKnowledgeTypes() {
		List<KnowledgeType> list = new KnowledgeTypeDAOImpl().selectChildrenByParentId(0l);
		int count = new KnowledgeTypeDAOImpl().count();
		Dom4jXmlFileUtils.create(SystemConstant.WEB_ROOT + "test/knowledgesTypeTree.xml");

		Document doc = Dom4jXmlFileUtils.load(SystemConstant.WEB_ROOT + "test/knowledgesTypeTree.xml");
		Element nodes = doc.getRootElement();
		nodes.selectSingleNode("//maxId").setText(count + "");
		generateChildKnowledgeTypes(nodes);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");// 设置XML文件的编码格式
		XMLWriter writer;
		try {
			writer = new XMLWriter(
					new FileWriterWithEncoding(SystemConstant.WEB_ROOT + "test/knowledgesTypeTree.xml", "UTF-8"),
					format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void generateKnowledges() {

	}
}
