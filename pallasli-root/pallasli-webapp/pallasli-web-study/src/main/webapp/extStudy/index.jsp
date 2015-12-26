<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.io.*" %>
<%String rootPath=request.getContextPath();  
try{
String rootPath2=request.getRealPath("/"); 
File file = new File(rootPath2+"/extStudy/asd.txt"); 
File parent = file.getParentFile(); 
if(parent!=null&&!parent.exists()){ 
parent.mkdirs(); 
} 
file.createNewFile(); 

}catch(Exception e){}




%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/dependences/ext/3.1.0/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/src/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="Pallas.app.menu.menus.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/plugins/pagerbar/pagerbar.js"></script>
	
	
	
	<script src="jquery-1.3.2.js" type="text/javascript"></script>
		<script src="jquery.autogrow.js" type="text/javascript"></script>
		<script src="scripts.js" type="text/javascript"></script>
		
<script type="text/javascript" src="<%=rootPath%>/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript" src="<%=rootPath%>/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="<%=rootPath%>/extDirect/api/api.js"></script>
		</head>
<script>

Ext.onReady(function(){
	var helpTbar= [{
        text: 'Ext3.1.0帮助文档',
        split:true,
        menu: [{
		           xtype:"", text: 'Ext3.1.0帮助文档',
		            handler: function() {
		                window.open("/ext3.1.0/docs/index.html");
		            	}
		        },{
	                text: 'Ext3.1.0帮助文档',
	                handler: function() {
	                    window.open("/ext3.1.0/docs/index.html");
	                }
        	
   			 }]
        
    },'->',{
        text: 'Ext3.1.0帮助文档',
        handler: function() {
            window.open("/ext3.1.0/docs/index.html");
        }
    },{
        text: 'login--portal',
        handler: function() {
    		mainPanel.load({url:'login.do',script:true})
        }
    }];

	var windowMenuPanel=new Ext.Panel({
		title:"测试",
		items:windowMenuTreePanel
	});
	var webMenuPanel=new Ext.Panel({
		title:"小案例",
		items:webMenuTreePanel
	});
	
	var headerPanel=new Ext.Panel({
		title:"",
		height:28,
		border:false,
		tbar:helpTbar,
		region:"north"
	});
	var menuPanel=new Ext.Panel({
		title:"",
		border:false,
		split:true,
		items:[windowMenuPanel,webMenuPanel],
		layout:'accordion',
		collapsible:true,
		width:200,
		region:"west"
	});
	var mainPanel=new Ext.TabPanel({
		title:"",
		border:false,
		region:"center",
		id:'mainPanelId'
	});
	
	new Ext.Viewport({
		layout:'border',
		title:"",
		items:[headerPanel,menuPanel,mainPanel]
	});

});

</script>