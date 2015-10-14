(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [ {
			"dataIndex" : "assignee",
			"width" : 300,
			"text" : "流程名称"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "操作类型"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "操作时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 550,
			"text" : "操作内容"
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();
