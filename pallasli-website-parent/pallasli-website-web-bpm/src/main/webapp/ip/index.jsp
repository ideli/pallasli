<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.*"%>
<%@page import="java.text.Collator"%>
<%@page import="java.text.CollationKey"%>
<%@page import="java.util.Comparator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>ListFile</title>
</head>
<body>
<table>
<%
class CollatorComparator implements Comparator {

	Collator collator = Collator.getInstance(); 

	public int compare(Object element1, Object element2){
	    CollationKey key1 = collator.getCollationKey(element1.toString());
	    CollationKey key2 = collator.getCollationKey(element2.toString());
	    return -key1.compareTo(key2);
	}
}

try{
	String logPath = getServletConfig().getServletContext().getRealPath("/log/");

	File d = new File(logPath);
	File [] files = d.listFiles();
	
	Map fileMap = new TreeMap(new CollatorComparator());
	for(int i=0; i<files.length; i++){
		fileMap.put(files[i].getName(), files[i].length()<1024 ? files[i].length() + "B" : (files[i].length()/1024) + "KB");
	}
	Iterator itr = fileMap.keySet().iterator();
	int i = 1;
    while(itr.hasNext()) {
    	Object obj = itr.next();
        //System.out.println(i + "," + map.get(i));
        out.println("<tr><td><a href='./viewLog.jsp?name=" + obj + "'>" + obj + "</a></td><td>" + fileMap.get(obj) + "</td></tr>");
        if (i++ >= 10)break;
    }
}
catch(Exception e){
	out.println(e.getMessage());
}
%>
</table>
</body>
</html>