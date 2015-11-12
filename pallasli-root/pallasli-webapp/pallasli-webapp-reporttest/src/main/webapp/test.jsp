<%@page import="bean.ItemStatReport"%>
<%@page import="bean.PaAnCustomJRdataSource"%>
<%@page import="bean.User"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("param1", "p1");
	ItemStatReport user = new ItemStatReport();
	user.setName("lytman man m");
	user.setSex("man m man man man ");
user.setAge("12");
user.setDesc("ddd");
	
	
	Collection<ItemStatReport> collection = new ArrayList<ItemStatReport>();
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	collection.add(user);
	PaAnCustomJRdataSource jrd = new PaAnCustomJRdataSource(collection);

	
	
	JasperFillManager.fillReportToFile(this.getServletContext()
			.getRealPath("/reports/simple2.jasper"), map, jrd);
 

File sourceFile = new File(this.getServletContext().getRealPath(
		"/reports/simple2.jrprint"));
JasperExportManager.exportReportToHtmlFile(sourceFile.getPath());

JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
	 byte[] bytes=JasperExportManager.exportReportToPdf(jasperPrint); 
	 //response.sendRedirect("reports/simple2.html");
	  response.setContentType("application/pdf");
	 response.setContentLength(bytes.length);
	 
	 ServletOutputStream outStream = response.getOutputStream();
	 outStream.write(bytes,0,bytes.length);
	 outStream.flush();
	 outStream.close();
	 out.clear(); 
%>