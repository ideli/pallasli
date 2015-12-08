<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.mixky.ca.util.DateUtils" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = request.getParameter("name")==null?"":request.getParameter("name");
String notBefore = "";  //起始日期
String notAfter = "";   //截止日期
Date today = new Date(); //当前日期
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
notBefore = sdf.format(today);
notAfter = sdf.format(DateUtils.GoMonth(today,12));

String type = request.getParameter("type")==null?"":request.getParameter("type");
String certNO = "";
String dn = "";
String errmsg = "";
if(!type.trim().equals("")){
     notBefore = request.getAttribute("notBefore")==null?notBefore:(String)request.getAttribute("notBefore");
	 notAfter = request.getAttribute("notAfter")==null?notAfter:(String)request.getAttribute("notAfter");
	 name = request.getAttribute("name")==null?name:(String)request.getAttribute("name");	 
	 dn = request.getAttribute("dn")==null?"":(String)request.getAttribute("dn");
	 certNO = request.getAttribute("certNO")==null?certNO:(String)request.getAttribute("certNO");
	 errmsg = request.getAttribute("errmsg")==null?"":(String)request.getAttribute("errmsg");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 	<link href="<%=path%>/resources/css/style.css" type="text/css" rel="stylesheet" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <script language="javascript">
   function OnUpdateCert(){
       <%if(type.trim().equals("1")){ %> 
           var Sign = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0) ? document.getElementById("SignCtrl") : document.getElementById("SignPlugin");

			var csp = "EnterSafe ePass3003 CSP v1.0";
			var container = "";
			var cert = 	"<%=request.getAttribute("cert")%>";
			var result = Sign.UpdateCert(csp, container, cert);
			if(result ==1){
			    parent.getMsg("延期证书操作成功！");
			}else{
			    parent.getMsg("延期证书操作失败！");
			}	
		<%}else if(type.trim().equals("2")){%>
		      parent.getMsg("<%=errmsg%>");
		 <%}%>
	}
	
	function validatForm(){
		var cn = document.myform.name.value;
		var notBefore = document.myform.notBefore.value;
		var notAfter = document.myform.notAfter.value;
		
		//判断姓名不为空
		if(cn == ''){
			 parent.getMsg('请输入用户名！');
			 document.myform.name.focus();
			 return false;
		}
		if(notBefore == ''){
			 parent.getMsg('请输入起始时间！');
			 document.myform.notBefore.focus();
			 return false;
		}
		if(notAfter == ''){
			 parent.getMsg('请输入到期时间！');
			 document.myform.notAfter.focus();
			 return false;
		}
		if(DateDiff(notBefore, notAfter) == 1){
		   parent.getMsg('到期时间不能小于起始时间！');
		   document.myform.notAfter.value = '';
		   return false;
		}
		parent.getWait();
		return true;
	}
	
	function date_change(doc, rq){
	   var sj = parent.dateformat(rq);
	   if(sj.indexOf('NaN') != -1){
	       parent.getMsg('日期格式错误，请重新输入！');
	       doc.value = '';
	   }
	   else{
	     doc.value =sj;
	   }
	}
	
	/**
 *比较两个日期:
 *   date1 > date2   return 1;
 *   date1 = date2   return 0;
 *   date1 < date2   return -1;
 */
function DateDiff(date1, date2){
	var array_date1 = (new String(date1)).split("-");
	var array_date2 = (new String(date2)).split("-");

	if (parseInt(array_date1[0],10) > parseInt(array_date2[0],10)){
		return 1;
	}
	if (parseInt(array_date1[0],10) < parseInt(array_date2[0],10)){
		return -1;
	}
	if (parseInt(array_date1[1],10) > parseInt(array_date2[1],10)){
		return 1;
	}
	if (parseInt(array_date1[1],10) < parseInt(array_date2[1],10)){
		return -1;
	}
	if (parseInt(array_date1[2],10) > parseInt(array_date2[2],10)){
		return 1;
	}
	if (parseInt(array_date1[2],10) < parseInt(array_date2[2],10)){
		return -1;
	}
	return 0;
}
	
  	</script>
  
  <body onLoad="OnUpdateCert()">
    <object id="SignCtrl" classid="CLSID:4F8F009A-5276-4341-A9A0-971E0AFCE2B6"  height="0" width="0"></OBJECT>
    <embed id="SignPlugin" type="application/x-scasign" style="VISIBILITY: hidden" height="0" width="0" >
  		<div class="main">
		<div class="upbg"></div>
		<!--=====================================信息区域 开始======================================-->
		<div class="mbox2">
			<div class="tt2">
			</div>
			
   <form id="myform" name="myform"   method="post" action="ExtendCertServlet" >
   <input type="hidden" name="ContainerName" value="">
   <div class="subbox1">
      <table width="98%" cellpadding="0" cellspacing="0" align="center" class="table1">
					<tr>
					  <td width="60" class="td_right"><div align="right" style="font-size:12px;"><span style="color:red">*用户名:</span></div></td>
					  <td valign="middle" class="td_right"><div align="left">
					      <input  type="text" name="name" class="input1" style="width:20% " value='<%=name %>' readOnly/><!-- 6-18位字母、数字或下划线组合。 -->&nbsp;&nbsp;提交延期证书请求时，请确保已经安装根证书，并且已经插入USB KEY。
					  </div></td>
				  </tr>
				  <tr>
					  <td width="60" class="td_right"><div align="right" style="font-size:12px;"><span style="color:red">*起始时间:</span></div></td>
					  <td valign="middle" class="td_right"><div align="left">
					      <input  type="text" name="notBefore" class="input1" style="width:20% " value='<%=notBefore%>'  onchange="date_change(this,this.value)"/><!--("yyyy-MM-dd");。 -->
					  </div></td>
				  </tr>
				  <tr>
					  <td width="60" class="td_right"><div align="right" style="font-size:12px;"><span style="color:red">*到期时间:</span></div></td>
					  <td valign="middle" class="td_right"><div align="left">
					      <input  type="text" name="notAfter" class="input1" style="width:20% " value='<%=notAfter%>' onchange="date_change(this,this.value)"/><!-- ("yyyy-MM-dd"); -->
					  </div></td>
				  </tr>
					<tr>
					  <td valign="bottom" class="td_right"><div align="center">
					  </div></td>
				      <td valign="bottom" class="td_left"><input type="submit" name="Submit" value="延期" class="btn"  onclick="return validatForm()" /> &nbsp;
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
