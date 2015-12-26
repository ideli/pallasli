<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath(); %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/dependences/ext/4.2.1/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/4.2.1/bootstrap.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/scripts/pallas/app/menu/Pallas.app.menu.menus.js"></script>
	
		<script src="<%=rootPath%>/scripts/JQuery/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="<%=rootPath%>/scripts/JQuery/jquery.autogrow.js" type="text/javascript"></script>
		<script src="<%=rootPath%>/scripts/JQuery/scripts.js" type="text/javascript"></script>
	</head>
	<body>
		<script type="text/javascript">
		
			Ext.onReady(function(){
				Ext.require(['*']);
				var helpTbar= ['->',{
		            text: 'Ext3.1.0帮助文档',
		            handler: function() {
		                window.open("/ext3.1.0/docs/index.html");
		            }
		        },'-',{
		            text: 'Ext4.2.1帮助文档',
		            handler: function() {
		            	window.open("/ext4.2.1/docs/index.html");
		            }
		        }];
/**
				var managerMenuPanel=new Ext.panel.Panel({
					title:"管理",
					items:managerMenuTreePanel
				});
				var webMenuPanel=new Ext.panel.Panel({
					title:"前端",
					items:webMenuTreePanel
				});
				var javaMenuPanel=new Ext.panel.Panel({
					title:"java",
					items:javaMenuTreePanel
				});
				var databaseMenuPanel=new Ext.panel.Panel({
					title:"数据库",
					items:databaseMenuTreePanel
				});
				
				var webServerMenuPanel=new Ext.panel.Panel({
					title:"web服务器/应用服务器",
					items:webServerMenuTreePanel
				});
				var buildingMenuPanel=new Ext.panel.Panel({
					title:"构建工具",
					items:buildingMenuTreePanel
				});
				var systemMenuPanel=new Ext.panel.Panel({
					title:"操作系统",
					items:systemMenuTreePanel
				});
				var yunMenuPanel=new Ext.panel.Panel({
					title:"云平台"
				});**/
				var headerPanel=new Ext.panel.Panel({
					title:"",
					height:28,
					tbar:helpTbar,
					region:"north"
				});
				/**var menuPanel=new Ext.panel.Panel({
					title:"",
					items:[managerMenuPanel,webMenuPanel,javaMenuPanel,databaseMenuPanel,webServerMenuPanel,buildingMenuPanel,systemMenuPanel,yunMenuPanel],
					layout:'accordion',
					collapsible:true,
					width:200,
					region:"west"
				});**/
				var mainPanel=new Ext.panel.Panel({
					title:"",
					layout:'fit',
					region:"center",
					id:'mainPanelId',
					items:new Ext.panel.Panel({
						layout:'fit',
						title:'',
						height:530,
						width:'auto',
						autoScroll : true,
						autoLoad:{url:"ext3.1.0Test.jsp",scripts:true},
						bbar:[{text:'测试javascript/Extjs3.1.0',id:'button_id'}]
					})
				});
				var footerPanel=new Ext.panel.Header({
					title:"",
					html:"",
					height:20,
					region:"south"
				});
				
				new Ext.Viewport({
					layout:'border',
					title:"",
					items:[headerPanel,
							//menuPanel,
							mainPanel,footerPanel]
				});
			
			});
		
		</script>
	
	</body>
</html>