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
					store :  [ [ "01", "指派给我" ], [ "02", "由我创建" ],
								[ "03", "由我评审" ], [ "04", "由我关闭" ] ]

				},{
					text : "变更",
					handler : function() {

					}
				}, {
					text : "评审",
					handler : function() {

					}
				}, {
					text : "关闭",
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
			"text" : "需求名称",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "所属计划",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "创建者",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "创建时间",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "预计",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "状态",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "阶段",
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
