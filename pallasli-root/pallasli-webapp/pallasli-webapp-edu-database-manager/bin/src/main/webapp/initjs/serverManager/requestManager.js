(function() {
	var grid_toolbar = [ '清空','删除','查询条件XXXXXX','查询','刷新'];

	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		thead : [
{
	header : '激活时间',
	dataIndex : 'activetime',
	sortable : true,
	width : 130
}, {
	header : '登录账户',
	dataIndex : 'account',
	width : 80,
	sortable : true
}, {
	header : '姓名',
	dataIndex : 'username',
	width : 80,
	sortable : true
}, {
	header : '耗时(毫秒)',
	dataIndex : 'costtime',
	width : 80,
	sortable : true
},{
	header : '描述信息',
	dataIndex : 'description',
	width : 350,
	sortable : true
}, {
	header : '请求路径',
	dataIndex : 'requestpath',
	sortable : true,
	width : 200
}, {
	header : '请求方法',
	dataIndex : 'methodname',
	sortable : true,
	width : 150
},  {
	header : '事件编号',
	dataIndex : 'eventid',
	hidden : true,
	width : 120,
	sortable : true
}, {
	header : '用户ID',
	dataIndex : 'userid',
	hidden : true
}, {
	id : '_blank',
	dataIndex : '_blank'
}
		         ],
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