<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	//设置PageOfficeCtrl控件的服务页面
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
	poCtrl1.setCaption("简单的给Excel赋值");
	poCtrl1.setSaveFilePage("jsppage.do?url=pageOffice/savefile");
	//poCtrl1.setSaveDataPage("jsppage.do?url=pageOffice/savedata");
	//定义Workbook对象
	Workbook workBook = new Workbook();
	Sheet sheet=workBook.openSheet("Sheet1");
	workBook.setDisableSheetSelection(true);
	workBook.setDisableSheetRightClick(true);
	sheet.setReadOnly(true);
	//Table table=sheet.openTable("A1:Y500");
	//table.setReadOnly(false);
	//table.setSubmitName("Info");
	poCtrl1.setWriter(workBook);
	
	poCtrl1.setOfficeToolbars(false);
	//隐藏菜单栏
	poCtrl1.setMenubar(false);
	//隐藏工具栏
	poCtrl1.setCustomToolbar(false);
	//打开Word文件
//	poCtrl1.webOpen("test.xls", OpenModeType.xlsNormalEdit, "张三");
	poCtrl1.setTagId("PageOfficeCtrl2"); //此行必须
	// 读取参数
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	

	String stores = request.getParameter("stores");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

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
		Ext.onReady(function(){

			var panel = Ext.create('Ext.panel.Panel', {
        	});
			var btn_loadexcel = new Ext.Button({
				text:"导入Excel文件",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").ExcelImportDialog(); 
				}
			});
			var btn_loadword = new Ext.Button({
				text:"导入word文件",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").WordImportDialog(); 
				}
			});
			var btn_loadppt = new Ext.Button({
				text:"导入microsoft文件",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").ShowDialog(1); 
				}
			});
			var btn_drexcel = new Ext.Button({
				text:"导入",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").WebSave(); 
					var path = document.getElementById("PageOfficeCtrl2").CustomSaveResult;
					if(path==''){
						alert("上传失败");
					}else{
						MyAction.doEcho(path,function(result){
							console.log(result);
						});
						//var directsave=eval("< %=submitDirect%>");
						//directsave({excelpath:path,key:key,orders:"xmdm1:order1,"},function(result){
							
						//});
					}
				}
			});
			new Ext.Panel({
				border:false,
				renderTo:"btn-panel2",
                buttonAlign:'center', 
                buttons:[btn_loadexcel,btn_loadword,btn_loadppt,btn_drexcel]
				
			});
		});
	</script>
	<body>
		<div style="width: auto; height: 93%;z-index: 9999">
			<div style="width: 100%; height: 100%; float:left; z-index:9999">
				<po:PageOfficeCtrl id="PageOfficeCtrl2"  ></po:PageOfficeCtrl>
			</div>
		</div>
		<div id="btn-panel2" style="width: 100%; height:7%; z-index:9999">
			
		</div>
	</body>
</html>
