(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[  '->', {
			text : "挂起",
			handler : function() {
			}
		}, {
			text : "激活",
			handler : function() {
			}
		}, {
			text : "终止",
			handler : function() {
			}
		}, {
			text : "重新发起",
			handler : function() {
			}
		}, {
			text : "重新指派",
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
			"dataIndex" : "id",
			"text" : "任务id"
		}, {
			"dataIndex" : "processDefinitionId",
			"text" : "流程定义id"
		}, {
			"dataIndex" : "processInstanceId",
			"text" : "流程实例id"
		}, {
			"dataIndex" : "taskDefinitionKey",
			"text" : "当前环节id"
		}, {
			"dataIndex" : "assignee",
			"text" : "指定处理人"
		}, {
			"dataIndex" : "category",
			"text" : "流程分类"
		}, {
			"dataIndex" : "createTime",
			"text" : "任务创建时间"
		}, {
			"dataIndex" : "delegationState",
			"text" : "代理状态"
		}, {
			"dataIndex" : "description",
			"text" : "任务描述"
		}, {
			"dataIndex" : "dueDate",
			"text" : "任务用时"
		}, {
			"dataIndex" : "executionId",
			"text" : "执行流id"
		}, {
			"dataIndex" : "formKey",
			"text" : "表单key"
		}, {
			"dataIndex" : "name",
			"text" : "当前环节名称"
		}, {
			"dataIndex" : "owner",
			"text" : "任务所有者"
		}, {
			"dataIndex" : "parentTaskId",
			"text" : "parentTaskId"
		}, {
			"dataIndex" : "priority",
			"text" : "优先级"
		}, {
			"dataIndex" : "tenantId",
			"text" : "tenantId"
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();