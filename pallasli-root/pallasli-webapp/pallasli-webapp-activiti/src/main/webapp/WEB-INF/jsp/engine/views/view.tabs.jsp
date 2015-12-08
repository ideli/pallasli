<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="com.mixky.toolkit.gson.JsonFunction"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.toolkit.JsonObjectTool"%>

<%
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	
	View view = (View)request.getAttribute("view");
	User user = (User)request.getAttribute("user");
	
	List<Action> actions = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);
	
	JsonArray items = new JsonArray();
	for(int i=0;i<actions.size();i++){
		Action btn = actions.get(i);
		JsonObject tabPanel = new JsonObject();
		if(btn.getF_config() != null){
			JsonObject autoLoad = new JsonObject();
			if(btn.getF_config().has("viewkey")){
				JsonFunction url = new JsonFunction("app.url + '/' + 'view.do'");
				autoLoad.add("url", url);
			}else if(btn.getF_config().has("url")){
				JsonFunction url = new JsonFunction("app.url + '/' + '" + btn.getF_config().get("url").getAsString() + "'");
				autoLoad.add("url", url);
			}
			autoLoad.addProperty("scripts", true);
			JsonObject params = new JsonObject();
			if(btn.getF_config().has("viewkey")){
				params.addProperty("viewkey", btn.getF_config().get("viewkey").getAsString());
			}
			params.addProperty("appkey", appkey);
			autoLoad.add("params", params);
			tabPanel.add("autoLoad", autoLoad);
			if(btn.getF_config().has("configs")){
				JsonObjectTool.applyJson(tabPanel, btn.getF_config().get("configs").getAsJsonObject());
			}
		}
		tabPanel.addProperty("title", btn.getF_caption());
		tabPanel.addProperty("iconCls", btn.getF_icon());
		items.add(tabPanel);
	}
%>
<script language='javascript'>
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];

	var itemConfigs = <%=items%>;
	var items = [];
	for(var i=0;i<itemConfigs.length;i++){
		items.push(new Ext.Panel(itemConfigs[i]));
	}
	for(var i=0;i<items.length;i++){
		if(items[i].autoLoad){
			items[i].autoLoad.params.panelid = items[i].getId();
		}
	}
	
	var tabs = new Ext.TabPanel({
		border : false,
		activeTab: 0,
		defaults : {
			border : false,
			layout : 'fit'
		},
		enableTabScroll : true,
		items : items
	});

	panel.getCurrentView = function(){
		return tabs.getActiveTab();
	}

	panel.refresh = function(){
		var view = tabs.getActiveTab();
		if(view && view.refresh){
			view.refresh();
		}
	}
	
	panel.add(tabs);
	panel.doLayout();
	panel.setAutoScroll(false);
});
</script>

