(function() {
	var grid_toolbar = [ '清空','删除','查询条件XXXXXX','查询','刷新'];

	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		thead : [  {
		         				header : '激活时间',
				dataIndex : 'starttime',
				width : 140,
				sortable : true
			}, {
				header : '耗时(毫秒)',
				dataIndex : 'costtime',
				width : 70,
				sortable : true
			}, {
				header : '执行SQL',
				dataIndex : 'sqltext',
				id : 'sqltext',
				width : 100
			}, {
				header : '影响行数',
				dataIndex : 'effectrows',
				width : 60,
				sortable : true
			} ],
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