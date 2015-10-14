<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%String basePath=request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>项目管理</title>



	<script type="text/javascript" src="/pallasli-web-js-css/scripts/jQuery/boot.js"></script>
	
	
    <script type="text/javascript">
    </script>
</head>
 <body style="height: 100%; padding: 0;margin: 0;">
<div  class="mini-panel"  title="新增Todo" style="width:auto; height:100%"  showHeader="false"  borderStyle="border:solid 0px #aaa;">
  
 <div id="form1"   >
        <input name="id" class="mini-hidden" />
        <table>
            
            <tr>
                <td>
                    <label for="textbox1$text">所属部门：</label>
                </td>
                <td>
                    <input id="textbox1"  name="username" class="mini-textbox" required="true" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="pwd1$text">用户名：</label>
                </td>
                <td>
                    <input id="pwd1" name="Pwd" class="mini-password" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textarea1$text">姓名：</label>
                </td>
                <td>
                    <input id="textarea1" name="Area" class="mini-textarea" required="true"/>
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">邮箱：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">开户日期：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">访问次数：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">最后登录ip：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">最后登录时间：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">qq：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>
                   手机：
                </td>
                <td>
                    <input name="Age" class="mini-spinner" value="22" minValue="10" maxValue="50" />
                </td>
            </tr>
     
            <tr>
                <td>
                   电话：
                </td>
                <td>
                    <input name="Married" class="mini-checkbox" text="婚否" value="Y" trueValue="Y" falseValue="N" />
                    <br />
                    <span id="combobox_error" style="color:Red;"></span>
                </td>
            </tr>
           
            <tr>
                <td>
                   通讯地址：
                </td>
                <td>
                    <input name="Married" class="mini-checkbox" text="婚否" value="Y" trueValue="Y" falseValue="N" />
                </td>
            </tr>
            <tr>
                <td>
               邮政编码：
                </td>
                <td>
                    <input name="Married" class="mini-checkbox" text="婚否" value="Y" trueValue="Y" falseValue="N" />
                </td>
            </tr>
            
                <td>
                    
                </td>
                <td>
                    <input value="修改" type="button" onclick="submitForm()" />

                    <input value="退出" type="button" onclick="resetForm()"/>
                                                          
                    
                </td>
            </tr>
        </table>
    </div>
 
 </div>
 

</body>
<script type="text/javascript">

</script>
</html>
