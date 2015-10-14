(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[  {
					xtype:"combo",
					fieldLabel : "查询分类",
					displayField : "display",
					valueField : "value",
					queryMode : 'local',
					store :  [ [ "01", "今日安排" ], [ "02", "本周计划" ],
								[ "03", "上周总结" ], [ "04", "所以TODO" ],
								[ "05", "未完成" ] ]

				}, {
					xtype:"textfield",
					fieldLabel : "查询日期"

				}, {
					xtype:"textfield",
					fieldLabel : "请输入姓名"

				}, {
					text : "未完成",
					handler : function() {

					}
				}, {
					text : "详情",
					handler : function() {

					}
				}, {
					text : "编辑",
					handler : function() {

					}
				}, {
					text : "删除",
					handler : function() {

					}
				}, {
					text : "查询",
					handler : function() {

					}
				} ],
		thead : [ {
			"dataIndex" : "id",
			"text" : "id"
		}, {
			"dataIndex" : "key",
			"text" : "日期",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "类型",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "优先级",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "名称",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "开始",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "结束",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "状态",
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