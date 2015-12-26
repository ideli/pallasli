<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath();  %>
<html>
<head>

<link rel="stylesheet" type="text/css" href="<%=rootPath%>/dependences/ext/3.1.0/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/src/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/plugins/pagerbar/pagerbar.js"></script>
	<script type="text/javascript">
			Ext.onReady(function(){

				var tree1Panel=new Ext.tree.TreePanel({
					rootVisible : false,
					border:false,
					root:{
						text:"根节点",
						expanded:true,
						children:[{
									text:"测试普通grid",
									leaf:true,
									listeners:{
										click:function(){
											var tabs=Ext.getCmp('mainPanelId');
											var id_tmp=Ext.id();
								        	tabs.add({
								                title: '测试普通grid' ,
								                layout:'anchor',
								                id:id_tmp,
												autoLoad:{url:"gridTest.jsp",scripts:true,params:{panelId:id_tmp}},
												bbar:[{text:'测试'}],
												autoScroll:true,
								                closable:true
								            }).show();
										}	
									}
								}	          
						]
						
					}
					
				});




				
				var fit1Panel=new Ext.Panel({
					title:"fit1Panel",
					border:false
				});

				var fit2Panel=new Ext.Panel({
					title:"fit2Panel",
					border:false
				});


				var firstPanel=new Ext.Panel({
					title:"firstPanel",
					layout:'accordion',
					items:[tree1Panel],
					border:false
				});
				var secondPanel=new Ext.Panel({
					title:"secondPanel",
					border:false
				});

				var headerPanel=new Ext.Panel({
					title:"headerPanel",
					height:28,
					border:false,
					region:"north"
				});
				var westPanel=new Ext.Panel({
					title:"westPanel",
					border:false,
					width:200,
					layout:'accordion',
					split:true,
					region:"west",
					items:[firstPanel,secondPanel]
				});
				var centerPanel=new Ext.TabPanel({
					title:"centerPanel",
					border:false,
					id:'mainPanelId',
					region:"center"
				});


				
						new Ext.Viewport({
							layout:'border',
							title:"",
							items:[headerPanel,westPanel,centerPanel]
						});

				});
		
		</script>
</head>
<body>


</body>

</html>