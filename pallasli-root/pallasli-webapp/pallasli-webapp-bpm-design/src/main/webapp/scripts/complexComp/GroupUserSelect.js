Ext.define('Pallas.activitiDesigner.complexComp.GroupUserSelect', {
	extend : "Ext.panel.Panel",
	alias : [ 'widget.groupuserselect' ],
	bodyPadding : 10,
	layout : "border",
	height : 400,
	autoHeight : true,
	initComponent : function() {
		var me = this;

		me.grid = new Ext.grid.GridPanel({
			title : "已选信息",
			region : "east",
			width : 300,
			store : Ext.create('Ext.data.Store', {
				fields : [],
				data : [ {} ]
			}),
			columns : [ {
				header : "类型"
			}, {
				header : "分配表达式"
			} ],
			stripeRows : true,
			clicksToEdit : 1,
			autoScroll : true,
			stateId : "x-editor-complex-grid",
			enableHdMenu : false, // Disable header menu

			listeners : {
				"rowdblclick" : function(g, index, e) {
					deleteFormSource(index);
				}
			}

		});

		var pageNumber = 20;

		me.userCm = [ new Ext.grid.RowNumberer(), {
			header : "个人编号",
			dataIndex : 'id',
			width : 120
		}, {
			header : "姓名",
			dataIndex : 'name',
			width : 280,
			sortable : true
		} ];

		me.groupCm = [ new Ext.grid.RowNumberer(), {
			header : "编号",
			dataIndex : 'id',
			width : 120
		}, {
			header : "名称",
			dataIndex : 'name',
			width : 280,
			sortable : true
		} ];
		me.departmentCm = [ new Ext.grid.RowNumberer(), {
			header : "编号",
			dataIndex : 'id',
			width : 120
		}, {
			header : "名称",
			dataIndex : 'name',
			width : 280,
			sortable : true
		} ];
		me.positionCm = [ new Ext.grid.RowNumberer(), {
			header : "编号",
			dataIndex : 'id',
			width : 120
		}, {
			header : "名称",
			dataIndex : 'name',
			width : 280,
			sortable : true
		} ];

		me.userGrid = new Ext.grid.GridPanel({
			title : "用户",
			store : Ext.create('Ext.data.Store', {
				fields : [],
				data : [ {} ]
			}),
			columns : me.userCm,
			stripeRows : true,
			autoScroll : false,
			enableHdMenu : false, // Disable header menu
			height : 470,
			listeners : {
				"rowdblclick" : function(g, index, e) {

					var r = g.store.getAt(index);

					addToTarget("candidateUsers", r.data.id);
				}
			},
			tbar : [ {
				xtype : "textfield",
				fieldLabel : "关键字",
				id : "userGrid_keyword_id"
			}, "->", {
				text : "搜索",
				handler : function() {
					me.userSearchHandler();
				}
			} ],
			bbar : new Ext.PagingToolbar({
				firstText : '首页',
				lastText : '尾页',
				nextText : '下一页',
				prevText : '上一页',
				refreshText : '刷新',
				beforePageText : '第',
				afterPageText : '页，共 {0} 页',
				displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
				emptyMsg : '没有符合条件的数据',
				store : me.userStore,
				displayInfo : true,
				pageSize : pageNumber
			})
		});
		me.groupGrid = new Ext.grid.GridPanel({
			title : "分组",
			store : Ext.create('Ext.data.Store', {
				fields : [],
				data : [ {} ]
			}),
			columns : me.groupCm,
			stripeRows : true,
			autoScroll : false,
			enableHdMenu : false, // Disable header menu
			height : 470,

			listeners : {
				"rowdblclick" : function(g, index, e) {
					var r = g.store.getAt(index);

					addToTarget("candidateGroups", r.data.id);
				}
			},
			tbar : [ {
				xtype : "textfield",
				fieldLabel : "关键字",
				id : "groupsGrid_keyword_id"
			}, "->", {
				text : "搜索",
				handler : function() {
					me.groupSearchHandler();
				}
			} ],
			bbar : new Ext.PagingToolbar({
				firstText : '首页',
				lastText : '尾页',
				nextText : '下一页',
				prevText : '上一页',
				refreshText : '刷新',
				beforePageText : '第',
				afterPageText : '页，共 {0} 页',
				displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
				emptyMsg : '没有符合条件的数据',
				store : me.groupStore,
				displayInfo : true,
				pageSize : pageNumber
			})
		});

		me.departmentGrid = new Ext.grid.GridPanel({
			title : "部门",
			store : Ext.create('Ext.data.Store', {
				fields : [],
				data : [ {} ]
			}),
			columns : me.departmentCm,
			stripeRows : true,
			autoScroll : false,
			enableHdMenu : false, // Disable header menu

			listeners : {
				"rowdblclick" : function(g, index, e) {
					var r = g.store.getAt(index);

					addToTarget("candidateGroups", r.data.id);
				}
			},
			height : 470,
			tbar : [ {
				xtype : "textfield",
				fieldLabel : "关键字",
				id : "departmentGrid_keyword_id"
			}, "->", {
				text : "搜索",
				handler : function() {

					me.departmentSearchHandler();

				}
			} ],
			bbar : new Ext.PagingToolbar({
				firstText : '首页',
				lastText : '尾页',
				nextText : '下一页',
				prevText : '上一页',
				refreshText : '刷新',
				beforePageText : '第',
				afterPageText : '页，共 {0} 页',
				displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
				emptyMsg : '没有符合条件的数据',
				store : me.departmentStore,
				displayInfo : true,
				pageSize : pageNumber
			})
		});
		me.positionGrid = new Ext.grid.GridPanel({
			title : "岗位",
			store : Ext.create('Ext.data.Store', {
				fields : [],
				data : [ {} ]
			}),
			columns : me.positionCm,
			stripeRows : true,
			autoScroll : false,
			enableHdMenu : false, // Disable header menu

			listeners : {
				"rowdblclick" : function(g, index, e) {
					var r = g.store.getAt(index);

					addToTarget("candidateGroups", r.data.id);
				}
			},
			height : 470,
			tbar : [ {
				xtype : "textfield",
				fieldLabel : "关键字",
				id : "positionGrid_keyword_id"
			}, "->", {
				text : "搜索",
				handler : function() {
					me.positionSearchHanlder();
				}
			} ],
			bbar : new Ext.PagingToolbar({
				firstText : '首页',
				lastText : '尾页',
				nextText : '下一页',
				prevText : '上一页',
				refreshText : '刷新',
				beforePageText : '第',
				afterPageText : '页，共 {0} 页',
				displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
				emptyMsg : '没有符合条件的数据',
				store : me.positionStore,
				displayInfo : true,
				pageSize : pageNumber
			})
		});

		me.tabPanel = new Ext.TabPanel({
			activeTab : 0,
			region : "center",
			enableTabScroll : false,
			animScroll : false,
			items : [ me.userGrid, me.groupGrid, me.departmentGrid,
					me.positionGrid ]
		});
		me.items = [ me.tabPanel, me.grid ];

		me.callParent();
	}
});
