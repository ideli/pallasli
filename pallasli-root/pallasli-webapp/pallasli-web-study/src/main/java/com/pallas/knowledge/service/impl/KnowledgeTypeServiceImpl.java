package com.pallas.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.knowledge.bean.KnowledgeType;
import com.pallas.knowledge.dao.KnowledgeTypeDAO;
import com.pallas.knowledge.service.KnowledgeTypeService;

public class KnowledgeTypeServiceImpl implements KnowledgeTypeService {

	private KnowledgeTypeDAO knowledgeTypeDao;

	public KnowledgeTypeDAO getKnowledgeTypeDao() {
		return knowledgeTypeDao;
	}

	public void setKnowledgeTypeDao(KnowledgeTypeDAO knowledgeTypeDao) {
		this.knowledgeTypeDao = knowledgeTypeDao;
	}

	@Override
	public List<KnowledgeType> loadKnowledgeType(KnowledgeType data) {
		List<KnowledgeType> list = new ArrayList<KnowledgeType>();

		list = knowledgeTypeDao.selectChildrenByParentId(data.getId());
		return list;
	}

	@Override
	public List<KnowledgeType> addKnowledgeType(KnowledgeType data) {
		List<KnowledgeType> list = new ArrayList<KnowledgeType>();
		knowledgeTypeDao.insert(data);
		//
		// Document doc = Dom4jXmlFileUtils.load(SystemConstant.WEB_ROOT
		// + "data/knowledgesTypeTree.xml");
		//
		// org.dom4j.Node maxidn = doc.selectSingleNode("//maxId");
		// long maxid = Long.parseLong(maxidn.getText()) + 1;
		// maxidn.setText(maxid + "");
		//
		// org.dom4j.Node n = doc
		// .selectSingleNode("//node[id='" + parentId + "']");
		// Element newN = ((Element) n).addElement("node");
		// newN.addElement("id").setText(maxid + "");
		// newN.addElement("text").setText(text);
		// newN.addElement("leaf").setText(isLeaf + "");
		// newN.addElement("expanded").setText(!isLeaf + "");
		// OutputFormat format = OutputFormat.createPrettyPrint();
		// format.setEncoding("UTF-8");// 设置XML文件的编码格式
		// XMLWriter writer;
		// try {
		// if (isLeaf) {
		// Dom4jXmlFileUtils.create(SystemConstant.WEB_ROOT + path
		// + ".xml");
		// } else {
		// new File(SystemConstant.WEB_ROOT + path).mkdir();
		// }
		// writer = new XMLWriter(new FileWriterWithEncoding(
		// SystemConstant.WEB_ROOT + "data/knowledgesTypeTree.xml",
		// "UTF-8"), format);
		// writer.write(doc);
		// writer.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// KnowledgeType node = new KnowledgeType();
		// node.setText(text);
		// node.setId(maxid);
		// node.setLeaf(isLeaf);
		// node.setExpanded(!isLeaf);
		list.add(data);
		return list;
	}

	@Override
	public List<KnowledgeType> alterKnowledgeType(KnowledgeType data) {
		List<KnowledgeType> list = new ArrayList<KnowledgeType>();
		knowledgeTypeDao.update(data);
		return list;
	}

	@Override
	public JsonObject deleteKnowledgeType(KnowledgeType data) {
		KnowledgeType knowledgeType = knowledgeTypeDao.select(data.getId());
		boolean success = knowledgeTypeDao.delete(knowledgeType);
		JsonObject json = new JsonObject();
		json.addProperty("success", success);
		return json;
	}

}
