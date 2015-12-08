<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.toolkit.gson.JsonFunction"%>
<%@ page import="com.mixky.portal.desktop.DesktopDataService"%>
<%@ page import="com.wasoft.processall.*"%>

<%
	String key = request.getParameter("key");
	String appkey = request.getParameter("appkey");
	String panelid = "portlet-" + key;
	User user = (User)request.getAttribute("user");
	PROCESSTYPEALLLIST pro= ProcessAllClient.instance().processAllList(user.getGrbh());
	int yws = 0;
%>
<style type="text/css">
<!--
.STYLE1 {
	font-family: "宋体";
	font-size: 12px;
	color: #222;
}
-->
</style>
<script language=javascript>
function openWorkflow(processUNID,processInstanceId,formKey,appKey){
	Mixky.wasoft.workflow.Workflow.openWorkflow(processUNID,processInstanceId,appKey,formKey);
}
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	
	var html = [
	   <%
      List<PROCESSTYPEALLITEM> typelist = pro.getWFITEM();
      for(int i = 0;i < typelist.size();i++){
         PROCESSTYPEALLITEM protype = typelist.get(i);
         List<PROCESSITEM> proclist = protype.getWFITEM();
         if(proclist.size() > 0){
		      String folderid = protype.getWFFOLDERID();
              String appicon =  DesktopDataService.instance().getBpmAppIcon(folderid);
              if(appicon == null || appicon.trim().equals("")){
                 appicon = "/portal/resources/images/bpm/process/picon/category.png";
              }
              else{
                 appicon = "/portal/resources/"+appicon;
              }
		  %>
		'<table width=100% border=0 cellpadding=0 cellspacing=0 style="border:0px solid #98D0E0;background:#B0C4DE url(/portal/resources/images/bpm/process/processtype/BlueBackground.jpg) repeat-x top;"  >',
		'<tr>',
		'<td width=30 >',
		'<img src="<%=appicon%>" title="<%=protype.getWFFOLDERNAME()%>" ></td>',
			'<td style="padding-left:10px;" >',
			<%
			      for(int j = 0;j < proclist.size();j++){
    			   PROCESSITEM proc = proclist.get(j);
    			   String picon = "/portal/resources/images/bpm/process/picon/category.png";
    			   if(!proc.getWFICONS().trim().equals("")){
    			      if(proc.getWFICONS().trim().indexOf(".")!=-1){
    			          picon = proc.getWFICONS();
    			      }
    			   }
    			   yws++;
			   %>
				'<table align=left width=60>',
				'<tr>',
			'<td align=left nowrap>',
		'<a href="" onclick="',
		"openWorkflow('<%=proc.getWFPROCESSUNID()%>','','<%=proc.getWFFORMKEY()%>','<%=proc.getWFAPPKEY()%>');return false;",
		'"><span class="STYLE1"><%=proc.getWFSUBJECT()%></span></a>',
		'</td>',
			'</tr>',
			' </table>',
			<%}%> 
		'</td>',
		'</tr>',
			'</table>',
		<%}} %>
	];
	
	var btnywgl = new Ext.Action({
		text : '进入业务管理',
		isDefault : true,

		handler : function(){
			MixkyApp.desktop.closeModule('OA', 'waWorkBench');
			var module = MixkyApp.desktop.openModule('OA', 'waWorkBench');
			if(module){
			   var view = module.openView('waWorkBench.qryStartFlow.vStartFlow');
			}
		}
	});
	
	var sjxs = new Ext.form.DisplayField({
       	value:'共'+<%=yws%>+'种业务'
	});
	var tools = [sjxs,'->', btnywgl];

	// 表格对象
	var form = new Ext.Panel({
		autoScroll : true,
		border : false,
       // applyTo : 'abc',
		tbar : tools,
		html: html.join('')
	});
	
	
	// 输出附加脚本 end
	panel.add(form);
	panel.doLayout();
});
</script>