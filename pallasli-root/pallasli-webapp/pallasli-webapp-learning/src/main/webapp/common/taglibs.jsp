<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<%
	String path = request.getContextPath();
	request.setAttribute("basePath", path);
	request.setAttribute("scripts", path + "/scripts");
	request.setAttribute("styles", path + "/styles");
	request.setAttribute("images", path + "/images");
%>
<html>
	<head>
		<script type="text/javascript" src="${scripts}/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="${scripts}/ext/ext-all.js"></script>
		<script type="text/javascript" src="${scripts}/ext/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${scripts}/redkit/redkit.js"></script>
		<script type="text/javascript" src="${scripts}/redkit/ui/TabPanel.js"></script>
		<script type="text/javascript" src="${scripts}/redkit/ui/QueryDialog.js"></script>
		<script type="text/javascript" src="${scripts}/redkit/ui/treeCombo.js"></script>
		<script type="text/javascript" src="${scripts}/page/Page.js"></script>
		<script type="text/javascript" src="${scripts}/ext-patch/ext-patch.js"></script>
		<script type="text/javascript" src="${scripts}/default.js"></script>
		<link rel="stylesheet" type="text/css" href="${styles}/default.css" />
		<link rel="stylesheet" type="text/css" href="${styles}/ext-patch.css" />
		<link rel="stylesheet" type="text/css" href="${styles}/bizfuse.css" />
	<script type="text/javascript" src="${scripts}/ext-plugins/mif/miframe-debug.js"></script>
	<script type="text/javascript" src="${scripts}/ext-plugins/tabs/TabCloseMenu.js"></script>
		<link rel="stylesheet" href="${scripts}/ext/resources/css/ext-all.css"
			type="text/css"></link>
	<link rel="stylesheet" href="${styles}/examples.css" type="text/css"></link>
</head>
</html>


