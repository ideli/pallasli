<%@page import="com.google.gson.JsonPrimitive"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@page import="com.mixky.engine.design.application.Application"%>
<%@page import="java.util.List"%>
<%@ page import="com.mixky.app.ApplicationParameters"%>
<%@page contentType="text/html; charset=utf-8"%>
<%
	List<Application> applications = DesignObjectLoader.instance().findObjects("application", null);
	JsonArray apps = new JsonArray();
	for(int i=0;i<applications.size();i++){
		Application app = applications.get(i);
		if("designer".equals(app.getKey())){
			continue;
		}
		JsonArray data = new JsonArray();
		//['key','caption','url','note','state']
		data.add(new JsonPrimitive(app.getKey()));
		data.add(new JsonPrimitive(app.getF_caption() == null ? "" : app.getF_caption()));
		data.add(new JsonPrimitive(app.getF_url() == null ? "" : app.getF_url()));
		data.add(new JsonPrimitive(app.getF_note() == null ? "" : app.getF_note()));
		data.add(new JsonPrimitive("unknown"));
		apps.add(data);
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><%=ApplicationParameters.instance().getApplicationName()%>——应用开发集成环境</title>
		<!-- Ext Core Include -->
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/resources/css/ext-all.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/ext-all-debug.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/locale/ext-lang-zh_CN.js"></script>
		
		<link rel="stylesheet" type="text/css" href="resources/css/portal.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/portal.webpage.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/mixky.awsoft.icon.css"/>
	</head>
</html>
<script language=javascript>
Ext.onReady(function(){
	var header = new Ext.Panel({
    	region : 'north',
        height : 39,
        minSize : 39,
        maxSize : 39,
	    margins : '0 0 0 0',
	    cmargins :'0 0 0 0',
	    border : false,
	    bodyCssClass : 'mixky-portal-title',
		data : {
			title : "伟奥3.0应用运行控制集成环境", 
			username : "",
			departmentname : ""
		},
		tpl : [
			'<table class="mixky-titlebar" height="100%">',
				'<tr valign=middle>',
					'<td width=100% class="mixky-appname">{title}</td>',
					'<td nowrap class="mixky-userinfo">{departmentname}　{username}</td>',
				'</tr>',
			'</table>'
		]
	});
	var appgrid = new Ext.grid.GridPanel({
    	region : 'center',
    	store : new Ext.data.ArrayStore({
    	    idIndex: 0,  
    	    fields: ['key','caption','url','note','state'],
    	    
    	    data : <%=apps%>
    	}),
    	sm : new Ext.grid.CheckboxSelectionModel(),
    	columns : [
			new Ext.grid.CheckboxSelectionModel(),
    		{id: 'key', dataIndex: 'key', header: '应用标识', width: 100},
    		{id: 'caption', dataIndex: 'caption', header: '应用名称', width: 150},
    		{id: 'url', dataIndex: 'url', header: '访问路径', width: 250},
    		{id: 'state', dataIndex: 'state', header: '状态', width: 80, renderer : function(value, metaData, record, rowIndex, colIndex, store){
    			var icon = 's.gif'
    			switch(value){
    			case 'connectting':
    				icon = "loading.gif";
    				break;
    			case 'unknown':
    				icon = "unknow.png";
    				break;
    			case 'running':
    				icon = "running.png";
    				break;
    			}
    			return "　<img title=" + value + " src ='resources/icon/common/" + icon + "'>　"
    		}},
    		{id: 'note', dataIndex: 'note', header: '说明'}
    	],
		autoExpandColumn : 'note',
		tbar : [{
			text : '刷新状态',
			iconCls : 'icon-common-refresh',
			handler : function(){
				var records = appgrid.getStore().getRange();
				appgrid.refreshApp(records);
			}
		},'->',{
			text : '启动应用',
			iconCls : 'icon-common-extend',
			handler : function(){
				if(appgrid.getSelectionModel().hasSelection()){
					var records = appgrid.getSelectionModel().getSelections();
					appgrid.restartApp(records);
				}else{
					alert('请选择需要重启的应用');
				}
			}
		},{
			text : '重新装载',
			iconCls : 'icon-common-extend',
			handler : function(){
				if(appgrid.getSelectionModel().hasSelection()){
					var records = appgrid.getSelectionModel().getSelections();
					appgrid.reloadApp(records);
				}else{
					alert('请选择需要重新装载的应用');
				}
			}
		},'-',{
			text : '生成文件',
			iconCls : 'icon-common-import',
			handler : function(){
				if(appgrid.getSelectionModel().hasSelection()){
					var records = appgrid.getSelectionModel().getSelections();
					appgrid.rebuildApp(records);
				}else{
					alert('请选择需要重新生成文件的应用');
				}
			}
		}],
		listeners : {
			afterrender : function(){
				var records = this.getStore().getRange();
				this.refreshApp.defer(200, this, [records]);
			}
		}
	});
	appgrid.restartApp = function(apps){
		if(apps.length > 0){
			var app = apps.pop();
			app.set('state', 'connectting');
			Ext.Ajax.request({
				url: app.get('url') + '/console.do',
				success: function(response){
					app.set('state', 'running');
					appgrid.restartApp(apps);
				},
				failure: function(){
					alert('重启【' + app.get('caption') + '】应用失败');
					app.set('state', 'unknown');
				},
				params: {a: 'start'}
			});
		}else{
			appgrid.getStore().commitChanges();
		}
	}
	appgrid.reloadApp = function(apps){
		if(apps.length > 0){
			var app = apps.pop();
			app.set('state', 'connectting');
			Ext.Ajax.request({
				url: app.get('url') + '/console.do',
				success: function(){
					app.set('state', 'running');
					appgrid.reloadApp(apps);
				},
				failure: function(){
					alert('重新装载【' + app.get('caption') + '】应用设计对象失败');
					app.set('state', 'unknown');
				},
				params: {a: 'reload'}
			});
		}else{
			appgrid.getStore().commitChanges();
		}
	}
	appgrid.rebuildApp = function(apps){
		if(apps.length > 0){
			var app = apps.pop();
			app.set('state', 'connectting');
			Ext.Ajax.request({
				url: app.get('url') + '/console.do',
				success: function(){
					app.set('state', 'running');
					appgrid.rebuildApp(apps);
				},
				failure: function(){
					alert('生成【' + app.get('caption') + '】应用文件失败');
					app.set('state', 'unknown');
				},
				params: {a: 'rebuild'}
			});
		}else{
			appgrid.getStore().commitChanges();
		}
	}
	appgrid.refreshApp = function(apps){
		if(apps.length > 0){
			var app = apps.pop();
			app.set('state', 'connectting');
			Ext.Ajax.request({
				url: app.get('url') + '/console.do',
				success: function(response){
					var obj = Ext.decode(response.responseText);
					app.set('state', obj.state);
					app.commit();
					appgrid.refreshApp(apps);
				},
				failure: function(){
					alert('获得应用【' + app.get('caption') + '】状态失败');
					app.set('state', 'unknown');
					app.commit();
					appgrid.refreshApp(apps);
				},
				params: {a: 'state'}
			});
		}
	}
	var view = new Ext.Viewport({
		layout : 'border',
		items : [header, appgrid]
	});
});
</script>