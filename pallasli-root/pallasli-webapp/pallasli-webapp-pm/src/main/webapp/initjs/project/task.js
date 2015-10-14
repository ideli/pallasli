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
			store :  [ [ "01", "所有任务" ], [ "02", "指派给我" ],
						[ "03", "由我完成" ] ]

		},{
			xtype:"combo",
			fieldLabel : "查询分类",
			displayField : "display",
			valueField : "value",
			queryMode : 'local',
			store :  [ [ "01", "未开始" ], [ "02", "进行中" ],
						[ "03", "已完成" ], [ "04", "已关闭" ],
						[ "05", "已延期" ] ]

		}, {
					xtype:"textfield",
					fieldLabel : "请输入姓名"

				},{
					text : "分组查看",
					handler : function() {

					}
				}, {
					text : "需求变动",
					handler : function() {

					}
				}, {
					text : "导出",
					handler : function() {

					}
				}, {
					text : "统计报表",
					handler : function() {

					}
				}, {
					text : "新增任务",
					handler : function() {

					}
				}, {
					text : "完成",
					handler : function() {

					}
				}, {
					text : "关闭",
					handler : function() {

					}
				}, {
					text : "编辑",
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
			"text" : "优先级",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "所属产品",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "指派给",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "指派时间",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "解决者",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "解决时间",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "预计",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "消耗",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "剩余",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "截止",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "状态",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "相关需求",
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