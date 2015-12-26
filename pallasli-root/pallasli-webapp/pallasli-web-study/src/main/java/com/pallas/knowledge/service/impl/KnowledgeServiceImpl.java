package com.pallas.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.knowledge.bean.Knowledge;
import com.pallas.knowledge.dao.KnowledgeDAO;
import com.pallas.knowledge.service.KnowledgeService;
import com.pallasli.constant.SystemConstant;

public class KnowledgeServiceImpl implements KnowledgeService {
	private KnowledgeDAO knowledgeDao;

	public KnowledgeDAO getKnowledgeDao() {
		return knowledgeDao;
	}

	public void setKnowledgeDao(KnowledgeDAO knowledgeDao) {
		this.knowledgeDao = knowledgeDao;
	}

	@Override
	public JsonObject changeKnowledge(JsonArray dataarr) {
		JsonObject data = dataarr.get(0).getAsJsonObject();
		JsonObject result = new JsonObject();
		// String xmlpath = data.has("xmlpath") ? data.get("xmlpath")
		// .getAsString() : "";
		String id = data.has("id") ? data.get("id").getAsString() : "";
		String parentId = data.has("parentId") ? data.get("parentId")
				.getAsString() : "";
		String caption = data.has("caption") ? data.get("caption")
				.getAsString() : "";
		String content = data.has("content") ? data.get("content")
				.getAsString() : "";
		Knowledge knowledge = new Knowledge();
		knowledge.setId(Long.parseLong(id));
		knowledge.setParentId(Long.parseLong(parentId));
		knowledge.setContent(content);
		knowledge.setCaption(caption);
		knowledgeDao.update(knowledge);
		// Document doc = Dom4jXmlFileUtils
		// .load(SystemConstant.WEB_ROOT + xmlpath);
		// if (!id.equals("knowledgeWin")) {
		// org.dom4j.Node n = doc.selectSingleNode("//knowledge[id=\"" + id
		// + "\"]/id");
		// System.out.println(n.getText());
		// org.dom4j.Node captionNode = doc
		// .selectSingleNode("//knowledge[id=\"" + id + "\"]/caption");
		// org.dom4j.Node contentNode = doc
		// .selectSingleNode("//knowledge[id=\"" + id + "\"]/content");
		// captionNode.setText(caption);
		// contentNode.setText(content);
		//
		// } else {
		// org.dom4j.Node maxidn = doc.selectSingleNode("//maxId");
		// int maxid = Integer.parseInt(maxidn.getText()) + 1;
		//
		// Element n = doc.getRootElement().addElement("knowledge");
		// Element idN = n.addElement("id");
		// idN.setText(maxid + "");
		// Element captionN = n.addElement("caption");
		// captionN.setText(caption);
		// Element contentN = n.addElement("content");
		// contentN.setText(content);
		// maxidn.setText(maxid + "");
		// }
		// OutputFormat format = OutputFormat.createPrettyPrint();
		// format.setEncoding("UTF-8");// 设置XML文件的编码格式
		// XMLWriter writer;
		// try {
		// writer = new XMLWriter(new FileWriterWithEncoding(
		// SystemConstant.WEB_ROOT + xmlpath, "UTF-8"), format);
		// writer.write(doc);
		// writer.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		result.addProperty("success", true);
		return result;
	}

	@Override
	public JsonObject addKnowledge(JsonArray dataarr) {
		JsonObject data = dataarr.get(0).getAsJsonObject();
		JsonObject result = new JsonObject();
		// String xmlpath = data.has("xmlpath") ? data.get("xmlpath")
		// .getAsString() : "";
		String parentId = data.has("parentId") ? data.get("parentId")
				.getAsString() : "";
		String caption = data.has("caption") ? data.get("caption")
				.getAsString() : "";
		String content = data.has("content") ? data.get("content")
				.getAsString() : "";
		Knowledge knowledge = new Knowledge();
		knowledge.setParentId(Long.parseLong(parentId));
		knowledge.setContent(content);
		knowledge.setCaption(caption);
		knowledgeDao.insert(knowledge);
		// Document doc = Dom4jXmlFileUtils
		// .load(SystemConstant.WEB_ROOT + xmlpath);
		// if (!id.equals("knowledgeWin")) {
		// org.dom4j.Node n = doc.selectSingleNode("//knowledge[id=\"" + id
		// + "\"]/id");
		// System.out.println(n.getText());
		// org.dom4j.Node captionNode = doc
		// .selectSingleNode("//knowledge[id=\"" + id + "\"]/caption");
		// org.dom4j.Node contentNode = doc
		// .selectSingleNode("//knowledge[id=\"" + id + "\"]/content");
		// captionNode.setText(caption);
		// contentNode.setText(content);
		//
		// } else {
		// org.dom4j.Node maxidn = doc.selectSingleNode("//maxId");
		// int maxid = Integer.parseInt(maxidn.getText()) + 1;
		//
		// Element n = doc.getRootElement().addElement("knowledge");
		// Element idN = n.addElement("id");
		// idN.setText(maxid + "");
		// Element captionN = n.addElement("caption");
		// captionN.setText(caption);
		// Element contentN = n.addElement("content");
		// contentN.setText(content);
		// maxidn.setText(maxid + "");
		// }
		// OutputFormat format = OutputFormat.createPrettyPrint();
		// format.setEncoding("UTF-8");// 设置XML文件的编码格式
		// XMLWriter writer;
		// try {
		// writer = new XMLWriter(new FileWriterWithEncoding(
		// SystemConstant.WEB_ROOT + xmlpath, "UTF-8"), format);
		// writer.write(doc);
		// writer.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		result.addProperty("success", true);
		return result;
	}

	@Override
	public List<Knowledge> loadKnowledges(long parentId) {
		List<Knowledge> list = new ArrayList<Knowledge>();
		// JsonObject addNode = dataarr.get(0).getAsJsonObject();
		// String node = addNode.has("parentId") ? addNode.get("parentId")
		// .getAsString() : "0";
		System.out.println(SystemConstant.WEB_ROOT);
		list = knowledgeDao.selectChildrenByParentId(parentId);
		return list;
	}

	@Override
	public JsonObject deleteKnowledge(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String node = addNode.has("id") ? addNode.get("id").getAsString() : "0";
		Knowledge knowledge = knowledgeDao.select(Long.parseLong(node));
		boolean success = knowledgeDao.delete(knowledge);
		JsonObject json = new JsonObject();
		json.addProperty("success", success);
		return json;
	}

}
