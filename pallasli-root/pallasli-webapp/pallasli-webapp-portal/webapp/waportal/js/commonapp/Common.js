Ext
		.define(
				'Pallas.portal.waapp.commonapp.Common',
				{
					statics : {
						onlineusers : function() {

							var store = new Ext.data.DirectStore({
								directFn : OrganizationDirect.onlineusers,
								paramOrder : [ 'params' ],
								baseParams : {
									params : {}
								},
								remoteSort : false,
								root : 'results',
								totalProperty : 'totals',
								idProperty : 'id',
								fields : [ "id", "uname", "ip" ]
							});
							store.load();
							var btnExituser = new Ext.Action(
									{
										"text" : "强制退出用户",
										"handler" : function() {
											var records = grid
													.getSelectedRecords();
											if (records.length > 0) {
												var uname = records[0]
														.get('uname');
												Ext.MessageBox
														.confirm(
																'操作提示',
																'您确定要强制退出'
																		+ uname
																		+ '用户吗？',
																function(btn) {
																	if (btn == 'yes') {
																		OrganizationDirect
																				.delonlineusers(
																						uname
																								.toString(),
																						function(
																								result,
																								e) {
																							if (result
																									&& result.success) {
																								Ext.MessageBox
																										.show({
																											title : '提示',
																											msg : result.msg,
																											modal : true,
																											buttons : Ext.Msg.OK,
																											icon : Ext.Msg.INFO,
																											width : 250,
																											closable : false
																										});
																								store
																										.reload();
																							} else {
																								Ext.MessageBox
																										.show({
																											title : '提示',
																											msg : result.msg,
																											modal : true,
																											buttons : Ext.Msg.OK,
																											icon : Ext.Msg.ERROR,
																											width : 250,
																											closable : false
																										});
																							}
																						});
																	}
																});
											} else {
												Ext.MessageBox.show({
													title : '提示',
													msg : "请选择需要操作的用户记录！",
													modal : true,
													buttons : Ext.Msg.OK,
													icon : Ext.Msg.INFO,
													width : 250,
													closable : false
												});
											}
										}
									});

							var sm = new Ext.selection.RowModel({
								singleSelect : true
							});
							var buttons = [ '->', '-', btnExituser, '-' ];
							var contextmenus = [ btnExituser ];
							var grid = new Ext.grid.GridPanel({
								border : false,
								stripeRows : true,
								enableHdMenu : false,
								lineBreak : false,
								cellSelect : true,
								autoExpandColumn : 'ip',
								sm : sm,
								columns : [ new Ext.grid.RowNumberer(), {
									"id" : "uname",
									"dataIndex" : "uname",
									"header" : "用户名",
									width : 120,
									"sortable" : true
								}, {
									"id" : "ip",
									"dataIndex" : "ip",
									"header" : "ip地址"
								} ],
								store : store,
								tbar : buttons,
								ddGroup : 'grid2tree',
								maskDisabled : true,
								loadMask : {
									msg : '正在装载数据...'
								},
								contextMenu : new Ext.menu.Menu({
									items : contextmenus
								}),
								bbar : new Ext.PagingToolbar({
									displayMsg : '在线用户共有{2} 人',
									emptyMsg : '没有符合条件的数据',
									pageSize : 2000000,
									store : store,
									displayInfo : true
								}),
								viewConfig : {
									getRowClass : function(record, index) {
										return 'wasoft-grid-cell-inner';
									}
								},
								getSelectedRecords : function() {
									return this.getSelectionModel()
											.getSelections();
								}
							});
							win = new Ext.Window({
								title : '在线用户',
								width : 520,
								height : 450,
								modal : true,
								maximizable : false,
								minimizable : false,
								resizable : false,
								constrain : true,
								manager : Pallas.portal.waapp.AppUtil.MixkyApp.getManager(),
								layout : 'fit',
								items : grid
							});
							win.show();
						},
						ChangePassword : function() {
							var formPanel = new Ext.form.FormPanel(
									{
										labelWidth : 100,
										frame : true,
										defaultType : 'textfield',
										bodyStyle : "padding:10px;padding-left:15px;padding-right:15px;padding-top:40px;overflow-x:hidden",
										items : [
												{
													xtype : 'textfield',
													fieldLabel : '原密码',
													name : 'srcpassword',
													inputType : 'password',
													anchor : '100%',
													allowBlank : false,
													labelStyle : "color:red",
													height : 40
												},
												{
													xtype : 'textfield',
													fieldLabel : '新密码(必须是8至20位且包含字符和数字)',
													name : 'newpassword',
													inputType : 'password',
													anchor : '100%',
													allowBlank : false,
													labelStyle : "color:red",
													minLength : 8,
													minLengthText : '必须是8至20位且包含字符和数字',
													maxLength : 20,
													maskRe : new RegExp(
															'[0-9 | A-Z | a-z]'),
													height : 40
												}, {
													xtype : 'textfield',
													fieldLabel : '新密码确认',
													name : 'newpassword2',
													inputType : 'password',
													anchor : '100%',
													allowBlank : false,
													labelStyle : "color:red",
													height : 40
												} ]
									});

							win = new Ext.Window(
									{
										title : '修改密码',
										width : 480,
										height : 320,
										iconCls : 'icon-portal-password',
										shim : false,
										maximizable : false,
										minimizable : false,
										animCollapse : false,
										resizable : false,
										constrain : true,
										modal : true,
										layout : 'fit',
										manager : Pallas.portal.waapp.AppUtil.MixkyApp.getManager(),
										items : [ formPanel ],
										buttons : [
												{
													text : '确认',
													handler : function() {
														var form = formPanel
																.getForm();
														if (form.isValid()) {
															var srcpassword = form
																	.findField(
																			'srcpassword')
																	.getValue();
															var newpassword = form
																	.findField(
																			'newpassword')
																	.getValue();
															var newpassword2 = form
																	.findField(
																			'newpassword2')
																	.getValue();

															if (/[0-9]+/
																	.test(newpassword)
																	&& (/[a-z]+/
																			.test(newpassword) || /[A-Z]+/
																			.test(newpassword))) {
																if (newpassword == newpassword2) {
																	var notifyWin = MixkyApp
																			.showWaitMessage("正在修改用户密码...");
																	OrganizationDirect
																			.changePassword(
																					srcpassword,
																					newpassword,
																					function(
																							result,
																							e) {
																						if (result
																								&& result.success) {
																							notifyWin
																									.setIconClass('x-icon-done');
																							notifyWin
																									.setTitle('完成');
																							notifyWin
																									.setMessage('用户密码修改完毕.');
																							win
																									.close();
																						} else {
																							notifyWin
																									.setIconClass('x-icon-done');
																							notifyWin
																									.setTitle('错误');
																							notifyWin
																									.setMessage('用户密码修改失败.');
																						}
																						MixkyApp
																								.hideNotification(notifyWin);
																					});
																} else {
																	MixkyApp
																			.showErrorMessage("两次输入密码不一致");
																}
															} else {
																MixkyApp
																		.showErrorMessage('输入的密码必须是8至20位且包含字符和数字！');
																;
															}
														}
													}
												}, {
													text : '取消',
													handler : function() {
														win.close();
													}
												} ]
									});
							win.show();

							var npass = formPanel.getForm().findField(
									'newpassword');

							if (Ext.isDefined(npass)) {
								npass
										.on(
												'change',
												function(f, n, o) {
													var params = npass
															.getValue();
													if (params.toString()
															.trim() != '') {
														if (/[0-9]+/
																.test(params
																		.toString()
																		.trim())
																&& (/[a-z]+/
																		.test(params
																				.toString()
																				.trim()) || /[A-Z]+/
																		.test(params
																				.toString()
																				.trim()))) {
														} else {
															Ext.MessageBox
																	.show({
																		title : '提示',
																		msg : "'输入的密码必须含字符和数字，请重新输入！",
																		modal : true,
																		buttons : Ext.Msg.OK,
																		icon : Ext.Msg.WARNING,
																		width : 350,
																		closable : false,
																		fn : function() {
																			npass
																					.setValue('');
																			npass
																					.focus(
																							false,
																							10);
																		}
																	});
														}
													}
												});
							}
						},
						getOrganizationWindow : function(config, value, fn) {

							config = Ext.apply({
								selectMulti : true,
								selectType : 'mix',
								valueField : 'expression',
								displayField : 'f_caption',
								valueSeparator : ';'
							}, config);

							if (!Mixky.wasoft.lib.OrganizationWindow) {
								var tree = new Ext.tree.TreePanel(
										{
											region : 'center',
											autoScroll : true,
											rootVisible : false,
											root : {
												nodeType : 'async',
												text : '人员选择',
												id : 'root-organization'
											},
											loader : new Ext.tree.TreeLoader(
													{
														directFn : OrganizationDirect.getOrganizationTree1,
														paramOrder : [ 'type' ],
														baseParams : {
															type : 'mix'
														},
														preloadChildren : true
													}),
											tools : [ {
												id : 'maximize',
												qtip : '展开节点',
												handler : function() {
													tree.expandAll();
												}
											}, {
												id : 'minimize',
												qtip : '收合节点',
												handler : function() {
													tree.collapseAll();
												}
											}, {
												id : 'refresh',
												handler : function() {
													tree.refresh();
												}
											} ],
											refresh : function() {
												this.getRootNode().reload();
											}
										});

								var store = new Ext.data.DirectStore(
										{
											directFn : OrganizationDirect.getOrgUsersList,
											paramOrder : [ 'limit', 'start',
													'expression' ],
											baseParams : {
												limit : 60,
												start : 0,
												key : 'expression'
											},
											root : 'results',
											totalProperty : 'totals',
											idProperty : 'id',
											fields : [ {
												name : 'id',
												mapping : 'id'
											}, {
												name : 'f_name',
												mapping : 'f_name'
											}, {
												name : 'f_caption',
												mapping : 'f_caption'
											}, {
												name : 'expression',
												mapping : 'expression'
											} ]
										});

								// 用户列表框
								var grid = new Ext.grid.GridPanel(
										{
											region : 'east',
											width : 520,
											minSize : 200,
											maxSize : 400,
											split : true,
											autoExpandColumn : 'f_caption',
											enableHdMenu : false,
											enableColumnMove : false,
											store : store,
											columns : [
													new Ext.grid.RowNumberer(),
													{
														id : 'f_caption',
														dataIndex : 'f_caption',
														width : 130,
														header : '用户名'
													}, {
														id : 'f_name',
														dataIndex : 'f_name',
														width : 260,
														header : '客户编号'
													} ],
											bbar : new Ext.PagingToolbar(
													{
														firstText : '首页',
														lastText : '尾页',
														nextText : '下一页',
														prevText : '上一页',
														refreshText : '刷新',
														beforePageText : '第',
														afterPageText : '页，共 {0} 页',
														displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
														emptyMsg : '没有符合条件的数据',
														pageSize : 60,
														store : store,
														displayInfo : true,
														items : [
																'-',
																'每页显示:',
																new Ext.form.ComboBox(
																		{
																			editable : false,
																			triggerAction : 'all',
																			width : 50,
																			store : [
																					2,
																					10,
																					20,
																					30,
																					50,
																					100,
																					200 ],
																			value : 60,
																			listeners : {
																				'select' : function(
																						c,
																						record,
																						index) {
																					grid
																							.getBottomToolbar().pageSize = c
																							.getValue();
																					grid
																							.getBottomToolbar()
																							.changePage(
																									1);
																				}
																			}
																		}) ]
													}),
											// 装载用户列表
											loadExpression : function(
													expression) {
												this.getStore().baseParams.expression = expression;
												this.getBottomToolbar()
														.moveFirst();
											}
										});
								// 已选择组织结构对象框
								var selectedbox = new Ext.DataView(
										{
											region : 'south',
											split : true,
											height : 80,
											minSize : 50,
											maxSize : 250,
											style : 'background-color:white',
											tpl : new Ext.XTemplate(
													'<tpl for=".">',
													'<div class="user-expression-item icon-common-{type}" id="{expression}">{f_caption}</div>',
													'</tpl>',
													'<div class="x-clear"></div>'),
											selectedClass : 'x-user-expression-view-selected',
											overClass : 'x-user-expression-view-over',
											itemSelector : 'div.user-expression-item',
											multiSelect : true,
											plugins : [ new Ext.DataView.DragSelector() ],
											store : new Ext.data.JsonStore({
												idProperty : 'expression',
												fields : [ 'expression',
														'type', 'id', 'f_name',
														'f_caption' ]
											})
										});
								// 定义窗口
								var win = new Ext.Window(
										{
											manager : MixkyApp.desktop
													.getManager(),
											title : '人员选择',
											iconCls : 'icon-common-organization',
											modal : true,
											layout : 'border',
											border : false,
											buttonAlign : 'center',
											height : midheight,
											width : 780,
											maximizable : false,
											minimizable : false,
											constrain : true,
											closeAction : 'hide',
											defaults : {
												border : false
											},
											items : [ tree, grid, selectedbox ],
											buttons : [
													{
														text : '确定',
														iconCls : 'icon-common-confirm',
														handler : function() {
															var records = selectedbox
																	.getStore()
																	.getRange();
															var values = '', display = '';
															if (win.selectMulti
																	&& win.valueSeparator == '') {
																values = [];
															}
															var display = '';
															for (var i = 0; i < records.length; i++) {
																display = display
																		+ records[i]
																				.get(win.displayField)
																		+ ';';
																if (win.selectMulti
																		&& win.valueSeparator == '') {
																	values
																			.push(records[i]
																					.get(win.valueField));
																} else {
																	values = values
																			+ records[i]
																					.get(win.valueField)
																			+ win.valueSeparator;
																}
															}
															win.onSelectedFn(
																	display,
																	values,
																	records);
															win.hide();
														}
													},
													{
														text : '取消',
														iconCls : 'icon-common-cancel',
														handler : function() {
															win.hide();
														}
													} ]
										});
								// 已选择框右键菜单
								selectedbox.contextMenu = new Ext.menu.Menu({
									items : [ {
										text : '移除选择',
										iconCls : 'icon-common-delete',
										handler : function() {
											selectedbox.removeSelected();
										}
									} ]
								});
								// 选中表达式
								selectedbox.selectExpression = function(
										expression) {
									// 判断多选
									if (!win.selectMulti
											&& selectedbox.getStore()
													.getCount() > 0) {
										return;
									}
									// 服务器端解析表达式
									OrganizationDirect
											.getExpressionData(
													expression,
													win.selectType,
													function(result, e) {
														// 添加
														for (var i = 0; i < result.results.length; i++) {
															var exp = result.results[i];
															var record = selectedbox
																	.getStore()
																	.getById(
																			exp.expression);
															if (!record) {
																selectedbox
																		.getStore()
																		.loadData(
																				[ result.results[i] ],
																				true);
															}
														}
													});
								};
								// 初始化已经选中的值
								selectedbox.loadSelectedExpressions = function(
										usersexpression) {
									selectedbox.clearSelected();
									if (Ext.isDefined(usersexpression)
											&& usersexpression != '') {
										var values = '';
										if (typeof usersexpression == 'string') {
											usersexpression = usersexpression
													.split(win.valueSeparator);
										}
										for (var i = 0; i < usersexpression.length; i++) {
											if (!usersexpression[i]) {
												continue;
											}
											values = values
													+ usersexpression[i] + ';';
										}
										if (values && values != '') {
											OrganizationDirect
													.loadSelectedExpressions(
															win.valueField,
															values,
															function(result, e) {
																if (result) {
																	selectedbox
																			.getStore()
																			.loadData(
																					result.results,
																					true);
																}
															});
										}
									}
								};
								// 清空选中的用户
								selectedbox.clearSelected = function() {
									selectedbox.getStore().removeAll();
								};
								// 删除选中的用户
								selectedbox.removeSelected = function() {
									var records = selectedbox
											.getSelectedRecords();
									for (var i = 0; i < records.length; i++) {
										selectedbox.getStore().remove(
												records[i]);
									}
								};
								// 选中组织结构树
								tree
										.getSelectionModel()
										.on(
												'selectionchange',
												function(sm, node) {
													if (!node) {
														return;
													}
													var expression = node.attributes['expression'];
													if (!expression
															|| expression == '') {
														return;
													}
													grid
															.loadExpression(expression);
												});
								// 双击组织结构树
								tree
										.on(
												'dblclick',
												function(node, e) {
													var expression = node.attributes['expression'];
													if (!expression
															|| expression == '') {
														return;
													}
													if (!this.selectMulti
															&& this.selectType == 'user') {
														return;
													} else {
														selectedbox
																.selectExpression(expression);
													}
												});
								// 双击用户列表
								grid.on('rowdblclick',
										function(g, index, e) {
											var u = g.getSelectionModel()
													.getSelected();
											selectedbox.selectExpression(u
													.get('expression'));
										});
								// 双击用户列表
								selectedbox.on('dblclick', function(dv, index,
										e) {
									selectedbox.removeSelected();
								});
								// 双击用户列表
								selectedbox.on('contextmenu', function(dv,
										index, node, e) {
									dv.contextMenu.showAt(e.getXY());
								});
								win.initConfigration = function(config, value,
										fn) {
									// 装载树结构
									if (config.selectType != tree.getLoader().baseParams.type) {
										tree.getLoader().baseParams.type = config.selectType;
										tree.refresh();
									}
									// 应用参数
									Ext.apply(this, config);
									// 设置显示模式
									switch (this.selectType) {
									case 'user':
									case 'mix':
										grid.setVisible(true);
										break;
									case 'department':
									case 'role':
										grid.setVisible(false);
										break;
									}
									win.onSelectedFn = fn;
									// 装载初始值
									selectedbox.loadSelectedExpressions(value);

								};
								Mixky.wasoft.lib.OrganizationWindow = win;
							}
							Mixky.wasoft.lib.OrganizationWindow
									.initConfigration(config, value, fn);
							return Mixky.wasoft.lib.OrganizationWindow;
						}

					}
				});