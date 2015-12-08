<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.atwa.factory.DataFactory" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.mixky.system.ContextHolder" %>
<%@ page import="com.mixky.app.dataservice.BpmFlowDataService" %>
<%
	  try{
	      String source = request.getParameter( "source" );
	      JsonObject json = BpmFlowDataService.instance().makeBpmFiles(source, request);
	      boolean success = (json.has("success")?json.get("success").getAsBoolean():false);
		  String msg = (json.has("msg")?json.get("msg").getAsString():" ");
	      if(success){
	          String fileName = (json.has("fileName")?json.get("fileName").getAsString():" ");
	          String zipDir = (json.has("zipDir")?json.get("zipDir").getAsString():" ");
	          if(!fileName.trim().equals("") && !zipDir.trim().equals("")){
			      out.clear();
			      response.reset();
			      response.setContentType("application/x-download");
			      String fileNameCode = new String(fileName.getBytes("GB2312"), "ISO8859-1"); 
		    	  response.setHeader("content-disposition", "attachment;filename=\"" + fileNameCode + "\""); 
		    	  File fileLoad = new File(zipDir);	    	     
			      FileInputStream fis = new FileInputStream(fileLoad);
				  byte[] buffer = new byte[1024];
		    	  int byteread = 0;    	
		    	  while ((byteread = fis.read(buffer))!= -1){
			    	response.getOutputStream().write(buffer,0,byteread);
				  }
				  response.getOutputStream().flush();
				  fis.close();
				  fileLoad.delete();
			  }
		  }
		  else{
		     throw new Exception(msg);
		  }
	  }
	  catch(Exception e){
		  System.out.println("err:" +e.getMessage());
%>
		<script language="javascript">
		  alert("<%=e.getMessage()%>");
		</script>
<%		  
	  }	 
%>