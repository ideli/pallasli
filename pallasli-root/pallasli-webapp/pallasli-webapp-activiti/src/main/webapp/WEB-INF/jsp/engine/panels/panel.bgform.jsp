<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.mixky.common.database.JsonObjectDao"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.design.DesignObject"%>
<%@ page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.document.Document"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.design.store.StoreManager"%>
<%@ page import="com.mixky.engine.design.store.Table"%>
<%@ page import="com.mixky.engine.design.store.Field"%>
<%@ page import="com.mixky.engine.design.store.TableForm"%>
<%@ page import="com.mixky.engine.design.store.TableFormDataService"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	String documentid = request.getParameter("documentid");
	Panel panel = (Panel)request.getAttribute("panel");
	User user = (User)request.getAttribute("user");
	Document document = (Document)request.getAttribute("document");
	JsonObjectDao data = (JsonObjectDao)request.getAttribute("data");
	Map<String, ObjectAuthority> map = (Map<String, ObjectAuthority>)request.getAttribute("authmap");
	// 获得按钮权限
	List<ObjectAuthority> buttonauths = DocumentManager.instance().getFilterObjectAuthority(map, panel.getF_buttons(), user);
	// 获得字段权限
	TableForm tableform = DesignObjectLoader.instance().loadDesignObject(panel.getF_i_tableform().get("data").getAsString());
	List<ObjectAuthority> fieldauths = null;
	if(map == null){
		fieldauths = TableFormDataService.instance().getFormFieldAuths(tableform);
	}else{
		List<Field> fields = TableFormDataService.instance().getFormFields(tableform);
		fieldauths = DocumentManager.instance().getFilterObjectAuthority(map, fields, user);
	}
	DesignObject table = tableform.getParent();
	DesignObject module = table.getParent();
	String bgFilePath = ""; //JsonFileSerializer.instance().getFileRootRelate() + module.getF_key() + "/" + tableform.getKey() + ".jpg";
	int width = 794;
	int height = 1123;
	if(tableform.getF_width()>0){
		width = tableform.getF_width();
	}
	if(tableform.getF_height()>0){
		height = tableform.getF_height();
	}
	
%>
<script language='javascript'>
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	
	var refreshAction = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-sys-refresh',
		handler : function(){
			panel.refresh();
		}
	});
<%
	for(int i=0;i<fieldauths.size();i++){
		ObjectAuthority auth = fieldauths.get(i);
		Field field = (Field)auth.getObject();
%>
	var <%=field.getF_name()%> = <%=StoreManager.instance().getBgFormFieldEditor(auth) %>;
	<%=field.getF_name()%>.document = panel;
<%
	}
%>
	var items = <%=TableFormDataService.instance().getBgFormItems(tableform)%>;

	var buttons = [refreshAction, '->'];
<%
	for(int i=0;i<buttonauths.size();i++){
		ObjectAuthority auth = buttonauths.get(i);
		if(auth.hasAuth(ObjectAuthority.A_EDIT) || auth.hasAuth(ObjectAuthority.A_READ)){
			Action button = (Action)auth.getObject();;
%>
	var <%=button.getF_name()%> = new Ext.Action({
		text : '<%=button.getF_caption()%>',
		iconCls : '<%=button.getIcon()%>',
		handler : (<%=button.getRunHandler()%>)
	});
	buttons.push(<%=button.getF_name()%>);
<%
		}
	}
%>

	var form = new Ext.form.FormPanel({
		renderTo : app.key + '-<%=tableform.getKey()%>-<%=documentid%>',
		autoScroll : true,
		border : false,
		layout : 'absolute',
		width : <%=width%>,
		height : <%=height%>,
		bodyStyle : 'background:transparent;',
		fileUpload : true,
		trackResetOnLoad : true,
		paramOrder : ['panelkey', 'documentid', 'params'],
		baseParams : {
			panelkey : '<%=panel.getKey()%>',
			documentid : <%=documentid%>,
			params : panel.document.params
		},
		api : {
			load : eval(app.keyPrefix + 'AppDirect.loadFormData'),
			submit : eval(app.keyPrefix + 'AppDirect.submitFormData')
		},
		items:items
	});

	panel.submit = function(fn){
		if(!form.getForm().isValid()){
			MixkyApp.showErrorMessage("表单数据填写非法，保存失败");
			return;
		}
		if(form.getForm().isDirty()){
			form.getForm().submit({
				success : function(f,a){
					form.getForm().load();
					panel.document.submitPanelOver(panel, fn);
				},
				failure : function(f, a){
					MixkyApp.showFormActionFail(f, a);
				}
			});
		}else{
			panel.document.submitPanelOver(panel, fn);
		}
	}

	// 获得需要提交的数据
	panel.getSubmitData = function(){
		if (!form.getForm().isValid()){
			MixkyApp.showErrorMessage("表单数据填写非法");
			return false;
		}else{
			return form.getForm().getFieldValues();
		}
	}

	panel.refresh = function(){
		form.getForm().load();
	}

	// 输出附加脚本 begin
<%
	if(panel.getF_custom_script() != null){
		out.print(panel.getF_custom_script());
	}
%>

	var tb = new Ext.Toolbar({
		renderTo : app.key + '-<%=tableform.getKey()%>-<%=documentid%>-toolbar',
		items:buttons
	})

	// 输出附加脚本 end
	//panel.add(new Ext.Toolbar({items:buttons}));
	tb.render();
	form.render();
	panel.doLayout();
	// 初始化视图数据
	panel.refresh();

	win.maximize();
	win.doLayout();
});
</script>
<div id='<%=appkey%>-<%=tableform.getKey()%>-<%=documentid%>-toolbar'></div>
<DIV id='form-pos-editor-table' style="padding:15px;background-color:darkgray;">
<TABLE cellSpacing=0 cellPadding=0 align=center bgColor=white>
	<TR style="HEIGHT: 2px">
		<TD vAlign=top background='resources/images/report/t_l.gif'></TD>
		<TD vAlign=top background='resources/images/report/t_z.gif'></TD>
		<TD vAlign=top background='resources/images/report/t_y.gif'></TD>
	<TR>
		<TD vAlign=top style='width:2px' background='resources/images/report/b_l.gif'></TD>
		<TD>
			<div id='<%=appkey%>-<%=tableform.getKey()%>-<%=documentid%>' style='position:relative;width:<%=width%>px;height:<%=height%>px;background-repeat:no-repeat;background-image:url("<%=bgFilePath%>");'></div>
		</TD>
		<TD vAlign=top style='width:3px' background='resources/images/report/b_y.gif'></TD>
	</TR>
	<TR style="HEIGHT: 7px">
		<TD vAlign=top background='resources/images/report/d_l.gif'></TD>
		<TD vAlign=top background='resources/images/report/d_z.gif'></TD>
		<TD vAlign=top background='resources/images/report/d_y.gif'></TD>
	</TR>
</TABLE>
</DIV>