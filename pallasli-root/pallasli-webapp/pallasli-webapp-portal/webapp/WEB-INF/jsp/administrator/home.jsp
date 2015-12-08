<%@ page import="com.google.gson.JsonObject"%>
<%@page contentType="text/html; charset=utf-8"%>
<%
	JsonObject applicationInfo = (JsonObject)request.getAttribute("applicationInfo");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><%=applicationInfo.get("title").getAsString()%> - 应用管理</title>
		<!-- Ext Core Include -->
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/resources/css/ext-all.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/ext-all.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/locale/ext-lang-zh_CN.js"></script>
		<!-- SWFUpload Include -->
		<script type="text/javascript" src="dependences/swfupload/swfupload.js"></script>
		<!-- Ext Plugins Include -->
		<!-- Ext Plugins Include -->
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/pagerbar/pagerbar.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/CheckColumn.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/TabCloseMenu.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/DataView-more.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/RowEditor.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/pagerbar/PagingRowNumberer.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/datetimefield/datetimefield_1.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/RowEditor.css"/>
		<!-- Google Chart API Include -->
		<script type="text/javascript" src="dependences/googlechart/jsapi"></script>
		<script type="text/javascript" src="dependences/googlechart/default,orgchart.I.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/googlechart/orgchart.css"/>
		<script type="text/javascript" src="resources/js/plugins/GVisualizationPanel.js"></script>
		<!-- Direct API Include -->
		<script type="text/javascript" src="resources/output/administrator/api.js"></script>
		
		<!-- Sets the basepath for the library if not in same directory -->
		<script type="text/javascript">
			mxBasePath = 'dependences/mxgraph/src';
		</script>

		<!-- Loads and initiaizes the library -->
		<script type="text/javascript" src="dependences/mxgraph/mxclient.min.js"></script>

<script language=javascript>
// 初始化应用参数
var ApplicationInfo = <%=applicationInfo%>;
</script>
		<!-- Mixky Editor & Plugins Include -->
		<script type="text/javascript" src="resources/js/plugins/mixky.upload.button.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.editor.paneltriggerfield.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.editor.textbox.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.editor.jsonobject.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.editor.organization.js"></script>
		<script type="text/javascript" src="resources/js/administrator/editor/mixky.editor.designobject.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.propertygrid.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.rowordercolumn.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.rownumberercolumn.js"></script>
		<link rel="stylesheet" type="text/css" href="resources/js/editor/mixky.organization.window.css"/>
		<script type="text/javascript" src="resources/js/editor/mixky.organization.window.js"></script>
		<!-- Mixky Lib Include -->
		<script type="text/javascript" src="resources/js/mixky.lib.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.lib.notification.js"></script>
		<!-- Mixky Core Include -->
		<link rel="stylesheet" type="text/css" href="resources/css/administrator.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/mixky.awsoft.icon.css"/>
		<script type="text/javascript" src="resources/output/administrator/mixky.awsoft.lib.js"></script>
		<script type="text/javascript" src="resources/output/administrator/mixky.awsoft.lib.classes.js"></script>
		<script type="text/javascript" src="resources/output/administrator/mixky.awsoft.framework.js"></script>
		<script type="text/javascript" src="resources/js/administrator/mixky.awsoft.app.js"></script>
		<!-- yzz ux js  -->
		<script type="text/javascript" src="dependences/ux/ux_LockingGridView.js"></script>
		<link rel="stylesheet" type="text/css" href="resources/css/portal.css"/>
		<script type="text/javascript" src="resources/js/common/atwasoft.common.prototype.js"></script>
	</head>
	<body scroll="no">
		<div id="mixky-loading-mask"></div>
		<div id="mixky-loading">
			<div class="mixky-loading-indicator">正在装载应用...</div>
		</div>
	</body>
</html>
<script language=javascript>
// 创建应用
var MixkyApp = new Mixky.awsoft.App();
</script>