package com.pallas.study.transform;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.pallas.knowledge.bean.Knowledge;
import com.pallas.knowledge.bean.KnowledgeType;
import com.pallas.knowledge.dao.impl.KnowledgeDAOImpl;
import com.pallas.knowledge.dao.impl.KnowledgeTypeDAOImpl;
import com.pallasli.constant.SystemConstant;
import com.pallasli.xml.Dom4jXmlFileUtils;
import com.pallasli.xml.Dom4jXmlParseUtils;
import com.pallasli.xml.XmlUtils;

public class XmlToSql {

	public static void main(String[] a) {
		new XmlToSql().generateKnowledgeTypes();
	}

	public void generateKnowledgeTypes() {
		System.out.println(SystemConstant.WEB_ROOT + "data/knowledgesTypeTree.xml");
		Document doc = Dom4jXmlFileUtils.load(SystemConstant.WEB_ROOT + "data/knowledgesTypeTree.xml");
		List<Element> nodes = doc.selectNodes("//node");
		try {
			List<KnowledgeType> nodeList = (List<KnowledgeType>) Dom4jXmlParseUtils.parseXml2List(nodes,
					KnowledgeType.class);
			for (KnowledgeType n : nodeList) {
				if (n.getText() != null) {
					// n.setId(null);
					new KnowledgeTypeDAOImpl().insert(n);
				}
			}
			for (KnowledgeType n : nodeList) {

				if (n.isLeaf()) {

					long parentId = n.getParentId();
					String dir = SystemConstant.WEB_ROOT + "data/学习总结/";
					String path = "";
					while (parentId != 0) {
						KnowledgeType tmp = new KnowledgeTypeDAOImpl().select(parentId);
						path = tmp.getText() + "/" + path + "/";
						parentId = tmp.getParentId();
					}
					path = dir + path + n.getText() + ".xml";
					Document doc2 = Dom4jXmlFileUtils.load(path);
					List<Element> nodes2 = doc2.selectNodes("//knowledge");
					// List<Knowledge> nodeList2 = (List<Knowledge>)
					// Dom4jXmlParseUtils
					// .parseXml2List(nodes2, Knowledge.class);
					List<Knowledge> nodeList2 = XmlUtils.parseXmlToList(path, Knowledge.class);
					for (Knowledge k : nodeList2) {
						if (k.getCaption() != null) {
							k.setParentId(n.getId());
							k.setId(null);
							System.out.println(k.getId() + "  " + k.getCaption() + " " + n.getText());
							new KnowledgeDAOImpl().insert(k);
						}
					}
				}
			}
			for (long i = 1; i < 5; i++) {

				// KnowledgeType n = new KnowledgeTypeManager().load(i);
			}

			for (long i = 1; i < 5; i++) {

				// Knowledge n = new KnowledgeManager().load(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void generateKnowledges() {

	}
}
