<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.excelreader.*,
	com.pallasli.utils.*"
	pageEncoding="gb2312"%>

<%
//****************************PageOffice组件的使用***************************************
FileSaver fs = new FileSaver(request, response);
String fileName=new Date().getTime()+"user- Id";
String path=request.getSession().getServletContext().getRealPath("upload/"+fileName+"."+fs.getFileExtName());
fs.saveToFile(path);	 

fs.setCustomSaveResult(path);
fs.close();//关闭FileServer对象

//****************************PageOffice组件的使用***************************************
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
	</head>
	<body>
		<form id="form1">
			<div>

			</div>
		</form>
	</body>
</html>
