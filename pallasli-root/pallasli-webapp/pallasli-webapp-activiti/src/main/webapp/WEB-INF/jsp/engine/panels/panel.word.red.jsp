<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.mixky.common.database.JsonObjectDao"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.mixky.engine.attachment.AttachmentManager"%>
<%@ page import="com.mixky.engine.attachment.Attachment"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@ page import="com.mixky.system.ContextHolder"%>


<%
	// 读取参数
	//String rootpath=ContextHolder.instance().getWebRoot();
	//System.out.println("ssssssssssssssssssssss"+rootpath);
	String panelid = request.getParameter("panelid");
	String documentid = request.getParameter("documentid");
	String panelKey = request.getParameter("panelkey");
	String moduleKey = panelKey.substring(0, panelKey.indexOf('.'));
	String appkey = request.getParameter("appkey");
	
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
	String redWordAttachmentId = "0";
	String wordAttachmentId = "0";
	List<Attachment> atts = AttachmentManager.instance().getDocumentFiles(Long.parseLong(documentid), panelKey.toUpperCase(), null);
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

Ext.onReady(function(){
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	var panel = Ext.getCmp('<%=panelid%>');
	var sta = panel.document.params.F_STATE;
	var zt = false;
	function saveWordDocumentToServer()
	{
		saveWordDocument("mixkymsreddoc", "");
		Sleep(this, 500);
		this.NextStep=function()
		{
			//alert('save');
			unLoadWordDocument("mixkymsreddoc", true);
			// upload it
			var url = getDocumentUploadURI() + "?si=<%=documentid%>&st=<%=panelKey%>&ft=2&f&fn=myredword.doc&u=<%=user.getF_caption()%>";
			 //alert(url);
			
			var documentpath = mixap_doc_root + "myredword.doc";
			//alert(documentpath);
			uploadDocument("mixkywebaccess", url, documentpath, "");
			//alert(234);
			// load word document again
			loadWordDocument("mixkymsreddoc", documentpath, true, true, true);
		}
	}
	
	function loadWordDocumentFromServer() {
		unLoadWordDocument("mixkymsreddoc", false);
		var url = getDocumentDownloadURI() + "?id=<%=redWordAttachmentId%>";
		var documentpath = mixap_doc_root + "myredword.doc";
		downloadDocument("mixapredwebaccess", url, documentpath, "cookievalue");
		Sleep(this, 500);
		this.NextStep=function()
		{
			loadWordDocument("mixkymsreddoc", documentpath, true, true, true);
			Sleep(this, 500);
			this.NextStep=function()
			{
				lockWordDocument("mixkymsreddoc", "123");
			}
		}

	}

	//下载红头模板，准备套红头
	function downloadWordTemplateDocumentFrmSvr(templateId) {
		unLoadWordDocument("mixkymsreddoc", false);
		var url = getDocumentDownloadURI() + "?id=" + templateId + "&tablename=t_ql_app_senddoc_wordtemplate&fieldname=f_file";
		var documentpath = mixap_doc_root + "myredword.doc";
		downloadDocument("mixapredwebaccess", url, documentpath, "cookievalue");
	}


	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	
	var refreshAction = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-sys-refresh',
		handler : function(){
			panel.refresh();
		}
	});

	var redWordSelectAction = new Ext.form.ComboBox({
					width:260,
					name: 'F_DOC_TYPE',
					hiddenName: 'F_DOC_TYPE_HIDDEN',
					fieldLabel: '请选择发文稿纸',
					editable: false,
					triggerAction: 'all',
					mode: 'remote',
					valueField: 'value',
					displayField: 'display',
					allowBlank: false,
					store: Mixky.wasoft.lib.getDictionaryDoUrlStore(app.key,'app/mkoa/senddoc/word.template.get', {'F_TYPE':'<%=panelKey%>'}),
					//dourl: 'app/mkoa/senddoc/word.template.get',
					params:{}
				});
			
	//var redWordSelectAction=advanceForm.getwordPanel().findField('F_DOC_TYPE');

	redWordSelectAction.on('select',function(t,n,o){
						var value = redWordSelectAction.getValue();
						
		            	var templateId = value.substring(0, value.indexOf('|'));
		            	
		            	var flags = value.substring(value.indexOf('|') + 1, value.length);
		            	
		            	//var form = Ext.getCmp('<%=moduleKey%>.docSendDoc.pSendDoc-<%=documentid%>').getForm();
						var form = panel.document.items.get(0).items.get(0).getForm();
		            	
		            	form.findField('F_RED_FLAGS').setValue(flags);
		            	
		            	form.findField('F_RED_TMPLATE_ID').setValue(templateId);
		            	
		            	downloadWordTemplateDocumentFrmSvr(templateId);
		            	//MixkyApp.desktop.openDocument('qlSendDoc.docSendDoc', 0, {'F_TYPE': win.items.get(0).getRawValue()});
		            	//win.hide();
		            	panel.createRedWord();
	});
	
	
	

	
	var downloadRedWord = new Ext.Action({
		text : '下载文件',
		iconCls : 'icon-sys-download',
		handler : function(){
		
			location.href = "/mkoa/engine/file/sysdownload.do?tablename=T_MK_SYS_FILES&fieldname=F_CONTENT&id=<%=redWordAttachmentId%>&documentdbtype=none";
		}
	});

	
	var noTraceAction = new Ext.Action({
		text : '隐藏留痕',
		iconCls : 'icon-sys-notrace',
		handler : function(){
			panel.hideTrace();
		}
	});
	
	var buttons = [refreshAction, '->'];
	//buttons.push(redWordSelectAction);
	if(sta=='结印核发'){
		buttons.push(downloadRedWord);
		canEdit=false;
	}
	if(sta=='整稿'){
		buttons.push(redWordSelectAction);
		canEdit=false;
	}
	if(sta=='结束'){
		buttons.push(downloadRedWord);
		canEdit=false;
	}
