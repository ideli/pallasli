Ext
		.define(
				'Pallas.portal.waapp.lib.Cache',
				{
					statics : {

						favoriteStore : {},

						showFavoriteById : function(appkey, store, srckey) {

							if (!Ext
									.isDefined(Mixky.wasoft.lib.favoriteStore[srckey])) {

								var app = Mixky.wasoft.cache.Applications[appkey];

								FavoriteAppDirect
										.getUserFavorites(
												srckey,
												function(result, e) {
													if (result
															&& result.success) {
														Mixky.wasoft.lib.favoriteStore[srckey] = result.favorites;
														Mixky.wasoft.lib
																.showFavoriteById(
																		appkey,
																		store,
																		srckey);
													}
												});
							} else {
								var a = Mixky.wasoft.lib.favoriteStore[srckey];
								for (var i = 0; i < store.getCount(); i++) {
									var record = store.getAt(i);
									for (var j = 0; j < a.length; j++) {
										if (a[j] == record.get("ID")) {
											record.set("F_FAVORITE_FLAG", 1);
											record.commit();
											break;
										}
									}
								}
							}
						},

						updateFavorite : function(appkey, srckey, srcparam,
								title, value, fn) {

							var app = Mixky.wasoft.cache.Applications[appkey];
							var dt = Mixky.wasoft.lib.cache.getAppDocumentType(
									appkey, srckey);
							if (dt) {
								var a = Mixky.wasoft.lib.favoriteStore[srckey];
								FavoriteAppDirect
										.updateFavorite(
												appkey,
												dt.caption,
												srckey,
												srcparam.toString(),
												title,
												value,
												function(result, e) {
													if (result
															&& result.success) {
														if (value == 1) {
															if (Ext
																	.isDefined(a)) {
																// 更新到本地缓存
																a[a.length] = srcparam;
															}
															MixkyApp
																	.showInfoMessage(
																			title
																					+ '成功添加到收藏夹!',
																			'操作提示');
														} else {
															for (var i = 0; i < a.length; i++) {
																if (a[i] == srcparam) {
																	// 从本地缓存中移除
																	a
																			.removeAt(i);
																	break;
																}
															}
														}
														fn.call(this, true);
													} else {
														MixkyApp
																.showErrorMessage(
																		'收藏夹操作失败!',
																		'错误提示');
													}
												});
							} else {
								MixkyApp
										.showErrorMessage('未找到收藏的文档类型!', '错误提示');
							}
						},

						addFavoriteTag : function(appkey) {
							Ext.Msg
									.prompt(
											'添加收藏夹标签',
											'请输入标签名称:',
											function(btn, text) {
												if (btn == 'ok' && text != '') {

													var app = Mixky.wasoft.cache.Applications[appkey];
													var directFn = eval(app.keyPrefix
															+ 'AppDirect.submitRowForm');

													directFn(
															'mkFavorite.T_MK_APP_FAVORITE_TAG.frmFavoriteTagEdit',
															{
																F_NAME : text
															},
															function(result, e) {
																if (result
																		&& result.success) {
																	var m = MixkyApp.desktop
																			.getModule(
																					appkey,
																					'mkFavorite');
																	if (m) {
																		m = MixkyApp.desktop
																				.openModule(
																						appkey,
																						'mkFavorite');
																		m
																				.refresh(
																						true,
																						false);
																	}
																	MixkyApp.desktop
																			.openModule(
																					appkey,
																					'mkFavorite')
																			.refresh(
																					true,
																					false);
																} else {
																	MixkyApp
																			.showDirectActionFail(
																					"添加【"
																							+ text
																							+ "】标签失败",
																					result,
																					e);
																}
															})
												}
											});
						},

						addFavoriteUrl : function(appkey) {
							Ext.Msg
									.prompt(
											'添加网址收藏',
											'请输入收藏的网址:',
											function(btn, text) {
												if (btn == 'ok' && text != '') {

													var app = Mixky.wasoft.cache.Applications[appkey];

													FavoriteAppDirect
															.createFavoriteUrl(
																	appkey,
																	text,
																	function(
																			result,
																			e) {
																		if (result
																				&& result.success) {
																			MixkyApp.desktop
																					.openAppDocument(
																							appkey,
																							"mkFavorite.docFavorite",
																							result.id);
																		} else {
																			MixkyApp
																					.showDirectActionFail(
																							"添加网址收藏【"
																									+ text
																									+ "】失败",
																							result,
																							e);
																		}
																	})
												}
											});
						},

						openFavorite : function(appkey, srckey, srcparam) {

							var dt = Mixky.wasoft.lib.cache.getAppDocumentType(
									appkey, srckey);
							if (!dt) {
								var doc = Mixky.wasoft.lib.cache
										.getAppDocument(appkey, srckey);
								if (!doc) {
									MixkyApp.showErrorMessage('找不到收藏项类型【'
											+ srckey + '】定义，打开操作失败，!', '错误提示');
								} else {
									MixkyApp.desktop.openAppDocument(appkey,
											srckey, srcparam);
								}
								return;
							}
							switch (dt.type) {
							case 0: // 自定义收藏项
								if (Ext.isDefined(dt.handler)) {
									dt.handler(srcparam);
								}
								break;
							case 1: // 文档收藏项
								MixkyApp.desktop.openAppDocument(appkey,
										dt.param, srcparam);
								break;
							case 2: // URL收藏项
								var hostAddr = srcparam.trim();
								if (hostAddr.indexOf("http://") == -1
										&& hostAddr.indexOf("https://") == -1) {
									hostAddr = "http://" + hostAddr;
								}
								window.open(hostAddr);
								break;
							}
						}
					}
				});