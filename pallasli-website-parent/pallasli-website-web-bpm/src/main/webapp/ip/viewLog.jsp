<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@page import="java.io.*"%>
<%
String name = request.getParameter("name");
if (name == null)
{

}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=name %></title>
</head>
<body>
<table></table><pre>
<%

try {
	String logName = getServletConfig().getServletContext().getRealPath("/log/" + name);
    FileReader fr = new FileReader(logName);
    BufferedReader br = new BufferedReader(fr);
    String Line = br.readLine();

    int cnt = 0, loc = 0;
    while (Line != null) {
    	cnt++;
    	if (Line.indexOf("192.168.") != -1) loc++;
    	out.println(Line);
		Line = br.readLine();
    }
    out.println("local/total\t" + loc + "/" + cnt);
    br.close();
    fr.close();
  }
  catch (Exception e) {
    out.println("read file err:" + e.getMessage());
  }
%>
</pre>
</body>
</html>
