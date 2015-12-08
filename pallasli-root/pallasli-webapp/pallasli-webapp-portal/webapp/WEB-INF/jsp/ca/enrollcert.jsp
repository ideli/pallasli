<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mixky.ca.util.CertTools"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
    String province ="";
	String locality = "";
	String org = "";
	String ou = "";
	String email = "";
	String sn = "";
	String dn = "";
	String errmsg = "";
	
	String name = request.getParameter("name")==null?"":request.getParameter("name");
	String dndata = request.getParameter("dndata")==null?"":request.getParameter("dndata");
	String type = request.getParameter("type")==null?"":request.getParameter("type");
	
	if(!dndata.trim().equals("")){
	   province = CertTools.getPartFromDN(dndata, "ST") == null ? "" : CertTools.getPartFromDN(dndata, "ST");
	   locality = CertTools.getPartFromDN(dndata, "L") == null ? "" : CertTools.getPartFromDN(dndata, "L");
	   org = CertTools.getPartFromDN(dndata, "O") == null ? "" : CertTools.getPartFromDN(dndata, "O");
	   ou = CertTools.getPartFromDN(dndata, "OU") == null ? "" : CertTools.getPartFromDN(dndata, "OU");
	   email = CertTools.getPartFromDN(dndata, "E") == null ? "" : CertTools.getPartFromDN(dndata, "E");
	}

	if(!type.trim().equals("")){
	     name = request.getAttribute("name")==null?name:(String)request.getAttribute("name");
		 province = request.getAttribute("province")==null?province:(String)request.getAttribute("province");
		 locality = request.getAttribute("locality")==null?locality:(String)request.getAttribute("locality");
		 org = request.getAttribute("org")==null?org:(String)request.getAttribute("org");
		 ou = request.getAttribute("ou")==null?ou:(String)request.getAttribute("ou");
		 email = request.getAttribute("email")==null?email:(String)request.getAttribute("email");
		 sn = request.getAttribute("sn")==null?"":(String)request.getAttribute("sn");
		 dn = request.getAttribute("dn")==null?"":(String)request.getAttribute("dn");
		 errmsg = request.getAttribute("errmsg")==null?"":(String)request.getAttribute("errmsg");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="<%=path%>/resources/css/style.css" type="text/css" rel="stylesheet" />
		<script language="javascript">
	function OnGetCSPList(){
		try {   
		        var Sign = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0) ? document.getElementById("SignCtrl") : document.getElementById("SignPlugin");
		        var result = Sign.GetCSPList();
		        if(result == "") {
					alert("取CSP列表失败!");
				} else {
					list = new Array;
					list = result.split("||");				
					for(i= 0; i< list.length; i++){
						signUserCertForm.ieTokenList.options.add( new Option(list[i], list[i]));
					}
					signUserCertForm.ieTokenList.options[0].text = "EnterSafe ePass3003 CSP v1.0";
				} 
				<%if(type.trim().equals("1")){ %> 
	                var sPKCS7 = "<%=request.getAttribute("cert")%>";      
	               // var result = Sign.AcceptPKCS7(sPKCS7);
	             var csp =   document.signUserCertForm.ieTokenList.options[document.signUserCertForm.ieTokenList.selectedIndex].text;  
		           csp = (csp==null||csp=='undefined'||csp=='')?"": csp;  
	               var container = document.signUserCertForm.ContainerName.value;
		             container = (container==null||container=='undefined'||container=='')?"": container;
	                var result = Sign.UpdateCert(csp,container,sPKCS7);
					if(result == 1){
					     parent.SaveDS("<%=dn%>","<%=sn%>","签发用户证书成功！");
					}else{
					    parent.getMsg("签发用户证书失败！");
					}   
				<%}else if(type.trim().equals("2")){%>
				    parent.getMsg("<%=errmsg%>");
				<%}%>
	      }catch (err) {            
                document.write("<br>" + "没有可识别的USB KEY");            
                return false;       
          }    
         return true;
	}
	
	function OnCreatePKCS10(){		
		var Sign = (navigator.userAgent.toUpperCase().indexOf("MSIE")  > 0||navigator.userAgent.toUpperCase().indexOf("RV:11.0")  > 0) ? document.getElementById("SignCtrl") : document.getElementById("SignPlugin");
		
		var county = "CN";
		var province =  document.signUserCertForm.province.value;
		province = (province == null||province == 'undefined'||province == '')?"": province;
		var locality = document.signUserCertForm.locality.value;
		locality = (locality == null||locality == 'undefined'||locality == '')?"": locality;
		var org = document.signUserCertForm.org.value;
		org = (org == null||org == 'undefined'||org == '')?"": org;
		var ou = document.signUserCertForm.ou.value;
		ou = (ou == null||ou == 'undefined'||ou == '')?"": ou;
		var email = document.signUserCertForm.email.value;
		email = (email==null||email=='undefined'||email=='')?"": email;
		var name_ = document.signUserCertForm.name.value;
		name_ = (name_==null||name_=='undefined'||name_=='')?"": name_;
		var csp =   document.signUserCertForm.ieTokenList.options[document.signUserCertForm.ieTokenList.selectedIndex].text;  
		csp = (csp==null||csp=='undefined'||csp=='')?"": csp;  
		var container = document.signUserCertForm.ContainerName.value;
		container = (container==null||container=='undefined'||container=='')?"": container; 
		var result = Sign.DelContainer(csp, container);
		 result = Sign.CreatePKCS10(county, province, locality, org, ou, email, name_, csp, container,1024);
		document.signUserCertForm.p10.value = result;
		//Sign.AcceptPKCS7 
		//Sign.InstallPKCS7
	}
