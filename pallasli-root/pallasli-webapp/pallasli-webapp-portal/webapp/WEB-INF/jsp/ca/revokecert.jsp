<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = request.getParameter("name")==null?"":request.getParameter("name");
String type = request.getParameter("type")==null?"":request.getParameter("type");

String message = "";
String errmsg = "";
if(!type.trim().equals("")){
    message = request.getAttribute("message")==null?"": (String)request.getAttribute("message");
    errmsg = request.getAttribute("errmsg")==null?"":(String)request.getAttribute("errmsg");
    name = request.getAttribute("name")==null?name:(String)request.getAttribute("name");	
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="<%=path%>/resources/css/style.css" type="text/css" rel="stylesheet" />
</head>
  <script language="javascript">
    function window_load(){
       <%if(type.trim().equals("1")){ %>  
          parent.getMsg("<%=message%>");      
	  <%}else if(type.trim().equals("2")){%>
	      parent.getMsg("<%=errmsg%>");
	  <%}%>
     }
	
	function getWait(){
	    parent.getWait();
	}
  </script>
  <body onLoad="window_load()">
 <div class="main">
		<div class="upbg"></div>
		<!--=====================================信息区域 开始======================================-->
		<div class="mbox2">
		<div class="tt2"></div>
          	<div class="subbox1"> 
    <form name="theForm" method="post" action="RevokeCertServlet">      
    <table width="98%" cellpadding="0" cellspacing="0" align="center" class="table1">
				<tr>
					  <td width="133" class="td_right"><div align="right" style="font-size:12px;"><span style="color:red">*用户名：</span></div></td>
					  <td valign="middle" class="td_right"><div align="left">
					      <input  type="text" name="cn" class="input1" style="width:30% " value='<%=name%>' readOnly/><!-- 6-18位字母、数字或下划线组合。 -->
					  </div></td>
				</tr>
			    <tr>
					  <td valign="bottom" class="td_right"><div align="center">
					  </div></td>
				      <td valign="bottom" class="td_left"><input type="submit" name="Submit" value="作废" class="btn"  onclick="return getWait()"/> &nbsp;
			         </td>
				</tr>
				</table> </div>
			<div class="clear"></div>
		</div>
	</div >	 
    </form>  
  </body>
</html>