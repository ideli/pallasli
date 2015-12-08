package com.mixky.designer.outline;

import java.util.ArrayList;
import java.util.List;

import com.garage.xdatabase.cache.DataCacheFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mixky.engine.design.DesignObject;
import com.mixky.engine.design.DesignObjectFactory;
import com.mixky.engine.design.DesignObjectLoader;

public class OutlineManager {

	private static OutlineManager singleton;
	
	protected static OutlineManager instance(){
		if(singleton == null){
			singleton = new OutlineManager();
		}
		return singleton;
	}
	

	protected void getTreeItems(String key, String type, JsonArray items){
		
		type = type.toLowerCase();
		
		if("root".equals(type)){
			getRootNodes(items);
		}else if("modulefolder".equals(type)){
			getModuleFolderNodes(items);
		}else if("module".equals(type)){
			getModuleNodes(key, items);
		}else if("document".equals(type)){
			getDocumentNodes(key, items);
		}else if("query".equals(type)){
			getQueryNodes(key, items);
		}else if("group".equals(type)){
			getGroupNodes(key, items);
		}else if("node".equals(type)){
			getNodeNodes(key, items);
		}else if("modulemenu".equals(type)){
			getModuleMenuNodes(key, items);
		}else if("modulemenufolder".equals(type)){
			getModuleMenuNodes(getFolderKey(key), items);
		}else if("tablefolder".equals(type)){
			getTableFolderNodes(getFolderKey(key), items);
		}else if("queryfolder".equals(type)){
			getQueryFolderNodes(getFolderKey(key), items);
		}else if("documentfolder".equals(type)){
			getDocumentFolderNodes(getFolderKey(key), items);
		}else if("modulerolefolder".equals(type)){
			getModuleRoleFolderNodes(getFolderKey(key), items);
		}else if("documenttypefolder".equals(type)){
			getDocumentTypeFolderNodes(getFolderKey(key), items);
		}else if("portletfolder".equals(type)){
			getPortletFolderNodes(getFolderKey(key), items);
		}
	}
	
	private String getFolderKey(String key){
		String realKey = "";
		if(!(key == null || "".equals(key))){
			int pos = key.lastIndexOf(".");
			if(pos <= 0){
				realKey = key;
			}else{
				realKey = key.substring(0, pos);
			}
		}
		return realKey;
	}	

	private void getRootNodes(JsonArray items){
		items.add(createTreeNode("dictionaryfolder", "dictionaryfolder", "dictionaryfolder", "数据字典", true, "dictionaryfolder", null, null));

		JsonArray moduleNodes = new JsonArray();
		getModuleFolderNodes(moduleNodes);
		items.add(createTreeNode("modulefolder", "modulefolder", "modulefolder", "模块设计", false, "modulefolder", moduleNodes, null));
	}

	private void getModuleMenuNodes(String key, JsonArray items){
		List<DesignObject> modulemenus = DesignObjectLoader.instance().findObjects("modulemenu", key);
		DesignObjectFactory.instance().orderDesignObjectList(modulemenus);
		for(int i=0;i<modulemenus.size();i++){
			DesignObject child = modulemenus.get(i);
			JsonArray children = new JsonArray();
			getModuleMenuNodes(child.getKey(), children);
			String note = "[" + child.getF_name() + "]";
			if(child.getF_note() != null){
				note = note + child.getF_note();
			}
			items.add(createTreeNode(child.getKey(), child.getKey(), child.getF_class(), child.getF_caption(), false, child.getF_class(), children, note));
		}
	}

	private void getTableFolderNodes(String key, JsonArray items){
		List<DesignObject> tables = DesignObjectLoader.instance().findObjects("table", key);
		DesignObjectFactory.instance().orderDesignObjectList(tables);
		for(int i=0;i<tables.size();i++){
			DesignObject query = tables.get(i);
			String note = "[" + query.getF_name() + "]";
			if(query.getF_note() != null){
				note = note + query.getF_note();
			}
			items.add(createTreeNode(query.getKey(), query.getKey(), query.getF_class(), query.getF_caption(), true, query.getF_class(), null, note));
		}
	}

	private void getGroupNodes(String key, JsonArray items){
		List<DesignObject> groups = DesignObjectLoader.instance().findObjects("group", key);
		DesignObjectFactory.instance().orderDesignObjectList(groups);
		for(int i=0;i<groups.size();i++){
			DesignObject child = groups.get(i);
			JsonArray children = new JsonArray();
			getGroupNodes(child.getKey(), children);
			String note = "[" + child.getF_name() + "]";
			if(child.getF_note() != null){
				note = note + child.getF_note();
			}
			items.add(createTreeNode(child.getKey(), child.getKey(), child.getF_class(), child.getF_caption(), false, child.getF_class(), children, note));
		}
	}	

