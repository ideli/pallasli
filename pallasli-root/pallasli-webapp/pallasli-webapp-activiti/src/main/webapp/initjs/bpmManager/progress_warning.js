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
			"width" : 100,
			"text" : "分类"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "编码"
		}, {
			"dataIndex" : "assignee",
			"width" : 550,
			"text" : "内容"
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "保存";
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

