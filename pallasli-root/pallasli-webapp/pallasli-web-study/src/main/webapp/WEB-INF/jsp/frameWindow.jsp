
<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="utf-8"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
String urlParam="";
Enumeration paramNames=request.getParameterNames();
while(paramNames.hasMoreElements()){
	String paramName=paramNames.nextElement().toString();
	urlParam+=paramName+"="+request.getParameter(paramName).toString();
	if(paramNames.hasMoreElements()){
		urlParam+= "&";
	} 
} 
String basePath=request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <link rel="stylesheet" type="text/css" href="<%=basePath%>/scripts/desktop/css/desktop.css" />

	<script type="text/javascript" src="<%=basePath%>/scripts/include-ext.js?theme=classic"></script>
    <script type="text/javascript" src="<%=basePath%>/scripts/desktop/options-toolbar.js"></script>
<script type="text/javascript" src="<%=basePath%>/djn/djn-remote-call-support.js"></script>
<script type="text/javascript" src="<%=basePath%>/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="<%=basePath%>/api/api.js"></script>
<script type="text/javascript"> 

        Ext.onReady(function () {
			var viewportid=Ext.id();
        	Ext.create('Ext.container.Viewport', {
				layout:"fit",
				loader: {
    	            url: 'jsppage.do?<%=urlParam%>',
    	            params:{panelId:viewportid},
    	            autoLoad:true,
    	            scripts:true
    	        }
        	});
        });
    </script>
</head>
</html>
