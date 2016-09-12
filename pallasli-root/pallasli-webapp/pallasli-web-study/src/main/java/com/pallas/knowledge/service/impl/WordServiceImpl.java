package com.pallas.knowledge.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.knowledge.bean.TreeNode;
import com.pallas.knowledge.service.WordService;
import com.pallasli.constant.SystemConstant;
import com.pallasli.utils.Dom4jXmlFileUtils;
import com.pallasli.utils.Dom4jXmlParseUtils;

public class WordServiceImpl implements WordService {
	@SuppressWarnings("unchecked")
	@Override
	public List<TreeNode> loadWordType(JsonArray data) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		JsonObject addNode = data.get(0).getAsJsonObject();
		String node = addNode.has("node") ? addNode.get("node").getAsString() : "";
		Document doc = com.pallasli.utils.Dom4jXmlFileUtils.load(SystemConstant.WEB_ROOT + "data/WordsTypeTree.xml");
		try {
			list = (List<TreeNode>) Dom4jXmlParseUtils.parseXml2List(doc, TreeNode.class, "node[id='" + node + "']");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TreeNode> addWordType(JsonArray data) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		JsonObject addNode = data.get(0).getAsJsonObject();
		String path = addNode.has("path") ? addNode.get("path").getAsString() : "";
		String parentId = addNode.has("parentId") ? addNode.get("parentId").getAsString() : "";
		boolean isLeaf = addNode.has("leaf") ? addNode.get("leaf").getAsBoolean() : false;
		String text = addNode.has("text") ? addNode.get("text").getAsString() : "";
		Document doc = Dom4jXmlFileUtils.load(SystemConstant.WEB_ROOT + "data/WordsTypeTree.xml");

		org.dom4j.Node maxidn = doc.selectSingleNode("//maxId");
		long maxid = Long.parseLong(maxidn.getText()) + 1;
		maxidn.setText(maxid + "");

		org.dom4j.Node n = doc.selectSingleNode("//node[id='" + parentId + "']");
		Element newN = ((Element) n).addElement("node");
		newN.addElement("id").setText(maxid + "");
		newN.addElement("text").setText(text);
		newN.addElement("leaf").setText(isLeaf + "");
		newN.addElement("expanded").setText(!isLeaf + "");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");// 设置XML文件的编码格式
		XMLWriter writer;
		try {
			if (isLeaf) {
				WordUtils.exportDoc(SystemConstant.WEB_ROOT + path + ".doc", " ");
			} else {
				new File(SystemConstant.WEB_ROOT + path).mkdir();
			}
			writer = new XMLWriter(
					new FileWriterWithEncoding(SystemConstant.WEB_ROOT + "data/WordsTypeTree.xml", "UTF-8"), format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TreeNode node = new TreeNode();
		node.setText(text);
		node.setId(maxid);
		node.setLeaf(isLeaf);
		node.setExpanded(!isLeaf);
		list.add(node);
		return list;
	}

	@Override
	public List<TreeNode> alterWordType(JsonArray data) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		// JsonObject addNode = data.get(0).getAsJsonObject();
		// String parentId = addNode.has("parentId") ? addNode.get("parentId")
		// .getAsString() : "";
		Document doc = Dom4jXmlFileUtils.load(SystemConstant.WEB_ROOT + "data/WordsTypeTree.xml");
		// org.dom4j.Node n = doc
		// .selectSingleNode("//node[id='" + parentId + "']");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");// 设置XML文件的编码格式
		XMLWriter writer;
		try {
			writer = new XMLWriter(
					new FileWriterWithEncoding(SystemConstant.WEB_ROOT + "data/WordsTypeTree.xml", "UTF-8"), format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TreeNode node = new TreeNode();
		node.setLeaf(true);
		list.add(node);
		return list;
	}
}
