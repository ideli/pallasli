Ext
		.define(
				'Pallas.portal.waapp.desktop.webpage.Toolbar',
				{
					extend : "Ext.panel.Panel",
					requires : [ 'Pallas.portal.waapp.desktop.webpage.TaskBar',
							'Pallas.portal.waapp.lib.Actions',
							'Pallas.portal.waapp.desktop.webpage.QuickBar',
							'Pallas.portal.waapp.AppUtil',
							'Pallas.portal.waapp.lib.Cache',
							'Pallas.portal.waapp.lib.Common' ],
					alias : [ "webpage.toolbar" ],
					initComponent : function() {

						this.winsButton = new Pallas.portal.waapp.desktop.webpage.TaskBar();
						this.quickButton = new Pallas.portal.waapp.desktop.webpage.QuickBar();

						this.items = this.initToolBarItems();

						this.callParent();

					},
					initMenuItems : function(parentId) {
						var menus = Pallas.portal.waapp.AppUtil.MixkyApp.userMenus;
						var items = [];
						for (var i = 0; i < menus.length; i++) {
							var menu = Pallas.portal.waapp.lib.Cache.Menus[menus[i]];
							if (!Ext.isDefined(menu)
									|| menu.parentId != parentId) {
								continue;
							}
							var menuCfg = {
								text : menu.text,
								name : menu.name,
								iconCls : menu.iconCls,
								tooltip : menu.qtip
							};
							if (parentId == 0) {
								menuCfg.iconAlign = 'top';
								if (!Ext.isDefined(menu.modulekey)
										|| menu.modulekey == '') {
									menuCfg.xtype = 'splitbutton';
								} else {
									menuCfg.handler = Pallas.portal.waapp.lib.Actions.handlerAction;
								}
							} else {
								menuCfg.handler = Pallas.portal.waapp.lib.Actions.handlerAction;
							}
							var subitems = this.initMenuItems(menu.id);
							if (subitems.length > 0) {
								menuCfg.menu = {
									items : subitems
								};
							}
							// 增加一个对按钮属性的处理机会
							Pallas.portal.waapp.lib.Common
									.handlerMenuConfig(menuCfg);

							items.push(new Ext.Action(menuCfg));
						}
						return items;
					},
					initToolBarItems : function() {
						var IMButton = new Ext.Button(
								{
									text : "在线交流",
									iconAlign : 'top',
									iconCls : 'icon-portal-menu-chat',
									handler : function() {
										// Mixky.wasoft.lib.chatroom();
										IM.openMainWin();
										return;
										DesktopDirect.accessToUserInformation(
												"1", function(result, e) {
													name = result.name;
													pawd = result.pawd;
													if ("" != name
															&& "" != pawd) {
														start(name, pawd);
													}
												});
										if (EimTest == null)
											var EimTest = {};
										/**
										 * 获取画面Dom元素对象
										 */
										EimTest.$ = function(id) {
											// alert("EimTest.$");
											return document.getElementById(id);
										};
										/**
										 * 验证IM是否安装了。
										 * 
										 */
										EimTest.isInstall = function() {
											if (EimUtil.engine == null) {
												try {
													EimUtil.engine = new ActiveXObject(
															EimUtil.ActiveName);
													Ext.Msg
															.show({
																title : '提示',
																msg : '请确认客户端已安装并且网络设置正确，客户端及配置说明请在登陆页面下载！',
																buttons : {
																	no : '确定'
																}
															});
												} catch (e) {
													// alert('IM没有安装！');
													// Ext.Msg('提示', 'IM没有安装！');
													Ext.Msg
															.show({
																title : '提示',
																msg : 'IM没有安装,请从登录页面下载IM。',
																buttons : {
																	no : '确定'
																}
															});
												}
											}
											return EimUtil.engine != null;
										};
										/**
										 * 启动客户端
										 * 
										 */
										EimTest.RunEim = function(name, pawd) {
											// var userName ="<%=userName%>";
											// var password ="<%=password%>";
											var userName = name;
											var password = pawd;
											// alert(userName);
											// alert(password);
											EimUtil.RunEim(userName, password);

										};
										function start(name, pawd) {
											EimTest.isInstall();
											EimTest.RunEim(name, pawd);
										}
										;
									}
								});
						this.menuitems = this.initMenuItems(0);
						 var workdesk = this.menuitems.shift();
						 this.menuitems.unshift( workdesk,
						 Pallas.portal.waapp.lib.Actions.ShowDesktop);
						this.menuitems.push(this.quickButton, this.winsButton,
								IMButton, Pallas.portal.waapp.lib.Actions.Exit);
						var fontsize = 66;
						var items = [ {
							xtype : 'buttongroup',
							columns : Math.round(screen.width / fontsize),
							items : this.menuitems
						} ];
						// var items = this.initMenuItems(0);
						// items.push([IMButton,this.quickButton,Pallas.portal.waapp.lib.Actions.Exit]);
						// items.push('->');
						/*
						 * items.push( { xtype : 'buttongroup', columns : 5,
						 * items:[ IMButton, //
						 * Pallas.portal.waapp.lib.Actions.ShowDesktop,
						 * this.quickButton, this.winsButton,
						 * Pallas.portal.waapp.lib.Actions.Exit ] });
						 */
						return items;
					},
					appendWindow : function(win) {
						this.winsButton.appendWindow(win);
					},
					removeWindow : function(win) {
						this.winsButton.removeWindow(win);
					}

				});