<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.*"%>
<%
	// 读取参数
	String panelid = request.getParameter("panelid");
	String modelbm = request.getParameter("modelbm");

	String msg = request.getAttribute("msg") == null ? "" : request
			.getAttribute("msg").toString();
	String str = "说明：密码和指纹中任意一项即可验证，如果密码和指纹都有则按密码进行验证。";
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作平台</title>

<link rel="stylesheet" type="text/css"
	href="/pallasli-web-js-css/styles/desktop/desktop.css" />

<script type="text/javascript"
	src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>

<script type="text/javascript"
	src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>

<script type="text/javascript">
function saveUsername(theForm) {

}

function validateForm(form) {
	return true;
}
</script>

<body>
	<center>
		<form method="post" id="registerForm" action="register.do"
			onsubmit="saveUsername(this);return validateForm(this)">

			<table width="260" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td valign="top" align="center" colspan="2"
						style="font-size: 12px;"></td>
				</tr>
				<tr>
					<td height="35" colspan="2" valign="top"
						style="font-size: 12px; font-weight: 600">&nbsp;&nbsp;用&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;： <input
						 width=120px name="userName" type="text" size="20" />
					</td>
				</tr>
				<tr>
					<td height="35" colspan="2" valign="top"
						style="font-size: 12px; font-weight: 600">&nbsp;&nbsp;口&nbsp;&nbsp;&nbsp;&nbsp;令&nbsp;&nbsp;： <input
						type="password"   name="password" size="20" />
					</td>
				</tr> 
				<tr>
					<td height="35" colspan="2" valign="top"
						style="font-size: 12px; font-weight: 600">重&nbsp;复&nbsp;口&nbsp;令： <input
						type="password"  name="checkpassword" size="20" />
					</td>
				</tr> 
				<tr>
					 <td valign="top" align="center" colspan="2"><input
						class="button" name="register" value="注册" type="submit"
					 /></td>
				</tr>
			</table>
		</form>
	</center>
</html>