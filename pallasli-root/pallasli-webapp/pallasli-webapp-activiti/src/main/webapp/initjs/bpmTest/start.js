(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "启动流程",
			handler : function() {
			}
		}, {
			text : "流程图",
			handler : function() {
			}
		},{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [  {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "流程名"
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "版本号"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "版本发布时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "所属应用"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "所属分类"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "启动表单"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "启动表单权限"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "按钮配置"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "意见设置"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "催办设置"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "跳转设置"
		},{
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "流程授权"
		}, {
			"dataIndex" : "assignee",
			"width" : 250,
			"text" : "前置事件"
		}, {
			"dataIndex" : "assignee",
			"width" : 250,
			"text" : "后置事件"
		}],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();