<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="utf-8"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	
	//String type=request.getParameter("type");
	//if(type.equals("add")){
		String path=request.getParameter("path");

		String panelId = request.getParameter("panelId");


		//设置PageOfficeCtrl控件的服务页面
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
		poCtrl1.setTitlebar(false);
		poCtrl1.setMenubar(false);
		//poCtrl1.setCustomToolbar(false);
	//	poCtrl1.setOfficeToolbars(false);
		//poCtrl1.addCustomToolButton("保存", "SaveDocument()", 1);
		// poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
		// poCtrl1.addCustomToolButton("-", "", 0);
		 poCtrl1.addCustomToolButton("全屏切换", "SwitchFullScreen()", 4);
		
		
		String filePath = request.getSession().getServletContext().getRealPath("/")+path;
		poCtrl1.setSaveFilePage("jsppage.do?url=worddoc/savefile&path="+filePath); 
		System.out.println(filePath);
		System.out.println(filePath);
		poCtrl1.webOpen(filePath, OpenModeType.docNormalEdit, "");
		poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
		filePath=filePath.replaceAll("\\\\", "/");
		
	//}
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
 <script language="javascript" type="text/javascript">
     function SaveDocument() {

    	 //document.getElementById("PageOfficeCtrl1").SaveFilePage="jsppage.do?url=worddoc/savefile&path=<-%=filePath%>";
    	 //alert(document.getElementById("PageOfficeCtrl1").SaveFilePage);
    	 document.getElementById("PageOfficeCtrl1").WebSave();
     }
     function ShowPrintDlg() {
         document.getElementById("PageOfficeCtrl1").ShowDialog(4); //打印对话框
     }
     function SwitchFullScreen() {
         document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;
     }
 </script> 
	
				<po:PageOfficeCtrl id="PageOfficeCtrl1"  ></po:PageOfficeCtrl>
</html>
