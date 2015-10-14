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
		thead : [  {
			"dataIndex" : "assignee",
			"width" : 200,
			"text" : "所属流程"
		}, {
			"dataIndex" : "assignee",
			"width" : 550,
			"text" : "业务信息摘要"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "上次存稿日期"
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "启动";
			}
		},{
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "复制";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
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