</script>
	</head>
	<body onLoad="OnGetCSPList()">
	    <object id="SignCtrl" classid="CLSID:4F8F009A-5276-4341-A9A0-971E0AFCE2B6"  height="0" width="0"></OBJECT>
        <embed id="SignPlugin" type="application/x-scasign" style="VISIBILITY: hidden" height="0" width="0" >
		<div class="main">
			<div class="upbg"></div>
			<!--=====================================信息区域 开始======================================-->
			<div class="mbox2">
				<div class="tt2"></div>
			
				<form name="signUserCertForm" method="post"
					action="EnrollCertServlet">
					
					<input type="hidden" name="ContainerName"
						value="<%=application.getInitParameter("ContainerName")%>">
						<input type="hidden" name="providerName" value="">
							<input type="hidden" name="providerType" value="">
								<input type="hidden" name="uCertType" value="1">
									<input type="hidden" name="isCouple" value="1" />
									<input type="hidden" name="reqMode" value="2" />
									<input type="hidden" name="reqStatus" value="1" />
									<input type="hidden" name="p10" value="">
										<div class="subbox1">
											<table width="98%" cellpadding="0" cellspacing="0"
												align="center" class="table1">
												<tr>
													<td width="133" class="td_right">
														<div align="right">
															<span style="color: red">*用户名：</span>
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="name" class="input1"
																style="width: 50%" value='<%=name %>' readOnly/>
															<!-- 6-18位字母、数字或下划线组合。 -->
														</div>
													</td>
												</tr>

												<tr>
													<td class="td_right">
														<div align="right">
															<span style="color: red">*国家：</span>
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="country" class="input1"
																style="width: 50%" value='CN' readOnly/>
														</div>
													</td>
												</tr>
												<tr>
													<td class="td_right">
														<div align="right">
															<span style="color: red">*省份：</span>
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="province" class="input1"
																style="width: 50%" value='<%=province %>' />
														</div>
													</td>
												</tr>
												<tr>
													<td class="td_right">
														<div align="right">
															<span style="color: red">*城市：</span>
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="locality" class="input1"
																style="width: 50%" value='<%=locality%>' />
														</div>
													</td>
												</tr>
												<tr>
													<td class="td_right">
														<div align="right">
															<span style="color: red">*单位：</span>
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="org" class="input1"
																style="width: 50%" value='<%=org%>' />
														</div>
													</td>
												</tr>
												<tr>
													<td class="td_right">
														<div align="right">
															部门：
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="ou" class="input1"
																style="width: 50%" value='<%=ou%>' />
														</div>
													</td>
												</tr>

												<tr>
													<td class="td_right">
														<div align="right">
															电子邮件：
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<input type="text" name="email" class="input1"
																style="width: 50%" value='<%=email%>' />
														</div>
													</td>
												</tr>
												<tr>
													<td class="td_right">
														<div align="right">
															选择证书设备：
														</div>
													</td>
													<td valign="middle" class="td_right">
														<div align="left">
															<select id="ieTokenList" name="ieTokenList"
																class="input1" style="width: 50%; height: 22px; line-height: 22px;">
															</select>

														</div>
													</td>
												</tr>

												<tr>
													<td valign="bottom" class="td_right">
														<div align="center">
														</div>
													</td>
													<td valign="bottom" class="td_left">
														<input type="submit" name="Submit" value="签发" class="btn"
															onclick="return validatForm()" />
														&nbsp;
													</td>
												</tr>
											</table>
										</div>

										<div class="clear"></div>
				</form>
			</div>
		</div>
	</body>
	<script language="javascript">
	function validatForm(){
		var cn = document.signUserCertForm.name.value;
		var cc = document.signUserCertForm.country.value;
		var cp = document.signUserCertForm.province.value;
		var cl = document.signUserCertForm.locality.value;
		var co = document.signUserCertForm.org.value;
		var email =  document.signUserCertForm.email.value;
		//判断姓名不为空
		if(cn == ''){
			 parent.getMsg('请输入用户名！');
			document.signUserCertForm.name.focus();
			return false;
		}
		if(cc == ''){
			 parent.getMsg('请输入国家！');
			document.signUserCertForm.country.focus();
			return false;
		}
		if(cp == ''){
			 parent.getMsg('请输入省份！');
			document.signUserCertForm.province.focus();
			return false;
		}
		if(cl == ''){
			 parent.getMsg('请输入城市！');
			document.signUserCertForm.locality.focus();
			return false;
		}
		if(co == ''){
			 parent.getMsg('请输入单位！');
			document.signUserCertForm.org.focus();
			return false;
		}
	//当录入email信息后，判断邮件地址是否合法
		if(email.length != 0){
			if(isasciistr(email)==0){
				 parent.getMsg('邮件不正确,只允许输入字母数字等,不允许输入中文！');
				document.signUserCertForm.email.focus();
				return false;
			}
		 	var myreg = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
		    if(!myreg.test(email)){
			  	 parent.getMsg("请输入有效的email!");
			    return false;
		    }
	   	}	   
		OnCreatePKCS10();//生成证书请求
		parent.getWait();
		return true;
	}

	function  isasciistr(str){  
	    var  strSource  =".!@#$%^&*+_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	    var  ch;  
	    var  i;  
	    var  temp;  
	    for  (i=0;i<=(str.length-1);i++){  
	        ch  =  str.charAt(i);  
	        temp  =  strSource.indexOf(ch);
	        if  (temp==-1)	return 0;    
	    }  
	    return 1;
	} 
</script>
</html>