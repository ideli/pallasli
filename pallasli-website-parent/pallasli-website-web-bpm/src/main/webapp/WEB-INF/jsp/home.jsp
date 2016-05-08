<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
String jsessionId=new Random().nextInt(1000000)+"";
request.getSession().setAttribute("JSESSIONID", jsessionId); %>
<!doctype html>
<html lang="zh-CN">
<head>
</head>
<body>
 
 <a href="firstPage.jsp">firstPage</a>
</body>
</html>