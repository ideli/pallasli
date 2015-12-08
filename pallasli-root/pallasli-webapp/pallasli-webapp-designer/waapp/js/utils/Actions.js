Ext
		.define(
				'Pallas.extDesigner.waapp.utils.Actions',
				{
					requires : [
							"Pallas.extDesigner.waapp.utils.AllComponents",
							"Pallas.extDesigner.waapp.utils.DesignOperate",
							'Pallas.extDesigner.waapp.config.ApplicationInfo' ],
					"statics" : {
						applicationInfo : Pallas.extDesigner.waapp.config.ApplicationInfo,
						switchTheme : function() {
							var cssPath = "../dependences/ext/ext-3.2.1/resources/css/";
							if (this.text == 'defalut') {
								Ext.util.CSS.swapStyleSheet('theme', cssPath
										+ 'xcheme-blue.css');
							} else {
								Ext.util.CSS.swapStyleSheet('theme', cssPath
										+ this.text + '.css');
							}
						},
						themeBtn : new Ext.Action({
							text : '窗口风格',
							// scale : 'large',
							// iconAlign : 'top',
							iconCls : 'icon-common-skin',
							xtype : 'splitbutton',
							menu : [ {
								text : 'default',
								handler : this.switchTheme
							}, {
								text : 'black',
								handler : this.switchTheme
							}, {
								text : 'calista',
								handler : this.switchTheme
							}, {
								text : 'chocolate',
								handler : this.switchTheme
							}, {
								text : 'darkgray',
								handler : this.switchTheme
							}, {
								text : 'galdaka',
								handler : this.switchTheme
							}, {
								text : 'gray',
								handler : this.switchTheme
							}, {
								text : 'gray-extend',
								handler : this.switchTheme
							}, {
								text : 'green',
								handler : this.switchTheme
							}, {
								text : 'indigo',
								handler : this.switchTheme
							}, {
								text : 'midnight',
								handler : this.switchTheme
							}, {
								text : 'olive',
								handler : this.switchTheme
							}, {
								text : 'peppermint',
								handler : this.switchTheme
							}, {
								text : 'pink',
								handler : this.switchTheme
							}, {
								text : 'purple',
								handler : this.switchTheme
							}, {
								text : 'silverCherry',
								handler : this.switchTheme
							}, {
								text : 'slate',
								handler : this.switchTheme
							}, {
								text : 'slickness',
								handler : this.switchTheme
							}, {
								text : 'slickness2',
								handler : this.switchTheme
							} ]
						}),

						exitBtn : new Ext.Action({
							text : '退出系统',
							scale : 'large',
							// iconAlign : 'top',
							iconCls : 'icon-common-exit'
						}),
						importBtn : new Ext.Action({
							text : '导入designs',
							iconCls : 'icon-common-import',
							isObjectRelated : true,
							handler : function() {
								var dialog = new Ext.ux.UploadDialog.Dialog({
									url : 'designer/fileupload.do',
									modal : true,
									reset_on_hide : false,
									allow_close_on_upload : true,
									upload_autostart : true,
									post_var_name : 'upload'
								});
								dialog.show();
							}
						}),
						exportBtn : new Ext.Action({
							text : '导出designs',
							iconCls : 'icon-common-export',
							isObjectRelated : true,
							handler : function() {
								location.href = 'designer/filedownload.do';
							}
						}),
						viewJSONBtn : new Ext.Action({
							text : 'JSON',
							iconCls : 'icon-common-json',
							isObjectRelated : true,
						}),
						refreshBtn : new Ext.Action(
								{
									text : '刷新',
									iconCls : 'icon-common-refresh',
									isObjectRelated : true,
									handler : function() {
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var outline = allComponents.outline;
										outline.refresh();
									}
								}),
						addBtn : new Ext.Action({
							text : '添加对象',
							xtype : 'splitbutton',
							iconCls : 'icon-common-add',
							// iconAlign : 'top',
							arrowAlign : 'right',
							rowspan : 2,
							isObjectRelated : true,
							ignoreParentClicks : true,
							menu : new Ext.menu.Menu({
								id : 'add-submenu'
							})
						}),
						openBtn : new Ext.Action(
								{
									text : '打开',
									iconCls : 'icon-common-open',
									isObjectRelated : true,
									handler : function() {
										var oid = Pallas.extDesigner.waapp.lib.Context.activatedObject;
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var outline = allComponents.outline;
										var workspace = allComponents.workspace;
										if (!oid) {
											alert('未指定打开对象');
										} else {
											outline.selectObject(oid);
											workspace.openEditor(oid);
										}
									}
								}),
						renameBtn : new Ext.Action(
								{
									text : '修改键值',
									iconCls : 'icon-common-rename',
									isObjectRelated : true,
									handler : function() {
										var context = Pallas.extDesigner.waapp.lib.Context;
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var outline = allComponents.outline;
										var workspace = allComponents.workspace;
										var designOperate = Pallas.extDesigner.waapp.utils.DesignOperate;
										designOperate
												.showRenameWindow(function(
														oldKey, newKey) {
													DesignObjectDirect
															.renameObject(
																	oldKey,
																	newKey,
																	function(
																			result,
																			e) {

																		if (result
																				&& result.success) {
																			var srckey = result.oldkey;
																			var dstkey = result.newkey;
																			var mclass = result.mclass;
																			if (outline
																					.renameObject(
																							srckey,
																							dstkey)) {
																				context
																						.activateObject({
																							id : dstkey,
																							key : dstkey,
																							mclass : mclass
																						});
																				if (workspace
																						.renameObject(srckey)) {
																					MixkyApp.framework
																							.openObject({
																								id : dstkey,
																								key : dstkey,
																								mclass : mclass
																							});
																				}
																			}
																		} else {
																			alert('rename object ['
																					+ oldName
																					+ '] to ['
																					+ newName
																					+ '] failed');
																		}
																	});
												});
									}
								}),
						copyBtn : new Ext.Action(
								{
									text : '复制',
									iconCls : 'icon-common-copy',
									isObjectRelated : true,
									handler : function() {
										var context = Pallas.extDesigner.waapp.lib.Context;
										var oid = context.activatedObject;
										if (!oid) {
											alert('未指定复制对象');
											context.clipboardObject = undefined;
										} else {
											context.clipboardObject = oid;
										}
										// 刷新菜单
										Pallas.extDesigner.waapp.utils.ActionOperate
												.setActionEnabled();
									}
								}),
						pasteBtn : new Ext.Action(
								{
									text : '粘贴',
									iconCls : 'icon-common-paste',
									isObjectRelated : true,
									handler : function() {
										var context = Pallas.extDesigner.waapp.lib.Context;
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var outline = allComponents.outline;
										var framwork = allComponents.framwork;
										var srcoid = context.clipboardObject;
										var dstoid = context.activatedObject;
										if (!srcoid) {
											alert('剪贴板为空');
											return;
										}
										if (!dstoid) {
											alert('未指定粘贴对象');
											return;
										}
										Ext.Msg
												.prompt(
														'粘帖对象',
														'请输入新对象键值:',
														function(btn, newkey) {
															if (btn == 'ok') {
																DesignObjectDirect
																		.pasteObject(
																				srcoid.key,
																				dstoid.key,
																				newkey,
																				function(
																						result,
																						e) {

																					if (result
																							&& result.success) {
																						if (dstoid.mclass == srcoid.mclass) {
																							outline
																									.refreshParent();
																						} else {
																							outline
																									.refresh();
																						}
																						context
																								.activateObject({
																									id : result.key,
																									key : result.key,
																									mclass : result.mclass
																								});
																						framwork
																								.openObject({
																									id : result.key,
																									key : result.key,
																									mclass : result.mclass
																								});
																					} else {
																						alert('paste object ['
																								+ srcoid.mclass
																								+ ']['
																								+ srcoid.key
																								+ '] to '
																								+ '['
																								+ dstoid.mclass
																								+ ']['
																								+ dstoid.key
																								+ '] failed');
																					}
																				});
															}
														});
									}
								}),
						deleteBtn : new Ext.Action(
								{
									text : '删除',
									iconCls : 'icon-common-delete',
									isObjectRelated : true,
									handler : function() {
										var oid = Pallas.extDesigner.waapp.lib.Context.activatedObject;
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var framwork = allComponents.framwork;
										if (!oid) {
											alert('未指定删除对象');
										} else {
											Ext.MessageBox
													.confirm(
															'危险操作提示',
															'删除对象['
																	+ oid.key
																	+ ']，该操作不可恢复，您确定吗？',
															function(btn) {
																if (btn == 'yes') {
																	DesignObjectDirect
																			.deleteObject(
																					oid.key,
																					function(
																							result,
																							e) {

																						if (result
																								&& result.success) {
																							framwork
																									.deleteObject(result.key);
																						}
																					});
																}
															});
										}
									}
								}),
						saveBtn : new Ext.Action(
								{
									text : '保存',
									iconCls : 'icon-common-save',
									isObjectRelated : true,
									handler : function() {
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var workspace = allComponents.workspace;
										var panel = workspace.getActiveTab();
										if (!panel || panel.items.lenth == 0) {
											return false;
										}
										workspace.savePanel(
												panel.items.items[0], true);
									}
								}),
						applyBtn : new Ext.Action(
								{
									text : '应用',
									iconCls : 'icon-common-apply',
									isObjectRelated : true,
									handler : function() {
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var workspace = allComponents.workspace;
										var panel = workspace.getActiveTab();
										if (!panel || panel.items == 0) {
											return false;
										}
										if (panel.items.length == 1) {
											workspace
													.savePanel(
															panel.items.items[0],
															false);
										} else {
											workspace.savePanel(panel
													.getActiveTab(), false);
										}
									}
								}),
						buildFilesBtn : new Ext.Action({
							text : '生成文件',
							scale : 'large',
							// iconAlign : 'top',
							iconCls : 'icon-common-skin',
							xtype : 'splitbutton'
						}),
						searchBtn : new Ext.Action({
							text : '查找',
							iconCls : 'icon-common-search'
						}),
						extendsBtn : new Ext.Action({
							text : '扩展功能',
							xtype : 'splitbutton',
							iconCls : 'icon-common-extend',
							isObjectRelated : true,
							// iconAlign : 'top',
							arrowAlign : 'right',
							rowspan : 2,
							ignoreParentClicks : true,
							menu : new Ext.menu.Menu({
								id : 'extends-menu'
							})
						}),
						syncToApplicationBtn : new Ext.Action(
								{
									text : '同步更新',
									scale : 'large',
									// iconAlign : 'top',
									iconCls : 'icon-common-update',
									xtype : 'splitbutton',
									menu : [
											{
												text : '同步数据到门户',
												handler : function() {
													Ext.MessageBox
															.confirm(
																	'操作提示',
																	'将改动的数据同步到应用门户，您确定吗？',
																	function(
																			btn) {
																		if (btn == 'yes') {
																			BuilderDirect
																					.syncToApplication(function(
																							result,
																							e) {
																						if (result
																								&& result.success) {
																							MixkyApp
																									.showInfoMessage('同步数据操作成功！');
																						}
																					});
																		}
																	});
												}
											},
											{
												text : '刷新应用缓存',
												handler : function() {
													var fn = MixkyApp.appCodePrefix
															+ "DesignerDirect.refreshApplicationCache";
													fn(function(result, e) {
														if (result
																&& result.success) {
															MixkyApp
																	.showInfoMessage('刷新应用缓存操作成功！');
														}
													});
												}
											},
											{
												text : '生成应用文件',
												handler : function() {
													var fn = MixkyApp.appCodePrefix
															+ "DesignerDirect.buildApplicationFiles";
													fn(function(result, e) {
														if (result
																&& result.success) {
															MixkyApp
																	.showInfoMessage('生成应用文件操作成功！');
														}
													});
												}
											} ]
								}),
						appchgBtn : new Ext.Action(
								{
									text : '设置应用',
									iconCls : 'icon-common-extend',
									isObjectRelated : true,
									handler : function() {
										var allComponents = Pallas.extDesigner.waapp.utils.AllComponents;
										var outline = allComponents.outline;
										var applicationInfo = Pallas.extDesigner.waapp.config.ApplicationInfo;
										var appcode = {
											"xtype" : "textfield",
											"anchor" : "100%",
											"inputValue" : "",
											"selectOnFocus" : true,
											"name" : "appcode",
											"fieldLabel" : "应用标识",
											"allowBlank" : true,
											"labelStyle" : "color:red",
											"value" : applicationInfo.appcode
										};
										var apppath = {
											"xtype" : "textfield",
											"anchor" : "100%",
											"inputValue" : "",
											"selectOnFocus" : true,
											"name" : "apppath",
											"fieldLabel" : "应用路径",
											"allowBlank" : true,
											"labelStyle" : "color:red",
											"value" : applicationInfo.fileroot
										};

										var form1 = new Ext.form.FormPanel(
												{
													layout : 'form',
													border : false,
													fileUpload : true,
													trackResetOnLoad : true,
													autoScroll : true,
													bodyStyle : "padding:10px;overflow-x:hidden",
													items : [
															{
																"layout" : "form",
																"border" : false,
																labelWidth : 80,
																"columnWidth" : .70,
																"items" : appcode,
																"style" : "padding-left:10px",
																height : 50
															},
															{
																"layout" : "form",
																"border" : false,
																labelWidth : 80,
																"columnWidth" : .70,
																"items" : apppath,
																"style" : "padding-left:10px",
																height : 50
															} ]
												});

										var win = new Ext.Window(
												{
													title : '设置应用->对话框',
													modal : true,
													height : 280,
													width : 520,
													layout : 'fit',
													items : form1,
													buttonAlign : 'center',
													maximizable : false,
													minimizable : false,
													resizable : false,
													constrain : true,
													closable : false,
													manager : new Ext.WindowGroup(),
													bbar : [
															{
																text : '关闭',
																handler : function() {
																	win.close();
																}
															},
															'->',
															{
																text : '确定',
																handler : function() {
																	var v_appcode = form1
																			.getForm()
																			.findField(
																					'appcode')
																			.getValue();
																	var v_apppath = form1
																			.getForm()
																			.findField(
																					'apppath')
																			.getValue();
																	if (v_appcode
																			.trim() == "") {
																		Ext.MessageBox
																				.show({
																					title : '提示',
																					msg : "应用标识不能为空!",
																					modal : true,
																					buttons : Ext.Msg.OK,
																					icon : Ext.Msg.WARNING,
																					width : 250,
																					closable : false
																				});
																	} else if (v_apppath
																			.trim() == "") {
																		Ext.MessageBox
																				.show({
																					title : '提示',
																					msg : "应用路径不能为空!",
																					modal : true,
																					buttons : Ext.Msg.OK,
																					icon : Ext.Msg.WARNING,
																					width : 250,
																					closable : false
																				});
																	} else {
																		DesignObjectDirect
																				.chgModuleDesignObject(
																						v_appcode,
																						v_apppath,
																						function(
																								result,
																								e) {

																							if (result
																									&& result.success) {
																								outline
																										.refresh(outline
																												.getRootNode());
																								var item = {};
																								item.appcode = v_appcode;
																								item.fileroot = v_apppath;
																								Ext
																										.apply(
																												applicationInfo,
																												item);
																								win
																										.close();
																							}
																						});
																	}
																}
															} ]
												});
										win.show();
									}
								})

					}
				});