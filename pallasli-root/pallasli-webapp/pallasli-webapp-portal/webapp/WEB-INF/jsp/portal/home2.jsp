<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.google.gson.JsonArray"%>
<%@ page import="com.mixky.portal.application.ApplicationDataService"%>
<%@page contentType="text/html; charset=utf-8"%>
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
	String title=applicationInfo.get("title").getAsString();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><%=title%></title>
		<!-- Ext Core Include -->
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/resources/css/ext-all.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/ext-all.gzjs"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/locale/ext-lang-zh_CN.js"></script>
		<!-- SWFUpload Include -->
		<script type="text/javascript" src="dependences/swfupload/swfupload.js"></script>
		<!-- Ext Plugins Include -->
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/CheckColumn.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/TabCloseMenu.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/DataView-more.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/RowEditor.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/RowEditor.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/portal/portlet.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/portal/portalcolumn.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/portal/portal.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/portal/portal.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/pagerbar/PagingRowNumberer.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/pagerbar/PanelResizer.js"></script>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/pagerbar/ProgressBarPager.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/pagerbar/PanelResizer.css"/>
		<script type="text/javascript" src="dependences/extjs/ext-3.2.1/plugins/datetimefield/datetimefield.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/extjs/ext-3.2.1/plugins/datetimefield/css/datetimefield.css"/>
		<!-- Direct API Include -->
		<script type="text/javascript" src="resources/output/portal/api.js"></script>

<%=ApplicationDataService.instance().outputApplicationInclude()%>

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
		<script type="text/javascript" src="resources/js/plugins/mixky.favorite.column.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.rowordercolumn.js"></script>
		<script type="text/javascript" src="resources/js/plugins/mixky.rownumberercolumn.js"></script>

		<!-- Calender Plugin -->
		<script type="text/javascript" src="resources/js/plugins/calendar/common/RepeatType.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/common/Mask.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/mixky.calendar.ds.js"></script>
		<script type="text/javascript" src="resources/js/plugins/calendar/mixky.calendar.js"></script>
		<link rel="stylesheet" type="text/css" href="resources/js/plugins/calendar/css/calendar_core.css"/>
		<!-- Calender Plugin -->
		
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
		<link rel="stylesheet" type="text/css" href="resources/js/editor/mixky.organization.window.css"/>
		<script type="text/javascript" src="resources/js/editor/mixky.organization.window.field.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.radiogroup.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.textbox.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.filefield.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.emailaddress.js"></script>
		<script type="text/javascript" src="resources/js/editor/mixky.combo.treefield.js"></script>
		<!-- Mixky Lib Include -->
		<script type="text/javascript" src="resources/js/mixky.lib.js"></script>
		<script type="text/javascript" src="resources/js/common/ext-base-ajax.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.lib.notification.js"></script>
		<script type="text/javascript" src="resources/js/common/mixky.lib.messagebox.fix.js"></script>
		<!-- Mixky Core Include -->
		<link rel="stylesheet" type="text/css" href="resources/css/portal.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/portal.webpage.css"/>
		<link rel="stylesheet" type="text/css" href="resources/css/mixky.awsoft.icon.css"/>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.commonapp.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.lib.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.cache.applications.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.cache.menus.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.framework.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.workflow.js"></script>
		<script type="text/javascript" src="resources/output/portal/mixky.wasoft.webpage.desktop.js"></script>
		<script type="text/javascript" src="resources/js/portal/mixky.wasoft.app.js"></script>
		<!-- Portal Dictionary Store -->
		<script type="text/javascript" src="resources/output/portal/mixky.awsoft.cache.dictionarys.js"></script>
		<!-- yzz add  fields-->
		<script type="text/javascript" src="resources/js/editor/atwasoft.lenovo.textfield.js"></script>
		<script type="text/javascript" src="resources/js/editor/atwasoft.numbertriggerfield.js"></script>
		<script type="text/javascript" src="resources/js/editor/atwasoft.numberText.js"></script>
		<!-- yzz add  common method-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.lib.js"></script>
		<script type="text/javascript" src="resources/js/common/atwasoft.common.format.js"></script>
		<!-- yzz overloaded  method-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.prototype.js"></script>
		<!-- lzh add  common method-->
		<script type="text/javascript" src="resources/js/common/atwasoft.common.zjhm.js"></script>
		<script type="text/javascript" src="resources/js/common/atwasoft.common.date.js"></script>
		<!-- yzz ux js  -->
		<script type="text/javascript" src="dependences/ux/LockingGridView.js"></script>
		<script type="text/javascript" src="dependences/ux/gridToExcel.js"></script>
		<link rel="stylesheet" type="text/css" href="dependences/ux/css/LockingGridView.css"/>
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
var tc=0;
var MixkyApp = new Mixky.wasoft.App({
	userMenus:<%=usermenus%>, 
	userConfig : <%=userconfig.toString()%>,
	openParams : <%=openParams.toString()%>
});

/*window.onbeforeunload = function(){
    if(tc==0){
	    if(navigator.userAgent.indexOf("MSIE")>0){
		       var n = window.event.screenX - window.screenLeft;  
		       var b = n > document.documentElement.scrollWidth-20;  
		       if(b && window.event.clientY<0 || window.event.altKey){  
		            url= 'logout.do';
				    Ext.Ajax.request({
				           url: url,    
				           method: "POST",
				           params: {}
			        });
			        alert('您已经强制关闭了系统，可能会造成您在一定时间内无法登陆系统！');  
		       }
		}
		else{
		       url= 'logout.do';
		       Ext.Ajax.request({
		           url: url,    
		           method: "POST",
		           params: {}
		       });
		       alert('您已经强制关闭了系统，可能会造成您在一定时间内无法登陆系统！');   
		}
    }
}*/
<!--     
    window.onbeforeunload = onbeforeunload_handler;   
    window.onunload = onunload_handler;   
    function onbeforeunload_handler(){   
        if(tc==0){
		    if(navigator.userAgent.indexOf("MSIE")>0){
			       var n = window.event.screenX - window.screenLeft;  
			       var b = n > document.documentElement.scrollWidth-20;  
			       if(b && window.event.clientY<0 || window.event.altKey){  
			            url= 'logout.do';
					    Ext.Ajax.request({
					           url: url,    
					           method: "POST",
					           params: {}
				        });
				        alert('您已经强制关闭了系统，可能会造成您在一定时间内无法登陆系统！');  
			       }
			       else{
			           var warning="离开此页面将放弃所有未保存的数据?";           
                       return warning;  
			       }
			}
			else{  
			        var warning="离开此页面将放弃所有未保存的数据,如果是非法退出将使您在1个小时内不能登陆?";   
                    return warning;  
			}
        }
    }   
       
    function onunload_handler(){   
          
    }   
// -->   

</script>