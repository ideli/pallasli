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
					store :  [ [ "01", "今天" ], [ "02", "昨天" ],
								[ "03", "本周" ], [ "04", "上周" ], [ "05", "本月" ],
								[ "06", "上月" ] ]

				}, {
					xtype:"textfield",
					fieldLabel : "开始日期",

				}, {
					xtype:"textfield",
					fieldLabel : "结束日期"

				},  {
					xtype:"textfield",
					fieldLabel : "请输入姓名"

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
			"text" : "操作者",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "动作",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "对象类型",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "对象名称",
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