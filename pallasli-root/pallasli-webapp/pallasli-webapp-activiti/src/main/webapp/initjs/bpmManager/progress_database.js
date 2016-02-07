(function(){
	var grid = new Pallasli.panel.Grid({
		xtype : "grid",
		width:"1200",
		tbar:[ {
			text : "增加行",
			handler : function() {
			}
		},{
			text : "搜索",
			handler : function() {
				var me=grid;
				me.load("/pallasli-webapp-activiti/bpmtest/taskDoneList?userId=kermit");
			}
		}],
		thead : [  {
			"dataIndex" : "assignee",
			"width" : 100,
			"text" : "分类"
		}, {
			"dataIndex" : "assignee",
			"width" : 150,
			"text" : "编码"
		}, {
			"dataIndex" : "assignee",
			"width" : 550,
			"text" : "内容"
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "保存";
			}
		}, {
			"dataIndex" : "assignee",
			"width" : 50,
			"text" : "",
			"renderer" : function(){
				return "删除";
			}
		} ],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		
		items : [grid]
	});

})();

	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);
	Ext
			.onReady(function() {

				var panel = Ext.getCmp("${panelId}");

				var grid_columns = [];
				var grid_store = Ext.create('Ext.data.ArrayStore', {});
				var grid_toolbar = [ {
					text : " ",
					handler : function() {
					}
				} ];

				var pagePanel = Ext.create("Ext.grid.Panel", {
					width : 540,
					height : 200,
					border : false,
					region : "center",
					stripeRows : true,
					lineBreak : false,
					cellSelect : true,
					loadMask : {
						msg : '正在装载...'
					},
					tbar : grid_toolbar,
					columns : grid_columns,
					store : grid_store
				});
				panel.isLoading = false;
				var treePanel = Ext
						.create(
								"Pallas.framework.component.MenuTreePanel",
								{
									fields : [ 'text', 'urlPath',
											'menuCaption', 'menuTypeCode',
											'nodeName', 'menuTableName',
											'menuWhereSql', 'leaf', 'expanded',
											'prefixion', 'menuKey', 'tableName' ],
									directFn : DatabaseDirectAction.loadTables,
									listeners : {
										'itemclick' : function(panel, record,
												item, index, e, eOpts) {
											var tableName = record.data.menuKey;
											if (!panel.isLoading) {
												panel.isLoading = true;
												DatabaseDirectAction
														.loadColumns(
																tableName,
																function(result) {
																	Ext
																			.define(
																					'DynaticModel',
																					{
																						extend : 'Ext.data.Model',
																						fields : eval('['
																								+ result.fields
																								+ ']')
																					});
																	var gridstore = Ext
																			.create(
																					'Ext.data.JsonStore',
																					{
																						model : 'DynaticModel',
																						proxy : {
																							type : 'direct',
																							reader : {
																								root : "members.data"
																							},
																							directFn : DatabaseDirectAction.selectDataFromTable,
																							paramOrder : [
																									"tableName",
																									"columns" ],
																							extraParams : {
																								'tableName' : tableName,
																								'columns' : result.fields
																							},
																						},
																						listeners : {
																							load : function(
																									store,
																									records) {
																								store
																										.filterBy(function(
																												record,
																												id) {
																											console
																													.log(records);
																											return true;
																										});
																							}
																						}
																					});

																	pagePanel
																			.reconfigure(
																					gridstore,
																					result.columns);
																	pagePanel
																			.getStore()
																			.load();
																	panel.isLoading = false;
																});

											}

										}
									},
									region : "west",
									width : 200
								});
				var ui = Ext.create('Ext.Panel', {
					width : 500,
					border : false,
					layout : "border",
					items : [ treePanel, pagePanel ]
				});
				panel.add(ui);

			});
