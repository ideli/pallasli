(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[{
			text : "审批历史",
			handler : function() {
			}
		}, {
			text : "流程图",
			handler : function() {
			}
		}, {
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		} ],
		thead : [   {
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
			"text" : "业务描述概要"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "申请人"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "申请时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "办结环节"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "办结人"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "办结意见"
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();