(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "增加行",
			handler : function() {
			}
		},{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [ {
			"dataIndex" : "assignee",
			"width" : 400,
			"text" : "权限描述"
		}, {
			"dataIndex" : "assignee",
			"width" : 300,
			"text" : "岗位／用户／角色／组织"
		}, {
			"dataIndex" : "assignee",
			"width" : 400,
			"text" : "涉及流程"
		}, {
			"dataIndex" : "assignee",
			"width" : 40,
			"text" : "",
			"renderer" : function(){
				return "设置";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 40,
			"text" : "",
			"renderer" : function(){
				return "删除";
			}
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();
