<%@page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
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
System.out.println("*********************************************输出");
	Panel panel = (Panel)request.getAttribute("panel");
	System.out.println("*********************************************输出"+panel.getKey());
	ObjectAuthority panelauth = map.get(panel.getKey());
	System.out.println("*********************************************输出"+panel.getKey());
	String tablename = panel.getF_config().get("tablename").getAsString();
	String fieldname = panel.getF_config().get("fieldname").getAsString();
	
	boolean editable = panelauth.hasAuth(ObjectAuthority.A_EDIT);
%>


<script lang='javascript'>
	Ext.onReady(function(){
		var editable = <%=editable%>;
		var panel = Ext.getCmp('<%=panelid%>');		

		/*var fckPanel = new Ext.form.FormPanel({
			autoScroll : true,
			layout:'form',
			border : false,
			items : [{
				xtype:'<%=editable ? "htmleditor" : "displayfield"%>',
				anchor:"99% 99%",
				name:"F_CONTENT",
				hideLabel:true
			}]
		});	*/
		
		var fckPanel = new Ext.form.FormPanel({
			autoScroll : true,
			layout:'form',
			border : false,
			items : [{
				xtype:'<%=editable ? "tinymce" : "displayfield"%>',
				anchor:"100% 99%",
				name:"F_CONTENT",
				hideLabel:true,
				hideMode: "offsets",
				tinymceSettings: {
					theme : "advanced",
					plugins: "pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
					theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
					theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
					theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|",
					theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
					theme_advanced_toolbar_location : "top",
					theme_advanced_toolbar_align : "left",
					theme_advanced_statusbar_location : "",
					theme_advanced_resizing : true,
					language:"zh-cn"
			    }
			}]
		});	

		
		panel.submit = function(fn){
			var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
			if(editable){
				var directFn = eval(app.keyPrefix + 'AppDirect.setClobContext');
				directFn("<%=tablename%>", "<%=fieldname%>", <%=documentid%>, fckPanel.getForm().findField("F_CONTENT").getValue(), function(result, e){
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
						var content = fckPanel.getForm().findField("F_CONTENT").setValue(result.context);
					}else{
						panel.body.dom.innerHTML = result.context;
					}
				}else{
					MixkyApp.showDirectActionFail("获得文本内容", result, e);
				}
			});
		}

		// 输出附加脚本 end
		panel.add(fckPanel);
		panel.doLayout();
		//form.doLayout();
		// 初始化试图数据
		panel.refresh();
		
	});
</script>