Ext.define('Pallas.portal.waapp.desktop.webpage.Desktop',
		{
			extend : "Pallas.portal.waapp.framework.AbstractDesktop",
			requires : [ 'Pallas.portal.waapp.desktop.webpage.Toolbar',
					'Pallas.portal.waapp.desktop.webpage.Portal',
					'Pallas.portal.waapp.AppUtil',
					'Pallas.portal.waapp.lib.Cache',
					'Pallas.portal.waapp.lib.Context',
					'Pallas.portal.waapp.lib.Message'],
			layout : 'border',
			initComponent : function() {
				var me = this;
				Pallas.portal.waapp.AppUtil.MixkyApp = me;
				me.cwxx = Ext.create("widget.displayfield");
				me.ztxx = Ext.create("widget.displayfield");
				me.cwrq = Ext.create("widget.displayfield");
				me.ywsj = Ext.create("widget.displayfield");
				me.dksj = Ext.create("widget.displayfield");
				me.ywsj.setValue(me.ApplicationInfo.ywrq);
				me.dksj.setValue(me.ApplicationInfo.dkrq);
				var usertype = me.ApplicationInfo.usertype;
				var departmentname = me.ApplicationInfo.departmentname;
				var username = me.ApplicationInfo.username;
				var loginname = me.ApplicationInfo.loginname;
				var xsmc = (usertype == 4 ? "单位：" : "部门：");
				var sysdate = new Date();
				sysdate = Ext.util.Format.date(sysdate, 'Y年m月d日');
				me.dksj.setVisible(usertype != 4);
				var titleBbar = [ '系统日期：', sysdate, '-', me.ywsj, '-', me.dksj,
						'-', me.cwrq, '->', '-', xsmc + departmentname, '-',
						'操作员：' + username, '-', '账号:' + loginname, '-',
						me.cwxx, '-', me.ztxx ];
				me.titlebar = Ext.create("widget.panel", {
					region : 'south',
					bbar : titleBbar,
					height : 26
				});
				// 应用程序操作条
				me.toolbar = Ext.create("webpage.toolbar");
				// 桌面门户
				me.portalPanel = Ext.create("webpage.portal");
				// 内容
				me.workspace = Ext.create("widget.tabpanel", {
					enableTabScroll : true,
					bodyBorder : false,
					hideBorders : true,
					border : false,
					activeTab : 0,
					defaults : {
						border : false,
						autoScroll : true,
						bodyBorder : false
					},
					items : me.portalPanel,
				// plugins : new Ext.ux.TabCloseMenu()
				});
				var context = Pallas.portal.waapp.lib.Context;
				me.workspace.on('tabchange', function(tabpanel, panel) {
					var modulepanel = panel.items.get(0);
					context.activeApplicationKey = modulepanel.applicationkey;
					context.activeModuleKey = modulepanel.modulekey;
				});
				var centerPanel = Ext.create("widget.panel", {
					region : 'center',
					border : false,
					layout : 'fit',
					tbar : me.toolbar,
					items : me.workspace
				});
				// 桌面结构
				me.items = [ me.titlebar, centerPanel ];

				me.callParent();
				// me.view = viewport;
				// 文档窗口组管理
				me.windows = new Ext.WindowGroup();

				// 处理右键屏蔽
				Ext.getBody().on('contextmenu', function(e, el) {
					e.preventDefault();
				});
				// atwasoft.common.lib.getYwrq();
				// atwasoft.common.lib.getDkrq();
			},

			init : function() {
			},
			getManager : function() {
				return this.windows;
			},
			getWindow : function(id) {
				return this.windows.get(id);
			},
			minimizeWin : function(win) {
				win.minimized = true;
				win.hide();
			},
			removeWin : function(win) {
				me.toolbar.removeWindow(win);
			},
			appendWindow : function(win) {
				var me = this;
				win.render(Ext.getBody());

				me.toolbar.appendWindow(win);

				win.animateTarget = me.toolbar.winsButton.getEl();

				win.on({
					'minimize' : {
						fn : me.minimizeWin
					},
					'close' : {
						fn : me.removeWin
					}
				});

				return win;
			},
			createWindow : function(config, cls) {
				var me = this;
				var win = new (cls || Ext.Window)(Ext.applyIf(config || {}, {
					manager : me.windows,
					minimizable : true,
					maximizable : true
				}));
				return me.appendWindow(win);
			},
			hideAllWindow : function() {
				var me = this;
				me.getManager().each(function(win) {
					win.minimize();
				});
			},
			getCurrentModule : function() {
				var me = this;
				var panel = me.workspace.getActiveTab();
				if (panel != me.portalPanel) {
					return me.workspace.getActiveTab().items.get(0);
				}
			},
			// extend method
			showDesktop : function() {
				var me = this;
				me.hideAllWindow();
				me.workspace.setActiveTab(me.portalPanel);
			},
			// extend method
			setTheme : function(theme) {
				Ext.util.CSS.swapStyleSheet('theme', theme);
			},
			// extend method
			setBackgroundColor : function(hex) {
				var me = this;
				me.portalPanel.body.setStyle('background-color', hex);
			},
			// extend method
			setFrontColor : function(hex) {
				Ext.util.CSS.updateRule('.ux-shortcut-btn-text', 'color', hex);
			},
			// extend method
			setWallpaper : function(path) {
				var me = this;
				var notifyWin = Pallas.portal.waapp.lib.Message.showWaitMessage("正在装载墙纸...");
				var wp = new Image();
				wp.src = path;
				var task = new Ext.util.DelayedTask(verify, me);
				task.delay(200);
				var portalEl = me.portalPanel.body;
				function verify() {
					if (wp.complete) {
						task.cancel();

						notifyWin.setIconClass('x-icon-done');
						notifyWin.setTitle('装载完成');
						notifyWin.setMessage('已完成墙纸装载.');
						MixkyApp.hideNotification(notifyWin);

						portalEl.setStyle('background-image', 'url(' + wp.src
								+ ')');
					} else {
						task.delay(200);
					}
				}
			},
			// extend method
			setWallpaperPosition : function(pos) {
				var me = this;
				if (pos === "center") {
					var b = me.portalPanel.body;
					b.removeClass('wallpaper-tile');
					b.addClass('wallpaper-center');
				} else if (pos === "tile") {
					var b = me.portalPanel.body;
					b.removeClass('wallpaper-center');
					b.addClass('wallpaper-tile');
				}
			},
			// extend method
			addShortcut : function(o) {
				var me = this;
				me.portalPanel.addShortcut(o);
			},
			// extend method
			removeShortcut : function(btntype, appkey, key) {
				var me = this;
				me.portalPanel.removeShortcut(btntype, appkey, key);
			},
			// extend method
			addQuickStart : function(o) {
				var me = this;
				o.handler = function(b, e) {
					Mixky.wasoft.lib.actions.openShortcut(me.btntype,
							me.applicationkey, me.key, e);
				};
				me.toolbar.quickButton.appendButton(o);
			},
			// extend method
			removeQuickStart : function(btntype, appkey, key) {
				var me = this;
				me.toolbar.quickButton.removeButton(btntype, appkey, key);
			},
			// extend method
			addSubject : function(o) {
				var me = this;
				return me.portalPanel.addSubject(o);
			},
			// extend method
			removeSubject : function(appkey, key) {
				var me = this;
				return me.portalPanel.removeSubject(appkey, key);
			},
			getModule : function(appkey, modulekey) {
				var me = this;
				/*
				 * var win = me.getWindow('m-' + modulekey);
				 * if(Ext.isDefined(win)){ return win.items.get(0); }
				 */
				var id = 'm-' + appkey + '.' + modulekey;
				return me.workspace.getComponent(id);

			},
			closeModule : function(appkey, modulekey) {
				var me = this;
				var module = me.getModule(appkey, modulekey);
				if (module) {
					me.workspace.remove(module);
				}
			},
			// extend method
			openModule : function(appkey, modulekey) {
				var me = this;
				// var workspace = me.workspace;
				var id = 'm-' + appkey + '.' + modulekey;
				var panel = me.getModule(appkey, modulekey);
				if (!panel) {
					var module = Pallas.portal.waapp.lib.Cache.getAppModule(appkey,
							modulekey);
					if (!module) {
						alert('未定义模块 ' + appkey + '.' + modulekey);
						return;
					}
					var modulepanel = me.getModulePanel(appkey, modulekey);
					panel = me.workspace.add({
						id : id,
						title : module.title,
						layout : 'fit',
						border : false,
						closable : true,
						iconCls : module.icon,
						items : modulepanel
					});
				}
				me.workspace.setActiveTab(panel);
				return panel.items.get(0);
			},
			getAppDocument : function(appkey, documentkey, id) {
				var me = this;
				// var panelid = 'd-' + appkey + '.' + document.key
				// + '-' + id;
				// var win = me.getWindow(panelid);
				var win = me.getDocumentWindow(appkey, documentkey, id);
				if (Ext.isDefined(win)) {
					return win.items.get(0);
				}
			},
			// private method
			getDocumentWindow : function(appkey, documentkey, id) {
				var me = this;
				var docWindows = me.getManager().getBy(
						function(win) {
							if (win.isDocumentWindow) {
								var p = win.items.get(0);
								if (p.applicationkey == appkey
										&& p.documentkey == documentkey
										&& p.documentid == id) {
									return true;
								}
							}
							return false;
						});
				if (docWindows.length > 0) {
					return docWindows[0];
				}
			},
			// private method
			getDocumentWindowWithoutID : function(appkey, documentkey) {
				var me = this;
				var docWindows = me.getManager().getBy(
						function(win) {
							if (win.isDocumentWindow) {
								var p = win.items.get(0);
								if (p.applicationkey == appkey
										&& p.documentkey == documentkey) {
									return true;
								}
							}
							return false;
						});
				if (docWindows.length > 0) {
					return docWindows[0];
				}
			},
			// extend method
			_openDocument : function(appkey, document, id, params, cfg) {
				var me = this;
				// var panelid = 'd-' + appkey + '.' + document.key
				// + '-' + id;
				// var win = me.getWindow(panelid);
				var win = me.getDocumentWindow(appkey, document.key, id);
				if (!Ext.isDefined(win)) {
					var win2 = me.getDocumentWindowWithoutID(appkey,
							document.key);
					if (Ext.isDefined(win2)) {
						win2.close();
					}
					var panel = me.getDocumentPanel(appkey, document.key, id,
							params, cfg);
					win = me.createWindow(Ext.apply({
						// id : panelid,
						isDocumentWindow : true,
						title : document.title,
						iconCls : document.icon,
						layout : 'fit',
						border : false,
						maximizable : false,
						minimizable : true,
						resizable : false,
						constrain : true,
						width : 500,
						height : 500,
						modal : true,
						items : panel
					}, document.config));
				}
				win.show();
				return win.items.get(0);
			},
			_closeDocument : function(appkey, documentkey, id) {
				var me = this;
				// var panelid = 'd-' + appkey + '.' + documentkey +
				// '-' + id;
				// var win = me.getWindow(panelid);
				var win = me.getDocumentWindow(appkey, documentkey, id);
				if (Ext.isDefined(win)) {
					win.close();
				}
			},
			openWindow : function(id, config) {
				var me = this;
				var win = me.getWindow(id);
				if (!win) {
					win = me.createWindow(Ext.apply(config, {
						id : id
					}));
				}
				return win;
			},
			closeWindow : function(id) {
				var me = this;
				var win = me.getWindow(id);
				if (win) {
					win.close();
				}
			},
			closeAllWindow : function() {
				var me = this;
				me.getManager().each(function(win) {
					win.close();
				});
			},
			settitlebar : function(cwxx, ztxx, rjrq) {
				var me = this;
				me.cwxx.setValue(cwxx);
				me.ztxx.setValue(ztxx);
				me.cwrq.setValue(rjrq);
			},
			setYwrq : function(ywrq) {
				var me = this;
				me.ywsj.setValue(ywrq);
			},
			setDkrq : function(dkrq) {
				var me = this;
				me.dksj.setValue(dkrq);
			}

		});