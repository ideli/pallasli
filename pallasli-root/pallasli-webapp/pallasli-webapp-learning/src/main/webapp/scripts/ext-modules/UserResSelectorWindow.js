Ext.ux.UserResSelectorWindow = function(cfg) {
	Ext.apply(this, cfg);
	this.addEvents({
				'ready' : true,
				'beforeunload' : true
			});
}

Ext.extend(Ext.ux.UserResSelectorWindow, Ext.util.Observable, {

	win : null,
	searchType : '',
	title : '',
	single : false,
	roleTitle : null,
	userTitle : null,

	/*
	 * onSubmit(type,data)
	 */
	onSubmit : null,

	show : function(cfg) {

		var self = this;

		var rolrGridTitle = '角色信息';
		var userGridTitle = '用户信息';
		if (self.roleTitle != null && self.roleTitle != '') {
			rolrGridTitle = self.roleTitle;
		}
		if (self.userTitle != null && self.userTitle != '') {
			userGridTitle = self.userTitle;
		}

		var backColour = 'background-color: #DFE8F6';

		var departmentId = -2;

		var userResultLink = BizFuse.CONTEXT_URL + '/s/user/list';
		var roleResultLink = BizFuse.CONTEXT_URL + '/s/role/dep/type/list';
		var userStore = new Ext.data.JsonStore({
					root : 'data',
					baseParams : {
						departId : -2,
						fullname : ''
					},
					totalProperty : 'totalCount',
					fields : [{
								name : "id"
							}, {
								name : "username"
							}, {
								name : "fullname"
							}, {
								name : "departmentName"
							}],
					proxy : new Ext.data.HttpProxy({
								url : userResultLink
							})
				});
		var roleStore = new Ext.data.JsonStore({
					root : 'data',
					baseParams : {
						departId : -1,
						type : self.roleTypeCode,
						name : ''
					},
					totalProperty : 'totalCount',
					fields : [{
								name : "id"
							}, {
								name : "name"
							}, {
								name : "description"
							}, {
								name : 'roleTypeName'
							}],
					proxy : new Ext.data.HttpProxy({
								url : roleResultLink
							})
				});
		var sm = new Ext.grid.CheckboxSelectionModel({
					singleSelect : self.single
				});
		var userColumns = [{
					dataIndex : "username",
					header : "用户名"
				}, {
					dataIndex : "fullname",
					header : "姓名"
				}, {
					dataIndex : "departmentName",
					header : "部门"
				}];
		userColumns = userColumns.insertAt(0, sm);
		userColumns = userColumns.insertAt(0, new Ext.grid.RowNumberer());
		var userCm = new Ext.grid.ColumnModel(userColumns);
		var roleColumns = [{
					dataIndex : "name",
					header : "名称"
				}, {
					dataIndex : "description",
					header : "备注"
				}, {
					dataIndex : 'roleTypeName',
					header : '类型'
				}];
		roleColumns = roleColumns.insertAt(0, sm);
		roleColumns = roleColumns.insertAt(0, new Ext.grid.RowNumberer());
		var roleCm = new Ext.grid.ColumnModel(roleColumns);
		var searchData;
		switch (self.searchType) {
			case 'USER' : {
				searchData = [['用户', 'USER']];
				break;
			}
			case 'ROLE' : {
				searchData = [['角色', 'ROLE']];
				break;
			}
			default : {
				searchData = [['用户', 'USER'], ['角色', 'ROLE']];
			}
		}
		var comStore = new Ext.data.SimpleStore({
					fields : ['display', 'value'],
					data : searchData
				});
		var comSearchType = new Ext.form.ComboBox({
					readOnly : true,
					store : comStore,
					valueField : 'value',
					displayField : 'display',
					mode : 'local',
					triggerAction : 'all',
					width : 60
				});

		var tbxName = new Ext.form.TextField({width : 200});
		var pagingBar = new Ext.PagingToolbar({
					pageSize : 20,
					displayInfo : true,
					beforePageText : '第',
					displayMsg : '显示 {0} - {1} 条记录，共 {2} 条记录',
					emptyMsg : BizFuse.message['data.empty']
				});
		var btnSelect = new Ext.Button({
					text : '确定',
					handler : function() {
						if (sm.getSelections().length > 0) {
							var select = comSearchType.getValue();
							var selectData = sm.getSelections();
							var type;
							switch (select) {
								case 'USER' : {
									type = 'USER';
									break;
								}
								case 'ROLE' : {
									type = 'ROLE';
									break;
								}
								default : {
									type = '';
									break;
								}
							}
							self.fireSubmit(type, selectData);
						} else {
							self.win.close();
						}
					}
				});
		var grid = new Ext.grid.GridPanel({
					store : new Ext.data.JsonStore({}),
					loadMask : new Ext.LoadMask(Ext.getBody(), {
								msg : BizFuse.message['load.wait']
							}),
					region : 'center',
					height : 350,
					title : '信息',
					bbar : pagingBar,
					margins : '-2 -2 -2 0',
					cm : new Ext.grid.ColumnModel([]),
					sm : sm,
					bodyStyle : backColour,
					viewConfig : {
						forceFit : true,
						emptyText : BizFuse.message['data.empty']
					}
				});
		var depTree = new Ext.tree.TreePanel({
					region : 'west',
					title : '部门',
					border : true,
					animCollapse : false,
					animate : false,
					split : true,
					width : 170,
					minSize : 160,
					maxSize : 400,
					collapsible : true,
					collapseMode : 'mini',
					hideCollapseTool : true,
					margins : '-1 -1 -1 -1',
					rootVisible : true,
					lines : false,
					autoScroll : true,
					collapseFirst : false,
					root : new Ext.tree.TreeNode({
								id : -2
							}),
					rootVisible : false
				});
		depTree.getSelectionModel().on('beforeselect', function(sm, node) {
					return (node.attributes.filter != null);
				});
		depTree.getSelectionModel().on('selectionchange', function(sm, node) {
					if (node) {
						var data = node.attributes;
						departmentId = data.id;
					}
				});
		var roleRoot = depTree.getRootNode();
		var node_by_all = new Ext.tree.TreeNode({
					text : '全部',
					id : -2,
					filter : 'all'
				})
		roleRoot.appendChild(node_by_all);
		var noDep = new Ext.tree.TreeNode({
					text : '未指定部门',
					id : -999,
					filter : 'no'
				});
		roleRoot.appendChild(noDep);
		var node_by_depart = new Ext.tree.AsyncTreeNode({
					text : '组织机构',
					id : '0',
					filter : 'dep',
					loader : new Ext.tree.TreeLoader({
								dataUrl : BizFuse.CONTEXT_URL
										+ "/s/department/tree/get"
							})
				});
		roleRoot.appendChild(node_by_depart);

		function loadRecords() {
			var select = comSearchType.getValue();
			var condition = tbxName.getValue();
			switch (select) {
				case 'USER' : {
					userStore.baseParams.departId = departmentId;
					userStore.baseParams.fullname = condition;
					grid.reconfigure(userStore, userCm);
					grid.setTitle('用户信息');
					pagingBar.bind(userStore);
					userStore.load({
								params : {
									start : 0,
									limit : 20
								}
							})
					break;
				}
				case 'ROLE' : {
					roleStore.baseParams.departId = departmentId;
					roleStore.baseParams.name = condition;
					grid.reconfigure(roleStore, roleCm);
					grid.setTitle(rolrGridTitle);
					pagingBar.bind(roleStore);
					roleStore.load({
								start : 0,
								limit : 20
							});
				}
				default : {
					break;
				}
			}
		}

		var _cfg = {
			layout : 'border',
			width : 650,
			height : 390,
			bodyBorder : true,
			buttonAlign : 'center',
			closeAction : 'hide',
			modal : 'true',
			items : [depTree, {
						region : 'center',
						layout : 'border',
						border : false,
						items : [{
									region : 'north',
									frame : true,
									layout : 'column',
									split : true,
									height : 40,
									border : true,
									defaults : {
										border : false
									},
									margins : '-1 -1 0 0',
									items : [{
												columnWidth : .20,
												items : comSearchType
											}, {
												columnWidth : .50,
												defaults : {
													border : false,
													anchor : '90%'
												},
												items : tbxName
											}, {
												columnWidth : .10,
												defaults : {
													border : false,
													anchor : '90%'
												},
												items : new Ext.Button({
															text : '查询',
															handler : loadRecords
														})
											}]
								}, grid]
					}],
			buttons : [btnSelect, {
						text : BizFuse.message['close.text'],
						handler : function() {
							self.win.close();
						}
					}]
		};
		if (self.searchType == 'USER' || self.searchType == 'ROLE') {
			comSearchType.disable();
		}
		this.win = new Ext.Window(_cfg);
		this.win.show();
		this.win.setTitle(self.title);
		comSearchType.setValue(searchData[0][1]);
		node_by_all.select();
		loadRecords();
	},

	fireSubmit : function(type, data) {
		if (this.onSubmit) {
			this.onSubmit(type, data);
		}
	}
});
