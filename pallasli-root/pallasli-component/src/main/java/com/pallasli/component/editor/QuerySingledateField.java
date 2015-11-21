package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.engine.context.ContextVariablesContainer;
import com.mixky.engine.design.view.Column;
import com.mixky.engine.design.view.IQueryEditor;
import com.mixky.engine.design.view.QuerySql;
import com.mixky.engine.organization.dao.User;
import com.mixky.toolkit.JsonObjectTool;

public class QuerySingledateField extends IQueryEditor{
   
	@Override
	public JsonObject getEditor(Column column, User user) {
		JsonObject json = new JsonObject();
		json.addProperty("name", column.getRealFieldName());
		json.addProperty("xtype", "datefield");
		json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", column.getF_caption());
		json.addProperty("format", "Y-m-d");
		json.addProperty("altFormats", "Y-m-d H:i:s|m/d/Y|Ymd|Y-m-d");
	    String str = "";
	    if ((column.getF_config() != null) && (column.getF_config().has("defaultValue")))
	    {
	      String str2 = column.getF_config().get("defaultValue").getAsString();
	      if ((str2 != null) && (!"".equals(str2.trim())))
	          str = str2.trim();
	    }
	    str = ContextVariablesContainer.instance().replaceContextVariables(str, user);
	    json.addProperty("value",str);
		JsonObjectTool.applyJson(json, column.getF_config());
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
