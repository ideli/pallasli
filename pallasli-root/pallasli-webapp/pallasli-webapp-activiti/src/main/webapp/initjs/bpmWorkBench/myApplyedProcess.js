(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			xtype : "checkbox",
			boxLabel : "审批中",
			handler : function() {
			}
		}, '-',{
			xtype : "checkbox",
			boxLabel : "已办结",
			handler : function() {
			}
		}, '-',{
			xtype : "checkbox",
			boxLabel : "已退回",
			handler : function() {
			}
		}, '->',{
			text : "查看",
			handler : function() {
			}
		},  {
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [ {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "所属流程"
		}, {
			"dataIndex" : "assignee",
			"width" : 200,
			"text" : "业务信息概要"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "当前环节"
		}, {
			"dataIndex" : "assignee",
			"width" : 80,
			"text" : "待办人"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "流程发起时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "流程完成时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 80,
			"text" : "流程状态"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "流程持续时间"
		}, {
			"dataIndex" : "assignee",
			"width" : 80,
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