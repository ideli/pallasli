<%@page import="com.pallas.designer.design.bean.SimpleFormAuthPage"%>
<%@page import="com.pallas.designer.design.bean.AuthForm"%>
<%@page import="com.pallas.designer.design.bean.SimpleGridAuthPage"%>
<%@page import="com.pallas.designer.design.bean.AuthGrid"%>
<%@page import="com.pallas.designer.design.bean.AuthComp"%>
<%@page import="java.util.List"%>
<%@page import="com.pallas.designer.design.action.AuthPageAction"%>
<%@page import="com.pallas.designer.design.bean.AuthPage"%>
<%@page import="com.pallas.sys.util.JsonFunction"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%String basePath=request.getContextPath();
String nid=request.getAttribute("nid").toString();


//根据menuKey，user 获取权限组件
//---过程--
//1.根据menuKey,user过滤权限,pageComplist
//2.根据 pageComplist进行布局
//3.输出到页面
//
//AuthPage authPage = AuthPageAction.instance().loadAuthPage(menu,user);
//
AuthPage authPage = AuthPageAction.instance().loadAuthPage("p_addMenu",2);

List<AuthComp> authCompList=authPage.getAuthCompList(); 
AuthComp comp=((SimpleFormAuthPage)authPage).getAuthForm();
String fields=((AuthForm)comp).getFields().toString();
String buttons=((AuthForm)comp).getButtons().toString();
buttons=buttons.replaceAll("\\\\t", "");
buttons=buttons.replaceAll("\\\\r\\\\n", "");

String title=authPage.getTitle();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript">

        Ext.onReady(function(){
			var fields=<%=fields%>;  
			var panelId="<%=nid%>"; 
			var title="<%=title%>";
			var buttons=<%=buttons%>;
			var parentPanel=Ext.getCmp(panelId);
			
			parentPanel.setTitle(title); 
			var form_addType=Ext.create("Ext.form.Panel",{
				border: false,
				fieldDefaults: {
					labelWidth: 100
				},
				defaultType: 'textfield',
				bodyPadding: 5,
				floatable: false,
				items: fields,
				bbar: buttons 
				                      
			});    
			
			form_addType.getForm().setValues(parentPanel.initValues);
			parentPanel.add(form_addType);
        	parentPanel.doLayout();
        });
    </script>
</head>
</html>
