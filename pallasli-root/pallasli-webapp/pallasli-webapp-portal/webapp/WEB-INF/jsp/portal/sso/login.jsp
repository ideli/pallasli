<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@page import="java.security.cert.CertificateFactory"%>
<%@page import="com.mixky.ca.util.DateUtils"%>
<%@page import="com.mixky.system.ContextHolder"%>
<%
    String appmap = request.getContextPath();
    String jianpanImage =appmap + "/WEB-INF/jsp/portal/sso/administrator.gif";
    
	//java.security.cert.X509Certificate[] certs=null;
	String dn = "";
	String cn = "";
	Date  after = null;
	Date today = new Date();
	boolean daychk = false;
	String msg = "";
	String caiss = "";
	
	if(ApplicationParameters.instance().isCaflag()){
	    if (ApplicationParameters.instance().getCaiss() == null || "".equals(ApplicationParameters.instance().getCaiss()) || "null".equals(ApplicationParameters.instance().getCaiss())) {
			  String filename = "rootca.cer";
	          String file_sep = System.getProperty("file.separator");
	          String webroot_path = ContextHolder.instance().getWebRoot();
	          String fileDir = file_sep + "crl" + file_sep;	
	          String realDir = webroot_path + fileDir; 
	          String realpath = realDir + file_sep + filename;
	   
	          CertificateFactory cf = CertificateFactory.getInstance("X.509");    
			  FileInputStream in1 = new FileInputStream(realpath);    
			  java.security.cert.Certificate c1 = cf.generateCertificate(in1);  
			    
			  X509Certificate t = (X509Certificate)c1; 
			  String certCN = t.getSubjectDN().toString();
			  if(certCN != null){
				 String type = "CN";
				 String type1 = "O";
				 String[] dnArray = certCN.split(",");
				 for (int i = 0; i < dnArray.length; i++) {
					String temp = dnArray[i];
					String[] str = temp.trim().split("=");
					if (str[0].trim()==type || str[0].trim()==type1 || str[0].trim().equalsIgnoreCase(type)|| str[0].trim().equalsIgnoreCase(type1)) {
					    caiss = str[1].trim();
			            break;
					}
				 }
			  }
			  ApplicationParameters.instance().setCaiss(caiss);
		}
		else{
		    caiss = ApplicationParameters.instance().getCaiss().trim();
		}
	}
	//获取证书的DN项值。
	/*if(ApplicationParameters.instance().isCaflag()){
	    try{
	         session.removeAttribute("user_logindn");
	         certs=(X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
	         if (certs == null) {
	                 //out.println("没有查找到证书。");
	         } else if (certs.length == 0) {
	                 out.println("Certificates length is 0");
	         } else {
	                 java.security.cert.X509Certificate cert = certs[0];
	                 dn = cert.getSubjectX500Principal().toString();
	                 
	                 String type = "CN";
					 String[] dnArray = dn.split(",");
					 for (int i = 0; i < dnArray.length; i++) {
						String temp = dnArray[i];
						String[] str = temp.trim().split("=");
						if (str[0].trim()==type || str[0].trim().equalsIgnoreCase(type)) {
							cn = str[1].trim();
							break;
						}
					 }
					 after = cert.getNotAfter();
					 if (DateUtils.GoMonth(today, 2).after(after)){
					     msg = "您的密钥将于" + DateUtils.formatDate(after, "yyyy年MM月dd日") + 
					           "到期，为保证您正常办理业务，请携带此密钥及时到中心进行延期。";
	        			 daychk = true;
	        		 }
	        		 session.setAttribute("user_logindn", dn.trim());
	         }
	         
			 if(!cn.trim().equals("")){
			   readonly = "readonly";
			 }
	    } catch(Exception e){
	               e.printStackTrace();
	    }
	}*/

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
	obj = request.getAttribute("username");
	if (obj != null) {
		loginName = String.valueOf(obj);
	}
	
	String password = "";
	obj = request.getAttribute("password");
	if (obj != null) {
		password = String.valueOf(obj);
	}
	
	String zjhm = "";
	obj = request.getAttribute("zjhm");
	if (obj != null) {
		zjhm = String.valueOf(obj);
	}
	
	boolean showForce = false;
	obj = request.getAttribute("showForce");
	if (obj != null) {
		showForce = Boolean.valueOf(obj.toString());
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><!--  manifest="offline.appcache" -->
<head>
<link rel="shortcut icon" type="image/x-icon" href="http://www.atwasoft.com/favicon.ico" media="screen" /> 
<title>安泰伟奥住房公积金管理平台</title>
<style>a{TEXT-DECORATION:none}</style>
<link rel="stylesheet" type="text/css" href="loginimages/layout.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">

<style type="text/css">
#divone{
    width: 473px; /* 背景色设为蓝色 */*/
    width: 460px\9; /* 背景色设为灰色。仅IE6与IE7可执行 */
    width: 460px\0; /* 背景色设为蓝色，仅IE8认可 */
        height:150px;
        cursor:pointer;}
