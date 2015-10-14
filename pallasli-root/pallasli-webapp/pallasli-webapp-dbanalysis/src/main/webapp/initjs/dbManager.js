(function() {

	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		tbar:[{text:"增加"}],
		thead : [  {
			header : '数据库类型',
			dataIndex : 'iconid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '数据库名称',
			dataIndex : 'previewpath',
			renderer : function(value) {
				//return '<img src=' + value + ' />';
			},
			width : 50
		}, {
			header : '数据库描述',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '用户名',
			dataIndex : 'filename',
			width : 150
		},{
			header : '密码',
			dataIndex : 'filename',
			width : 150
		},{
			header : '设置默认',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [ [ {
			text : "r1c1",
			cls : "active"
		}, {
			text : "r1c2"
		}, {
			text : "r1c3"
		}, {
			text : "r1c4"
		}, {
			text : "r1c5"
		}, {
			text : "r1c6"
		}, {
			text : "r1c7"
		} ], [ {
			text : "r2c1",
			cls : "success"
		}, {
			text : "r2c2"
		}, {
			text : "r2c3"
		}, {
			text : "r2c4"
		}, {
			text : "r2c5"
		}, {
			text : "r2c6"
		}, {
			text : "r2c7"
		} ], [ {
			text : "r3c1",
			cls : "info"
		}, {
			text : "r3c2"
		}, {
			text : "r3c3"
		}, {
			text : "r3c4"
		}, {
			text : "r3c5"
		}, {
			text : "r3c6"
		}, {
			text : "r3c7"
		} ], [ {
			text : "r4c1",
			cls : "danger"
		}, {
			text : "r4c2"
		}, {
			text : "r4c3"
		}, {
			text : "r4c4"
		}, {
			text : "r4c5"
		}, {
			text : "r4c6"
		}, {
			text : "r4c7"
		} ] ]
	});

	new Pallasli.window.ViewPort({
		items : [ grid ]
	});
})();