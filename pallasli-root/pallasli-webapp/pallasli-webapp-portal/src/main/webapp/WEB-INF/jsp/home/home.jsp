<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>工作平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript"
	src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script> 

<script type="text/javascript"
	src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>


<script type="text/javascript"
	src="/pallasli-webapp-orgnization/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="/pallasli-webapp-orgnization/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="/pallasli-webapp-orgnization/extDirect/api/api.js"></script>

<script type="text/javascript"
	src="/pallasli-webapp-activiti/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="/pallasli-webapp-activiti/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="/pallasli-webapp-activiti/extDirect/api/api.js"></script>

<script type="text/javascript"
	src="/pallasli-webapp-designer/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="/pallasli-webapp-designer/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="/pallasli-webapp-designer/extDirect/api/api.js"></script>

<script type="text/javascript" src="/pallasli-webapp-designer/scripts/design/definedModels.js"></script>
	
<script type="text/javascript">


Pallas.design.api.REMOTING_API.enableBuffer = 0;
Ext.Direct.addProvider(Pallas.design.api.REMOTING_API);

Pallas.activiti.api.REMOTING_API.enableBuffer = 0;
Ext.Direct.addProvider(Pallas.activiti.api.REMOTING_API);

Pallas.orgnization.api.REMOTING_API.enableBuffer = 0;
Ext.Direct.addProvider(Pallas.orgnization.api.REMOTING_API);


Ext.direct.Manager.addProvider(Pallas.portal.api.REMOTING_API,{
	type: 'remoting',
	url: "/pallasli-webapp-designer/djn/directprovider",
	actions:Pallas.design.api.REMOTING_API.actions
});

Ext.direct.Manager.addProvider(Pallas.portal.api.REMOTING_API,{
	type: 'remoting',
	url: "/pallasli-webapp-activiti/djn/directprovider",
	actions:Pallas.activiti.api.REMOTING_API.actions
});


