<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mixky.engine.organization.dao.User"%>
<%
	Object object = null;
	String applicationName = "";
	List<String> moduleKeys = null;
	object = request.getAttribute("applicationName");
	if (object != null) {
		applicationName = (String)object;
	}
	object = request.getAttribute("moduleKeys");
	if (object != null) {
		moduleKeys = (List<String>)object;
	}
	if (moduleKeys == null || moduleKeys.size() < 1) {
%>
<html>
 <title><%=applicationName%></title>
 <body>
 </body>
</html>
<%
	} else {
%>

<html>
 <title><%=applicationName%></title>
 <body>
	<h2>应用系统数据管理</h2>
	<hr>
	<b>设计缓存</b>&nbsp;<a href="?a=rc&p=a">全部重新装载</a><br>
<%
	for (int i = 0; i < moduleKeys.size(); i ++) {
%>
		<%=moduleKeys.get(i)%>&nbsp;<a href="?a=rc&p=<%=moduleKeys.get(i)%>">重新装载</a><br>
<%		
	}
%>
	<br><br>
	<b>运行文件</b>&nbsp;<a href="?a=bf&p=a">重新生成</a> &nbsp;
 </body>
</html>
<%		
	}
%>