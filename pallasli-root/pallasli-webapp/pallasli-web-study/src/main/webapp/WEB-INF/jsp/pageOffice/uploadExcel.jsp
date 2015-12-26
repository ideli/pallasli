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
	poCtrl1.webOpen("test.xls", OpenModeType.xlsNormalEdit, "张三");
	poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
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
	Pallas.design.api.REMOTING_API.enableBuffer = 0;  
	Ext.Direct.addProvider(Pallas.design.api.REMOTING_API);  
	
		Ext.onReady(function(){

			var panel = Ext.create('Ext.panel.Panel', {
        	});
			var store = new Ext.data.SimpleStore({	
				data : <%=stores%>,		
				fields : ["xmmc","xmdm"]
			});
			var columns = [
							{
								"id" : "key",
								"header":"关键字",
								"width":50,
								"align":"center",
								xtype:'templatecolumn',
								tpl:'<input name=action style="margin:0px; " type=checkbox id={id} />'
							}, {
								"id" : "xz",
								"header":"选择",
								"width":35,
								xtype:'templatecolumn',
								tpl:'<input name=action style="margin:0px; "  type=checkbox id={id} />',
								"align":"center"
							}, {
								"id":"xmmc",
								"dataIndex":"xmmc",
								"header":"项目名称",
								"sortable":false,
								"width":130,
								"renderer":function(value,cell,record){
										cell.attr='style="white-space:normal"';
										return value;
							}},{"id":"bgxx","dataIndex":"bghxx","header":"对应列数",
								"sortable":false,width:70,editor: {
					                xtype: 'numberfield',
					                allowBlank: false
					            }}];
			var grid = new Ext.grid.Panel({
				border : false,
				stripeRows: true,
				lineBreak : false,
				cellSelect : true,
		        loadMask: {msg:'正在装载...'},
				columns : columns,
				plugins: [
				           Ext.create('Ext.grid.plugin.CellEditing', {
				               clicksToEdit: 1
				           })
				       ],
				store : store
			});

			new Ext.Panel({
				border:false,
				layout:'fit',
				renderTo:"p-grid-select-daoru",
                items:[grid]
				
			});
			var btn_loadexcel = new Ext.Button({
				text:"导入Excel文件",
				handler:function(){
					document.getElementById("PageOfficeCtrl1").ExcelImportDialog(); 
				}
			});

			var btn_drexcel = new Ext.Button({
				text:"导入",
				handler:function(){
					document.getElementById("PageOfficeCtrl1").WebSave(); 
					var path = document.getElementById("PageOfficeCtrl1").CustomSaveResult;
					if(path==''){
						alert("上传失败");
					}else{
						MyAction.doEcho(path,function(result, event){  
		                    var transaction = event.getTransaction(),  
	                        content = Ext.String.format('<b>Successful call to {0}.{1} with response:</b><pre>{2}</pre>',  
	                            transaction.action, transaction.method, Ext.encode(result));  
	                       console.log(content);
	                	});  
						//var directsave=eval("< %=submitDirect%>");
						//directsave({excelpath:path,key:key,orders:"xmdm1:order1,"},function(result){
							
						//});
					}
				}
			});
			new Ext.Panel({
				border:false,
				renderTo:"btn-panel",
                buttonAlign:'center', 
                buttons:[btn_loadexcel,btn_drexcel]
				
			});
		});
	</script>
	<body>
		<div style="width: auto; height: 93%;">
			<div style="width: 75%; height: 100%; float:left;">
				<po:PageOfficeCtrl id="PageOfficeCtrl1"></po:PageOfficeCtrl>
			</div>
			<div id="p-grid-select-daoru"  style="width: 25%; height: 100%; float:left; ">
			</div>
		</div>
		<div id="btn-panel" style="width: 100%; height:7%;">
			
		</div>
	</body>
</html>
