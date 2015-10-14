(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[  '->',{
			text : "阅览",
			handler : function() {
			}
		}, '-',  {
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [ {
			"dataIndex" : "assignee",
			"width" : 300,
			"text" : "所属流程"
		}, {
			"dataIndex" : "assignee",
			"width" : 200,
			"text" : "业务信息概要"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "传阅人"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "阅览环节"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "是否已阅"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "收到时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "发起人"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "流程状态"
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();