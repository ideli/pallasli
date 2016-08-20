(function() {
	var grid_toolbar = [  '杀死会话', '查询条件XXXXXX', '查询', '刷新' ];

	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		thead : [  {
			header : '用户编号',
			dataIndex : 'userid',
			width : 75,
			hidden : true,
			sortable : true
		}, {
			header : '会话创建时间',
			dataIndex : 'sessionCreatedTime',
			width : 140
		}, {
			header : '登录账户',
			dataIndex : 'account',
			width : 150
		}, {
			header : '姓名',
			dataIndex : 'username',
			width : 90
		}, {
			header : '客户端IP',
			dataIndex : 'loginIP',
			width : 100
		}, {
			header : '客户端浏览器',
			dataIndex : 'explorer',
			width : 120
		}, {
			header : '会话ID',
			dataIndex : 'sessionID',
			width : 250
		}, {
			dataIndex : '_blank',
			id : '_blank'
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