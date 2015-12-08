<%@ page import="com.mixky.app.ApplicationParameters"%>
<%@page contentType="text/html; charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><%=ApplicationParameters.instance().getApplicationName()%>——应用开发集成环境</title>
		<!-- Ext Core Include -->
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/resources/css/ext-all.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/ext-all-debug.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/locale/ext-lang-zh_CN.js"></script>

<script language=javascript>
Ext.namespace("Mixky.wasoft.cache");
</script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.cache.applications.js"></script>
	</head>
</html>
<script language=javascript>
Ext.onReady(function(){
	var tabpanel = new Ext.TabPanel({
		region: 'center',
		deferredRender : false,
		enableTabScroll : true,
		cmenu : new Ext.menu.Menu({
	    	items : [{
	    		text : '刷新',
	    		iconCls : 'icon-sys-refresh',
	    		handler : function(){
	    			tabpanel.getActiveTab().refresh();
	    		}
	    	}]
	    }),
	    refresh : function(){
			var p = tabpanel.getActivateTab
	    },
		listeners : {
			'contextmenu' : function(tab, p, e){
	    		tab.cmenu.showAt(e.getXY());
			}
		}
	});
	tabpanel.addAppPanel = function(app){
		var panel = new Ext.Panel({
			title : app.text,
			layout : 'fit',
			iframeUrl : app.url,
			refresh : function(){
				this.iframeEl.dom.src = 'about:blank';
				this.iframeEl.dom.src = this.iframeUrl;
			},
			listeners : {
				'render' : function(p){
					var iframe = document.createElement("iframe");
					iframe.setAttribute("width","100%");
					iframe.setAttribute("height","100%");
					iframe.setAttribute("frameborder","0");
					p.iframeEl = p.body.appendChild(iframe);
					p.iframeEl.dom.src = p.iframeUrl;
				}
			}
		});
		if(app.key == 'designer'){
			tabpanel.insert(1, panel);
		}else if(app.key == 'portal'){
			tabpanel.insert(0, panel);
		}else{
			tabpanel.add(panel);
		}
		tabpanel.doLayout();
		tabpanel.setActiveTab(panel);
	}

	tabpanel.refresh = function(){
		this.addAppPanel({text : '管理工具',url : 'administrator.do'});
		var count = 1;
		for(n in Mixky.wasoft.cache.Applications){
			this.addAppPanel.defer(count * 1500, this, [Mixky.wasoft.cache.Applications[n]]);
			count ++;
		}
		this.addAppPanel.defer(count * 1500, this, [{key: 'portal', text : '应用门户',url : 'home.do'}]);
	}

	var view = new Ext.Viewport({
		layout : 'border',
		items : [tabpanel]
	});

	tabpanel.refresh.defer(100, tabpanel);
});
</script>