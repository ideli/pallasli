(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "增加",
			handler : function() {
				document.getElementById(main.id).src="../engine/view.html?jsurl=../initjs/menuResourceManager/resource.js";

			}
		},{
			text : "修改",
			handler : function() {
			}
		},{
			text : "删除",
			handler : function() {
			}
		},{
			text : "查询",
			handler : function() {
			}
		},{
			text : "引入",
			handler : function() {
			}
		}],
		thead : [ {
			"dataIndex" : "assignee",
			"width" : 300,
			"text" : "菜单名称"
		}, {
			"dataIndex" : "assignee",
			"width" : 200,
			"text" : "请求路径"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "排序号"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "上级菜单"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "菜单类型"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "图标css类别"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "Tab导航图标"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "展开状态"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "桌面图标文件"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "桌面弹窗宽度"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "桌面弹窗高度"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "桌面弹窗滚动条"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "菜单编号"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "备注"
		} ],
		datastore : [ ]
	});
	
	var main = new Pallasli.window.IFrame({
		name : "workPage",
		xtype : "iframe"
	});

	new Pallasli.window.ViewPort({
		items : [ grid, main ]
	}); 

})();

