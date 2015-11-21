package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.view.Column;
import com.mixky.engine.design.view.IQueryEditor;
import com.mixky.engine.design.view.QuerySql;
import com.mixky.engine.organization.dao.User;
import com.mixky.toolkit.JsonObjectTool;

public class QueryTriggerField extends IQueryEditor{
   
	@Override
	public JsonObject getEditor(Column column, User user) {
		JsonObject json = new JsonObject();
		json.addProperty("name", column.getRealFieldName());
		json.addProperty("xtype", "trigger");
		json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", column.getF_caption());
		json.addProperty("triggerClass", "x-form-search-trigger");
		if (column.getF_config() != null && !column.getF_config().isJsonNull()&& column.getF_config().has("triggerclick")) {
			String fn = column.getF_config().get("triggerclick").getAsString();
			json.add("onTriggerClick", new JsonFunction(fn));
		}
		JsonObjectTool.applyJson(json, column.getF_config());
		json.remove("triggerclick");
		return json;
	}

	@Override
	public void getQueryString(Column column, JsonObject params, QuerySql sql) {
		if(params.has(column.getRealFieldName() + "_begin") && !"".equals(params.get(column.getRealFieldName() + "_begin").getAsString())){
			sql.appendWhere(column.getF_name() + " >= " + params.get(column.getRealFieldName() + "_begin").getAsString());
		}
		if(params.has(column.getRealFieldName() + "_end") && !"".equals(params.get(column.getRealFieldName() + "_end").getAsString())){
			sql.appendWhere(column.getF_name() + " <= " + params.get(column.getRealFieldName() + "_end").getAsString());
		}
	}
}
