Ext
		.define(
				'Pallas.extDesigner.PageElementTree',
				{
					extend : "Pallas.extDesigner.ui.TreePanel",
					requires : [],
					border : true,
					rootVisible : false,

					title : '页面元素',
					border : false,
					width : 200,
					region : "west",
					viewConfig : {
						plugins : {
							ptype : 'treeviewdragdrop'
						}
					},

					rootVisible : true,

					initComponent : function() {
						var me = this;
						var editorData = {};
						me.tools = [ {
							type : 'expand',
							tooltip : 'Refresh form Data',
							// hidden:true,
							handler : function(event, toolEl, panel) {
								me.getRootNode().expand();

							}
						}, {
							type : 'collapse',
							tooltip : 'Get Help',
							handler : function(event, toolEl, panel) {
								me.getRootNode().collapse();
							}
						}, {
							type : 'left',
							tooltip : 'Get Help',
							handler : function(event, toolEl, panel) {
								me.collapse();
							}
						} ];
						me.listeners = {
							'itemclick' : function(treeview, item, htmlItem,
									index, e) {
								if (Ext.getCmp(item.raw.shapeId))
									Ext.getCmp(item.raw.shapeId).items.items[0].el.dom
											.click();
							},
							'itemcontextmenu' : function(panel, record, item,
									index, e, eOpts) {
								var contextmenu = Ext
										.create(
												'Ext.menu.Menu',
												{
													width : 100,
													autoHeight : true,
													plain : true,
													floating : true,
													items : [
															{
																text : '刷新',
																hidden : true,
																handler : function() {
																}
															},
															{
																text : '增加面板',
																menu : [
																		{
																			text : '普通面板',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.panel.Panel");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							expanded : true,
																							leaf : false
																						});
																				child
																						.set(
																								"text",
																								"ss");
																				console
																						.log(child);
																			}
																		},
																		{
																			text : 'form面板',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.panel.FormPanel");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							expanded : true,
																							leaf : false
																						});

																			}
																		},
																		{
																			text : 'grid面板',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.panel.Grid");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							expanded : true,
																							leaf : false
																						});

																			}
																		},
																		{
																			text : 'tree面板',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.panel.TreePanel");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							expanded : true,
																							leaf : false
																						});

																			}
																		} ]
															},
															{
																text : '增加功能按钮',
																hidden : true,
																menu : [
																		{
																			text : '菜单',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.action.Menu");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '菜单项',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.action.MenuItem");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '按钮',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.action.Button");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		} ]
															},
															{
																text : '增加组件',
																hidden : true,
																menu : [
																		{
																			text : '文本框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Text");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '数值框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Number");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '日期框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Date");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '单选按钮',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Radio");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '复选框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Checkbox");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '时间框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Time");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '下拉框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.ComboBox");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '多行文本框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.TextArea");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '显示框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Display");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '文件框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.File");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : 'HTML编辑框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.HtmlEditor");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		},
																		{
																			text : '弹出框',
																			handler : function() {

																				var name = Ext
																						.id();
																				editorData.children = editorData.children
																						|| [];
																				editorData.children[editorData.children.length] = {
																					name : name,
																					children : []
																				};

																				var child = me
																						.getRootNode();
																				if (!record.data.root) {
																					child = child
																							.findChild(
																									'text',
																									record.data.text,
																									true);
																				}

																				var tmpShape = Ext
																						.create("Pallas.extDesigner.shape.field.Trigger");
																				Ext
																						.getCmp(
																								'trucks')
																						.add(
																								tmpShape);
																				child = child
																						.appendChild({
																							id : name,
																							text : name,
																							checked : false,
																							shapeId : tmpShape
																									.getId(),
																							leaf : true
																						});

																			}
																		} ]
															} ]
												});
								if (record.data.root || (!record.data.leaf)) {
									contextmenu.items.items[0].hidden = record.data.leaf;
									contextmenu.items.items[1].hidden = record.data.leaf;

									contextmenu.items.items[2].hidden = record.data.root
											|| record.data.leaf;
									contextmenu.items.items[3].hidden = record.data.root
											|| record.data.leaf;
									contextmenu.showAt(e.browserEvent.clientX,
											e.browserEvent.clientY);
								}

							}
						};
						me.callParent();
					}
				});