	private void getQueryNodes(String key, JsonArray items){
		List<DesignObject> querys = new ArrayList<DesignObject>();
		mergeDesignObjectList(querys, DesignObjectLoader.instance().findObjects("group", key));
		mergeDesignObjectList(querys, DesignObjectLoader.instance().findObjects("view", key));
		DesignObjectFactory.instance().orderDesignObjectList(querys);
		for(int i=0;i<querys.size();i++){
			DesignObject child = querys.get(i);
			String note = "[" + child.getF_name() + "]";
			if(child.getF_note() != null){
				note = note + child.getF_note();
			}
			boolean isleaf = true;
			JsonArray children = null;
			if("group".equals(child.getF_class())){
				children = new JsonArray();
				getGroupNodes(child.getKey(), children);
				isleaf = false;
			}
			items.add(createTreeNode(child.getKey(), child.getKey(), child.getF_class(), child.getF_caption(), isleaf, child.getF_class(), children, note));
		}
	}

	private void getQueryFolderNodes(String key, JsonArray items){
		List<DesignObject> querys = DesignObjectLoader.instance().findObjects("query", key);
		DesignObjectFactory.instance().orderDesignObjectList(querys);
		for(int i=0;i<querys.size();i++){
			DesignObject query = querys.get(i);
			JsonArray children = new JsonArray();
			getQueryNodes(query.getKey(), children);
			String note = "[" + query.getF_name() + "]";
			if(query.getF_note() != null){
				note = note + query.getF_note();
			}
			items.add(createTreeNode(query.getKey(), query.getKey(), query.getF_class(), query.getF_caption(), false, query.getF_class(), children, note));
		}
	}
	
	private void getDocumentNodes(String key, JsonArray items){
		List<DesignObject> panels = DesignObjectLoader.instance().findObjects("panel", key);
		DesignObjectFactory.instance().orderDesignObjectList(panels);
		for(int i=0;i<panels.size();i++){
			DesignObject panel = panels.get(i);
			String note = "[" + panel.getF_name() + "]";
			if(panel.getF_note() != null){
				note = note + panel.getF_note();
			}
			items.add(createTreeNode(panel.getKey(), panel.getKey(), "panel", panel.getF_caption(), true, "panel", null, note));
		}
	}

	private void getDocumentFolderNodes(String key, JsonArray items){
		List<DesignObject> documents = DesignObjectLoader.instance().findObjects("document", key);
		DesignObjectFactory.instance().orderDesignObjectList(documents);
		for(int i=0;i<documents.size();i++){
			DesignObject document = documents.get(i);
			JsonArray children = new JsonArray();
			getDocumentNodes(document.getKey(), children);
			String note = "[" + document.getF_name() + "]";
			if(document.getF_note() != null){
				note = note + document.getF_note();
			}
			items.add(createTreeNode(document.getKey(), document.getKey(), "document", document.getF_caption(), false, "document", children, note));
		}
	}

	private void getNodeNodes(String key, JsonArray items){
		List<DesignObject> nodes = DesignObjectLoader.instance().findObjects("route", key);
		DesignObjectFactory.instance().orderDesignObjectList(nodes, "f_key,f_name");
		for(int i=0;i<nodes.size();i++){
			DesignObject query = nodes.get(i);
			String note = "[" + query.getF_name() + "]";
			if(query.getF_note() != null){
				note = note + query.getF_note();
			}
			items.add(createTreeNode(query.getKey(), query.getKey(), query.getF_class(), query.getF_caption(), true, query.getF_class(), null, note));
		}
		
	}
	
	private void getDocumentTypeFolderNodes(String key, JsonArray items){
		List<DesignObject> documenttypes = DesignObjectLoader.instance().findObjects("documenttype", key);
		DesignObjectFactory.instance().orderDesignObjectList(documenttypes);
		for(int i=0;i<documenttypes.size();i++){
			DesignObject documenttype = documenttypes.get(i);
			String note = "[" + documenttype.getF_name() + "]";
			if(documenttype.getF_note() != null){
				note = note + documenttype.getF_note();
			}
			items.add(createTreeNode(documenttype.getKey(), documenttype.getKey(), documenttype.getF_class(), documenttype.getF_caption(), true, documenttype.getF_class(), null, note));
		}
	}
	
	private void getPortletFolderNodes(String key, JsonArray items){
		List<DesignObject> portlets = DesignObjectLoader.instance().findObjects("portlet", key);
		DesignObjectFactory.instance().orderDesignObjectList(portlets);
		for(int i=0;i<portlets.size();i++){
			DesignObject portlet = portlets.get(i);
			String note = "[" + portlet.getF_name() + "]";
			if(portlet.getF_note() != null){
				note = note + portlet.getF_note();
			}
			items.add(createTreeNode(portlet.getKey(), portlet.getKey(), portlet.getF_class(), portlet.getF_caption(), true, portlet.getF_class(), null, note));
		}
	}
	
