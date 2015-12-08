Ext
		.define(
				'Pallas.portal.waapp.lib.CwzjHandler',
				{
					requires : [ "Pallas.portal.waapp.commonapp.Preferences",
							"Pallas.portal.waapp.commonapp.Common",
							"Pallas.portal.waapp.lib.Cache",
							'Pallas.portal.waapp.AppUtil' ],
					statics : {
						// 菜单处理函数
						handlerAction : function(item, e) {
							// Mixky.lib.alertObject(item);
							if (item.name != "wacwztbg"
									&& item.name != "wacwhssz"
									&& item.name.substring(0, 4) == "wacw") {
								WacwAppDirect
										.getZtChk(
												true,
												function(result, E) {
													if (result
															&& !result.success) {
														MixkyApp.desktop
																.closeModule(
																		'wacw',
																		'wacwpzgl');
														MixkyApp.desktop
																.closeModule(
																		'wacw',
																		'wacwqmcl');
														MixkyApp.desktop
																.closeModule(
																		'wacw',
																		'wacwzbgl');
														MixkyApp.desktop
																.closeModule(
																		'wacw',
																		'wacwpjgl');
														MixkyApp.desktop
																.closeModule(
																		'wacw',
																		'wacwreport');
														MixkyApp.desktop
																.closeModule(
																		'wacw',
																		'wacwpzcx');
														var params = {
															'title' : '账套选择',
															'width' : 300,
															'height' : 280
														}
														var title = params.title;
														var width = params.width;
														var height = params.height;
														var panel = 'ztdialog';
														var url = 'app/common/ztml';
														var recordHandler = function(
																record) {
															var ztbh = record["ztbh"];
															var cznd = record["nd"]
																	+ record["yf"];
															WacwAppDirect
																	.getZtxx(
																			ztbh,
																			cznd,
																			function(
																					result,
																					e) {
																			});
														}
														var win1 = MixkyApp.desktop
																.openWindowWithJspUrl(
																		'wacw',
																		panel,
																		url,
																		{
																			title : title,
																			width : width,
																			height : height,
																			closable : false,
																			minimizable : true,
																			modal : true,
																			manager : MixkyApp.desktop
																					.getManager(),
																			bbar : [
																					{
																						text : '关闭',
																						handler : function() {
																							win1
																									.close();
																						}
																					},
																					'->',
																					{
																						text : '确定',
																						handler : function() {
																							// 获得回调参数
																							var record = win1.items
																									.get(
																											0)
																									.getRecord();

																							win1
																									.winRecordHandler(record);
																						}
																					} ]
																		}, {},
																		params);
														// 视图调用接口
														win1.winRecordHandler = function(
																record) {
															if (record
																	&& record != null) {
																recordHandler(record);
																win1.close();
															}
														}
													} else {
														this.openMenu
																.call(
																		item,
																		item.applicationKey,
																		item.name,
																		e);
													}
												});
							} else if (item.name != "wazjztbg"
									&& (item.name.substring(0, 4) == "wazj"
											|| item.name.substring(0, 4) == "mkFM" || item.name
											.substring(0, 4) == "FM_C")) {
								WazjAppDirect
										.getZtChk(
												false,
												function(result, E) {
													// alert(result.success);
													if (result
															&& !result.success) {
														var params = {
															'title' : '账套选择',
															'width' : 340,
															'height' : 280
														}
														var title = params.title;
														var width = params.width;
														var height = params.height;
														var panel = 'ztdialog';
														var url = 'app/common/zttxml';
														var recordHandler = function(
																record) {
															var ztbh = record["ztbh"];
															var cznd = record["nd"]
																	+ record["yf"];
															WazjAppDirect
																	.getZtxx(
																			ztbh,
																			cznd,
																			function(
																					result,
																					e) {
																				if (result.success) {
																					WazjAppDirect
																							.im_txjc(
																									ztbh,
																									cznd,
																									function(
																											result,
																											e) {
																										if (result.msg == 1) {
																											var win1 = MixkyApp.desktop
																													.openWindowWithJspUrl(
																															'wazj',
																															'zjdltx',
																															'app/common/dltx',
																															{
																																title : '提醒信息',
																																width : 800,
																																height : 450,
																																closable : false,
																																minimizable : true,
																																modal : true,
																																manager : MixkyApp.desktop
																																		.getManager(),
																																bbar : [
																																		'->',
																																		{
																																			text : '确定',
																																			handler : function() {
																																				// 获得回调参数
																																				win1
																																						.close();
																																			}
																																		} ]
																															});
																										}
																									});
																				}
																			});
														}
														var win1 = MixkyApp.desktop
																.openWindowWithJspUrl(
																		'wazj',
																		panel,
																		url,
																		{
																			title : title,
																			width : width,
																			height : height,
																			closable : false,
																			minimizable : true,
																			modal : true,
																			manager : MixkyApp.desktop
																					.getManager(),
																			bbar : [
																					{
																						text : '关闭',
																						handler : function() {
																							win1
																									.close();
																						}
																					},
																					'->',
																					{
																						text : '确定',
																						handler : function() {
																							// 获得回调参数
																							var record = win1.items
																									.get(
																											0)
																									.getRecord();
																							win1
																									.winRecordHandler(record);
																						}
																					} ]
																		}, {},
																		params);
														// 视图调用接口
														win1.winRecordHandler = function(
																record) {
															if (record
																	&& record != null) {
																recordHandler(record);
																win1.close();
															}
														}
													} else {
														this.openMenu
																.call(
																		item,
																		item.applicationKey,
																		item.name,
																		e);
													}
												});
							} 
						}
					}
				});