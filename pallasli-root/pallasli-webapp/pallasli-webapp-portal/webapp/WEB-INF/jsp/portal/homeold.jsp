<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="com.mixky.portal.application.ApplicationDataService"%>
<%@page contentType="text/html; charset=utf-8"%>

<jsp:directive.page import="com.mixky.engine.certification.MixkyUserCertification"/>
<jsp:directive.page import="com.mixky.engine.organization.dao.User"/>
<%   
	JsonObject applicationInfo = (JsonObject)request.getAttribute("applicationInfo");
	String departmentname=applicationInfo.get("departmentname").getAsString();
	String username=applicationInfo.get("username").getAsString();
	JsonObject userconfig = (JsonObject)request.getAttribute("userconfig");
	JsonArray usermenus = (JsonArray)request.getAttribute("usermenus");
	// 处理初始打开模块、视图或文档（与IM整合）
	JsonObject openParams = null;
	Object obj = request.getAttribute("openParams");
	if(obj == null){
		openParams = new JsonObject();
	}else{
		openParams = (JsonObject)obj;
	}
	//String title=applicationInfo.get("title").getAsString()+"___("+departmentname+"    "+username+")";
	String title = applicationInfo.get("title").getAsString();
	User user = MixkyUserCertification.instance().getUserInfo(request);
	String url = request.getRequestURL().toString();
	String msg = ((String)session.getAttribute("msg"));
	session.removeAttribute("msg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Expires" CONTENT="0">
        <meta http-equiv="Cache-Control" CONTENT="no-cache">
        <meta http-equiv="Pragma" CONTENT="no-cache">
        <meta http-equiv="X-UA-Compatible" content="edge" >
        <link rel="shortcut icon" type="image/x-icon" href="http://www.atwasoft.com/favicon.ico" media="screen" />
		<title><%=title%></title>
		<!-- Ext Core Include -->
		<link rel="stylesheet" type="text/css" href="resources/css/mingpian.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/resources/css/ext-all.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/RowEditor.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/portal/portal.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/pagerbar/PanelResizer.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/datetimefield/css/datetimefield.css"/>
		<link rel="stylesheet" type="text/css" href="resources/js/plugins/calendar/css/calendar_core.css"/>
		<link rel="stylesheet" type="text/css" href="resources/js/editor/mixky.organization.window.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/portal.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/portal.webpage.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/mixky.awsoft.icon.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/ux/css/LockingGridView.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/ux/css/ColumnHeaderGroup.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/ux/css/MultiSelect.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/ux/treegrid/treegrid.css"/>
		
		<!-- <link rel="stylesheet" type="text/css" href="resources/map/reset.css"/> -->
		<link rel="stylesheet" type="text/css" href="resources/map/jquery.hotspot.css"/>
		
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/ext-all-debug.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/locale/ext-lang-zh_CN.js"></script>
		<!-- SWFUpload Include -->
		<script type="text/javascript" src="dependences/swfupload/swfupload_1.js"></script>
		<!-- Ext Plugins Include -->
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/plugins.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/portal/portal_1.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/pagerbar/pagerbar.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/datetimefield/datetimefield_1.js"></script>
		<!-- yzz add  common format 必须在这个位置method-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.format.js"></script>
		<!-- Direct API Include -->
		<script type="text/javascript" src="resources/output/portal/api.js"></script>
<%=ApplicationDataService.instance().outputApplicationInclude(user)%>
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
		<script type="text/javascript" src="resources/js/plugins/plugins.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.triggerfield.enterkey.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.form.focus.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/common/RepeatType.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/common/Mask.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/mixky.calendar.ds.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/mixky.calendar.js"></script>
		<!-- Calender Plugin -->
		<!-- <script type="text/javascript" src="resources/js/editor/editor.js"></script>-->
		<script type="text/javascript" src="resources/js/editor/mixky.editor.paneltriggerfield.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.checkboxgroup.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.combo.view.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.depttreefield.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.display.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.editor.organization.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.editor.textbox.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.emailaddress.js"></script>
		<!-- script type="text/javascript" src="resources/js/editor/mixky.mixky.handsign.field.js"></script -->
		<script type="text/javascript" src="resources/js/editor/mixky.opinion.field.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.organization.window.field.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.radiogroup.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.textbox.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.filefield.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.emailaddress.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.combo.treefield.js"></script>
		<script type="text/javascript" src="resources/js/editor/atwasoft.lenovo.textfield.js"></script>
		<script type="text/javascript" src="resources/js/editor/atwasoft.numbertriggerfield.js"></script>
		<script type="text/javascript" src="resources/js/editor/atwasoft.numberText.js"></script>
		 <script type="text/javascript" src="resources/js/editor/mixky.organization.window.js"></script>
		<!-- Mixky Lib Include -->
		<script type="text/javascript" src="resources/js/atwasoft.lib.js"></script>

		<script type="text/javascript" src="resources/js/common/ext-base-ajax.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.lib.notification.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.lib.messagebox.fix.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.doc.functions.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.webaccess.functions.js"></script>
		<!-- yzz overloaded  method-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.prototype.js"></script>
		<!-- lzh add  common method-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.zjhm.js"></script>
		<script type="text/javascript" src="resources/js/common/atwasoft.common.date.js"></script>
		
		<!-- Mixky Core Include -->
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.commonapp.js"></script>

		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.lib.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.cache.applications.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.cache.menus.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.framework.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.workflow.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.webpage.desktop.js"></script>
		
		<script type="text/javascript" src="resources/js/portal/atwasoft.app.js"></script>
		<!-- Portal Dictionary Store -->
		<script type="text/javascript" src="resources/output/portal/mixky.awsoft.cache.dictionarys.js"></script>
		<!-- yzz add  common lib 必须在outpu后面-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.lib.js"></script>
		<!-- yzz ux js  -->
		<script type="text/javascript" src="dependences/ux/ux_LockingGridView.js"></script>
		<script type="text/javascript" src="dependences/ux/ColumnHeaderGroup.js"></script>
		<script type="text/javascript" src="dependences/ux/ux_RowspanView.js"></script>
		<script type="text/javascript" src="dependences/ux/ux_RowspanLockGridView.js"></script>
		<script type="text/javascript" src="dependences/ux/GroupSummary.js"></script>
		
		<script type="text/javascript" src="dependences/ux/MultiSelect.js"></script>
		<script type="text/javascript" src="dependences/ux/ItemSelector.js"></script>
		<script type="text/javascript" src="dependences/ux/treegrid/TreeGridSorter.js"></script>
		<script type="text/javascript" src="dependences/ux/treegrid/TreeGridColumnResizer.js"></script>
		<script type="text/javascript" src="dependences/ux/treegrid/TreeGridNodeUI.js"></script>
		<script type="text/javascript" src="dependences/ux/treegrid/TreeGridLoader.js"></script>
		<script type="text/javascript" src="dependences/ux/treegrid/TreeGridColumns.js"></script>
		<script type="text/javascript" src="dependences/ux/treegrid/TreeGrid.js"></script>
		<!-- scanjs Include -->
		<!--	
			<script type="text/javascript" src="dependences/scanjs/fujitsuscan.js"></script>
		    <script type="text/javascript" src="dependences/scanjs/kodakscan.js"></script>
        -->
		<script type="text/javascript" src="resources/ux/uploaddialog/ext.ux.uploaddialog.js"></script>
		<script type="text/javascript" src="resources/ux/uploaddialog/ext.swf.uploaddialog.js"></script>
		<!-- Google Chart API Include -->
		<script type="text/javascript" src="dependences/googlechart/jsapi"></script>
		<script type="text/javascript" src="dependences/googlechart/default,orgchart.I.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/googlechart/orgchart.css"/>
		<script type="text/javascript" src="resources/js/plugins/GVisualizationPanel.js"></script>
		<!-- tinymce -->
		<script type="text/javascript" src="dependences/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
		<script type="text/javascript" src="dependences/ux/Ext.ux.TinyMCE.min.js"></script>
		<!-- jquery  jOrgChart API Include -->
		<link rel="stylesheet" type="text/css" href="dependences/jquery/custom.css"/>
		<link rel="stylesheet" type="text/css" href="dependences/jquery/jquery.jOrgChart.css"/>
		<script type="text/javascript" src="dependences/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="dependences/jquery/jquery-ui.min.js"></script>
		<script type="text/javascript" src="dependences/jquery/jquery.jOrgChart.js"></script>
		<script type="text/javascript" src="dependences/echarts/esl/esl.js"></script>
		<script type="text/javascript" src="dependences/echarts/Atwasoft.chart.js"></script>
		<script type="text/javascript" src="dependences/echarts/Atwasoft.chart.Chart.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-extends.js"></script>
	    <script type="text/javascript" src="resources/js/common/atwasoft.common.home.js"></script>
	    
	    <!--北铁地图追加-->
		<script type="text/javascript" src="resources/map/modernizr.custom.js"></script>
	    <script type="text/javascript" src="resources/map/jquery.hotspot.min.js"></script>
	</head>
	<!-- <body scroll="no"  onmousemove = 'onmousemove_handler(event)'> -->
	<body scroll="no">
		<div id="mixky-loading-mask"></div>
		<div id="mixky-loading">
			<div class="mixky-loading-indicator">正在装载应用...</div>
		</div>
		<iframe src="jsppage.do?url=portal/refresh" style="display:hidden"></iframe>
		<form name="homeForm"  method="POST">
		   <INPUT type="hidden" value="" name="msg_signed">
		</form>
	</body>
	<jsp:include page="../ca/sign/signInfo.jsp"/>
</html>
<script language=javascript>
// 创建应用
var MixkyApp = new Mixky.wasoft.App({userMenus:<%=usermenus%>, userConfig : <%=userconfig.toString()%>,openParams : <%=openParams.toString()%>
});   

var isbegen = true;

function caState() {
   if(isbegen){
    <%if(msg != null && !msg.trim().equals("")){%>
	       Ext.MessageBox.show({title:'提示',msg:'<%=msg%>',modal:true,buttons:Ext.Msg.OK,
	         icon:Ext.Msg.WARNING,width:450,closable:false});
	         isbegen = false;
	<%}%>
	}
<%if (url.startsWith("https://")){%>
	 if(!signchk()){
	    Ext.MessageBox.show({title:'提示',msg:"请检查您的USB KEY是否插好,由于系统无法找到证书,已经自动退出！",
	    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:450,closable:false,fn:function(){
	       window.location = "logout.do";
	    }});
	 }
   <%}%>
}
   var chkCaState = {
       run: caState,
       interval: 60000 //CA插入的USB KEY间隔，毫秒为单位,。
   };
Ext.TaskMgr.start(chkCaState);
</script>