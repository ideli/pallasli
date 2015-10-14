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
 <div style="width:auto;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>       
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="key" class="mini-textbox" emptyText="请输入姓名" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div class="mini-fit">
    <div id="datagrid1" class="mini-datagrid" style="width:auto;height:100%" allowResize="true"
        url="../data/AjaxService.jsp?method=SearchEmployees"  idField="id" multiSelect="true" >
        <div property="columns">
            <!--<div type="indexcolumn"></div>        -->
            <div type="checkcolumn" ></div>        
            <div field="loginname" width="120" headerAlign="center" allowSort="true">员工帐号</div>    
            <div field="name" width="120" headerAlign="center" allowSort="true">姓名</div>    
            <div header="工作信息">
                <div property="columns">
                    <div field="dept_name" width="120">所属部门</div>
                    <div field="position_name" width="100">职位</div>
                    <div field="salary" dataType="currency" currencyUnit="￥" align="right" width="100" allowSort="true">薪资</div>
                </div>
            </div>
            <div field="createtime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>    
            <div header="基本信息">
                <div property="columns">
                    <div field="gender" width="100" renderer="onGenderRenderer">性别</div>
                    <div field="age" width="100" allowSort="true">年龄</div>
                    <div field="birthday" width="100" renderer="onBirthdayRenderer">出生日期</div>
                    <div field="married" width="100" align="center" renderer="onMarriedRenderer">婚否</div>
                    <div field="email" width="100">邮箱</div>
                </div>
            </div>
            <div header="学历信息">
                <div property="columns">
                    <div field="educational_name" width="100">学历</div>
                    <div field="school" width="120">毕业院校</div>
                </div>
            </div>                     
        </div>
    </div>
    </div>
 

</body>
<script type="text/javascript">

</script>
</html>
