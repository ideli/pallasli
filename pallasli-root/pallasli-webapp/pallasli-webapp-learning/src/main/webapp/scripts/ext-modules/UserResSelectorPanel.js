UserResSelectorPanel = function(config) {

	var self = this;

	var backColour = 'background-color: #DFE8F6';

	var userResultLink = BizFuse.CONTEXT_URL
			+ '/ds/user-res.htm?actionLink=onUserGetLink';
	var roleResultLink = BizFuse.CONTEXT_URL
			+ '/ds/user-res.htm?actionLink=onRoleGetLink';

	var selectType = null;

	var userStore = new Ext.data.JsonStore({
				root : 'data',
				totalProperty : 'totalCount',
				fields : [{
							"name" : "id",
							"type" : "int"
						}, {
							"name" : "username"
						}, {
							"name" : "fullname"
						}, {
							"name" : "departmentName"
						}, {
							"name" : "rolesName"
						}],
				proxy : new Ext.data.HttpProxy({
							url : userResultLink
						})
			});

	var roleStore = new Ext.data.JsonStore({
				root : 'data',
				totalProperty : 'totalCount',
				fields : [{
							"name" : "id",
							"type" : "int"
						}, {
							"name" : "name"
						}, {
							"name" : "description"
						}],
				proxy : new Ext.data.HttpProxy({
							url : roleResultLink
						})
			});

	var sm = new Ext.grid.CheckboxSelectionModel({
				singleSelect : true
			});

	var userColumns = [{
				"dataIndex" : "id",
				"header" : "id",
				"hidden" : true,
				"name" : "id"
			}, {
				"dataIndex" : "username",
				"header" : "用户名",
				"name" : "username"
			}, {
				"dataIndex" : "fullname",
				"header" : "全名",
				"name" : "fullname"
			}, {
				"dataIndex" : "departmentName",
				"header" : "部门",
				"name" : "departmentName"
			}, {
				"dataIndex" : "rolesName",
				"header" : "角色",
				"name" : "rolesName"
			}];
	userColumns = userColumns.insertAt(0, sm);
	userColumns = userColumns.insertAt(0, new Ext.grid.RowNumberer());
	var userCm = new Ext.grid.ColumnModel(userColumns);

	var roleColumns = [{
				"dataIndex" : "id",
				"header" : "id",
				"hidden" : true,
				"name" : "id"
			}, {
				"dataIndex" : "name",
				"header" : "名称",
				"name" : "name"
			}, {
				"dataIndex" : "description",
				"header" : "备注",
				"name" : "description"
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
				emptyText : '请选择',
				listeners : {
					select : function() {
						var select = this.getValue();
						if (select != "") {
							if (selectType == null || selectType != select) {
								switch (select) {
									case 'USER' : {
										grid.reconfigure(userStore, userCm);
										pagingBar.bind(userStore);
										userStore.load({
													params : {
														start : 0,
														limit : 10
													}
												});
										selectType = 'USER';
										break;
									}
									case 'ROLE' : {
										grid.reconfigure(roleStore, roleCm);
										pagingBar.bind(roleStore);
										roleStore.load({
													params : {
														start : 0,
														limit : 10
													}
												});
										selectType = 'ROLE';
									}
									default :{
										break;
									}
								}
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
			});

	var btnSelect = new Ext.Button({
				text : '确定',
				handler : function() {
					if (sm.getCount() > 0) {
						var select = comSearchType.getValue();
						var selectData = sm.getSelected();
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
						return false;
					}
				}
			});

	var pagingBar = new Ext.PagingToolbar({
				pageSize : 10,
				displayInfo : true,
				displayMsg : '显示 {0} - {1} 条记录，共 {2} 条记录',
				emptyMsg : BizFuse.message['data.empty']
			});

	var grid = new Ext.grid.GridPanel({
				store : new Ext.data.JsonStore({}),
				region : 'center',
				cm : new Ext.grid.ColumnModel([]),
				sm : sm,
				autoScroll : false,
				bodyStyle : backColour,
				border : false,
				height : 335,
				buttons : [btnSelect
//						, {
//							text : '重置',
//							handler : function() {
//								self.fireSubmit(null, null);
//							}
//						}
						],
				buttonAlign : 'center',
				bbar : pagingBar,
				viewConfig : {
					forceFit : true
				}
			});

	var btnSearch = new Ext.Button({
				text : '搜索',
				width : 10,
				handler : function() {
				}
			});

	var formPanle = new Ext.Panel({
		region : 'north',
		bodyStyle : backColour,
		border : false,
		layout : 'form',
		height : 30,
		defaults : {
			bodyStyle : backColour,
			border : false
		},
		items : comSearchType
		});

	var _cfg = {
		width : 550,
		height : 370,
		border : true,
		bodyBorder : true,
		items : [formPanle, grid]
	};

	Ext.apply(_cfg, config);

	UserResSelectorPanel.superclass.constructor.call(this, _cfg);

};

Ext.extend(UserResSelectorPanel, Ext.Panel, {
			/*
			 * onSubmit(type,data)
			 */
			onSubmit : null,

			fireSubmit : function(type, data) {
				if (this.onSubmit) {
					this.onSubmit(type, data);
				}
			},
			/*
			 * searchType:'USER','ROLE'
			 */
			searchType : ''

		});