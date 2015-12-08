<%@page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@page import="net.fckeditor.FCKeditor"%>
<%@page import="net.fckeditor.FCKeditorConfig"%>
<%@page import="com.mixky.engine.design.document.Panel"%>
<%@page import="com.mixky.engine.design.document.Document"%>
<%@page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@page import="com.mixky.engine.design.DesignObject"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%
	String documentid = request.getParameter("documentid");
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");

	Map<String, ObjectAuthority> map = (Map<String, ObjectAuthority>)request.getAttribute("authmap");

	Panel panel = (Panel)request.getAttribute("panel");
	
	ObjectAuthority panelauth = map.get(panel.getKey());
	
	String tablename = panel.getF_config().get("tablename").getAsString();
	String fieldname = panel.getF_config().get("fieldname").getAsString();
	
	FCKeditor fckEditor = new FCKeditor(request, "fck_" + panelid);
	fckEditor.setBasePath("dependences/fckeditor");
	fckEditor.setHeight("100%");
	FCKeditorConfig cfg = fckEditor.getConfig();
	cfg.put("ToolbarStartExpanded", "true");
	cfg.put("StartupFocus ", "true");
	
	boolean editable = panelauth.hasAuth(ObjectAuthority.A_EDIT);
	
	String id = "fck_" + panelid;
%>

<%//=fckEditor%>
<div>
	<input id="<%=id%>" name="fck_p-mkoa.mkNotify.docNotify.pContent-11" value="" type="hidden" /><input id="<%=id%>___Config" value="StartupFocus =true&amp;ToolbarStartExpanded=true" type="hidden" />
	<iframe id="<%=id%>___Frame" frameborder="0" height="100%" scrolling="no" width="100%" src="/dependences/fckeditor/editor/fckeditor.html?InstanceName=<%=id%>&amp;Toolbar=Default">
	</iframe>
</div>

<script lang='javascript'>
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
		var panel = Ext.getCmp('<%=panelid%>');	
		panel.refresh();
	}
<%
	if(editable){
%>

	function getEditorIframeBody(){
		var myiframe = document.getElementById("fck_<%=panelid%>___Frame").document;
		if(!myiframe){
			myiframe = document.getElementById("fck_<%=panelid%>___Frame").contentWindow.document;
		}
		var oCell = myiframe.getElementById( 'xEditingArea' ).firstChild;
		if(!oCell.document){
			return oCell.contentWindow.document.body;
		}else{
			return oCell.document.body;
		}
	}
	
	function getEditorHTMLContents() {
		var body = getEditorIframeBody();
		return body.innerHTML;
	}
	
	function SetEditorContents(ContentStr) {
		var body = getEditorIframeBody();
		body.innerHTML = ContentStr;
	}
<%
	}
%>
	Ext.onReady(function(){
		var editable = <%=editable%>;
		var panel = Ext.getCmp('<%=panelid%>');
		
		panel.submit = function(fn){
			var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
			if(editable){
				var directFn = eval(app.keyPrefix + 'AppDirect.setClobContext');
				directFn("<%=tablename%>", "<%=fieldname%>", <%=documentid%>, getEditorHTMLContents(), function(result, e){
					if(result && result.success){
						panel.document.submitPanelOver(panel, fn);
					}else{
						MixkyApp.showDirectActionFail("保存文本内容", result, e);
					}
				});
			}else{
				panel.document.submitPanelOver(panel, fn);
			}
		}

		panel.refresh = function(){
			// 此处获得内容
			var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
			var directFn = eval(app.keyPrefix + 'AppDirect.getClobContext');
			directFn("<%=tablename%>", "<%=fieldname%>", <%=documentid%>, function(result, e){
				if(result && result.success){
					if(editable){
						SetEditorContents(result.context);
					}else{
						panel.body.dom.innerHTML = result.context;
					}
				}else{
					MixkyApp.showDirectActionFail("获得文本内容", result, e);
				}
			});
		}
	});
</script>