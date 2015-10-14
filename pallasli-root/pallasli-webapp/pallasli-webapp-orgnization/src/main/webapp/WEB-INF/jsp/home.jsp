<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>组织结构管理</title>
<link rel="stylesheet" type="text/css"
	href="/pallasli-web-js-css/styles/desktop/desktop.css" />

<script type="text/javascript"
	src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>


<script type="text/javascript"
	src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>


<script type="text/javascript"
	src="/pallasli-webapp-portal/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="/pallasli-webapp-portal/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript"
	src="/pallasli-webapp-portal/extDirect/api/api.js"></script>

</head>

<script type="text/javascript">
	Pallas.portal.api.REMOTING_API.enableBuffer = 0;
	Ext.Direct.addProvider(Pallas.portal.api.REMOTING_API);

	Ext.direct.Manager.addProvider(Pallas.orgnization.api.REMOTING_API, {
		type : 'remoting',
		url : "/pallasli-webapp-portal/djn/directprovider",
		actions : Pallas.portal.api.REMOTING_API.actions
	});
	Ext.Loader.setPath({
		'Pallas.portal' : '/pallasli-webapp-portal/scripts/portal',
		'Pallas.lib' : '/pallasli-webapp-portal/scripts/lib',
		'Pallas.chat' : '/pallasli-webapp-portal/scripts/chat'
	});
	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);

	function openDocument(appkey, params) {
		var panelId = Ext.id();
		var tmpPanel = Ext.create("Ext.panel.Panel", {
			layout : 'fit',
			closable : false,
			border : false,
			id : panelId,
			loader : {
				url : '/' + appkey + '/jsppage.do?url=' + params.url
						+ '&panelId=' + panelId,
				autoLoad : true,
				scripts : true,
				params : params
			}
		});

		console.log(document);
		console.log(document.body);
		
		var addDeptWindow = new Ext.Window(Ext.apply({
			layout : 'fit',
			width : 400,
			height : 300,
			resizable : false,
			draggable : true,
			closable : true,
			modal : true,
			closeAction : 'hide',
			title : '<span class="commoncss">'+params.title+'</span>',
			// iconCls : 'page_addIcon',
			collapsible : false,
			titleCollapse : false,
			maximizable : false,
			minimizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : true,
			pageY : (document.body.clientHeight  -params.height?params.height:300)/2,
			pageX : (document.body.clientWidth -params.width?params.width:400)/2,
			animateTarget : Ext.getBody(),
			constrain : true,
			items : [ tmpPanel ],
			buttons : [
					{
						text : '保存',
						iconCls : 'acceptIcon',
						id : 'btn_id_save_update',
						handler : function() {
							if (runMode == '0') {
								Ext.Msg.alert('提示',
										'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
								return;
							}
							var mode = Ext.getCmp('windowmode').getValue();
							if (mode == 'add')
								saveDeptItem();
							else
								updateDeptItem();
						}
					}, {
						text : '重置',
						id : 'btnReset',
						iconCls : 'tbar_synchronizeIcon',
						handler : function() {
							clearForm(addDeptFormPanel.getForm());
						}
					}, {
						text : '关闭',
						iconCls : 'deleteIcon',
						handler : function() {
							addDeptWindow.hide();
						}
					} ]
		},params));
		addDeptWindow.show();
	}

	Ext.onReady(function() {

		var appkey = "pallasli-webapp-orgnization";

		SysNavigationDirect.loadMenus(function(result) {
			var appMenuButtons = result.appMenus;

			var viewPanel = Ext.create('Ext.tab.Panel', {
				items : [],
				region : "center"
			});

			var openTab = function(params) {

				var url = params.url.replace(/\//g, '_');
				var panelId = "tab_" + url;
				var tabPanel = Ext.get(panelId);
				if (tabPanel) {
					viewPanel.setActiveTab(tabPanel.id);
					return;
				}
				var tabPage = Ext.create("Ext.panel.Panel", {
					title : params.title,
					//region : 'center', // center region is required, no width/height specified
					xtype : 'tab',
					layout : 'fit',
					border : false,
					closable : true,
					params : params,
					//margins : '5 5 0 0',
					id : panelId,
					loader : {
						url : '/' + appkey + '/jsppage.do?url=' + params.url
								+ '&panelId=' + panelId + '&nid=' + panelId,
						autoLoad : true,
						scripts : true,
						params : params
					},
					listeners : {
						activate : function(tab) {
							tab.loader.load();
						}
					}
				});
				viewPanel.add(tabPage);
				viewPanel.setActiveTab(tabPage);
			};

			appMenuButtons = [ {
				text : '菜单资源管理',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgManager/resourcesManger'
					};
					openTab(params);
				}

			}, {
				text : 'UI托管',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgManager/uiManager'
					};
					openTab(params);
				}

			}, {
				text : '组织机构管理',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgManager/deptManager'
					};
					openTab(params);
				}

			}, {
				text : '角色管理与授权',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgManager/roleManager'
					};
					openTab(params);
				}

			}, {
				text : '人员管理与授权',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgManager/userManager'
					};
					openTab(params);
				}

			}, {
				text : '权限管理',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgnization/qxgl'
					};
					openTab(params);
				}

			}, {
				text : '资源管理',
				iconCls : 'add16',
				handler : function() {
					var params = {
						title : this.text,
						url : 'orgnization/zygl'
					};
					openTab(params);
				}

			} ];

			var portal = Ext.create("Ext.panel.Panel", {
				tbar : appMenuButtons,
				layout : "fit",
				items : [ viewPanel ]
			});
			Ext.create('Ext.container.Viewport', {
				layout : 'fit',
				items : [ portal ]
			});

		});
	});
</script>
</html>