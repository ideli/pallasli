(function() {
	var grid_toolbar = [   '新建', '编辑', '删除', '暂停', '恢复','开始','停止','查询条件','查询' ];

	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		thead : [  {
			header : '计划名称',
			dataIndex : 'iconid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '周期',
			dataIndex : 'previewpath',
			renderer : function(value) {
				//return '<img src=' + value + ' />';
			},
			width : 50
		}, {
			header : '时间',
			width : 150
		}, {
			header : '任务类名称',
			dataIndex : 'filename',
			width : 150
		},{
			header : '开始时间',
			width : 100
		},{
			header : '结束时间',
			width : 100
		},{
			header : '备注',
			width : 50
		},{
			header : '上次运行时间',
			width : 150
		},{
			header : '下次运行时间',
			width : 150
		},{
			header : '状态',
			width : 50
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