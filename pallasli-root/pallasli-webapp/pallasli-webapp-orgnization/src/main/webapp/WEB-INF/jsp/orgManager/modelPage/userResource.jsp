var cm_part = [ {
									header : '授权',
									dataIndex : 'partauthtype',
									width : 80,

								}, {
									header : '元素Dom标识',
									dataIndex : 'cmpid',
									width : 130

								}, {
									header : '元素类型',
									dataIndex : 'cmptype',
									width : 100,
								}, {
									header : '托管页面功能菜单',
									dataIndex : 'menuname',
									width : 160
								}, {
									header : '元素编号',
									dataIndex : 'partid',
								}, {
									header : '元素描述',
									dataIndex : 'remark',
									width : 200
								}, {
									header : '菜单编号',
									dataIndex : 'menuid',
								}, {
									header : '人员编号',
									dataIndex : 'userid',
								}, {
									header : '授权编号',
									dataIndex : 'authorizeid',
								} ];
								var gridStoreFields = [];
								Ext.define('GridStoreModel', {
									extend : 'Ext.data.Model',
									fields : gridStoreFields

								});
								var grid_store = Ext.create(
										'Ext.data.ArrayStore', {
											model : 'GridStoreModel',
										});
								var grid_part = Ext
										.create(
												"Ext.grid.Panel",
												{
													title : '<span class="commoncss">托管UI元素列表</span>',
													height : 500,
													autoScroll : true,
													store : grid_store,
													region : 'center',
													columns : cm_part,
													tbar : [
															{
																text : '保存',
																iconCls : 'acceptIcon',
																id : 'id_save',
																handler : function() {
																	if (runMode == '0') {
																		Ext.Msg
																				.alert(
																						'提示',
																						'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
																		return;
																	}
																	saveData();
																}
															},
															'-',
															{
																text : '刷新',
																iconCls : 'arrow_refreshIcon',
																handler : function() {
																	store_part
																			.reload();
																}
															}, '-',
															'提示:挂起状态=取消授权' ]
												});

								var menuTreePanel = new Ext.tree.TreePanel(
										{

											title : '<span class="commoncss">功能菜单</span>',
											region : 'west',
											split : true,
											width : 220,
											minSize : 180,
											maxSize : 300,
											collapsible : true,
											autoScroll : true,
											animate : false
										});

								var userGrantWindow = new Ext.Window(
										{
											layout : 'border',
											width : document.body.clientWidth - 100,
											height : document.body.clientHeight - 50,
											resizable : true,
											draggable : true,
											closeAction : 'hide',
											title : '<span class="commoncss">UI元素人员授权</span>',
											iconCls : 'app_rightIcon',
											modal : true,
											collapsible : true,
											maximizable : true,
											animateTarget : document.body,
											// //如果使用autoLoad,建议不要使用动画效果
											buttonAlign : 'right',
											constrain : true,
											border : false,
											items : [ menuTreePanel, grid_part ],
											buttons : [ {
												text : '关闭',
												iconCls : 'deleteIcon',
												handler : function() {
													userGrantWindow.hide();
												}
											} ]
										});
								userGrantWindow.show();