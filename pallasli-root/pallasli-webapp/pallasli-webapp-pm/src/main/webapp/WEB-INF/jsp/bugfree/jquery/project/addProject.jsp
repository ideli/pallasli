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
<body >
 <div id="form1"  title="新增项目" >
        <input name="id" class="mini-hidden" />
        <table>
            
            <tr>
                <td>
                    <label for="textbox1$text">项目名称：</label>
                </td>
                <td>
                    <input id="textbox1"  name="username" class="mini-textbox" required="true" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="pwd1$text">项目代号：</label>
                </td>
                <td>
                    <input id="pwd1" name="Pwd" class="mini-password" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="textarea1$text">开始日期：</label>
                </td>
                <td>
                    <input id="textarea1" name="Area" class="mini-textarea" required="true"/>
                </td>
            </tr>
            <tr>
                <td>                   
                    <label for="date1$text">结束日期：</label>
                </td>
                <td>
                    <input id="date1" name="BirthDay" class="mini-datepicker" value="2010-10-12" required="true" />
                </td>
            </tr>
            <tr>
                <td>
                    团队名称：
                </td>
                <td>
                    <input name="Age" class="mini-spinner" value="22" minValue="10" maxValue="50" />
                </td>
            </tr>
             <tr>
                <td>
                    关联产品：
                </td>
                <td>
                    <input name="Time" class="mini-timespinner" format="H:mm" />
                </td>
            </tr>
            <tr>
                <td>
                    项目目标：
                </td>
                <td>
                    <input name="Married" class="mini-checkbox" text="婚否" value="Y" trueValue="Y" falseValue="N" />
                </td>
            </tr>
            <tr>
                <td>
                    项目描述：
                </td>
                <td>
                                       <input name="Married" class="mini-checkbox" text="婚否" value="Y" trueValue="Y" falseValue="N" />
               <br />
                    <span id="combobox_error" style="color:Red;"></span>
                </td>
            </tr>
           
            <tr>
                <td>
                   访问控制：
                </td>
                <td>
                    <div name="countrys2" class="mini-radiobuttonlist" value="en" repeatItems="4" repeatDirection="vertical" repeatLayout="table" url="data/projectManager/countrys.txt" textField="text" valueField="id" ></div>
                </td>
            </tr>
            
                <td>
                    
                </td>
                <td>
                    <input value="保持" type="button" onclick="submitForm()" />

                    <input value="重填" type="button" onclick="resetForm()"/>
                                                          
                    
                </td>
            </tr>
        </table>
    </div>

</body>
<script type="text/javascript">

</script>
</html>