.newTable tr td{padding:1px 0 1px 0;}
.blue{background-color:#09F; border:0px;height:20px;}
.forSpan{margin:0 50px 0 50px; color:#FFF;}
#jianpan{ border:#0FC;width:60px;height:20px;}
.litteButton{margin:0px 0px 0px 2px;
        width:26px;
         width: 26px\9; /* 背景色设为灰色。仅IE6与IE7可执行 */
    width: 26px\0; 
        height:20px;border:1px solid #FF0;cursor:pointer;}
.bigButton{margin:1px 1px 1px 1px;border:1px #FF0 solid;cursor:pointer;width:98%;height:44px;margin:0 1px 0 1px;}
#firstId{        width:76%;
         width: 78%\9; /* 背景色设为灰色。仅IE6与IE7可执行 */
    width: 78%\0; 
        height:1px;
}
#thirdId{        width:19%;
         width: 17%\9; /* 背景色设为灰色。仅IE6与IE7可执行 */
    width: 17%\0; 
}
#qiehuan{margin:0px 0px 0px 2px;height:20px;border:1px solid #FF0;cursor:pointer;}
</style>


<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="Scripts/base64.js" type="text/javascript"></script>
</head>

<body onload="return window_onload();">

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
          	<form name=loginForm  method="post">
          	     <%if(ApplicationParameters.instance().isCaflag()){%>
          	     <div id="text">
		           	    <div class="dengkh"><span>证件号码：</span> 
		           	   	  <input id="kh" name="k" type="text" onKeyPress="return onKeyPress('k', event)" value="<%=zjhm%>" size="18" style="FONT-SIZE: 12px" maxlength="18" />
		           	   	</div>
		           	    <div class="denglu">
		           	    <span>
		           	     <%if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE")!=-1||request.getHeader("User-Agent").toUpperCase().indexOf("RV:11.0")!=-1){ %>
          	            用&nbsp;&nbsp;户&nbsp;&nbsp;名：
          	            <%}else{ %>
          	            用&nbsp;户&nbsp;名：
          	            <%}%>
		           	    </span> 
		           	    <%if(request.getRequestURL().toString().startsWith("http://")){ %> 
		           	       <input id="dl" name="u" type="text" onKeyPress="return onKeyPress('u', event)" value="<%=loginName%>" size="18" style="FONT-SIZE: 12px" maxlength="18" />
		           	    <%}else{ %>  
		           	   	  <select id="dl" name="u" onKeyPress="return onKeyPress('u', event)" style="width:184px; height:22px;FONT-SIZE: 12px"> </select>
		           	   	<%} %>
		           	   	</div>
                        <div class="denglu_mima">登录密码：</span>
                          <input id="mima" name="p" type="password" onKeyPress="return onKeyPress('p', event)" onchange="password_onchange(this.value)" value="<%=password%>" size="18" style="FONT-SIZE: 12px" maxlength="18" />
                          <input type="button" value="   " onclick="createKeyBoard()" style="background-image:url('./resources/icon/portal/jianpan.png')"/>                        
                        </div>
                 </div>
                     <%if (showForce) {%>
                        <div class="denglu_qzdl">
						<span>强制登录：</span> 
							<input type="checkbox" name="f" value="force" style="margin-left:-1px"/>
                        </div>
                     <%}%>
                         <div class="denglu_err">         
                          <%=errtxt%>
                        </div>
                        
                         <div class="denglu_remember02" align="center">
                           <input type="button" name="b" style="FONT-SIZE: 14pt" id="denglu" value="登  录" onClick="return doLogin()"/>
                         </div>
                       <div class="denglu_err">         
                          <%=kong%>
                        </div>
                        <div>
                           <a href="crl/SignCtrlInstall.exe" style="color: rgb(255, 0, 0);">
                           <p align="left">1、所有用户首次登录时，请先下载并安装CA安全控件。</p>
                           </a>
                           <a href="crl/rootcrl.rar" style="color: rgb(255, 0, 0);" >
                           <p align="left">2、非系统管理员用户，访问需用https安全认证模式，需下载</br>
                            &nbsp;&nbsp;&nbsp;并安装根证书。</p>                                   
                           </a>
						</div>
					<%}else{%>
          	     <div id="text">
          	            <div class="dengkh"><span>证件号码：</span> 
		           	   	  <input id="kh" name="k" type="text" onKeyPress="return onKeyPress('k', event)" value="<%=zjhm%>" size="18" style="FONT-SIZE: 12px" maxlength="18" />
		           	   	</div>
		           	    <div class="denglu">
		           	    <span>
		           	     <%if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE")!=-1||request.getHeader("User-Agent").toUpperCase().indexOf("RV:11.0")!=-1){ %>
          	            用&nbsp;&nbsp;户&nbsp;&nbsp;名：
          	            <%}else{ %>
          	            用&nbsp;户&nbsp;名：
          	            <%}%>
		           	    </span> 
		           	   	  <input id="dl" name="u" type="text" onKeyPress="return onKeyPress('u', event)" value="<%=loginName%>" size="18" style="FONT-SIZE: 12px" maxlength="18" />
		           	   	</div>
                        <div class="denglu_mima">登录密码：</span>
                          <input id="mima" name="p" type="password" onKeyPress="return onKeyPress('p', event)" onchange="password_onchange(this.value)" value="<%=password%>" size="18" style="FONT-SIZE: 12px" maxlength="18" />
                          <input type="button" value="   " onclick="createKeyBoard()" style="background-image:url('./resources/icon/portal/jianpan.png')"/>                        
                        </div>
                 </div>
                        <div class="denglu_qzdl">
					<%if (showForce) {%>
						<span>强制登录：</span> 
							<input type="checkbox" name="f" value="force" style="margin-left:-1px"/>
                     <%}%>
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
                     <%}%>
					 <input type="hidden" value="" name="msg_signed">
            </form>
          </div>
        </div>
        <div class="siderbar_right"></div>
    </div>	
     <div id="login_footer">
     	<div class="login_footer01">
        	<p>Copyright;2011-2014  北京安泰伟奥信息技术有限公司</p>
        </div>
        <div class="login_footer02"></div>
     </div>
