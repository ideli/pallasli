<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%


    JasperFillManager.fillReportToFile(this.getServletContext().getRealPath(
			"/reports/Flower_Landscape.jasper"), null, new JREmptyDataSource());
String fname= JasperExportManager.exportReportToPdfFile(this.getServletContext().getRealPath(
			"/reports/Flower_Landscape.jrprint"));

File sourceFile = new File(this.getServletContext().getRealPath(
		"/reports/Flower_Landscape.jrprint"));

JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);



	File reportFile = new File(this.getServletContext().getRealPath(
			"/reports/Flower_Landscape.jrprint"));

	String url = "jdbc:mysql://localhost:3306/db";
	Map<String,Object> parameters = new HashMap<String,Object>();
	parameters.put("SQLSTR", "select * from employee");
	JasperExportManager.exportReportToHtmlFile(this.getServletContext().getRealPath(
				"/reports/Flower_Landscape.jrprint") );
	  response.sendRedirect("reports/Flower_Landscape.html");
	 byte[] bytes=JasperExportManager.exportReportToPdf(jasperPrint); 

	 response.setContentType("application/pdf");
	 response.setContentLength(bytes.length);
	 
	 ServletOutputStream outStream = response.getOutputStream();
	 outStream.write(bytes,0,bytes.length);
	 outStream.flush();
	 outStream.close();
	 out.clear();
	// out = pageContext.pushBody();
%>