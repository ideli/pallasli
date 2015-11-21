package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.engine.design.view.Column;
import com.mixky.engine.design.view.IQueryEditor;
import com.mixky.engine.design.view.QuerySql;
import com.mixky.engine.organization.dao.User;
import com.mixky.toolkit.JsonObjectTool;


public class QueryLenovoTextField extends IQueryEditor{
	
	@Override
	public JsonObject getEditor(Column column, User user) {
		JsonObject json = new JsonObject();
		
		json.addProperty("applicationkey", IFieldEditor.getApplicationKey());
		json.addProperty("name", column.getRealFieldName());
		json.addProperty("xtype", "lenovotextfield");
		json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", column.getF_caption());
		json.addProperty("allowBlank", false);
	
		JsonObjectTool.applyJson(json, column.getF_config());
		
		if (column.getF_config() != null && !column.getF_config().isJsonNull()&& column.getF_config().has("directFn")) {
			String fn = column.getF_config().get("directFn").getAsString();
			json.remove("directFn");
			json.add("directFn", new JsonFunction(fn));
		}
		
		if (column.getF_config() != null && !column.getF_config().isJsonNull()&& column.getF_config().has("params")) {
			String s_cs = column.getF_config().get("params").getAsString();
			json.remove("params");
			json.add("params", new JsonFunction(s_cs));			
		}
		if (column.getF_config() != null && !column.getF_config().isJsonNull()&& column.getF_config().has("store")) {
			String s_cs = column.getF_config().get("store").getAsString();
			json.remove("store");
			json.add("store", new JsonFunction(s_cs));			
		}
		return json;
	}

	@Override
	public void getQueryString(Column column, JsonObject params, QuerySql sql) {
		
	}

}
