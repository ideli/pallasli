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
                        <a class="mini-button" iconCls="icon-add" onclick="add()">选择产品</a>
                        <a class="mini-button" iconCls="icon-add" onclick="add()">选择项目</a>
                        <a class="mini-button" iconCls="icon-add" onclick="add()">按模块浏览</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">全部需求</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">更多搜索</a>
                    </td>
                    <td style="white-space:nowrap;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">导出</a>
                        <a class="mini-button" iconCls="icon-add" onclick="add()">统计报表</a>
                        <a class="mini-button" iconCls="icon-add" onclick="add()">新增需求</a>
                        <input id="key" class="mini-textbox" emptyText="请输入姓名" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div class="mini-fit" >
       <div class="mini-panel" title="产品模块" bodyStyle="padding:0px;" style="height:100%;width: 20%;float: left;"  regrion="west">
   		 <ul id="tree1" class="mini-tree" url="data/projectManager/tree.txt" style="width:100%;height:100%;"
   		  expandOnLoad=1 
                    showTreeIcon="true" textField="text" idField="id" value="base" expandOnNodeClick="true">        
                </ul>
	</div>

    <div id="datagrid1" class="mini-datagrid"  style="height:100%;width: 80%;float: left;" allowResize="true"
        url="../data/AjaxService.jsp?method=SearchEmployees"  idField="id" multiSelect="true"  >
        <div property="columns" >
            <!--<div type="indexcolumn"></div>        -->
            <div type="checkcolumn" ></div>        
            <div field="loginname" width="40" headerAlign="center" allowSort="true">id</div>   
            <div field="name" width="40" headerAlign="center" allowSort="true">优先级</div>     
            <div field="name" width="40" headerAlign="center" allowSort="true">需求名称</div>     
            <div field="name" width="120" headerAlign="center" allowSort="true">计划</div>     
            <div field="name" width="120" headerAlign="center" allowSort="true">创建</div>  
            <div field="name" width="320" headerAlign="center" allowSort="true">指派给</div>   
            <div field="name" width="120" headerAlign="center" allowSort="true">预计</div>   
            <div field="name" width="40" headerAlign="center" allowSort="true">状态</div>   
            <div field="name" width="40" headerAlign="center" allowSort="true">阶段</div>  
              <div header="操作">
                <div property="columns">
                    <div field="dept_name" width="40">变更</div>
                    <div field="position_name" width="40">评审</div>
                    <div field="position_name" width="40">编辑</div>
                </div>
            </div>  
                              
        </div>
    </div>
    </div>
 

</body>
<script type="text/javascript">

</script>
</html>
