Ext
		.define(
				'Pallas.portal.waapp.commonapp.Preferences',
				{
					requires:['Pallas.portal.waapp.AppUtil'],
					statics : {
						PreferencesBackground : function() {
							var store = new Ext.data.DirectStore({
								directFn : DesktopDirect.getWallpapers,
								paramOrder : [],
								root : 'results',
								totalProperty : 'totals',
								idProperty : 'path',
								fields : [ {
									name : 'id',
									mapping : 'id'
								}, {
									name : 'thumbnail',
									mapping : 'thumbnail'
								}, {
									name : 'path',
									mapping : 'path'
								}, {
									name : 'delflag',
									mapping : 'delflag'
								} ]
							});
							var tpl = new Ext.XTemplate(
									'<tpl for=".">',
									'<div class="pref-view-thumb-wrap" id="{id}">',
									'<div class="pref-view-thumb"><img src="{thumbnail}" title="{id}" /></div>',
									'<span>{shortName}</span></div>', '</tpl>',
									'<div class="x-clear"></div>');

							var view = new Ext.DataView(
									{
										autoHeight : true,
										anchor : '-20',
										emptyText : '没有可供选择的墙纸',
										itemSelector : 'div.pref-view-thumb-wrap',
										loadingText : 'loading...',
										singleSelect : true,
										overClass : 'x-view-over',
										prepareData : function(data) {
											data.shortName = Ext.util.Format
													.ellipsis(data.id, 17);
											return data;
										},
										store : store,
										tpl : tpl,
										contextMenu : new Ext.menu.Menu(
												{
													items : [ {
														text : '删除',
														iconCls : 'icon-common-delete',
														handler : function() {
															var record = view
																	.getSelectedRecords()[0];
															DesktopDirect
																	.deleteWallPaper(
																			record
																					.get('path'),
																			record
																					.get('thumbnail'),
																			function(
																					result,
																					e) {
																				if (result
																						&& result.success) {
																					store
																							.reload();
																				} else {
																					Ext.Msg
																							.alert(
																									'信息提示',
																									'删除失败！');
																				}
																			});
														}
													} ]
												}),
										listeners : {
											contextmenu : function(view, index,
													node, e) {
												var record = view.store
														.getAt(index);
												if (record.get('delflag') == true) {
													view.select(node);
													this.contextMenu.showAt(e
															.getXY());
												}
											}
										}
									});
							view.on('dblclick', function(v, index, node, e) {
								var record = v.store.getAt(index);
								if (record && record.get('path')) {
									Pallas.portal.waapp.AppUtil.MixkyApp.setWallpaper(record.get('path'));
								}
							});
							store.on('load', function(s, records) {
								if (records) {
									var t = Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.wallpaper;
									if (t) {
										view.select(t);
									}
								}
							}, this);

							var wpTile = new Ext.form.Radio(
									{
										checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.wallpaperposition == 'tile',
										name : 'wallpaperposition',
										boxLabel : '平铺方式',
										x : 15,
										y : 90
									});
							wpTile
									.on(
											'check',
											function(checkbox, checked) {
												if (checked) {
													Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.wallpaperposition = 'tile';
												}
											});
							var wpCenter = new Ext.form.Radio(
									{
										checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.wallpaperposition == 'center',
										name : 'wallpaperposition',
										boxLabel : '居中显示',
										x : 110,
										y : 90
									});
							wpCenter
									.on(
											'check',
											function(checkbox, checked) {
												if (checked) {
													Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.wallpaperposition = 'center';
												}
											});

							var transparencySlider = new Ext.Slider({
								minValue : 0,
								maxValue : 100,
								width : 100,
								value : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.transparency,
								x : 200,
								y : 40
							});

							var transparencyField = new Ext.form.NumberField({
								cls : 'x-field-percent',
								enableKeyEvents : true,
								maxValue : 100,
								minValue : 0,
								width : 45,
								value : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.transparency,
								x : 200,
								y : 70
							});

							var transparencyUpdateHandler = new Ext.util.DelayedTask(
									Pallas.portal.waapp.AppUtil.MixkyApp.setTransparency, Pallas.portal.waapp.AppUtil.MixkyApp);

							function transparencyHandler() {
								var v = transparencySlider.getValue();
								transparencyField.setValue(v);
								transparencyUpdateHandler.delay(100, null,
										null, [ v ]); // delayed task prevents
														// IE bog
							}

							transparencySlider.on({
								'change' : transparencyHandler,
								'drag' : transparencyHandler
							});

							transparencyField.on({
								'keyup' : {
									fn : function(field) {
										var v = field.getValue();
										if (v !== '' && !isNaN(v)
												&& v >= field.minValue
												&& v <= field.maxValue) {
											transparencySlider.setValue(v);
										}
									},
									buffer : 350
								}
							});

							function onChangeBackgroundColor() {
								var dialog = new Ext.ux.ColorDialog({
									border : false,
									closeAction : 'close',
									listeners : {
										'select' : {
											fn : onBackgroundColorSelect,
											scope : this,
											buffer : 350
										}
									},
									resizable : false,
									title : 'Color Picker'
								});
								dialog
										.show(Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.backgroundcolor);
							}

							function onBackgroundColorSelect(p, hex) {
								MixkyApp.setBackgroundColor(hex);
							}

							function onChangeFrontColor() {
								var dialog = new Ext.ux.ColorDialog({
									border : false,
									closeAction : 'close',
									listeners : {
										'select' : {
											fn : onFrontColorSelect,
											scope : this,
											buffer : 350
										}
									},
									resizable : false,
									title : 'Color Picker'
								});
								dialog.show(Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.frontcolor);
							}

							function onFrontColorSelect(p, hex) {
								Pallas.portal.waapp.AppUtil.MixkyApp.setFrontColor(hex);
							}

							var formPanel = new Ext.FormPanel(
									{
										border : false,
										height : 140,
										layout : 'absolute',
										items : [
												{
													border : false,
													items : {
														border : false,
														html : '选择墙纸显示方式：'
													},
													x : 15,
													y : 15
												},
												{
													border : false,
													items : {
														border : false,
														html : '<img border=0 src="waportal/images/portal/wallpaper-tile.png" width="64" height="44" border="0" alt="" />'
													},
													x : 15,
													y : 40
												},
												wpTile,
												{
													border : false,
													items : {
														border : false,
														html : '<img border=0 src="waportal/images/portal/wallpaper-center.png" width="64" height="44" border="0" alt="" />'
													},
													x : 110,
													y : 40
												},
												wpCenter/*
														 * , { border: false,
														 * items: {border:
														 * false,
														 * html:'设置任务栏透明度：'}, x:
														 * 200, y: 15 },
														 * transparencySlider,
														 * transparencyField
														 */,
												{
													border : false,
													items : {
														border : false,
														html : '设置颜色：'
													},
													x : 260,
													y : 15
												},
												new Ext.Button(
														{
															handler : onChangeFrontColor,
															text : '设置前景色',
															x : 260,
															width : 100,
															y : 50
														}),
												new Ext.Button(
														{
															handler : onChangeBackgroundColor,
															text : '设置背景色',
															width : 100,
															x : 260,
															y : 80
														}),
												new Ext.Button(
														{
															handler : function() {
																var dialog = new Ext.ux.UploadDialog.Dialog(
																		{
																			url : 'servlet/wallpaper.img.upload',
																			modal : true,
																			reset_on_hide : false,
																			allow_close_on_upload : true,
																			upload_autostart : true,
																			post_var_name : 'upload',
																			permitted_extensions : [
																					'JPG',
																					'jpg',
																					'jpeg',
																					'JPEG',
																					'GIF',
																					'gif',
																					'png',
																					'PNG' ]
																		});
																dialog
																		.on(
																				'uploadsuccess',
																				function() {
																					store
																							.reload();
																				});
																dialog.show();
															},
															text : '上传背景图片',
															width : 100,
															x : 330,
															y : 110
														}) ]
									});

							var panel = new Ext.Panel({
								title : '背景设置',
								iconCls : 'icon-sys-background',
								padding : '5px',
								items : [ {
									xtype : 'panel',
									autoScroll : true,
									height : 250,
									layout : 'anchor',
									bodyStyle : 'padding:10px',
									border : true,
									items : view
								}, formPanel ]
							});

							store.load();
							return panel;
						},
						PreferencesDesktop : function() {

							var uiWindow = new Ext.form.Radio(
									{
										boxLabel : 'Window模式',
										anchor : '60%',
										checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.uimode == 'window',
										name : 'uimodel'
									});
							uiWindow.on('check', function(checkbox, checked) {
								if (checked) {
									Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.uimode = 'window';
								}
							});
							var uiWebPage = new Ext.form.Radio(
									{
										boxLabel : '桌面操作模式',
										anchor : '40%',
										checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.uimode == 'webpage',
										name : 'uimodel'
									});
							uiWebPage.on('check', function(checkbox, checked) {
								if (checked) {
									Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.uimode = 'webpage';
								}
							});
							var desktopColumns2 = new Ext.form.Radio({
								boxLabel : '2栏',
								checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns == 2,
								name : 'columns'
							});
							desktopColumns2.on('check', function(checkbox,
									checked) {
								if (checked) {
									Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns = 2;
								}
							});
							var desktopColumns3 = new Ext.form.Radio({
								boxLabel : '3栏',
								checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns != 2
										&& Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns != 4,
								name : 'columns'
							});
							desktopColumns3.on('check', function(checkbox,
									checked) {
								if (checked) {
									Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns = 3;
								}
							});
							var desktopColumns4 = new Ext.form.Radio({
								boxLabel : '4栏',
								checked : Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns == 4,
								name : 'columns'
							});
							desktopColumns4.on('check', function(checkbox,
									checked) {
								if (checked) {
									Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns = 4;
								}
							});

							var store = new Ext.data.DirectStore({
								directFn : DesktopDirect.getDesktopStyles,
								paramOrder : [],
								root : 'results',
								totalProperty : 'totals',
								idProperty : 'path',
								fields : [ {
									name : 'id',
									mapping : 'id'
								}, {
									name : 'thumbnail',
									mapping : 'thumbnail'
								}, {
									name : 'path',
									mapping : 'path'
								} ]
							});
							var tpl = new Ext.XTemplate(
									'<tpl for=".">',
									'<div class="pref-view-thumb-wrap" id="{id}">',
									'<div class="pref-view-thumb"><img src="{thumbnail}" title="{id}" /></div>',
									'<span>{shortName}</span></div>', '</tpl>',
									'<div class="x-clear"></div>');
							var view = new Ext.DataView({
								autoHeight : true,
								anchor : '-20',
								emptyText : '没有可供选择的样式',
								itemSelector : 'div.pref-view-thumb-wrap',
								loadingText : 'loading...',
								singleSelect : true,
								overClass : 'x-view-over',
								prepareData : function(data) {
									data.shortName = Ext.util.Format.ellipsis(
											data.id, 17);
									return data;
								},
								store : store,
								tpl : tpl
							});
							view.on('selectionchange', function(v, sel) {
								if (sel.length > 0) {
									var record = v.getRecord(sel[0]);
									if (record && record.get('path')) {
										Pallas.portal.waapp.AppUtil.MixkyApp.setTheme(record.get('path'));
									}
								}
							});
							store.on('load', function(s, records) {
								if (records) {
									var t = Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.theme;
									if (t) {
										view.select(t);
									}
								}
							}, this);
							var panel = new Ext.Panel(
									{
										title : '界面设置',
										iconCls : 'icon-sys-desktopui',
										padding : '5px',
										items : [
												{
													xtype : 'fieldset',
													title : '设置桌面栏目最大列数（需刷新门户页面）',
													items : [ {
														layout : 'column',
														border : false,
														items : [ {
															columnWidth : .5,
															border : false,
															layout : 'form',
															items : {
																hideLabel : true,
																xtype : 'radiogroup',
																items : [
																		desktopColumns2,
																		desktopColumns3,
																		desktopColumns4 ]
															}
														} ]
													} ]
												}, {
													xtype : 'panel',
													autoScroll : true,
													height : 280,
													layout : 'anchor',
													bodyStyle : 'padding:10px',
													border : true,
													items : view
												} ]
									});
							store.load();
							return panel;
						},
						Preferences : function() {
							var desktop = this.PreferencesDesktop();
							var background = this
									.PreferencesBackground();
							var shortcuts = this
									.PreferencesShortcuts();
							var quickstarts = this
									.PreferencesQuickStarts();
							var subjects = this
									.PreferencesSubjects();
							win = new Ext.Window(
									{
										title : '应用参数定制',
										width : 500,
										height : 500,
										iconCls : 'icon-portal-preference',
										shim : false,
										maximizable : false,
										minimizable : false,
										animCollapse : false,
										resizable : false,
										constrain : true,
										modal : true,
										layout : 'fit',
										manager : Pallas.portal.waapp.AppUtil.MixkyApp.getManager(),
										items : [ {
											xtype : 'tabpanel',
											activeTab : 0,
											border : false,
											defaults : {
												autoScroll : true
											},
											items : [ desktop, background,
											// shortcuts,
											// quickstarts,
											subjects ]
										} ],
										buttons : [
												{
													text : '保存设置',
													iconCls : 'icon-common-save',
													handler : function() {
														Pallas.portal.waapp.lib.Actions.SavePreferences
																.handler();
													}
												},
												{
													text : '关闭',
													iconCls : 'icon-common-cancel',
													handler : function() {
														win.close();
													}
												} ]
									});
							win.show();
						},
						PreferencesQuickStarts : function() {

							var tree = new Ext.tree.TreePanel(
									{
										region : 'west',
										rootVisible : false,
										autoScroll : true,
										split : false,
										width : 200,
									/**	loader : new Ext.tree.TreeLoader(
												{
													paramOrder : [],
													directFn : DesktopDirect.getQuickStarts,
													listeners : {
														'load' : function(
																loader, node) {
															node
																	.eachChild(function(
																			child) {
																		if (MixkyApp
																				.hasQuickStart(
																						child.attributes.btntype,
																						child.attributes.applicationkey,
																						child.attributes.key)) {
																			child
																					.getUI()
																					.toggleCheck(
																							true);
																		}
																	});
														},
														'beforeload' : function(
																loader, node) {
															var key = node.attributes.key;
															if (Ext
																	.isDefined(key)) {
																try {
																	var app = Mixky.wasoft.cache.Applications[key];
																	var fn = eval(app.keyPrefix
																			+ 'AppDirect.getQuickStarts');
																	if (typeof (fn) != 'function') {
																		return false;
																		alert(app.keyPrefix
																				+ 'AppDirect.getQuickStarts is not a function');
																	}
																	loader.directFn = fn;
																} catch (e) {
																	alert('no found '
																			+ key
																			+ 'AppDirect.getQuickStarts function');
																	return false;
																}
															} else {
																loader.directFn = DesktopDirect.getQuickStarts;
															}
														}
													}
												}),**/
										listeners : {
											'checkchange' : function(node,
													checked) {
												if (node.leaf && node.id) {
													if (checked) {
														if (!MixkyApp
																.hasQuickStart(
																		node.attributes.btntype,
																		node.attributes.applicationkey,
																		node.attributes.key)) {
															MixkyApp
																	.addQuickStart({
																		text : node.attributes.text,
																		iconCls : node.attributes.iconCls,
																		btntype : node.attributes.btntype,
																		applicationkey : node.attributes.applicationkey,
																		key : node.attributes.key
																	});
														}
													} else {
														MixkyApp
																.removeQuickStart(
																		node.attributes.btntype,
																		node.attributes.applicationkey,
																		node.attributes.key);
													}
												}
												node.ownerTree.selModel
														.select(node);
											}
										},
										root : {
											id : 'root',
											text : '快捷菜单'
										}
									});

							var note = new Ext.Panel({
								region : 'center',
								border : false,
								html : '选择快捷菜单'
							});

							var panel = new Ext.Panel({
								layout : 'border',
								title : '快捷菜单',
								padding : '5px',
								border : false,
								iconCls : 'icon-portal-quickstart',
								items : [ tree, note ]
							});

							return panel;
						},
						PreferencesShortcuts : function() {

							var tree = new Ext.tree.TreePanel(
									{
										region : 'west',
										rootVisible : false,
										autoScroll : true,
										split : false,
										width : 200,
										/**loader : new Ext.tree.TreeLoader(
												{
													paramOrder : [],
													directFn : DesktopDirect.getShortcuts,
													listeners : {
														'load' : function(
																loader, node) {
															node
																	.eachChild(function(
																			child) {
																		if (MixkyApp
																				.hasShortcut(
																						child.attributes.btntype,
																						child.attributes.applicationkey,
																						child.attributes.key)) {
																			child
																					.getUI()
																					.toggleCheck(
																							true);
																		}
																	});
														},
														'beforeload' : function(
																loader, node) {
															var key = node.attributes.key;
															if (Ext
																	.isDefined(key)) {
																try {
																	var app = Mixky.wasoft.cache.Applications[key];
																	var fn = eval(app.keyPrefix
																			+ 'AppDirect.getQuickStarts');
																	if (typeof (fn) != 'function') {
																		return false;
																		alert(app.keyPrefix
																				+ 'AppDirect.getQuickStarts is not a function');
																	}
																	loader.directFn = fn;
																} catch (e) {
																	alert('no found '
																			+ key
																			+ 'AppDirect.getQuickStarts function');
																	return false;
																}
															} else {
																loader.directFn = DesktopDirect.getQuickStarts;
															}
														}
													}
												}),**/
										listeners : {
											'checkchange' : function(node,
													checked) {
												if (node.leaf && node.id) {
													if (checked) {
														if (!MixkyApp
																.hasShortcut(
																		node.attributes.btntype,
																		node.attributes.applicationkey,
																		node.attributes.key)) {
															MixkyApp
																	.addShortcut({
																		text : node.attributes.text,
																		iconCls : node.attributes.iconCls,
																		btntype : node.attributes.btntype,
																		applicationkey : node.attributes.applicationkey,
																		key : node.attributes.key
																	});
														}
													} else {
														MixkyApp
																.removeShortcut(
																		node.attributes.btntype,
																		node.attributes.applicationkey,
																		node.attributes.key);
													}
												}
												node.ownerTree.selModel
														.select(node);
											}
										},
										root : {
											id : 'root',
											text : '桌面按钮'
										}
									});

							var note = new Ext.Panel({
								region : 'center',
								border : false,
								html : '选择桌面按钮'
							});

							var panel = new Ext.Panel({
								layout : 'border',
								title : '桌面按钮',
								padding : '5px',
								border : false,
								iconCls : 'icon-portal-shortcut',
								items : [ tree, note ]
							})

							return panel;
						},
						PreferencesSubjects : function() {

							var tree = new Ext.tree.TreePanel(
									{
										region : 'west',
										rootVisible : false,
										autoScroll : true,
										split : false,
										width : 200,
										/**loader : new Ext.tree.TreeLoader(
												{
													paramOrder : [],
													directFn : DesktopDirect.getSubjects,
													listeners : {
														'load' : function(
																loader, node) {
															node
																	.eachChild(function(
																			child) {
																		if (MixkyApp
																				.hasSubject(
																						child.attributes.applicationkey,
																						child.attributes.key)) {
																			child
																					.getUI()
																					.toggleCheck(
																							true);
																		}
																	});
														},
														'beforeload' : function(
																loader, node) {
															var key = node.attributes.key;
															if (Ext
																	.isDefined(key)) {
																try {
																	var app = Mixky.wasoft.cache.Applications[key];
																	var fn = eval(app.keyPrefix
																			+ 'AppDirect.getSubjects');
																	if (typeof (fn) != 'function') {
																		return false;
																		alert(app.keyPrefix
																				+ 'AppDirect.getSubjects is not a function');
																	}
																	loader.directFn = fn;
																} catch (e) {
																	alert('no found '
																			+ key
																			+ 'AppDirect.getSubjects function');
																	return false;
																}
															} else {
																loader.directFn = DesktopDirect.getSubjects;
															}
														}
													}
												}),**/
										listeners : {
											'checkchange' : function(node,
													checked) {
												if (node.leaf && node.id) {
													if (checked) {
														if (!MixkyApp
																.hasSubject(
																		node.attributes.applicationkey,
																		node.attributes.key)) {
															MixkyApp
																	.addSubject({
																		applicationkey : node.attributes.applicationkey,
																		key : node.attributes.key,
																		text : node.attributes.text,
																		iconCls : node.attributes.iconCls,
																		width : 300,
																		height : 300,
																		webheight : 300,
																		left : 100,
																		top : 50
																	});
														}
													} else {
														MixkyApp
																.removeSubject(
																		node.attributes.applicationkey,
																		node.attributes.key);
													}
												}
												node.ownerTree.selModel
														.select(node);
											}
										},
										root : {
											id : 'root',
											text : '桌面栏目'
										}
									});

							var note = new Ext.Panel({
								region : 'center',
								border : false,
								html : '选择桌面栏目'
							});

							var panel = new Ext.Panel({
								layout : 'border',
								title : '桌面栏目',
								padding : '5px',
								border : false,
								iconCls : 'icon-portal-subject',
								items : [ tree, note ]
							})

							return panel;
						}
					}
				});