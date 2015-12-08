<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%if(ApplicationParameters.instance().isCaflag()){%>
<object id="SignCtrl" classid="CLSID:4F8F009A-5276-4341-A9A0-971E0AFCE2B6"  height="0" width="0"></OBJECT>
<embed id="SignPlugin" type="application/x-scasign" style="VISIBILITY: hidden" height="0" width="0" >
<%}%>
<script type="text/javascript" src="resources/js/common/signInfo.js"></script>
<script language="Javascript">
//对信息进行签名，并将签名信息赋给msg_signed属性
//成功返回true，否则返回false
<%
 String certCN = ((String)session.getAttribute("user_logindn"));
 if(certCN != null){
	 String type = "CN";
	
	 String[] dnArray = certCN.split(",");
	 for (int i = 0; i < dnArray.length; i++) {
		String temp = dnArray[i];
		String[] str = temp.trim().split("=");
		if (str[0].trim()==type || str[0].trim().equalsIgnoreCase(type)) {
			certCN = str[1].trim();
			break;
		}
	 }
 }
%>
function signData(strData){	 
    <%if(!ApplicationParameters.instance().isCaflag()){%>
    	return true;
    <%}else{%>
		if( strData != ""){
			try{	
			    var Sign = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0)? document.getElementById("SignCtrl") : document.getElementById("SignPlugin");
				var certCN = <%=certCN==null%> ? document.loginForm.u.options[loginForm.u.selectedIndex].value : "<%=certCN%>";
				
				var strSignedData = Sign.P7SignData(certCN,  strData);
				if(strSignedData != ""){
				 <% if(request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX")!=-1){%>
				    document.getElementsByTagName("input")["msg_signed"].value = strSignedData;
				 <%}else{ %>
					document.all("msg_signed").value = strSignedData;
				 <%}%>
					return true;
				}
				else{
					alert("未找到相应证书！");
					return false;
				}		
			}
			catch(e){
				alert("出错：" + e.message);
				return false;
			}
		}
		else{
			alert("待签名的值不能为空！");
			return false;
	    }
	    return true;
    <%}%>
}
function signchk(){
  <%if(ApplicationParameters.instance().isCaflag()){%>
      try{	
		    var Sign = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0) ? document.getElementById("SignCtrl") : document.getElementById("SignPlugin");
			var certCN = "<%=certCN%>";
			var strSignedData = Sign.P7SignData(certCN,  'USB KEY检查');
		    if(strSignedData == "") {
				return false;
			}
			else{
				return true;
			}		
		}
		catch(e){
			return false;
		}
   <%}%>
     return true;
}

function OnStartUKeyMonitoring(){
   <%if(ApplicationParameters.instance().isCaflag()){%>
		var useIE = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0) ? true : false;
		
		//此函数只调用一次
		if(useIE) {
			var Sign = document.getElementById("SignCtrl");
			Sign.StartUKeyMonitoring("", 1000/*检测间隔（毫秒数）*/, "ShuttleCsp11_3003.dll"/*P11库，与UKey驱动相关*/);
		} else {
			var Sign = document.getElementById("SignPlugin");
			Sign.StartUKeyMonitoring("MonitoringCallBack"/*回调脚本函数名称*/, 1000/*检测间隔（毫秒数）*/, "ShuttleCsp11_3003.dll"/*P11库，与UKey驱动相关*/);
		}
	<%}%>
}
</script>