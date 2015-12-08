Ext
		.define(
				'Pallas.portal.waapp.lib.Actions',
				{
					requires : [ "Pallas.portal.waapp.commonapp.Preferences",
							"Pallas.portal.waapp.commonapp.Common",
							"Pallas.portal.waapp.lib.Cache",
							'Pallas.portal.waapp.AppUtil',
							'Pallas.portal.waapp.lib.Message'],
					statics : {

						Preferences : {
						//	xtype : "menu",
							text : '桌面管理',
							scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-appman-deskman',
							 handler :function() {
								 Pallas.portal.waapp.commonapp.Preferences.Preferences();
							 }
						},

						SavePreferences : {
							//xtype : "menu",
							text : '保存门户设置',
							scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-appman-saveportal',
							handler : function() {
								var notifyWin = Pallas.portal.waapp.lib.Message
										.showWaitMessage("正在保存门户设置...");
								DesktopDirect
										.saveUserConfig(
												'',
												Pallas.portal.waapp.AppUtil.MixkyApp.userConfig,
												function(result, e) {
													if (result
															&& result.success) {
														notifyWin
																.setIconClass('x-icon-done');
														notifyWin
																.setTitle('保存完成');
														notifyWin
																.setMessage('门户设置保存完毕.');
													} else {
														notifyWin
																.setIconClass('x-icon-error');
														notifyWin
																.setTitle('保存失败');
														notifyWin
																.setMessage('门户设置保存失败.');
													}
													Pallas.portal.waapp.AppUtil.MixkyApp
															.hideNotification(notifyWin);
												});
							}
						},

						SaveAsDefaultPreferences : {
							//xtype : "menu",
							text : '保存为默认门户设置',
							scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-common-save',
							// 普通用户隐藏
							// hidden :
							// Pallas.portal.waapp.AppUtil.MixkyApp.ApplicationInfo.usertype
							// == 1,
							handler : function() {
								var notifyWin = Pallas.portal.waapp.AppUtil.MixkyApp
										.showWaitMessage("正在保存为默认设置...");
								DesktopDirect
										.saveUserConfig(
												'(default)',
												MixkyApp.userConfig,
												function(result, e) {
													if (result
															&& result.success) {
														notifyWin
																.setIconClass('x-icon-done');
														notifyWin
																.setTitle('保存完成');
														notifyWin
																.setMessage('默认门户设置保存完毕.');
													} else {
														notifyWin
																.setIconClass('x-icon-done');
														notifyWin
																.setTitle('保存失败');
														notifyWin
																.setMessage('默认门户设置保存失败.');
													}
													Pallas.portal.waapp.AppUtil.MixkyApp
															.hideNotification(notifyWin);
												});
							}
						},

						ChangePassword : {
							//xtype : "menu",
							text : '修改密码',
							scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-appman-passman',
						 handler :function(){
							 Pallas.portal.waapp.commonapp.Common.ChangePassword();
						 }
						},

						Exit : {
							//xtype : "menu",
							text : '退出系统',
							// scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-exit',
							handler : function() {
								Ext.Msg.confirm('退出警告',
										'退出系统，该操作将放弃所有未保存数据，您确定吗？', function(
												btn) {
											if (btn == 'yes') {
												/*
												 * Ext.MessageBox.show({title:'考勤提示',msg:'退出系统前您是否签退？',
												 * modal:true,buttons:Ext.Msg.YESNO,icon:Ext.Msg.WARNING,width:300,closable:false,fn:function(btn1){
												 * if(btn1 == 'yes'){
												 * KqglDirect.kqgl_qt(function(result,
												 * e){ if (result &&
												 * result.success) {
												 * Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
												 * icon:Ext.Msg.INFO,width:300,closable:false,fn:function(){
												 * tc=1; window.location =
												 * "logout.do"; }}); } else{
												 * Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
												 * icon:Ext.Msg.ERROR,width:300,closable:false,fn:function(){
												 * tc=1; window.location =
												 * "logout.do"; }}); } }) }
												 * else{ tc=1; window.location =
												 * "logout.do"; } }});
												 */
												tc = 1;
												window.location = "logout.do";
											}
										});
							}
						},

						OpenHelp : {
							//xtype : "menu",
							text : '查看帮助',
							// scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-help',
							hidden : true
						},

						OpenAdministrator : {
							//xtype : "menu",
							text : '应用管理',
							scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-appman-appman',
							// 普通用户隐藏
							// hidden :
							// Pallas.portal.waapp.AppUtil.MixkyApp.ApplicationInfo.usertype
							// == 1
							// ||
							// Pallas.portal.waapp.AppUtil.MixkyApp.ApplicationInfo.usertype
							// >= 3,
							handler : function() {
								window.open("administrator.do");
							}
						},

						ShowDesktop : {
							//xtype : "menu",
							text : '我的桌面',
							// scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-desktop',
							handler : function() {
								Pallas.portal.waapp.AppUtil.MixkyApp.showDesktop();
							}
						},

						OpenOnlineusers : {
							//xtype : "menu",
							text : '在线用户',
							scale : 'medium',
							iconAlign : 'top',
							iconCls : 'icon-portal-menu-appman-onlineuser',
						// hidden :
						// Pallas.portal.waapp.AppUtil.MixkyApp.ApplicationInfo.usertype
						// == 1
						// ||
						// Pallas.portal.waapp.AppUtil.MixkyApp.ApplicationInfo.usertype
						// >= 3,
						  handler :function(){
							   Pallas.portal.waapp.commonapp.Common.onlineusers();
						  }
						},

						getIconSizeAction : function(button, iconsize) {
							button.setIconClass(button.getIconClass()
									+ iconsize);
							return button;
						},

						// 菜单处理函数
						handlerAction : function(item, e) {
							// Mixky.lib.alertObject(item);
							if ((item.name != "wacwztbg"
									&& item.name != "wacwhssz"
									&& item.name.substring(0, 4) == "wacw") || (item.name != "wazjztbg"
									&& (item.name.substring(0, 4) == "wazj"
											|| item.name.substring(0, 4) == "mkFM" || item.name
											.substring(0, 4) == "FM_C")) ){
								Pallas.portal.waapp.lib.CwzjHandler.handlerAction();
							}else {
								Pallas.portal.waapp.lib.Actions.openMenu( item.applicationKey,
										item.name, e);
							}
						},

						// 打开菜单
						openMenu : function(appkey, key, e) {
							var menu;
							if (Ext.isDefined(appkey)) {
								menu = Pallas.portal.waapp.lib.Cache
										.getAppModuleMenu(appkey, key);
							} else {
								menu =Pallas.portal.waapp.lib.Cache.Menus[key];
								appkey = menu.applicationkey;
							}
							if (!menu) {
								return;
							}
							if (menu.handler) {
								menu.handler.call(this, e);
							} else {
								switch (menu.type) {
								case 'sysmodulegroup':
									break;
								case 'sysmodulemenu':
									if (!Ext.isDefined(appkey) || appkey == '') {
										MixkyApp.showErrorMessage('模块 ' + key
												+ ' 未指定应用标识！');
										return;
									}
									Pallas.portal.waapp.AppUtil.MixkyApp.openModule(appkey,
											menu.modulekey);
									var modulemenus = Pallas.portal.waapp.lib.Cache
											.getAppModuleMenus(appkey);
									for ( var m in modulemenus) {
										var item = modulemenus[m];
										if (item.parentId == menu.modulekey
												&& item.isdefault) {
											this.openMenu(appkey, item.name, e);
											break;
										}
									}
									break;
								case 'modulesubmenu':
								case 'modulemenu':
									var module = Pallas.portal.waapp.AppUtil.MixkyApp.openModule(
											appkey, menu.modulekey);
									if (Ext.isDefined(menu.viewurl)) {
										if (module && module.openUrl) {
											module.openUrl('v-' + appkey + '.'
													+ menu.key, menu.viewurl);
										}
									} else if (Ext.isDefined(menu.viewkey)) {
										if (module && module.openView) {
											module.openView(menu.viewkey);
										}
									}
									break;
								}
							}
						},
						// 打开快捷键
						openShortcut : function(type, appkey, key, e) {
							switch (type) {
							case 'sys':
								switch (key) {
								case 'preferences':
									this.Preferences.execute();
									break;
								case 'password':
									this.ChangePassword.execute();
									break;
								case 'help':
									this.OpenHelp.execute();
									break;
								case 'administrator':
									this.OpenAdministrator.execute();
									break;
								case 'desktop':
									this.ShowDesktop.execute();
									break;
								}
								break;
							case 'menu':
								this.openMenu(appkey, key, e);
								break;
							case 'document':
								var docparams = key.split('|');
								if (docparams.length > 1) {
									MixkyApp.desktop.openDocument(docparams[0],
											docparams[1]);
								}
								break;
							case 'folder':
								break;
							}
						}
					}
				});