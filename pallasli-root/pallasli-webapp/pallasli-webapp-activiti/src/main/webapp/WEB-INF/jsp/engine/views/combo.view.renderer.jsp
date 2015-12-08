<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.view.Query"%>
<%@ page import="com.mixky.engine.design.view.QuerySql"%>
<%@ page import="com.mixky.engine.design.view.ViewParameter"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.common.database.MixkyDataAccess"%>


<%
	String value = request.getParameter("value");
	String viewkey = request.getParameter("viewkey");
	JsonObject result = new JsonObject();
	if(value != null && !"".equals(value)){
		String key = value;
		View view = DesignObjectLoader.instance().loadDesignObject(viewkey);
		Column displayCol = DesignObjectLoader.instance().loadDesignObject(viewkey + ".DISPLAY");
		Query query = ViewDataService.instance().getQuery(view);
		QuerySql querysql = ViewDataService.instance().getQuerySql(query);
		
		querysql.appendField(displayCol.getSelectFieldName());

		ViewParameter vp = DesignObjectLoader.instance().loadDesignObject(viewkey + ".vpKey");
		String vpsql = vp.getF_sql();
		if(vpsql != null){
			vpsql = vpsql.replaceAll("\\|value\\|", value);
			querysql.appendWhere(vpsql);
		}
		
		List<List<String>> displayrecords = MixkyDataAccess.instance().find(querysql.getSql(false), null, 0, 0);
		if(displayrecords.size() > 0){
			key = displayrecords.get(0).get(0);
		}
		
		result.addProperty("key", key);
		result.addProperty("value", value);
	}else{
		JsonArray array = new JsonArray();
		result.add("results", array);	
	}
%>
<%=result.toString()%>