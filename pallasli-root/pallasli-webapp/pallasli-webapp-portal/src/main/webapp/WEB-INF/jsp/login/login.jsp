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

<script language='javascript'>
var errmsg="<%=msg%>";
	if (errmsg != null && errmsg.trim() != "") {
		alert(errmsg);
	}

	function displaymessage() {
		var spTimeOut = 5000;
		ZAZFingerActivex.spDeviceType = 2;
		ZAZFingerActivex.spComPort = 1;
		ZAZFingerActivex.spBaudRate = 6;
		ZAZFingerActivex.CharLen = 512;
		ZAZFingerActivex.TimeOut = spTimeOut;
		var mesg = ZAZFingerActivex.ZAZGetImgCode();
		if (mesg == "0") {
			setTimeout(function() {
				displaymessage();
			}, 3000);
			return ZAZFingerActivex.FingerCode;
		} else if (mesg == "-1") {
			alert("指纹识别仪连接失败");
		} else if (mesg == "-2") {
			alert("数据错误");

		} else if (mesg == "-3") {
			alert("设备控件调用文件被占用或被删除");

		} else if (mesg == "-4") {
			alert("超时退出");

		}
		return "";
	}

	function Match(spSrc, spDst) {
		return ZAZFingerActivex.ZAZMatch(spSrc, spDst);
	}
	function getFinger() {
		loginForm.fingerCode = '';
		var str = displaymessage();
		if (loginForm != '') {
			form1.fingerCode = str;
		}
	}

	function saveUsername(theForm) {

	}

	function validateForm(form) {
		return true;
	}

	function registerUser(){
		window.showModalDialog("jsppage.do?url=registerUser");
	}
	
	Ext.onReady(function() {
	//	setTimeout(displaymessage, 300);
	});
</script>

<style type="text/css">
<!--
body {
	margin-top: 0px;
	list-style: none;
	background-color: #fff;
	overflow:hidden;
}

#bg {
	background: url(/pallas_portal/images/login_bg.jpg) no-repeat center;
	overflow:hidden;
	width: 1360px;
	height: 768px;
}
#registerDiv{
	display:none;
	background-color:aqua;
	padding-top:30px;
	position: relative;
	top: 200px;
	left:30px;
	width: 300px;
	height: 180px;
}
.loginInf { 
	position: relative;
	top: 320px;
	left: -300px;
	width: 100px;
	height: 100px;
}

.link {
	position: relative;
	top: 380px;
	left: -250px;
}
-->
</style>
</head>



<body>
		<OBJECT classid="clsid:87772C8D-3C8C-4E55-A886-5BA5DA384424"
			name="ZAZFingerActivex" id="ZAZFingerActivex" width="0"
			height="0"> </OBJECT>

	<center>
		<div id="bg">

			<div class="loginInf">
				<form method="post" id="loginForm" action="auth.do"
					onsubmit="saveUsername(this);return validateForm(this)">

					<table width="260" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td valign="top" align="center" colspan="2"
								style="font-size: 12px;"></td>
						</tr>
						<tr>
							<td height="35" colspan="2" valign="top"
								style="font-size: 12px; font-weight: 600">用 户： <input
								id="userName" width=120px name="userName" type="text" size="20" />
							</td>
						</tr>
						<tr>
							<td height="35" colspan="2" valign="top"
								style="font-size: 12px; font-weight: 600">口 令： <input
								type="password" id="password" name="password" size="20" />
							</td>
						</tr>
						<tr>
							<td height="35" colspan="1" valign="top"
								style="font-size: 12px; font-weight: 400;"><input
								type="checkbox" class="checkbox" name="isForce" id="isForce"
								value="1" tabindex="3" /> &nbsp;强制登录</td>
							<td height="35" colspan="1" valign="top"
								style="font-size: 12px; font-weight: 400;"><input
								type="checkbox" class="checkbox"
								name="_spring_security_remember_me" id="rememberMe" tabindex="3" />
								&nbsp;让系统记住我</td>
						</tr>
						<tr>
							<td valign="top" align="center" colspan="1"><input
								class="button" name="login" value="登  录" type="submit" /></td>
							<td valign="top" align="center" colspan="1"><input
								class="button" name="register" value="注册" type="button" onclick="registerUser();" /></td>
						</tr>
					</table>
				</form>

			</div>
			
			<div id="registerDiv">
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
	</div>
			
			<div class="link">
				<table border="0" align="center" cellpadding="20" cellspacing="0">
					<tr>
						<td style="font-size: 12px;"><a
							href="/bizfuse/download/tool/setup.exe">客户端下载</a></td>
						<td style="font-size: 12px;"><a
							href="/bizfuse/download/tool/manual.pdf">用户手册</a></td>
					</tr>
				</table>
			</div>
		</div>
	</center>
</body>
</html>