package com.pallasli.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mixky.engine.design.view.Column;
import com.mixky.engine.design.view.IQueryEditor;
import com.mixky.engine.design.view.QuerySql;
import com.mixky.engine.organization.dao.User;
import com.mixky.toolkit.JsonObjectTool;

public class QueryScopeFields extends IQueryEditor{
    
	@Override
	public JsonObject getEditor(Column paramColumn, User paramUser)
	{
		JsonObject localJsonObject1 = new JsonObject();
	    localJsonObject1.addProperty("layout", "column");
	    localJsonObject1.addProperty("border", Boolean.valueOf(false));
	    localJsonObject1.addProperty("anchor", "100%");
	    JsonArray localJsonArray = new JsonArray();
	    Object localObject1 = "";
	    String str = "";  
	    if ((paramColumn.getF_config() != null) && (paramColumn.getF_config().has("defaultValue")))
	    {
	      String localObject2 = paramColumn.getF_config().get("defaultValue").getAsString();
	      if ((localObject2 != null) && (!"".equals(localObject2)))
	        if (((String)localObject2).indexOf(",") != -1)
	        {
	          String[] localObject3 = ((String)localObject2).split(",");
	          localObject1 = localObject3[0];
	          str = localObject3[1];
	        }
	        else
	        {
	          localObject1 = localObject2;
	        }
	    }
	    Object localObject2 = new JsonObject();
	    ((JsonObject)localObject2).addProperty("name", paramColumn.getRealFieldName() + "_begin");
	    ((JsonObject)localObject2).addProperty("xtype", "numberText");
	    ((JsonObject)localObject2).addProperty("labelWidth", Integer.valueOf(88));
	    ((JsonObject)localObject2).addProperty("fieldLabel", paramColumn.getF_caption());
	    ((JsonObject)localObject2).addProperty("anchor", "100%");
	    ((JsonObject)localObject2).addProperty("value", (String)localObject1);
	    JsonObjectTool.applyJson(((JsonObject)localObject2), paramColumn.getF_config());
	    Object localObject3 = new JsonObject();
	    ((JsonObject)localObject3).addProperty("columnWidth", Double.valueOf(0.59999999999999998D));
	    ((JsonObject)localObject3).addProperty("layout", "form");
	    ((JsonObject)localObject3).addProperty("border", Boolean.valueOf(false));
	    ((JsonObject)localObject3).add("items", (JsonElement)localObject2);
	    localJsonArray.add((JsonElement)localObject3);
	    JsonObject localJsonObject2 = new JsonObject();
	    localJsonObject2.addProperty("name", paramColumn.getRealFieldName() + "_end");
	    localJsonObject2.addProperty("xtype", "numberText");
	    localJsonObject2.addProperty("fieldLabel", "至");
	    localJsonObject2.addProperty("anchor", "100%");
	    localJsonObject2.addProperty("labelSeparator", " ");
	    localJsonObject2.addProperty("value", str);
	    JsonObjectTool.applyJson(localJsonObject2, paramColumn.getF_config());
	    JsonObject localJsonObject3 = new JsonObject();
	    localJsonObject3.addProperty("labelWidth", Integer.valueOf(20));
	    localJsonObject3.addProperty("columnWidth", Double.valueOf(0.40000000000000002D));
	    localJsonObject3.addProperty("layout", "form");
	    localJsonObject3.addProperty("border", Boolean.valueOf(false));
	    localJsonObject3.addProperty("bodyStyle", "padding-left:3px;");
	    localJsonObject3.add("items", localJsonObject2);
	    localJsonArray.add(localJsonObject3);
	    localJsonObject1.add("items", localJsonArray);
	    return ((JsonObject)(JsonObject)(JsonObject)localJsonObject1);
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
