<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.mixky.common.database.JsonObjectDao"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mixky.engine.attachment.AttachmentManager"%>
<%@page import="com.mixky.engine.attachment.Attachment"%>
<%@page import="com.mixky.engine.organization.dao.User"%>
<%@page import="com.mixky.engine.design.document.Panel"%>
<%@page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@ page import="com.mixky.system.ContextHolder"%> 

<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String documentid = request.getParameter("documentid");
	String panelKey = request.getParameter("panelkey");
	Panel panel = (Panel)request.getAttribute("panel");
	User user = (User)request.getAttribute("user");
	// 获得标签权限
	Map<String, ObjectAuthority> map = (Map<String, ObjectAuthority>)request.getAttribute("authmap");
	List<Panel> panels = new ArrayList<Panel>();
	panels.add(panel);
	List<ObjectAuthority> panelauths = DocumentManager.instance().getFilterObjectAuthority(map, panels, user);
	boolean canEdit = false;
	for (int i = 0; i < panelauths.size(); i++) {
		ObjectAuthority auth = panelauths.get(i);
		if (auth.hasAuth(ObjectAuthority.A_EDIT)) {
			canEdit = true;
		}
	}
	
	List<Attachment> atts = AttachmentManager.instance().getDocumentFiles(Long.parseLong(documentid), panelKey.toUpperCase(), null);
	String wordAttachmentId = "0";
	String redWordAttachmentId = "0";
	for (int i = 0; i < atts.size(); i++) {
		Attachment att = atts.get(i);
		if (att.getF_attach_type() == 1) {
			wordAttachmentId = String.valueOf(att.getId());
			//break;
		} else if (att.getF_attach_type() == 2) {
			redWordAttachmentId = String.valueOf(att.getId());
		}
	}
%>

<script language='javascript'>

initUserInfo("<%=user.getF_caption()%>", "<%=user.getF_dept_name()%>");

function newWordDocumentFromServer()
{
	unLoadWordDocument("mixkymsdoc", false);
	var url = getDocumentDownloadURI() + "?id=1";
	//alert(url);
	var documentpath = mixap_doc_root + "myword.doc";
	downloadDocument("mixkywebaccess", url, documentpath, "cookievalue");
	Sleep(this, 500);
	this.NextStep=function()
	{
		loadWordDocument("mixkymsdoc", documentpath, true, true, true);
	}
}

function saveWordDocumentToServer()
{//alert(2);
	saveWordDocument("mixkymsdoc", "");
	Sleep(this, 500);
	this.NextStep=function()
	{
		var documentpath = mixap_doc_root + "myword.doc";
		//unLoadWordDocument("mixkymsdoc", true);
		// upload it
		var url = getDocumentUploadURI() + "?si=<%=documentid%>&st=<%=panelKey%>&ft=1&f&fn=myword.doc&u=<%=user.getF_caption()%>";
		//var documentpath = mixap_doc_root + "myword.doc";
		uploadDocument("mixkywebaccess", url, documentpath, "");
		// load word document again
		//loadWordDocument("mixkymsdoc", documentpath, true, true, true);
	}
}

function loadWordDocumentFromServer() {
	//alert(123);
	unLoadWordDocument("mixkymsdoc", false);
	var url = getDocumentDownloadURI() + "?id=<%=wordAttachmentId%>";
	//alert('url'+url);
	var documentpath = mixap_doc_root + "myword.doc";
	//alert('documentpath'+documentpath);
	downloadDocument("mixkywebaccess", url, documentpath, "cookievalue");
	Sleep(this, 500);
	this.NextStep=function()
	{
		loadWordDocument("mixkymsdoc", documentpath, true, true, true);
	}
}

Ext.onReady(function(){
	Ext.useShims = true;
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var sta = panel.document.get(0).get(0).getForm().findField('F_STATE').getValue();

	var refreshAction = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-sys-refresh',
		handler : function(){
			panel.refresh();
		}
	});

	var newWordAction = new Ext.Action({
		text : '新建正文',
		iconCls : 'icon-sys-new',
		handler : function(){
			panel.createNewWord();
		}
	});

	var saveWordAction = new Ext.Action({
		text : '保存正文',
		//iconCls : 'icon-sys',
		handler : function(){
		saveWordDocumentToServer();
		}
	});

	var traceAction = new Ext.Action({
		text : '显示留痕',
		iconCls : 'icon-sys-trace',
		handler : function(){
			panel.showTrace();
		}
	});

	var noTraceAction = new Ext.Action({
		text : '隐藏留痕',
		iconCls : 'icon-sys-notrace',
		handler : function(){
			panel.hideTrace();
		}
	});
var downloadRedWord = new Ext.Action({
		text : '下载文件',
		iconCls : 'icon-sys-download',
		handler : function(){
		
			location.href = "/OA/engine/file/sysdownload.do?tablename=T_MK_SYS_FILES&fieldname=F_CONTENT&id=<%=redWordAttachmentId%>&documentdbtype=none";
		}
	});
	var buttons = [refreshAction, '->'];
	
	if(sta=='结束'){
		buttons.push(downloadRedWord);
		canEdit=false;
	}
<%
	if (canEdit) {
%>
	//buttons.push(newWordAction);
	buttons.push(saveWordAction);
<%
	}
%>
	buttons.push(traceAction);
	buttons.push(noTraceAction);
	buttons.push(saveWordAction);
	var wordPanel = new Ext.Panel({
		autoScroll : true,
		border : false,
		//tbar : buttons,
		trackResetOnLoad : true,
		bodyStyle : "padding:10px;padding-left:0px;padding-right:23px",
		html:'<object classid="clsid:00460182-9E5E-11d5-B7C8-B8269041DD57" codebase="resources/ocx/mixky.doc.ocx#version=1.2" id="mixkymsdoc" width="100%" height="100%"><param name="BorderStyle" value="0"><param name="TitlebarColor" value="52479"><param name="TitlebarTextColor" value="0"><param name="Titlebar" value="0"><param name="Menubar" value="1"></object><object classid="clsid:7F949355-7E91-4877-8646-CA30C3BA98CB" codebase="resources/ocx/mixky.webaccess.ocx#version=1.0" id="mixapwebaccess" width="16" height="16"></object><object classid="clsid:7F949355-7E91-4877-8646-CA30C3BA98CB" codebase="mixap.webaccess.ocx#version=1.0" id="mixkywebaccess" width="16" height="16"></object>',
		tbar: buttons
	});

	panel.createNewWord = function(){
		newWordDocumentFromServer();
	}
	
	panel.showTrace = function(){
		showWordDocumentRevision('mixkymsdoc', true);
	}
	
	panel.hideTrace = function(){
		showWordDocumentRevision('mixkymsdoc', false);
	}
	
	panel.submit = function(fn){
		saveWordDocumentToServer();
		panel.document.submitPanelOver(panel, fn);
	}

	panel.refresh = function(){
		loadWordDocumentFromServer();
	}

	// 输出附加脚本 end
	panel.add(wordPanel);
	panel.doLayout();
	// 初始化视图数据
	panel.refresh.defer(3000);
});
</script>