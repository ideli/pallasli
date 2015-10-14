(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[  { 
					xtype:"textfield",
					fieldLabel : "请输入姓名",

				},{
					text : "概况",
					handler : function() {

					}
				}, {
					text : "编辑",
					handler : function() {

					}
				}, {
					text : "模块管理",
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
			"text" : "产品代号",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "产品名称",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "产品负责人",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "测试负责人",
			"text" : "年龄",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "发布负责人",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "状态",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "产品描述",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "访问控制",
			"flex" : 1,
			"align" : "left"
		}, {
			"dataIndex" : "name",
			"text" : "分组白名单",
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