</div>

<!--主容器开始-->

</body>
<jsp:include page="../../ca/sign/signInfo.jsp"/>
<SCRIPT LANG="JAVASCRIPT">
    function window_onload(){
        /*if(<%=!ApplicationParameters.instance().isCaflag()%>){
           document.loginForm.k.focus();
        }
        else{
           document.loginForm.u.focus();
        }*/
        document.loginForm.k.focus();
        
        <%if(ApplicationParameters.instance().isCaflag() && request.getRequestURL().toString().startsWith("https://")){%>
	        var Sign = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0) ? document.getElementById("SignCtrl") : document.getElementById("SignPlugin");
					
			var iss = "<%=caiss%>";
			var result = Sign.GetCertList(iss);
			if(result == "") {
				alert("取证书列表失败!");
			} 
			else{
				list = new Array;
				list = result.split("||");
							
				for(i= 0; i< list.length; i++){
					document.loginForm.u.options.add( new Option(list[i], list[i]));
				}
			}
		<%}%>		
	}
	
	function password_onchange(v){
	   if(v != ''){
	      document.loginForm.p.value = BASE64.encode(v);
	   }
	}
  
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
	   if(<%=!ApplicationParameters.instance().isCaflag()%>){
		    if(document.loginForm.k.value == ''){
				showAlert("证件号码非法：不允许为空")
				document.loginForm.k.focus();
				return false;
			}
			if(document.loginForm.u.value == ''){
				showAlert("用户名非法：不允许为空")
				document.loginForm.u.focus();
				return false;
			}
			if(document.loginForm.p.value == ''){
				showAlert("登录密码非法：不允许为空")
				document.loginForm.p.focus();
				return false;
			}
	    }
	    else{
	        if(document.loginForm.k.value == ''){
				showAlert("证件号码非法：不允许为空")
				document.loginForm.k.focus();
				return false;
			}
            <%if(request.getRequestURL().toString().startsWith("http://")){ %> 
               if(document.loginForm.u.value == ''){
					showAlert("用户名非法：不允许为空")
					document.loginForm.u.focus();
					return false;
				}
            <%}else{%>
		        if(document.loginForm.u.options[document.loginForm.u.selectedIndex].value == ''){
					showAlert("用户名非法：不允许为空")
					document.loginForm.u.focus();
					return false;
				 }
			<%}%>
			if(document.loginForm.p.value == ''){
				showAlert("登录密码非法：不允许为空")
				document.loginForm.p.focus();
				return false;
			}
			<%if(request.getRequestURL().toString().startsWith("https://")){%>
				if(!signData("用户登录：" + document.loginForm.u.options[document.loginForm.u.selectedIndex].value)){
		    	   return false;
		        }
	        <%}%>
	    }
		/* var ua = navigator.userAgent.toLowerCase();  
		if(!(/\bchrome\b/.test(ua))){
			showAlert("本系统目前只支持Chrome浏览器！")
			window.open("http://www.google.com/chrome","_blank");
			return false;
		}*/
		/*
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
		if(<%=!ApplicationParameters.instance().isCaflag()%>){
			if(name == "k"){
			    document.loginForm.u.focus();
			}else if(name == "u"){
				document.loginForm.p.focus();
			}else if(name == "p"){
			    v = document.loginForm.p.value;
			    if(v != ''){
			        document.loginForm.p.value = BASE64.encode(v);
			    }
				return doLogin();
			}
		}
		else{
		    /* if(name == "u"){
				document.loginForm.p.focus();
			}else if(name == "p"){
				return doLogin();
			} */
			if(name == "k"){
			    document.loginForm.u.focus();
			}else if(name == "u"){
				document.loginForm.p.focus();
			}else if(name == "p"){
			    v = document.loginForm.p.value;
			    if(v != ''){
			        document.loginForm.p.value = BASE64.encode(v);
			    }
				return doLogin();
			}
		}
		return false;
	}

	function doLogin(){
		if(!validate()){
			return false;
		}
		
		document.loginForm.b.disabled=true;
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

//……………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………
function KeyBoard() {
        this.tag = document.getElementsByName("p");
        this.passVal;
        this.create = function() {
                var tags = document.getElementById("text");
                var newDiv = document.createElement("div");
                var postion = this.getElementPos(this.tag[0]);
                newDiv.style.position = "absolute";
                newDiv.style.left = postion[0];        
                newDiv.style.top = postion[1];
                newDiv.id = "divone";
                tags.appendChild(newDiv);
                var newTable = document.createElement("table");
                newTable.width = '100%';
                newTable.className = "newTable";
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                td.id = "firstId";
                tr.appendChild(td);        
                td = document.createElement("td");
                td.width = "5%";
                tr.appendChild(td);
                td = document.createElement("td");
                td.id = "thirdId";
                td.height = "1px";
                tr.appendChild(td);
                newTable.appendChild(tr);
                for(var i = 0; i <6 ; i ++) {
                        this.addRow(newTable,i);
                }
                newDiv.appendChild(newTable);
                newDiv.outerHTML = newDiv.outerHTML;
        }
        
        this.whatBrowser = function() {
                 if(navigator.userAgent.indexOf("MSIE")>0) {
                                return "MSIE";
                        }
                if(navigator.userAgent.indexOf("Firefox")>0){
                                return "Firefox";
                        }
                if(navigator.userAgent.indexOf("Opera")>0){
                                return "Opera";
                        }
                if(navigator.userAgent.indexOf("Safari")>0) {
                                return "Safari";
                        }
                if(navigator.userAgent.indexOf("Camino")>0){
                                return "Camino";
                        }
                if(navigator.userAgent.indexOf("Gecko")>0){
                                return "Gecko";
                        }
        }
        this.toDisable = function() {
                var tag = document.getElementById("mima");
                tag.readOnly = true;
                tag.value = "";        
        }
        //创建行函数
        this.addRow = function(newTable,sqnm) {
                var tr = document.createElement("tr");
                var td, spTd, input, span;
                var tdArray = new Array();
                var sLen, sBind;                
                var reg = /[^\u4e00-\u9fa5]/;//中文判断
                tr.className = "blue";
                td = document.createElement("td");
                switch(sqnm) {
                        case 0:
                                td = document.createElement("td");
                                td.colSpan = 3;
                                td.align = "center";
                                span = document.createElement("span");
                                span.innerHTML = "软键盘";
                                span.className = "forSpan";
                                td.appendChild(span);                               
                                tr.appendChild(td);
                                break;
                        case 1:
                                td.colSpan = 2;
                                tdArray = Array("~","!","@","#","$","%","^","&","*","(",")","_","+","|","退格");
                                break;
                        case 2:
                                td.colSpan = 2;
                                tdArray = Array("\'","1","2","3","4","5","6","7","8","9","0","-","=","\\");
                                break;
                        case 3:
                                td.colSpan = 3;
                                tdArray = Array("q","w","e","r","t","y","u","i","o","p","{","}","[","]","切换大\/小写");
                                break;
                        case 4:
                                tdArray = Array("a","s","d","f","g","h","j","k","l",":","\"",":","\'","确定");
                                break;
                        case 5:
                                tdArray = Array("z","x","c","v","b","n","m","<",">","?",",",".","/");
                                break;
                }
                sLen = tdArray.length;
                if(0!=sLen) {        
                        for(var i = 0; i < sLen; i++) {
                                if(reg.exec(tdArray[i])) {
                                        if(0 == tdArray[i].indexOf("切")) {
                                                input = this.createInput(tdArray[i]);
                                                if("MSIE" == this.whatBrowser()) {
                                                        input.style.width = "76px";
                                                }
                                                else {
                                                        input.style.width = "87px";        
                                                }
                                                input.className = "";
                                                input.id = "qiehuan";
                                                td.appendChild(input);        
                                        }
                                        else {        
                                                input = this.createInput(tdArray[i]);
                                                td.height = "20px";
                                                td.appendChild(input);
                                        }
                                }
                                else {
                                        if(0 == tdArray[i].indexOf("退")) {
                                                spTd = document.createElement("td");
                                                spTd.rowSpan = 2;
                                                input = this.createInput(tdArray[i]);
                                                input.className = "bigButton"
                                                input.style.height = "44px";
                                                input.style.width = "98%";
                                                input.id = "back";
                                                spTd.appendChild(input);
                                                                
                                        }
                                        else if(0 == tdArray[i].indexOf("确")){
                                                spTd = document.createElement("td");
                                                spTd.rowSpan = 2;
                                                spTd.colSpan = 2;
                                                input = this.createInput(tdArray[i]);
                                                input.className = "bigButton";
                                                input.style.width = "98%";
                                                input.id = "sure";
                                                spTd.appendChild(input);        
                                        }
                                }
                        }
                        tr.appendChild(td);
                        if(undefined!=spTd) {
                                tr.appendChild(spTd);        
                        }        
                }
                newTable.appendChild(tr);
                //添加绑定事件
        }
        this.addClick = function() {
                var tags = this.getElementsByClassName("litteButton");
                var i = tags.length;
                for(var j = 0; j<i; j++) {
                        (function(tag,type){
                                if('MSIE'==type) {
                                        tag.attachEvent("onclick",function(){change(tag.value)});
                                }
                                else {
                                        tag.addEventListener("click",function(){change(tag.value)},false);        
                                }
                        })(tags[j],this.whatBrowser()); 
                }
                
                var backTag = document.getElementById("back");        
                this.jianRong(backTag,backVal);
                var qieTag = document.getElementById("qiehuan");
                this.jianRong(qieTag,qieHuan);               
                var sureTag = document.getElementById("sure");
                this.jianRong(sureTag,sure);
        }
        
        this.jianRong = function(tag,fun) {
                if('MSIE'==this.whatBrowser()) {
                        tag.attachEvent("onclick",fun);
                }
                else {
                        tag.addEventListener("click",fun,false);        
                }
        }
        this.createInput = function(val) {
                var input;
                input = document.createElement("input");
                input.type = "button";
                input.value = val;
                input.style.width = "25px";
                input.className = "litteButton";
                return input;        
        }
        //获取对象方法
        this.getElementsByClassName = function(n) {
                var classElements = [],allElements = document.getElementsByTagName('*');
                for (var i=0; i< allElements.length; i++ )
                {
                        if (allElements[i].className == n ) {
                                classElements[classElements.length] = allElements[i]; //某类集合
                        }
                }
                        return classElements;
        }
        //获取对象位置
        this.getElementPos = function(tag) {
                var ua = navigator.userAgent.toLowerCase();
                var isOpera = (ua.indexOf('opera') != -1);
                var isIE = (ua.indexOf('msie') != -1 && !isOpera); // not opera spoof 
                if (tag.parentNode === null || tag.style.display == 'none') {
                        return false;
                }
                var parent = null;
                var pos = [];
                var box;
                if (tag.getBoundingClientRect)  
                {
                        box = tag.getBoundingClientRect();
                        var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
                        var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
                        pos[0] = box.left + scrollLeft + "px";
                        pos[1] = box.bottom +scrollTop + "px";
                        return pos;
                }
        }
}
function createKeyBoard() {
        var tag = document.getElementById("divone");
        if(undefined == tag) {
                var s = new KeyBoard;
                s.create();
                s.addClick();
                s.toDisable();
        }
        else {
        	var ma = document.getElementById("mima");
            ma.value = "";
            ma.readOnly = false;
        	tag.parentNode.removeChild(tag);     
        }
}
function change(val) {
        var tag = document.getElementById("mima");
        var newVal = tag.value + val;
        tag.value = newVal;        
}

function backVal() {
        var tag = document.getElementById("mima");
        var newVal, valLen;
        valLen = tag.value.length;
        if(0 == valLen) {
                return false;        
        }  
        else {
                newVal = tag.value.substr(0,valLen - 1);
                tag.value = newVal;                
        }
}
function qieHuan() {
        var reg = /[a-z|A-Z]/;
        var Reg = /[a-z]/;
        
        var classElements = [],allElements = document.getElementsByTagName('*');
        for (var i=0; i< allElements.length; i++ )
        {
                if (allElements[i].className == "litteButton" ) {
                        classElements[classElements.length] = allElements[i]; //某类集合
                }
        }

        var val;
        var i = classElements.length;
        for(var j = 0; j<i; j++) {
                val = classElements[j].value;
                if(reg.exec(val)) {
                        if(!Reg.exec(val)) {
                                classElements[j].value = val.toLowerCase (); 
                        }
                        else {
                                classElements[j].value = val.toUpperCase();          
                        }
                }
        }        
}

function jianPan() {
        var tag = document.getElementById("divone");
        tag.parentNode.removeChild(tag);
        var tag = document.getElementById("mima");
        tag.value = "";
        tag.readOnly = false;
}
function sure() {
    var tag = document.getElementById("divone");
    tag.parentNode.removeChild(tag);
    var tag = document.getElementById("mima");
    tag.readOnly = false;
}
</SCRIPT>
</html>