<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.excelreader.*"
	pageEncoding="gb2312"%>

<%
//****************************PageOffice组件的使用***************************************

Workbook wb = new Workbook(request, response);
List<Sheet> sheetList = wb.getSheets();
for(Sheet sheet:sheetList){
	Table tb= sheet.openTableBySubmitName("Info"); 
	while(!tb.getEOF()){
		if(tb.getDataFields()!=null&&tb.getDataFields().size()>3){
			System.out.println(tb.getDataFields().get(0).getText());
		} 
		tb.nextRow();
	}
	tb.close();
}
wb.setCustomSaveResult("param1=xxx;param2=yyy");
wb.close();

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
