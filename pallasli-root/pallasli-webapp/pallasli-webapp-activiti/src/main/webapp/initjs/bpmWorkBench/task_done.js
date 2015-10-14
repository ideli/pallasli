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
			"dataIndex" : "assignee",
			"text" : "assignee"
		}, {
			"dataIndex" : "category",
			"text" : "category"
		}, {
			"dataIndex" : "createTime",
			"text" : "createTime"
		}, {
			"dataIndex" : "delegationState",
			"text" : "delegationState"
		}, {
			"dataIndex" : "description",
			"text" : "description"
		}, {
			"dataIndex" : "dueDate",
			"text" : "dueDate"
		}, {
			"dataIndex" : "executionId",
			"text" : "executionId"
		}, {
			"dataIndex" : "formKey",
			"text" : "formKey"
		}, {
			"dataIndex" : "id",
			"text" : "id"
		}, {
			"dataIndex" : "name",
			"text" : "name"
		}, {
			"dataIndex" : "owner",
			"text" : "owner"
		}, {
			"dataIndex" : "parentTaskId",
			"text" : "parentTaskId"
		}, {
			"dataIndex" : "priority",
			"text" : "priority"
		}, {
			"dataIndex" : "processDefinitionId",
			"text" : "processDefinitionId"
		}, {
			"dataIndex" : "processInstanceId",
			"text" : "processInstanceId"
		}, {
			"dataIndex" : "taskDefinitionKey",
			"text" : "taskDefinitionKey"
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