<%@ page contentType="text/html; charset=utf-8"%>
<%
	String applicationName = "";
	Object obj = request.getAttribute("applicationName");
	if(obj != null){
		applicationName = String.valueOf(obj);
	}
	// 错误提示
	String errtxt = "";
	String kong   = "";
	if(request.getAttribute("errtxt") != null){
		errtxt = String.valueOf(request.getAttribute("errtxt"));
	}
	// 使用验证码
	obj = request.getAttribute("useICode");
	boolean useICode = false;
	if(obj != null){
		useICode = (Boolean)obj;
	}
	String loginName = "";
	obj = request.getAttribute("loginName");
	if (obj != null) {
		loginName = String.valueOf(obj);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>湖南省直住房管理中心</title>
<style>a{TEXT-DECORATION:none}</style>
<link rel="stylesheet" type="text/css" href="loginimages/layout.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body onload="document.loginForm.u.focus();">

<!--主容器开始-->	
<div id="container">
	<div id="login_header">
    	<div class="login_header01"></div>
        <div class="login_header02"></div>
        <div class="login_header03"></div>
        <div class="login_header04"></div>
    </div>
    <div id="login_mainbody">
    	<div class="siderbar_left"></div>
        <div class="siderbar_mid">
        <div class="siderbar_mid01">
            	<div class="mid01_top"></div>
                <div class="mid01_mid"></div>
                <div class="mid01_bot">     
                </div>
            </div>
          <div class="siderbar_mid02">
          	<form name=loginForm method="post">
           	   <div class="denglu"><span>用户名：</span> 
           	   	<input id="dl" name="u" type="text" onKeyPress="return onKeyPress('u', event)" value="<%=loginName%>" size="18" style="FONT-SIZE: 12px" maxlength="18" height="15"/>
           	   	</div>
                        <div class="denglu_mima">密<span> 码：</span>
                          <input id="mima" name="p" type="password" onKeyPress="return onKeyPress('p', event)" size="18" style="FONT-SIZE: 12px" maxlength="18" height="18"/>
                        </div>
                         <div class="denglu_err">         
                          <%=errtxt%>
                        </div>
                         <div class="denglu_remember02" align="center">
                           <input type="button" name="b" style="FONT-SIZE: 14pt" id="denglu" value="登  录" onClick="return doLogin()"/>
                         </div>
                       <div class="denglu_err">         
                          <%=kong%>
                        </div>
                        <div class="denglu_remember03">
                           <a href="resources/tools/download/im/WeAll-IM-Client.rar">
                           		 <span title="即时通讯软件下载地址。即时通讯软件：简称WA-IM,是与3.0系统配套研发的一款即时通讯软件。与3.0系统整合，可实现组织架构同步、双向登陆、待办消息提醒等功能！(内附 安装、登陆说明)" style="color: rgb(255, 0, 0);">
                           		   <img src="loginimages/images/but_12.png">
                           		 </span>
                           </a>
						</div>
						<!-- 
                         <div class="download_im" align="center">
                            <a href="resources/tools/download/im/WeAll-IM-Client.rar"  target=_blank class="browser-download">
                              <span title="即时通讯软件，简称WA-IM,是与3.0系统配套研发的一款即时通讯软件。与3.0系统整合，可实现组织架构同步、双向登陆、代办消息提醒等功能！(内附 安装、登陆说明)" style="color: rgb(255, 0, 0);">
                                    下载即时通讯客户端




                              </span>
                            </a>
                         </div>
                          -->
            </form>
          </div>
        </div>
        <div class="siderbar_right"></div>
    </div>	
     <div id="login_footer">
     	<div class="login_footer01">
        	<p>Copyright;2010-2011  北京安泰伟奥信息技术有限公司</p>
        </div>
        <div class="login_footer02"></div>
     </div>
</div>

<!--主容器开始-->

</body>
<SCRIPT LANG="JAVASCRIPT">
	function getWindowSize() {
	  var myWidth = 0, myHeight = 0;
	  if( typeof( window.innerWidth ) == 'number' ) {
		//Non-IE
		myWidth = window.innerWidth;
		myHeight = window.innerHeight;
	  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
		//IE 6+ in 'standards compliant mode'
		myWidth = document.documentElement.clientWidth;
		myHeight = document.documentElement.clientHeight;
	  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
		//IE 4 compatible
		myWidth = document.body.clientWidth;
		myHeight = document.body.clientHeight;
	  }
	  return {width:myWidth, height:myHeight};
	}

	function onWindowResize(){
		var size = getWindowSize();
		var login = document.getElementById('logindiv');
		login.style.top = (size.height - 768) / 2;
		login.style.left = (size.width - 1024) / 2;
		login.style.display = '';
	}

	function showAlert(msg){
		alert(msg);
	}

	function validate(){
		if(document.loginForm.u.value == ''){
			showAlert("用户名非法：不允许为空")
			document.loginForm.u.focus();
			return false;
		}
		if(document.loginForm.p.value == ''){
			showAlert("密码非法：不允许为空")
			document.loginForm.p.focus();
			return false;
		}
		/*var ua = navigator.userAgent.toLowerCase();  
        if(ua.indexOf("chrome")==-1){
            showAlert("本系统只能在IE8以上版本浏览器中使用！")
			return false;
        }*/
       // alert(navigator.userAgent.toLowerCase()+'====='+navigator.appName);
       /* var engine = null;
        if(navigator.appName == "Microsoft Internet Explorer") { 
             if (document.documentMode) // IE8
			      engine = document.documentMode;
			 else{ // IE 5-7
			      engine = 5; 
			      if (document.compatMode){
			         if (document.compatMode == "CSS1Compat")
			            engine = 7;
			      }
			 } 
            var ua = navigator.userAgent.toLowerCase();
		    if(ua.indexOf("msie 7.0")>0&&engine<8) { 
		       showAlert("本系统只支持IE8及以上版本浏览器！")
			   return false;
		    }else if(ua.indexOf("msie 6.0")>0&&engine<8){ 
		       showAlert("本系统只支持IE8及以上版本浏览器！")
			   return false;
		    } else if(ua.indexOf("msie 8.0")>0&&engine<8){ 
		       showAlert("本系统只支持IE8及以上版本浏览器！")
			   return false;
		    } else if(ua.indexOf("msie 9.0")>0&&engine<8){ 
		       showAlert("本系统只支持IE8及以上版本浏览器！")
			   return false;
		    } 
		}
		else if(document.loginForm.u.value!='admin'){
		     showAlert("本系统只支持IE8及以上版本浏览器！")
			 return false;
		} */
		return true;
	}
	function onKeyPress(name, event){
		if(event.keyCode != 13){
			return true;
		}
		if(name == "u"){
			document.loginForm.p.focus();
		}else if(name == "p"){
	//document.getElementById('inputB').focus();
			return doLogin();

		}
		return false;
	}

	function doLogin(){
		if(!validate()){
			return false;
		}
		document.loginForm.submit();
	}
	
	
function AC_FL_RunContent(){
  var ret = 
    AC_GetArgs
    (  arguments, ".swf", "movie", "clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
     , "application/x-shockwave-flash"
    );
  AC_Generateobj(ret.objAttrs, ret.params, ret.embedAttrs);
}
	
	
</SCRIPT>
</html>