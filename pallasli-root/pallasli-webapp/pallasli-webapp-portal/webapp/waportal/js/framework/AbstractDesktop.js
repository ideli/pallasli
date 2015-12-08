Ext
		.define(
				'Pallas.portal.waapp.framework.AbstractDesktop',
				{
					extend : "Ext.panel.Panel",
					requires : [   'Pallas.portal.waapp.lib.Cache', 'Pallas.portal.waapp.lib.Context',"Pallas.portal.waapp.framework.Module" ],
					// 应用
					app : null,
					// 初始化桌面
					init : Ext.emptyFn,
					// 设置消息窗口显示位置
					getAnimateTarget : function() {
						return document;
					},
					getModulePanel : function(appkey, modulekey, cfg) {
						var panel;
						var id = 'mp-' + appkey + '.' + modulekey;
						var module = Pallas.portal.waapp.lib.Cache.getAppModule(
								appkey, modulekey);
						var app = Pallas.portal.waapp.lib.Cache.Applications[appkey];
						if (Ext.isDefined(module.url)) {
							panel = new Ext.Panel(Ext.apply({
								id : id,
								iconCls : module.icon,
								layout : 'fit',
								autoLoad : {
									url : app.url + '/' + module.url,
									params : {
										panelid : id,
										modulekey : modulekey
									},
									loadScripts : true,
									scripts : true
								}
							}, cfg));
							panel.applicationkey = appkey;
							panel.modulekey = modulekey;
							panel.openView = Ext.emptyFn;
							panel.openUrl = Ext.emptyFn;
						} else {
							panel = new Pallas.portal.waapp.framework.Module(Ext.apply({
								'applicationkey' : appkey,
								'modulekey' : modulekey,
								border : false
							}, cfg));
						}
						return panel;
					},
					getDocumentPanel : function(appkey, documentkey, id,
							params, cfg) {
						return new Mixky.wasoft.Document(appkey, documentkey,
								id, params, cfg);
					},
					// 设置墙纸
					setWallpaper : Ext.emptyFn,
					// 设置墙纸位置
					setWallpaperPosition : Ext.emptyFn,
					// 设置透明度
					setTransparency : Ext.emptyFn,
					// 设置背景颜色
					setBackgroundColor : Ext.emptyFn,
					// 设置前景色
					setFrontColor : Ext.emptyFn,
					// 设置样式
					setTheme : Ext.emptyFn,
					// 添加桌面菜单项
					addContextMenuItem : Ext.emptyFn,
					// 添加快捷菜单
					addShortcut : Ext.emptyFn,
					// 移除快捷菜单
					removeShortcut : Ext.emptyFn,
					// 添加快速启动按钮
					addQuickStart : Ext.emptyFn,
					// 移除快速启动按钮
					removeQuickStart : Ext.emptyFn,
					// 添加桌面栏目
					addSubject : Ext.emptyFn,
					// 移除桌面栏目
					removeSubject : Ext.emptyFn,
					// 显示桌面
					showDesktop : Ext.emptyFn,
					// 获得文档
					getAppDocument : Ext.emptyFn,
					// 获得文档
					getDocument : Ext.emptyFn,
					// 获得模块
					getDocument : function(document, id) {
						this.getAppDocument(
								Mixky.wasoft.lib.Context.activeApplicationKey,
								documentkey, id);
					},
					// 打开模块
					openModule : Ext.emptyFn,
					// 打开文档
					_openDocument : Ext.emptyFn,
					// 打开文档
					openAppDocument : function(appkey, documentkey, id, params,
							cfg) {
						if (!Ext.isDefined(appkey)) {
							alert('未指定应用标识');
							return;
						}
						if (typeof (id) == "string") {
							id = parseInt(id);
						}
						var doc = Pallas.portal.waapp.lib.Cache.getAppDocument(appkey,
								documentkey);
						if (!doc) {
							MixkyApp.showErrorMessage('打开文档失败，文档类型['
									+ documentkey + ']没有定义。');
						} else {
							if (!id || id == 0) {
								if (Ext.isDefined(doc.tablename)
										&& doc.tablename != '') {
									Mixky.wasoft.lib
											.getNewTableRecordId(
													appkey,
													doc.tablename,
													function(id) {
														MixkyApp.desktop
																.openAppDocument(
																		appkey,
																		documentkey,
																		id,
																		params,
																		cfg);
													});
								} else {
									MixkyApp.showErrorMessage('打开文档失败，文档类型['
											+ documentkey + ']没有指定数据表。');
								}
							} else {
								this
										._openDocument(appkey, doc, id, params,
												cfg);
							}
						}
					},
					openDocument : function(documentkey, id, params, cfg) {
						this.openAppDocument(
								Mixky.wasoft.lib.Context.activeApplicationKey,
								documentkey, id, params, cfg);
					},
					// 关闭文档
					_closeDocument : Ext.emptyFn,
					// 关闭文档
					closeAppDocument : function(appkey, documentkey, id) {
						this._closeDocument(appkey, documentkey, id);
					},
					closeDocument : function(documentkey, id) {
						this.closeAppDocument(
								Mixky.wasoft.lib.Context.activeApplicationKey,
								documentkey, id);
					},
					// 打开指定路径的窗口（do路径）
					openWindowWithDoUrl : function(appkey, id, dourl, wincfg,
							panelcfg, params) {
						var app = Pallas.portal.waapp.lib.Cache.Applications[appkey];
						var win = this.getWindow(id);
						if (!Ext.isDefined(win)) {
							var panel = new Ext.Panel(Ext.apply({
								id : 'p-' + id,
								layout : 'fit',
								autoLoad : {
									url : app.url + '/' + dourl,
									params : Ext.apply({
										panelid : 'p-' + id
									}, params),
									loadScripts : true,
									scripts : true
								}
							}, panelcfg));
							win = this.createWindow(Ext.apply({
								id : id,
								title : 'window - ' + dourl,
								layout : 'fit',
								border : false,
								maximizable : false,
								minimizable : true,
								resizable : false,
								constrain : true,
								width : 500,
								height : 500,
								items : panel
							}, wincfg));
						}
						win.show();
						return win;
					},
					// 打开指定路径的窗口（jsp路径）
					openWindowWithJspUrl : function(appkey, id, jspurl, wincfg,
							panelcfg, params) {
						var app = Pallas.portal.waapp.lib.Cache.Applications[appkey];
						var win = this.getWindow(id);
						if (!Ext.isDefined(win)) {
							var panel = new Ext.Panel(Ext.apply({
								id : 'p-' + id,
								layout : 'fit',
								autoLoad : {
									url : app.url + '/jsppage.do',
									params : Ext.apply({
										url : jspurl,
										panelid : 'p-' + id
									}, params),
									loadScripts : true,
									scripts : true
								}
							}, panelcfg));
							win = this.createWindow(Ext.apply({
								id : id,
								title : 'window - ' + jspurl,
								layout : 'fit',
								border : false,
								maximizable : false,
								minimizable : true,
								resizable : false,
								constrain : true,
								width : 500,
								height : 500,
								items : panel
							}, wincfg));
						}
						win.show();
						return win;
					},

					openWindow : Ext.emptyFn,

					closeWindow : Ext.emptyFn,

					closeAllWindow : Ext.emptyFn,
					// 删除文档
					deleteAppDocument : function(appkey, documentkey, id, fn) {
						if (!Ext.isDefined(appkey)) {
							alert('未指定应用标识');
							return;
						}
						if (typeof (id) == "string") {
							id = parseInt(id);
						}
						Ext.MessageBox
								.confirm(
										'危险操作提示',
										'删除文档，该操作不可恢复，您确定吗？',
										function(btn) {
											if (btn == 'yes') {
												var app = Pallas.portal.waapp.lib.Cache.Applications[appkey];
												var directFn = eval(app.keyPrefix
														+ 'AppDirect.deleteDocument');
												directFn(
														documentkey,
														id,
														function(result, e) {
															if (result
																	&& result.success) {
																// 调用回调函数
																if (Ext
																		.isDefined(fn)) {
																	fn.call();
																}
																MixkyApp.desktop
																		.closeDocument(
																				appkey,
																				documentkey,
																				id);
															} else {
																MixkyApp
																		.showErrorMessage('删除文档失败');
															}
														});
											}
										});
					},
					// 删除文档
					deleteDocument : function(documentkey, id, fn) {
						this.deleteAppDocument(
								Pallas.portal.waapp.lib.Context.activeApplicationKey,
								documentkey, id, fn);
					}

				});