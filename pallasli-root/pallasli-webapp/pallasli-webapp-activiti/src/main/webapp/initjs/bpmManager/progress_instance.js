(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
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
		}, {
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/workflow/instance/list.sp");
			}
		}],
		thead : [ {
			"dataIndex" : "id",
			"text" : "id"
		}, {
			"dataIndex" : "key",
			"text" : "流程key",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "流程名",
			"flex" : 1,
			"align" : "left"
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();

