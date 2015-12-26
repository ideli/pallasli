<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	

	String stores = request.getParameter("stores");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>上传列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<script language='javascript'>
	Ext.Loader.setConfig({enabled: true});

	Ext.Loader.setPath('Ext.ux.DataView', 'dependences/ext/ux/DataView');
	Ext.Loader.setPath('MySamples', 'scripts/samples');
	
	function openWindow(id,title,params){
		var desktop = myDesktopApp.getDesktop();
        var win = desktop.getWindow(id);
        if(!params){params={};}
        params.autoLoad=true;
        params.scripts=true;
        if(!win){
            win = desktop.createWindow({
            	id:id,
                title:title,
                width:1200,
                height:540,
                iconCls: 'icon-upload',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {xtype: 'panel',
            	        border: false,
            	        loader: params
                    }
                ]
            });
        }
        win.show();
	}
		Ext.onReady(function(){

			Ext.create('Ext.panel.Panel', {
				renderTo:'uploadListPanel',
				buttonAlign:'left',
				buttons: [
				        {text:"动态列上传",handler:function(){
				        	openWindow('uploadExcelPanel-win',"动态列上传",{
	            	            url: 'jsppage.do?url=pageOffice/uploadExcel',
	            	            params:{stores:"[['单位编号','dwbh'],['单位名称','dwmc']]"}
	            	        });
				        }},
				        {text:"word上传",handler:function(){
				        	openWindow('uploadWordPanel-win',"word上传",{
	            	            url: 'jsppage.do?url=pageOffice/uploadWord',
	            	            params:{stores:"[['单位编号','dwbh'],['单位名称','dwmc']]"}
	            	        });
				        }}
	                ]
			});
			
		});
	</script>
	
	<body>
		<div id="uploadListPanel" style="width: 100%; height:100%;">
			
		</div>
	</body>
</html>
