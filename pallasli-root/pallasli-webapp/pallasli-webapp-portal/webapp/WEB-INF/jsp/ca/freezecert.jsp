<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = request.getParameter("name")==null?"":request.getParameter("name");
String type = request.getParameter("type")==null?"":request.getParameter("type");

String result = "";
String errmsg = "";
if(!type.trim().equals("")){
    result = request.getAttribute("result")==null?"":(String)request.getAttribute("result");
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
        parent.getMsg("<%=result%>");      
	<%}else if(type.trim().equals("2")){%>
	    parent.getMsg("<%=errmsg%>");
	<%}%>
  }
  </script>
  <body onLoad="window_load()">
  <div class="main">
		<div class="upbg"></div>
		<!--=====================================信息区域 开始======================================-->
		<div class="mbox2">
			<div class="tt2"></div>
    <form name="cmdForm" method="post" action="FreezeCertServlet">
          
          <input type="hidden" name="cmd" value="" />
          	<div class="subbox1">
          <table width="98%" cellpadding="0" cellspacing="0" align="center" class="table1">
				<tr>
					  <td width="133" class="td_right"><div align="right" style="font-size:12px;"><span style="color:red">*用户名：</span></div></td>
					  <td valign="middle" class="td_right"><div align="left">
					      <input  type="text" name="cn" class="input1" style="width:30% " value='<%=name %>' readOnly/><!-- 6-18位字母、数字或下划线组合。 -->
					  </div></td>
				</tr>
			    <tr>
					  <td valign="bottom" class="td_right"><div align="center">
					  </div></td>
				      <td valign="bottom" class="td_left"><input   onclick="return freeze()" type="submit" name="Submit" value="冻结" class="btn"  /> &nbsp;<input   onclick="return unfreeze()" type="submit" name="Submit" value="解冻" class="btn"  />
			         </td>
				</tr>
				<tr>
					  <td valign="bottom" class="td_right"><div align="center">
					  </div></td>
				      <td valign="bottom" class="td_left"> &nbsp;
			         </td>
				</tr>
				
				</table>
    	</div>
			<div class="clear"></div>
	  </form>
		</div>
	</div >	
  </body>
</html>
<script>
function freeze(){
    document.cmdForm.cmd.value = "freeze";
	document.cmdForm.submit();
	parent.getWait();
	return false;
}
function unfreeze(){
	document.cmdForm.cmd.value = "unfreeze";
	document.cmdForm.submit();
	parent.getWait();
	return false;
}
</script>