<%
	if (canEdit) {
%>
	
	//buttons.push(redWordAction);
	buttons.push(redWordSelectAction);
<%
	}
%>

	
	
	//buttons.push(noTraceAction);
	var wordPanel = new Ext.Panel({
		autoScroll : true,
		border : false,
		trackResetOnLoad : true,
		bodyStyle : "padding:10px;padding-left:0px;padding-right:23px",
		html:'<input type="hidden" id="templateFlags"><object classid="clsid:00460182-9E5E-11d5-B7C8-B8269041DD57" codebase="resources/ocx/mixky.doc.ocx#version=1.2" id="mixkymsreddoc" width="100%" height="100%"><param name="BorderStyle" value="0"><param name="TitlebarColor" value="52479"><param name="TitlebarTextColor" value="0"><param name="Titlebar" value="0"><param name="Menubar" value="1"></object><object classid="clsid:7F949355-7E91-4877-8646-CA30C3BA98CB" codebase="resources/ocx/mixky.webaccess.ocx#version=1.0" id="mixapredwebaccess" width="16" height="16"></object><object classid="clsid:7F949355-7E91-4877-8646-CA30C3BA98CB" codebase="mixap.webaccess.ocx#version=1.0" id="mixkywebaccess" width="16" height="16"></object>',
		tbar: buttons
	});
	
	panel.hideTrace = function(){
		showWordDocumentRevision('mixkymsreddoc', false);
	}
	panel.createRedWord = function(){
		//var form = panel.document.items.get(0).items.get(0).getForm();
    	//var form = Ext.getCmp('<%=moduleKey%>.docSendDoc.pSendDoc-<%=documentid%>').getForm();
		var form = panel.document.items.get(0).items.get(0).getForm();

		var wdapp=new ActiveXObject("Word.Application");
		//alert('ddddd'+123);

		var wddoc=wdapp.Documents.Open(mixap_doc_root + "myredword.doc");
		//alert(234);
		var wdmarks=wddoc.Bookmarks;
		//alert(24);
		wdmarks("body").Range.InsertFile(mixap_doc_root + "myword.doc");
		//alert(form.findField('F_RED_FLAGS').getValue());
		//alert(25);
		var fieldnametobookmark=form.findField('F_RED_FLAGS').getValue();
		//alert("SSS="+fieldnametobookmark);
		var fbarray=new Array();
		var arrayfield=new Array();
		var arraymark=new Array();
		fbarray=fieldnametobookmark.split(";");
		var fbarraynum=fbarray.length;
		//alert(12333333);
		if (fbarraynum>0)
		{
		  for (var i = 0; i < fbarraynum; i++) 
			 {
			 arrayfield[i] = fbarray[i].substring(0,fbarray[i].lastIndexOf("#"));
			if(!form.findField(arrayfield[i]))
			{
				alert("未找到域："+arrayfield[i]+"，程序退出，请检查模板设置！");
				wdapp.ActiveDocument.Save();
				wdapp.close();
				wdapp.quit();
				return false;
			}
			//alert(arrayfield[i]);
			arraymark[i] = fbarray[i].substring(fbarray[i].lastIndexOf("#")+1,fbarray[i].length);
			//alert(arrayfield[i]);
			if(!wdmarks.Exists(arraymark[i]))
			{
				alert("红头模板中未找到书签："+arraymark[i]+"，程序退出，请检查模板设置！");
				wddoc.ActiveDocument.Save();
				wddoc.close();
				wdapp.quit();
				return false;
			}
			wdmarks(arraymark[i]).Range.Text = form.findField(arrayfield[i]).getValue();	
			}
		}
		wdapp.ActiveDocument.Save();
		wdapp.quit();
		unLoadWordDocument("mixkymsreddoc", false);
		var documentpath = mixap_doc_root + "myredword.doc";
		loadWordDocument("mixkymsreddoc", documentpath, true, true, true);

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
	panel.refresh();
});
</script>