	private void getModuleRoleFolderNodes(String key, JsonArray items){
		List<DesignObject> moduleroles = DesignObjectLoader.instance().findObjects("modulerole", key);
		DesignObjectFactory.instance().orderDesignObjectList(moduleroles);
		for(int i=0;i<moduleroles.size();i++){
			DesignObject modulerole = moduleroles.get(i);
			String note = "[" + modulerole.getF_name() + "]";
			if(modulerole.getF_note() != null){
				note = note + modulerole.getF_note();
			}
			items.add(createTreeNode(modulerole.getKey(), modulerole.getKey(), modulerole.getF_class(), modulerole.getF_caption(), true, modulerole.getF_class(), null, note));
		}
		if(moduleroles.size() > 0){
			items.add(createTreeNode(key + "-auth", key, "moduleauthorityfolder", "权限列表", true, "moduleauthorityfolder", null, "模块设计对象权限配置"));
		}
	}
	
	private void getModuleNodes(String key, JsonArray items){
		JsonArray moduleMenuNodes = new JsonArray();
		getModuleMenuNodes(key, moduleMenuNodes);
		items.add(createTreeNode(key + ".modulemenufolder", key, "modulemenufolder", "模块菜单", false, "modulemenufolder", moduleMenuNodes, null));
		JsonArray tableNodes = new JsonArray();
		getTableFolderNodes(key, tableNodes);
		items.add(createTreeNode(key + ".tablefolder", key, "tablefolder", "数据表", false, "tablefolder", tableNodes, null));
		JsonArray queryNodes = new JsonArray();
		getQueryFolderNodes(key, queryNodes);
		items.add(createTreeNode(key + ".queryfolder", key, "queryfolder", "查询定义", false, "queryfolder", queryNodes, null));
		JsonArray documentNodes = new JsonArray();
		getDocumentFolderNodes(key, documentNodes);
		items.add(createTreeNode(key + ".documentfolder", key, "documentfolder", "文档", false, "documentfolder", documentNodes, null));
		JsonArray moduleroleNodes = new JsonArray();
		getModuleRoleFolderNodes(key, moduleroleNodes);
		items.add(createTreeNode(key + ".modulerolefolder", key, "modulerolefolder", "角色定义", false, "modulerolefolder", moduleroleNodes, null));
		JsonArray documentTypeNodes = new JsonArray();
		getDocumentTypeFolderNodes(key, documentTypeNodes);
		items.add(createTreeNode(key + ".documenttypefolder", key, "documenttypefolder", "文档类型注册", false, "documenttypefolder", documentTypeNodes, null));
		JsonArray portletNodes = new JsonArray();
		getPortletFolderNodes(key, portletNodes);
		items.add(createTreeNode(key + ".portletfolder", key, "portletfolder", "桌面栏目", false, "portletfolder", portletNodes, null));
	}
	/**
	 * 列出所有模块
	 * @param items
	 */
	private void getModuleFolderNodes(JsonArray items){
		List<DesignObject> modules = DataCacheFactory.instance().getSerializeableObjects("module");
		DesignObjectFactory.instance().orderDesignObjectList(modules);
		for(int i=0;i<modules.size();i++){
			DesignObject module = modules.get(i);
			JsonArray children = new JsonArray();
			getModuleNodes(module.getKey(), children);
			String note = "[" + module.getF_name() + "]";
			if(module.getF_note() != null){
				note = note + module.getF_note();
			}
			items.add(createTreeNode(module.getKey(), module.getKey(), "module", module.getF_caption(), false, "module", children, note));
		}
	}
	
	private JsonObject createTreeNode(String id, String key, String type, String text, boolean leaf, String iconCls, JsonArray children, String qtip){
		JsonObject node = new JsonObject();
		node.addProperty("id", id);
		node.addProperty("key", key);
		node.addProperty("mclass", type);
		node.addProperty("text", text);
		node.addProperty("leaf", leaf);
		if(iconCls != null && !"".equals(iconCls)){
			node.addProperty("iconCls", "icon-designer-" + iconCls);
		}
		if(qtip != null && !"".equals(qtip)){
			node.addProperty("qtip", qtip);
		}
		if(children != null && children.size() >0){
			node.add("children", children);
		}
		return node;
	}
	
	private void mergeDesignObjectList(List<DesignObject> first, List<Object> second){
		for(int i=0;i<second.size();i++){
			first.add((DesignObject)second.get(i));
		}
	}
}
