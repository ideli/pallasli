<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@page import="com.shineyue.ip.IPSeeker"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.*"%>
<%@page import="com.shineyue.ip.Util"%>

<%
try{
	String ip = request.getRemoteAddr();
	Util.saveIplog(ip,"(" + request.getHeader("Referer") + ")",request.getHeader("User-Agent"));
	/**String workPath = getServletConfig().getServletContext().getRealPath("/");
	SimpleDateFormat sdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	StringBuffer trace = new StringBuffer(sdd.format(new Date()) + "\t" + ip + "\t");
	if(ip.startsWith("192.168.")){
		trace.append("-\t\t(" + request.getHeader("Referer") + ")\t" + request.getHeader("User-Agent") + "\n");
		
	}
	else{
		//use file database
		/*
		IPSeeker ips = IPSeeker.getInstance(workPath);
		trace.append(ips.getCountry(ip)).append(" ")
			.append(ips.getArea(ip)).append("\n");

		*/

		//use database	
		/*trace.append(Util.getAddress(ip)).append("\t(" + request.getHeader("Referer")).append(")\t" + request.getHeader("User-Agent")).append("\n");
	}*/
	
	/**sdd = new SimpleDateFormat("yyyyMMdd");
	File logFile = new File(workPath + "/log/" + sdd.format(new Date()) + ".txt");
	//System.out.println(logFile.getAbsolutePath());
	FileWriter fw = null;
	try{
		if (!logFile.exists()){
			if (!logFile.createNewFile()){
				throw new Exception("create file failed!");
			}
		}
		fw = new FileWriter(logFile, true);
	    fw.write(trace.toString());
	    fw.flush();
	    fw.close();
	    fw = null;
	}
	catch(Exception e){
		System.out.println("=============>>write log err:" + e.getMessage());
	}
	finally{
		try{
			if (fw != null){
				fw.close();
			}
		}
		catch(Exception e){}
	}*/
}
catch(Exception e){
	out.println("=============>>perhaps ipSeeker err:" + e.getMessage());
}
%>
