Ext
		.define(
				'Pallas.extDesigner.waapp.utils.ActionOperate',
				{
					requires : [ "Pallas.extDesigner.waapp.utils.Actions",
							"Pallas.extDesigner.waapp.lib.Context",
							'Pallas.extDesigner.waapp.utils.DesignOperate',
							'Pallas.extDesigner.waapp.utils.ClassOperate',
							"Pallas.extDesigner.waapp.utils.AllComponents" ],
					"statics" : {

						// 设置操作是否可用
						setActionEnabled : function(cmp) {
							var actions = Pallas.extDesigner.waapp.utils.Actions;
							var designOperate = Pallas.extDesigner.waapp.utils.DesignOperate;
							var outline = Pallas.extDesigner.waapp.utils.AllComponents.outline;
							var context = Pallas.extDesigner.waapp.lib.Context;
							for (a in actions) {
								if (actions[a].initialConfig) {
									if (actions[a].initialConfig.isObjectRelated) {
										actions[a].setDisabled(true);
									}
									if (actions[a].menu) {
										actions[a].menu.items.removeAll();
									}
								}

							}
							var oid = context.activatedObject;
							if (!oid) {
								return;
							}
							// 设置通用菜单
							actions.openBtn.setDisabled(false);
							actions.refreshBtn.setDisabled(false);
							actions.appchgBtn.setDisabled(false);
							actions.importBtn.setDisabled(false);
							actions.exportBtn.setDisabled(false);
							// 清除所有添加菜单项
							var addSubMenu = Ext.menu.MenuMgr
									.get('add-submenu');
							addSubMenu.removeAll();
							// 清除所有扩展菜单项
							var extendsMenu = Ext.menu.MenuMgr
									.get('extends-menu');
							extendsMenu.removeAll();
							// 获得模块定义
							var classOperate = Pallas.extDesigner.waapp.utils.ClassOperate;
							var module = classOperate.getModule(oid.mclass);
							if (!module) {
								return;
							}
							// 设置“JSON”相关菜单
							if (module.jsonable) {
								actions.importBtn.setDisabled(false);
								actions.exportBtn.setDisabled(false);
								actions.viewJSONBtn.setDisabled(false);
							}
							// 设置“添加”菜单
							if (module.subModules
									&& module.subModules.length > 0) {
								actions.addBtn.setDisabled(false);
								for (var i = 0; i < module.subModules.length; i++) {
									var m = classOperate
											.getModule(module.subModules[i]);
									if (m != undefined) {
										addSubMenu
												.add({
													text : m.text,
													module : m,
													iconCls : m.iconCls,
													handler : function() {
														var m = this.initialConfig.module;
														designOperate
																.addDesignObject(
																		oid.key,
																		m.name,
																		function(
																				newkey,
																				mclass) {
																			if (outline
																					.refresh()) {
																				Pallas.extDesigner.waapp.lib.Context
																						.activateObject({
																							id : newkey,
																							key : newkey,
																							mclass : mclass
																						});
																			}
																			Pallas.extDesigner.waapp.utils.AllComponents.framwork
																					.openObject({
																						id : newkey,
																						key : newkey,
																						mclass : mclass
																					});
																		});
													}
												});
									}
								}
							}
							// 设置删除菜单
							if (module.deletable) {
								actions.renameBtn.setDisabled(false);
								actions.deleteBtn.setDisabled(false);
							}
							// 设置复制菜单
							if (module.copyable) {
								actions.copyBtn.setDisabled(false);
							}
							var clipboardObject = context.clipboardObject;
							// 设置粘贴菜单
							if (clipboardObject && module.subModules) {
								for (var i = 0; i < module.subModules.length; i++) {
									var subModule = module.subModules[i];
									if (subModule == 'all'
											|| clipboardObject.mclass == subModule) {
										actions.pasteBtn.setDisabled(false);
										break;
									}
								}
							}
							// 设置扩展功能菜单
							if (module.extendsMenu
									&& module.extendsMenu.length > 0) {
								actions.extendsBtn.setDisabled(false);
								extendsMenu.add(module.extendsMenu);
							}
						}

					}
				});