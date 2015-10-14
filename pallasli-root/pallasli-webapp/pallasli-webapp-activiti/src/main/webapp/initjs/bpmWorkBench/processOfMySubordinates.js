(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[  '->',{
			text : "新增行",
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
			"text" : "所属项目"
		}, {
			"dataIndex" : "assignee",
			"width" : 200,
			"text" : "所属应用"
		}, {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "分类编码"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "分类名称"
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