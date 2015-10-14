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
 <div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            <span style="padding-left:5px;">名称：</span>
            <input class="mini-textbox" />
            <a class="mini-button" iconCls="icon-search" plain="true">查找</a>                  
        </div>
        <div class="mini-fit">
            <ul id="tree1" class="mini-tree" url="data/projectManager/listTree.txt" style="width:100%;height: 100%"
                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false"
                
            >        
            </ul>
        </div>
    </div>
    <div showCollapseButton="true">
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            
            <a class="mini-button" iconCls="icon-add" plain="true" onclick="addRow()">新增</a>
            <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeRow()">删除</a>     
            <span class="separator"></span>             
            <a class="mini-button" iconCls="icon-save" plain="true" onclick="saveData()">保存</a>                  
        </div>
        <div class="mini-fit" >
            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" 
                borderStyle="border:0;"
                url="jQueryMiniUI/demo/data/AjaxService.jsp?method=GetDepartmentEmployees"
                showFilterRow="true" allowCellSelect="true" allowCellEdit="true"                
            >
                <div property="columns">            
                    <div field="loginname" width="120" headerAlign="center" allowSort="true">员工帐号
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                    </div>      
                    <div field="name" width="120" headerAlign="center" allowSort="true">员工姓名                        
                        <input property="editor" class="mini-textbox" style="width:100%;"/>
                        <input id="nameFilter" property="filter" class="mini-textbox" onvaluechanged="onNameFilterChanged" style="width:100%;" />
                    </div>                
                    <div field="gender" width="100" allowSort="true" renderer="onGenderRenderer" align="center" headerAlign="center">性别
                        <input property="editor" class="mini-combobox" style="width:100%;" data="Genders"/>
                    </div>            
                    <div field="age" width="100" allowSort="true">年龄
                        <input property="editor" class="mini-spinner" minValue="0" maxValue="200" value="25" style="width:100%;"/>
                    </div>
                    <div field="birthday" width="100" allowSort="true" dateFormat="yyyy-MM-dd">出生日期
                        <input property="editor" class="mini-datepicker" style="width:100%;"/>
                    </div>                                    
                    <div field="createtime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>                
                </div>
            </div>  
        </div>
    </div>        
</div>
</body>
 <script type="text/javascript">
        mini.parse();

        var tree = mini.get("tree1");
        var grid = mini.get("grid1");

        tree.on("nodeselect", function (e) {
        
            if (e.isLeaf) {
                grid.load({ dept_id: e.node.id });
            } else {
                grid.setData([]);
                grid.setTotalCount(0);
            }
        });
        //////////////////////////////////////////////
        var Genders = [{ id: 1, text: '男' }, { id: 2, text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        function onNameFilterChanged(e) {
            var textbox = e.sender;
            var key = textbox.getValue();
            
            var node = tree.getSelectedNode();
            if (node) {
                grid.load({ dept_id: node.id, key: key });
            }
        }
        function addRow() {            
            var node = tree.getSelectedNode();
            if (node) {
                var newRow = { name: "New Row" };
                newRow.dept_id = node.id;
                grid.addRow(newRow, 0);
            }
        }
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
        function saveData() {
            var data = grid.getChanges();
            var json = mini.encode(data);
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "jQueryMiniUI/demo/data/AjaxService.jsp?method=SaveEmployees",
                data: { data: json },
                type: "post",
                success: function (text) {
                    grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
    </script>
</html>
