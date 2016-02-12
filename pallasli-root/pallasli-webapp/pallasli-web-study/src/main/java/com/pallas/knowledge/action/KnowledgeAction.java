package com.pallas.knowledge.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.action.BaseAction;
import com.pallas.knowledge.bean.Knowledge;
import com.pallas.knowledge.bean.KnowledgeType;
import com.pallas.knowledge.service.KnowledgeService;
import com.pallas.knowledge.service.KnowledgeTypeService;
import com.pallasli.constant.SystemConstant;
import com.pallasli.utils.FileUtils;
import com.pallasli.utils.ZipUtils;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class KnowledgeAction extends BaseAction {
	private final KnowledgeService knowledgeService;
	private final KnowledgeTypeService knowledgeTypeService;

	public KnowledgeService getKnowledgeService() {
		return knowledgeService;
	}

	public KnowledgeTypeService getKnowledgeTypeService() {
		return knowledgeTypeService;
	}

	public KnowledgeAction() {
		ApplicationContext ctx = super.getContext();
		knowledgeService = (KnowledgeService) ctx.getBean("knowledgeService");
		knowledgeTypeService = (KnowledgeTypeService) ctx.getBean("knowledgeTypeService");
	}

	@DirectMethod(method = "loadKnowledgeType")
	public List<KnowledgeType> loadKnowledgeType(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String node = addNode.has("node") ? addNode.get("node").getAsString() : "0";
		KnowledgeType type = new KnowledgeType();
		type.setId(Long.parseLong(node));
		return knowledgeTypeService.loadKnowledgeType(type);
	}

	@DirectMethod(method = "loadKnowledges")
	public List<Knowledge> loadKnowledges(long parentId) {
		return knowledgeService.loadKnowledges(parentId);
	}

	@DirectMethod(method = "addKnowledgeType")
	public List<KnowledgeType> addKnowledgeType(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String parentId = addNode.has("parentId") ? addNode.get("parentId").getAsString() : "";
		boolean isLeaf = addNode.has("leaf") ? addNode.get("leaf").getAsBoolean() : false;
		String text = addNode.has("text") ? addNode.get("text").getAsString() : "";
		KnowledgeType knowledgeType = new KnowledgeType();
		knowledgeType.setParentId(Long.parseLong(parentId));
		knowledgeType.setLeaf(isLeaf);
		knowledgeType.setText(text);
		return knowledgeTypeService.addKnowledgeType(knowledgeType);
	}

	@DirectMethod(method = "alterKnowledgeType")
	public List<KnowledgeType> alterKnowledgeType(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String node = addNode.has("id") ? addNode.get("id").getAsString() : "0";
		String parentId = addNode.has("parentId") ? addNode.get("parentId").getAsString() : "";
		boolean isLeaf = addNode.has("leaf") ? addNode.get("leaf").getAsBoolean() : false;
		String text = addNode.has("text") ? addNode.get("text").getAsString() : "";
		KnowledgeType knowledgeType = new KnowledgeType();
		knowledgeType.setId(Long.parseLong(node));
		knowledgeType.setParentId(Long.parseLong(parentId));
		knowledgeType.setLeaf(isLeaf);
		knowledgeType.setText(text);
		return knowledgeTypeService.alterKnowledgeType(knowledgeType);
	}

	@DirectMethod(method = "deleteKnowledgeType")
	public JsonObject deleteKnowledgeType(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		String node = addNode.has("id") ? addNode.get("id").getAsString() : "0";
		KnowledgeType type = new KnowledgeType();
		type.setId(Long.parseLong(node));
		return knowledgeTypeService.deleteKnowledgeType(type);
	}

	@DirectMethod(method = "deleteKnowledge")
	public JsonObject deleteKnowledge(JsonArray data) {
		return knowledgeService.deleteKnowledge(data);
	}

	public JsonObject exportAllDocKnowledge(KnowledgeType ktype, String path) {
		List<KnowledgeType> list = knowledgeTypeService.loadKnowledgeType(ktype);

		if (list.size() > 0) {
			for (KnowledgeType type : list) {
				if (type.isLeaf()) {
					exportAllDocKnowledge(type, path);

				} else {
					exportAllDocKnowledge(type, path + type.getText() + "/");

				}

			}
		} else {
			exportDocKnowledge(ktype, path);
		}
		return new JsonObject();
	}

	@DirectMethod(method = "exportAllDocKnowledge")
	public JsonObject exportAllDocKnowledge(JsonArray data) {
		KnowledgeType type = null;
		type = new KnowledgeType();
		type.setId(0l);
		createKnowledgeFolders(type, "");
		type.setId(0l);
		exportAllDocKnowledge(type, "");
		return new JsonObject();
	}

	public JsonObject exportDocKnowledge(KnowledgeType type, String path) {
		JsonObject json = new JsonObject();
		List<Knowledge> list = knowledgeService.loadKnowledges(type.getId());
		String docKnowledgePath = SystemConstant.WEB_ROOT + "docKnowledge/";
		FileUtils.createFile(docKnowledgePath, true);
		String destFile = docKnowledgePath + path + type.getText() + ".doc";
		String fileCon = "";
		fileCon += "<html>";
		for (Knowledge k : list) {
			fileCon += "<span style=\"font-size: 28px\"><span style=\"font-family: 黑体\">" + k.getCaption()
					+ "<br /> <br /> </span></span> " + k.getContent();
		}
		fileCon += "</html>";
		WordUtils.exportDoc(destFile, fileCon);
		return json;
	}

	@DirectMethod(method = "exportDocKnowledge")
	public JsonObject exportDocKnowledge(JsonArray data) {
		JsonObject json = new JsonObject();

		JsonObject addNode = data.get(0).getAsJsonObject();
		String parentId = addNode.has("parentId") ? addNode.get("parentId").getAsString() : "0";
		String wordTitle = addNode.has("wordTitle") ? addNode.get("wordTitle").getAsString() : " ";
		List<Knowledge> list = knowledgeService.loadKnowledges(Long.parseLong(parentId));
		String docKnowledgePath = SystemConstant.WEB_ROOT + "docKnowledge/";
		FileUtils.createFile(docKnowledgePath, true);
		String destFile = docKnowledgePath + wordTitle + ".doc";
		String fileCon = "";
		fileCon += "<html>";
		for (Knowledge k : list) {
			fileCon += "<span style=\"font-size: 28px\"><span style=\"font-family: 黑体\">" + k.getCaption()
					+ "<br /> <br /> </span></span> " + k.getContent();
		}
		fileCon += "</html>";
		WordUtils.exportDoc(destFile, fileCon);
		return json;
	}

	private void createKnowledgeFolders(KnowledgeType type, String path) {
		List<KnowledgeType> list = null;

		list = knowledgeTypeService.loadKnowledgeType(type);
		for (KnowledgeType ktype : list) {
			if (!ktype.isLeaf()) {
				type.setId(ktype.getId());
				String docKnowledgePath = SystemConstant.WEB_ROOT + "docKnowledge/" + path + ktype.getText() + "/";
				FileUtils.createFile(docKnowledgePath, true);
				createKnowledgeFolders(type, path + ktype.getText() + "/");
			}
		}

	}

	public void exportAllHtmlKnowledgeMenu(KnowledgeType type, StringBuffer sb, String path) {
		List<KnowledgeType> list = null;

		list = knowledgeTypeService.loadKnowledgeType(type);
		for (KnowledgeType ktype : list) {

			if (ktype.isLeaf()) {
				System.out.println(ktype.getText());

				sb.append("\r\n<li><a onclick=\"change('ktype_" + ktype.getId() + "')\">" + ktype.getText()
						+ "</a>\r\n<ul  id='ktype_" + ktype.getId() + "'>");
				List<Knowledge> knowledgeList = knowledgeService.loadKnowledges(ktype.getId());
				// li 不含ul
				for (Knowledge k : knowledgeList) {
					sb.append("\r\n<li class='leaf' onclick='stopPropagation()' ><a href='" + path + ktype.getText()
							+ ".html#knowledge_" + k.getId() + "' target ='right' >" + k.getCaption()
							+ "</a><br></li>");

				}
				sb.append("</ul></li>");
			} else {
				type.setId(ktype.getId());
				sb.append("\r\n<li><a onclick=\"change('ktype_" + ktype.getId() + "')\">" + ktype.getText()
						+ "</a>\r\n<ul  id='ktype_" + ktype.getId() + "'>");
				String docKnowledgePath = SystemConstant.WEB_ROOT + "docKnowledge/" + path + ktype.getText() + "/";

				try {
					FileUtils.createFile(new String(docKnowledgePath.getBytes("utf-8")), true);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exportAllHtmlKnowledgeMenu(type, sb, path + ktype.getText() + "/");
				sb.append("\r\n</ul>\r\n</li>");
			}
		}

	}

	/**
	 * 初始化知识点菜单HTML
	 * 
	 * @param arrData
	 * @param sb
	 */
	private void initHtml(KnowledgeType type, StringBuffer sb) {
		String begin = FileUtils.readFileToString(SystemConstant.WEB_ROOT + "data/学习总结/menuHtmlBegin.txt");
		String end = FileUtils.readFileToString(SystemConstant.WEB_ROOT + "data/学习总结/menuHtmlEnd.txt");
		sb.append(begin.trim());
		exportAllHtmlKnowledgeMenu(type, sb, "");
		sb.append(end.trim());
	}

	public void exportAllHtmlKnowledge(KnowledgeType ktype, String path) {
		List<KnowledgeType> list = knowledgeTypeService.loadKnowledgeType(ktype);
		if (list.size() > 0) {
			for (KnowledgeType type : list) {
				ktype.setId(type.getId());
				ktype.setText(type.getText());
				if (type.isLeaf()) {
					exportAllHtmlKnowledge(ktype, path);
				} else {
					exportAllHtmlKnowledge(ktype, path + ktype.getText() + "/");

				}
			}
		} else {
			Knowledge k = new Knowledge();
			k.setParentId(ktype.getId());
			KnowledgeType type = new KnowledgeType();
			type.setText(ktype.getText());
			exportHtmlKnowledge(k, type, path);

		}
	}

	@DirectMethod(method = "exportAllHtmlKnowledge")
	public JsonObject exportAllHtmlKnowledge(JsonArray data) {
		KnowledgeType ktype = new KnowledgeType();
		ktype.setId(0l);
		ktype.setText("");
		StringBuffer sb = new StringBuffer();
		FileUtils.createFile(SystemConstant.WEB_ROOT + "docKnowledge", true);
		initHtml(ktype, sb);
		String destFile = SystemConstant.WEB_ROOT + "docKnowledge/menu.html";
		FileUtils.writeFile(destFile, sb.toString());

		String indexHtml = FileUtils.readFileToString(SystemConstant.WEB_ROOT + "data/学习总结/index.html");
		FileUtils.writeFile(SystemConstant.WEB_ROOT + "docKnowledge/index.html", indexHtml.trim());
		FileUtils.createFile(SystemConstant.WEB_ROOT + "docKnowledge/images", true);
		try {
			String fromPath = SystemConstant.WEB_ROOT + "data/学习总结/folder.gif";
			String toPath = SystemConstant.WEB_ROOT + "docKnowledge/images/folder.gif";
			FileUtils.copyImageFileFrom(fromPath, toPath);

			fromPath = SystemConstant.WEB_ROOT + "data/学习总结/folder-open.gif";
			toPath = SystemConstant.WEB_ROOT + "docKnowledge/images/folder-open.gif";

			FileUtils.copyImageFileFrom(fromPath, toPath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ktype.setId(0l);
		ktype.setText("");
		exportAllHtmlKnowledge(ktype, "");

		String zipPath = SystemConstant.WEB_ROOT + "download/test.zip";
		try {
			FileUtils.createFile(SystemConstant.WEB_ROOT + "download", true);
			ZipUtils.zipFolder(zipPath, SystemConstant.WEB_ROOT + "docKnowledge");
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		return json;
	}

	private JsonObject exportHtmlKnowledge(Knowledge knowledge, KnowledgeType knowledgeType, String path) {
		JsonObject json = new JsonObject();

		long parentId = knowledge.getParentId();
		String wordTitle = knowledgeType.getText();

		List<Knowledge> list = knowledgeService.loadKnowledges(parentId);
		String docKnowledgePath = SystemConstant.WEB_ROOT + "docKnowledge/" + path;
		String destFile = docKnowledgePath + wordTitle + ".html";
		String fileCon = "";
		fileCon += "<html><body>";
		for (Knowledge k : list) {
			fileCon += "<a name='knowledge_" + k.getId() + "'>" + k.getCaption() + "<br><a>" + "<div id='knowledge_"
					+ k.getId() + "'> " + k.getContent() + "</div>";
		}
		fileCon += "</body></html>";
		FileUtils.writeFile(destFile, fileCon);
		return json;
	}

	@DirectMethod(method = "exportHtmlKnowledge")
	public JsonObject exportHtmlKnowledge(JsonArray data) {

		JsonObject addNode = data.get(0).getAsJsonObject();
		String parentId = addNode.has("parentId") ? addNode.get("parentId").getAsString() : "0";
		String wordTitle = addNode.has("wordTitle") ? addNode.get("wordTitle").getAsString() : " ";
		Knowledge k = new Knowledge();
		k.setParentId(Long.parseLong(parentId));
		KnowledgeType type = new KnowledgeType();
		type.setText(wordTitle);

		JsonObject json = exportHtmlKnowledge(k, type, "");
		return json;
	}

	@DirectMethod(method = "saveKnowledge")
	public JsonObject saveKnowledge(JsonArray dataarr) {
		JsonObject ret = new JsonObject();
		JsonObject data = dataarr.get(0).getAsJsonObject();
		String id = data.has("id") ? data.get("id").getAsString() : "";
		if (id.equals("knowledgeWin")) {
			ret = knowledgeService.addKnowledge(dataarr);
		} else {
			ret = knowledgeService.changeKnowledge(dataarr);
		}
		return ret;
	}

}