Ext.direct.Manager.addProvider(Pallas.portal.api.REMOTING_API,{
	type: 'remoting',
	url: "/pallasli-webapp-orgnization/djn/directprovider",
	actions:Pallas.orgnization.api.REMOTING_API.actions
});

	Ext.Loader.setPath({
		'Pallas.portal' : 'scripts/portal',
		'Pallas.lib' : 'scripts/lib',
		'Pallas.chat' : 'scripts/chat',
	     	'Pallas.design.portal': '/pallasli-webapp-designer/scripts/design/app',
	     	'Pallas.common': '/pallasli-webapp-design/scripts/design/common',
	     	'Pallas.common.window': '/pallasli-webapp-designer/scripts/design/common/window',
	         	'Pallas.design.designWin': '/pallasli-webapp-designer/scripts/design/designWin'
	    });
	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);

	Ext.onReady(function() {


		
		SysNavigationDirect.loadMenus(function(result){
			var sysMenuButtons=result.sysMenus;
			var appMenuButtons=result.appMenus;
			
			
			
			sysMenuButtons=[  {
				//xtype : 'splitbutton',
				text : '设计工具',
				iconCls : 'add16',
				menu : [ Ext.create("Pallas.portal.SysMenu",{
					text : '字段设置',
					attributes:{appKey:"pallas_design",moduleKey:"m_g_sys.m_field_set",isActionMenu:true}
				}),Ext.create("Pallas.portal.SysMenu",{
					text : '接口设置',
					attributes:{appKey:"pallas_design",moduleKey:"m_g_sys.m_jksz",isActionMenu:true}
				}), Ext.create("Pallas.portal.SysMenu",{
					text : '页面设置',
					attributes:{appKey:"pallas_design",moduleKey:"m_g_sys.m_page_set",isActionMenu:true}
				}), Ext.create("Pallas.portal.SysMenu",{
					text : '系统设置',
					attributes:{appKey:"pallas_design",moduleKey:"m_g_sys.m_system_set",isActionMenu:true}
				}), Ext.create("Pallas.portal.SysMenu",{
					text : '数据库管理',
					attributes:{appKey:"pallas_design",moduleKey:"m_g_sys.m_field_set",isActionMenu:true}
				}) ]
			}, {
				xtype : 'splitbutton',
				text : '桌面管理',
				iconCls : 'add16',
				menu : [ {
					text : '桌面栏目',
					attributes:{key:"key"},
					handler : function() {
						var appKey="design";
						var moduleKey="module";
						var params={title:this.text};
						me.openModule(appKey,moduleKey,params);
					}
				} ]
			}, {
				text : '用户管理',
				iconCls : 'add16',
				menu : [ {
					text : '管理员登录',
					handler : function() {
						var appKey="design";
						var moduleKey="module";
						var params={title:this.text};
						me.openModule(appKey,moduleKey,params);
					}
				},  {
					text : '退出',
					iconCls : 'add16',
					attributes:{moduleKey:"key"},
					handler : function() {
						var appKey="design";
						var moduleKey="module";
						var params={title:this.text};
						me.openModule(appKey,moduleKey,params);
					}
				}]
			}];
			
			appMenuButtons=[ {
				text : '我的桌面',
				iconCls : 'add16',
				handler : function() {
					var firstWorkshop=Ext.getCmp("firstWorkshop");
					Pallas.portal.SinglePortal.workshopTabs.setActiveTab(firstWorkshop);
				}
			},{
				text : '内部项目管理',
				iconCls : 'add16',
				menu : [  
						Ext.create("Pallas.portal.SysMenu",{
							text : '我的地盘',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"dashboard",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '组织视图',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"orgnization",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '项目视图',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"project",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '产品视图',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"product",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '需求视图',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"story",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '文档视图',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"document",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '开发管理',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"development",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '测试视图',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"testing",isActionMenu:true}
						}),Ext.create("Pallas.portal.SysMenu",{
							text : '后台管理',
							attributes:{appKey:"pallas_soft_manager",moduleKey:"softManager",isActionMenu:true}
						})
				]
			},{ 
				text : '外部组织结构',
				iconCls : 'add16',
				menu : [ Ext.create("Pallas.portal.SysMenu",{
							text : '组织结构',
							attributes:{appKey:"pallasli-webapp-orgnization",moduleKey:"orgnization",isActionMenu:true}
						})]
			},{
				text : '业务应用',
				iconCls : 'add16',
				menu : [{
					text : '流程管理',
					iconCls : 'add16',
					menu : [  
							Ext.create("Pallas.portal.SysMenu",{
								text : '流程管理',
								attributes:{appKey:"pallas_activiti",moduleKey:"bpm",isActionMenu:true}
							}),Ext.create("Pallas.portal.SysMenu",{
								text : '我的工作台',
								attributes:{appKey:"pallas_activiti",moduleKey:"workbanch",isActionMenu:true}
							})
					]
				},{
					text : '数据分析',
					iconCls : 'add16',
					menu : [{
								text : '查询分析',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="analysis/query_analysis";
									var id="pallas_db_analysis_cxfx";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							}, {
								text : '报表分析',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="analysis/report_analysis";
									var id="pallas_db_analysis_bbfx";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							}, {
								text : '数据分析',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="analysis/data_analysis";
									var id="pallas_db_analysis_sjfx";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							}, {
								text : '数据源',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="build/datasource";
									var id="pallas_db_analysis_sjy";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							}, {
								text : '表达式',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="build/expression";
									var id="pallas_db_analysis_bds";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							}, {
								text : '查询模型',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="build/query_model";
									var id="pallas_db_analysis_cxmx";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							}, {
								text : '数据模型',
								handler:function(){
									var appKey="pallas_db_analysis";
									var url="build/data_model";
									var id="pallas_db_analysis_sjmx";
									var title=this.text;
									var params={};
									Pallas.portal.SinglePortal.addWorkshopView(appKey,url,id,title,params);
								}
							} ]
				}  ]
			}, {
				text : '在线交流',
				iconCls : 'add16',
				handler : function(){
					Ext.create("Pallas.chat.UserClient").showAt(document.documentElement.clientWidth-220,0);;
				}
			}, {
				text : '流程图设计',
				iconCls : 'add16',
				handler : function(){
					Ext.Loader.setPath({
						'Pallas.activitiDesigner' : '../activitiDesigner/scripts'
					});
					Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);


					Ext.create('Pallas.activitiDesigner.Designer', {
						width : window.innerWidth,
						height : window.innerHeight
					}).show();

				}
			}, {
				text : '学习总结',
				iconCls : 'add16',
				handler : function() { 
					window.open("/pallasli-web-study/home.do");
				}
			} ];
			
			var portal=Ext.create("Pallas.portal.Portal",{
				appMenuButtons:appMenuButtons,
				sysMenuButtons:sysMenuButtons				
			});
			Ext.create('Ext.container.Viewport', {
				layout : 'fit',
				
				items : [ portal ]
			});
			//Ext.create("Pallas.chat.UserClient").showAt(document.documentElement.clientWidth-220,0);;
			
		});


	});
</script>
</head>
</html>
