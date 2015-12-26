<%@page language="java" contentType="text/html; charset=utf-8"%>

<%String rootPath=request.getContextPath();  %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%=rootPath%>/dependences/ext/3.1.0/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/dependences/ext/3.1.0/src/locale/ext-lang-zh_CN.js"></script>
		<script src="<%=rootPath%>/scripts/JQuery/jquery-1.3.2.js" type="text/javascript"></script>
		<script src="<%=rootPath%>/scripts/JQuery/jquery.autogrow.js" type="text/javascript"></script>
		<script src="<%=rootPath%>/scripts/JQuery/scripts.js" type="text/javascript"></script>
	
	
<script type="text/javascript" src="<%=rootPath%>/MyAction/MyActionApi.js"></script>  
	</head>
<script>

Ext.zhouyang.REMOTING_API.enableBuffer = 0;  
Ext.Direct.addProvider(Ext.zhouyang.REMOTING_API);  
Ext.onReady(function(){
	
	//var textvalue=window.dialogArguments.document.getElementById("textarea_id").value;
	try{
		eval(window.dialogArguments.document.getElementById("textarea_id").value);

	
	}catch(e){
		alert(e);
		
	}

	
});
</script>