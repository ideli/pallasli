<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
	String nid = request.getAttribute("nid").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style>
.btn-comp {
	padding: 0px;
	margin: '0px;
	border-style: none;
	background-color: #FFF;
	background-image: url();
}
</style>
<script type="text/javascript">
	Ext.require([ 'Ext.direct.*', 'Ext.data.*', 'Ext.tree.*' ]);

	Ext.Loader.setPath({
		'Pallas.extDesigner' : '/activitiDesigner/extDesigner'
	});
	Ext
			.onReady(function() {
				var editorData = {};

				var store = Ext.create('Ext.data.TreeStore', {
					root : {
						text : "基础面板",
						expanded : true,
						children : []
					}
				});

				var containerItems = [ {
					xtype : "button",
					border : false,
					text : 'form',
					cType : 'Pallas.extDesigner.shape.panel.FormPanel',
					iconCls : 'icon-form',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : 'panel',
					cType : 'Pallas.extDesigner.shape.panel.Panel',
					iconCls : 'icon-panel',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : 'grid',
					cType : 'Pallas.extDesigner.shape.panel.Grid',
					iconCls : 'icon-grid',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : 'tree',
					cType : 'Pallas.extDesigner.shape.panel.TreePanel',
					iconCls : 'icon-tree',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}

				];

				var formItems = [ {
					xtype : "button",
					border : false,
					text : '文本框',
					cType : 'Pallas.extDesigner.shape.field.Text',
					iconCls : 'icon-field-text',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : '多行文本',
					cType : 'Pallas.extDesigner.shape.field.TextArea',
					iconCls : 'icon-field-textarea',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : '单选框',
					cType : 'Pallas.extDesigner.shape.field.Radio',
					iconCls : 'icon-radio',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : '复选框',
					cType : 'Pallas.extDesigner.shape.field.Checkbox',
					iconCls : 'icon-checked',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : '日期框',
					cType : 'Pallas.extDesigner.shape.field.Date',
					iconCls : 'icon-field-date',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : '下拉框',
					cType : 'Pallas.extDesigner.shape.field.ComboBox',
					iconCls : 'icon-combobox',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}, {
					xtype : "button",
					text : '按钮',
					cType : 'Pallas.extDesigner.shape.action.Button',
					iconCls : 'icon-button',
					cls : 'btn-comp',
					listeners : {
						click : function() {
						}
					}
				}

				];
				var treePanel1 = Ext.create('Ext.panel.Panel', {
					title : '容器',
					border : false,
					autoHeight : true,
					collapsible : true,
					items : containerItems

				});
				var treePanel2 = Ext.create('Ext.panel.Panel', {
					title : '数据源',
					autoHeight : true,
					collapsible : true,
					border : false,
					items : []
				});
				var treePanel3 = Ext.create('Ext.panel.Panel', {
					title : '工具条',
					autoHeight : true,
					collapsible : true,
					border : false,
					items : []
				});
				var treePanel4 = Ext.create('Ext.panel.Panel', {
					title : '表单组件',
					autoHeight : true,
					collapsible : true,
					border : false,
					items : formItems
				});
				var treePanel = Ext
						.create(
								'Pallas.extDesigner.ui.TreePanel',
								{
									title : '页面元素',
									border : false,
									width : 200,
									region : "west",
									store : store,
									viewConfig : {
										plugins : {
											ptype : 'treeviewdragdrop'
										}
									},
									tools : [
											{
												type : 'expand',
												tooltip : 'Refresh form Data',
												// hidden:true,
												handler : function(event,
														toolEl, panel) {
													treePanel.getRootNode()
															.expand();

												}
											},
											{
												type : 'collapse',
												tooltip : 'Get Help',
												handler : function(event,
														toolEl, panel) {
													treePanel.getRootNode()
															.collapse();
												}
											},
											{
												type : 'left',
												tooltip : 'Get Help',
												handler : function(event,
														toolEl, panel) {
													treePanel.collapse();
												}
											} ],
									rootVisible : true,
									listeners : {
										'itemclick': function(treeview,item,htmlItem,index,e){     
											Ext.getCmp(item.raw.shapeId).items.items[0].el.dom.click();
									    },
										'itemcontextmenu' : function(panel,
												record, item, index, e, eOpts) {
											var contextmenu = Ext
													.create(
															'Ext.menu.Menu',
															{
																width : 100,
																autoHeight : true,
																plain : true,
																floating : true, // usually you want this set to True (default)
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
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.panel.Panel");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																										expanded : true,
																										leaf : false
																									});
																							child.set("text","ss");
																							console.log(child);
																						}
																					},
																					{
																						text : 'form面板',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.panel.FormPanel");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																										expanded : true,
																										leaf : false
																									});
																						
																						}
																					},
																					{
																						text : 'grid面板',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.panel.Grid");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																										expanded : true,
																										leaf : false
																									});
																						
																						}
																					},
																					{
																						text : 'tree面板',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.panel.TreePanel");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
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
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.action.Menu");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '菜单项',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.action.MenuItem");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '按钮',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.action.Button");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
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
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Text");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						}
																					},
																					{
																						text : '数值框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Number");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '日期框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Date");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '单选按钮',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Radio");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '复选框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Checkbox");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '时间框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Time");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '下拉框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.ComboBox");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '多行文本框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.TextArea");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '显示框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Display");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '文件框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.File");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : 'HTML编辑框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.HtmlEditor");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					},
																					{
																						text : '弹出框',
																						handler : function() {
																							
																							var name = Ext.id();
																							editorData.children = editorData.children|| [];
																							editorData.children[editorData.children.length] = {
																									name : name,
																									children : []
																							};
														                                      
																							var child = treePanel.getRootNode(); 
																							if(!record.data.root){
																								child = child.findChild('text',record.data.text ,true);
																							}

																							var tmpShape = Ext.create("Pallas.extDesigner.shape.field.Trigger");
																							Ext.getCmp('trucks').add(tmpShape);
																							child = child.appendChild({
																										id : name,
																										text : name,
																										checked:false,
																										shapeId:tmpShape.getId(),
																                                        leaf:true
																									});
																						
																						
																						}
																					} ]
																		} ]
															});
											if(record.data.root||(!record.data.leaf)){
												contextmenu.items.items[0].hidden = record.data.leaf ;
												contextmenu.items.items[1].hidden = record.data.leaf ;
												
												contextmenu.items.items[2].hidden = record.data.root||record.data.leaf;
												contextmenu.items.items[3].hidden = record.data.root||record.data.leaf;
												contextmenu.showAt(e.browserEvent.clientX,e.browserEvent.clientY);
											}
										}
									}
								});

				var compPanel = Ext.create('Ext.panel.Panel', {
					title : '组件选择',
					region : "north",
					border : false,
					height : 200,
					//collapsible :true,
					tools : [ {
						type : 'expand',
						tooltip : 'Refresh form Data',
						// hidden:true,
						handler : function(event, toolEl, panel) {
							treePanel1.expand();
							treePanel2.expand();
							treePanel3.expand();
							treePanel4.expand();

						}
					}, {
						type : 'collapse',
						tooltip : 'Get Help',
						handler : function(event, toolEl, panel) {
							treePanel1.collapse();
							treePanel2.collapse();
							treePanel3.collapse();
							treePanel4.collapse();
						}
					}, {
						type : 'right',
						tooltip : 'Get Help',
						handler : function(event, toolEl, panel) {
							compPanel.collapse();
						}
					} ],
					items : [ treePanel1, treePanel2, treePanel3, treePanel4 ],
					rootVisible : false
				});

				var attributePanel = Ext.create(
						'Pallas.common.AttributePanel', {
							title : '属性设置',
							region : "center",
							sortableColumns : false,
							id : "attributePanel",
							nameColumnWidth : 100,
							columns : [ {
								header : "属性名",
								dataIndex : "name"
							}, {
								header : "值",
								dataIndex : "value"
							} ]
						});
				attributePanel.on("propertychange", function(data, name, newV,
						oldV) {
					//basicEditorPanel.layout="absolute";
					//basicEditorPanel.getLayout();
					//basicEditorPanel.updateLayout({
					//	defer : false, 
					//	isRoot : false
					//});
				});
				var compAttributePanel = Ext.create('Ext.panel.Panel', {
					width : 300,
					region : "east",
					layout : "border",
					items : [ attributePanel, compPanel ]
				});
				var basicEditorPanel = Ext.create('Ext.panel.Panel', {
					layout : {
						type : 'absolute'
					},
					id : 'trucks',
					height : 1000,
					width : 1376,
					resizable : true
				});
				var mainPanel = Ext.create('Ext.panel.Panel', {
					xtype : "panel",
					region : "center",
					//layout:{type:'column'},
					autoScroll : true,
					items : [ basicEditorPanel ]
				});
				Ext.PortalPanel2 = Ext.extend(Ext.panel.Panel, {
					xtype : "panel",
					layout : "border",
					border : false,
					autoScroll : false,
					initComponent : function() {
						this.items = [ {
							xtype : "panel",
							region : "north",
							autoHeight : true,
							border : false,
							bodyStyle : {
								'background-color' : 'transparent'
							},
							layout : "table",
							tbar : [ {
								xtype : "button",
								text : "新建"
							}, {
								xtype : "button",
								text : "打开"
							}, {
								xtype : "button",
								text : "保存"
							}, {
								xtype : "button",
								text : "另存"
							}, {
								xtype : "button",
								text : "导出"
							}, {
								xtype : "button",
								text : "导入"
							} ]
						}, compAttributePanel, treePanel, mainPanel ];
						Ext.PortalPanel2.superclass.initComponent.call(this);
					}
				});
				//	Ext.create('Ext.container.Viewport', {
				//		layout:'fit',
				//		items:[new Ext.PortalPanel()]
				//	});
				Ext.getCmp('<%=nid%>').add(new Ext.PortalPanel2());
				var trucks = Ext.getCmp('trucks');

				/**	Ext.each(treePanel4.items.items, function(comp) {  
						var proxy = new Ext.dd.DragSource(comp.getEl(), {group:'panelDD'});
						proxy.afterDragDrop = function(target, e, id) {
							var destComp=Ext.getCmp(id);  
							var srcCompd=Ext.getCmp(proxy.id);
							if(!srcCompd.cType){return;}
							var srcComp=Ext.create(srcCompd.cType, {
									fieldLabel:srcCompd.cType,
									columnWidth:0.3
								});		

				         	for(var i=0,len=trucks.items.items.length;i<len;i++){
				            	if(trucks.items.items[i].id==destComp.id){
				            		trucks.insert(i,srcComp);
				            		addNewProxy(srcComp);
				            		break;
				            	}
				            }
				       		Ext.getCmp('trucks').doLayout();
				            new Ext.dd.DDTarget(srcComp.getEl(), 'panelDD');
						};
					});  **/

				var addCompCompProxy = function(proxy) {
					proxy.afterDragDrop = function(target, e, id) {
						var destComp = Ext.getCmp(id);
						var srcCompd = Ext.getCmp(proxy.id);
						if (!srcCompd.cType) {
							for (var i = 0, len = destComp
									.findParentByType('panel').items.items.length; i < len; i++) {
								if (destComp.findParentByType('panel').items.items[i].id == destComp.id) {
									destComp.findParentByType('panel').insert(
											i, srcComp);
									addNewProxy(srcComp);
									break;
								}
							}
							return;
						}
						var srcComp = Ext.create(srcCompd.cType, {
							fieldLabel : srcCompd.cType,
							columnWidth : 0.3
						});

						destComp.add(srcComp);
						destComp.doLayout();
						new Ext.dd.DDTarget(srcComp.getEl(), 'CompDD');
						addCompCompProxy(new Ext.dd.DragSource(srcComp.getEl(),
								{
									group : 'CompDD'
								}));
					};
				};

				var comps = [];
				var resetCompProxy = function() {
					for (var i = 0; i < comps.length; i++) {
						var srcComp = comps[i];
						if (i == 0)
							new Ext.dd.DDTarget(srcComp.getEl(), 'CompDD');
						else {
							var proxy2 = new Ext.dd.DragSource(srcComp.getEl(),
									{
										group : 'CompDD'
									});
							addCompCompProxy(proxy2);
						}
					}
				};
				var addCompProxy = function(proxy) {
					proxy.afterDragDrop = function(target, e, id) {
						var destComp = Ext.getCmp(id);
						var srcCompd = Ext.getCmp(proxy.id);
						if (!srcCompd.cType) {
							destComp.add(srcCompd);
							return;
						}
						var srcComp = Ext.create(srcCompd.cType,
								CompConfigMap[srcCompd.cType]);

						destComp.add(srcComp);
						destComp.doLayout();
						addCompProxy(new Ext.dd.DragSource(srcComp.getEl(), {
							group : 'panelDD'
						}));
						comps.push(srcComp);
						resetCompProxy();
					};
				};

				Ext.each(treePanel4.items.items, function(comp) {
					//var proxy = new Ext.dd.DragSource(comp.getEl(), {group:'panelDD'});
					//addCompProxy(proxy);
				});

				var comp_comps = [];
				var initCompCompProxy = function(comp) {
					var proxy = new Ext.dd.DragSource(comp.getEl(), {
						group : 'form_comp_drag'
					});
					proxy.afterDragDrop = function(target, e, id) {
						var destComp = Ext.getCmp(id);
						var srcCompd = Ext.getCmp(proxy.id);
						destComp.add(srcCompd);
						destComp.doLayout();
					};
				};
				var initFormCompProxy = function(comp) {
					var proxy = new Ext.dd.DragSource(comp.getEl(), {
						group : 'form_comp_drag'
					});
					proxy.afterDragDrop = function(target, e, id) {
						var destComp = Ext.getCmp(id);
						var srcCompd = Ext.getCmp(proxy.id);
						if (!srcCompd.cType) {
							return;
						}
						var srcComp = Ext.create(srcCompd.cType,
								CompConfigMap[srcCompd.cType]);
						destComp.add(srcComp);
						destComp.doLayout();
						comp_comps.push(srcComp);

						initCompCompProxy(srcComp);
					};
				};
				//var initCompCompProxy=function(){
				//	for(var i=0;i<comp_comps.length;i++){
				//		console.log(comp_comps[i]);
				//		var comp=comp_comps[i];
				//		initCompProxy(comp);
				//	}
				//};

				//初始化设计器：designDD
				new Ext.dd.DDTarget(trucks.getEl(), 'designDD');
				//面板组件拖动分组： panel_drag
				Ext.each(treePanel1.items.items, function(comp) {
					var proxy = new Ext.dd.DragSource(comp.getEl(), {
						group : 'designDD'
					});
					proxy.afterDragDrop = function(target, e, id) {
						var destComp = Ext.getCmp(id);
						var srcCompd = Ext.getCmp(proxy.id);
						if (!srcCompd.cType) {
							return;
						}
						var srcComp = Ext.create(srcCompd.cType, Ext.apply(
								CompConfigMap[srcCompd.cType], {
									x : e.browserEvent.offsetX,
									y : e.browserEvent.offsetY
								}));
						console.log(target, e);
						destComp.add(srcComp);
						destComp.doLayout();
						//new Ext.dd.DDTarget(srcComp.getEl(), 'form_comp_drag');
					};
				});
				//表单组件拖动分组： form_comp_drag
				Ext.each(treePanel4.items.items, function(comp) {
					var proxy = new Ext.dd.DragSource(comp.getEl(), {
						group : 'designDD'
					});
					proxy.afterDragDrop = function(target, e, id) {
						var destComp = Ext.getCmp(id);
						var srcCompd = Ext.getCmp(proxy.id);
						if (!srcCompd.cType) {
							return;
						}
						var srcComp = Ext.create(srcCompd.cType, Ext.apply(
								CompConfigMap[srcCompd.cType], {
									x : e.browserEvent.pageX,
									y : e.browserEvent.pageY
								}));
						console.log(target, e);
						destComp.add(srcComp);
						destComp.doLayout();
						//new Ext.dd.DDTarget(srcComp.getEl(), 'form_comp_drag');
					};
					//initFormCompProxy(comp);
				});

				var addNewProxy = function(srcComp) {

					var proxy2 = new Ext.dd.DragSource(srcComp.getEl(), {
						group : 'eee'
					});
					var eee = new Ext.dd.DDTarget(srcComp.getEl(), 'eee');
					proxy2.afterDragDrop = function(target2, e2, id2) {
						var destCompe = Ext.getCmp(id2);
						var srcCompe = Ext.getCmp(proxy2.id);
						for (var ie = 0, lene = trucks.items.items.length; ie < lene; ie++) {
							if (trucks.items.items[ie].id == destCompe.id) {
								trucks.insert(ie, srcCompe);
								break;
							}
						}
						Ext.getCmp('trucks').doLayout();
					}
				};

				var addNewPanelProxy = function(srcComp) {

					var proxy2 = new Ext.dd.DragSource(srcComp.getEl(), {
						group : 'eee'
					});
					var eee = new Ext.dd.DDTarget(srcComp.getEl(), 'eee');
					proxy2.afterDragDrop = function(target2, e2, id2) {
						var destCompe = Ext.getCmp(id2);
						var srcCompe = Ext.getCmp(proxy2.id);
						for (var ie = 0, lene = destCompe
								.findParentByType('panel').items.items.length; ie < lene; ie++) {
							if (trucks.items.items[ie].id == destCompe.id) {
								trucks.insert(ie, srcCompe);
								break;
							}
						}
						destCompe.findParentByType('panel').doLayout();
					}
				};

				var addNewPanelProxyForComp = function(srcComp) {

					var proxy2 = new Ext.dd.DragSource(srcComp.getEl(), {
						group : 'eee'
					});
					var eee = new Ext.dd.DDTarget(srcComp.getEl(), 'eee');
					proxy2.afterDragDrop = function(target2, e2, id2) {
						var destCompe = Ext.getCmp(id2);
						var srcCompe = Ext.getCmp(proxy2.id);
						for (var ie = 0, lene = destCompe
								.findParentByType('panel').items.items.length; ie < lene; ie++) {
							if (trucks.items.items[ie].id == destCompe.id) {
								trucks.insert(ie, srcCompe);
								break;
							}
						}
						destCompe.findParentByType('panel').doLayout();
					}
				};

				var panelDD;
				Ext.each(treePanel1.items.items, function(comp) {
					/**var proxy = new Ext.dd.DragSource(comp.getEl(), {group:'designDD'});
					proxy.afterDragDrop = function(target, e, id) {
						var destComp=Ext.getCmp(id);  
						var srcCompd=Ext.getCmp(proxy.id);
						if(!srcCompd.cType){return;}
						var srcComp=Ext.create(srcCompd.cType, CompConfigMap[srcCompd.cType]);		
						destComp.add(srcComp);
					 	destComp.doLayout();
					 	console.log(srcCompd);
					 	if(true){
					      	 new Ext.dd.DDTarget(srcComp.getEl(), 'panelDD');
					 	}else{
					 	  	 new Ext.dd.DDTarget(srcComp.getEl(), 'CompDD');	         		
					 	}
					 **/

					/**   	
					Ext.create(new Ext.dd.DragSource,srcComp.getEl(), {
					group:'panel-zone',
						getDragData: function(e) {
							var sourceEl = e.getTarget();
							if (sourceEl) {
							                d = sourceEl.cloneNode(true);
							                d.id = Ext.id();
							                return {
							                    ddel: d,
							                    sourceEl: sourceEl
							                }
							            }
						}
					});
					

					Ext.create(new Ext.dd.DropTarget,destComp.getEl(), {
					group:'panel-zone',
						notifyDrop: function(source , event , dragNodeData ) {
							var dragged = source.dragData.ddel;
							var destinationContainer = this.getEl();
							destinationContainer.appendChild(dragged);
							return true;
						}
					}); 
					 **/

					/**
					 new Ext.dd.DragZone(srcComp.getEl(), {
					 getDragData: function(e) {
					 var sourceEl = e.getTarget( );
					 if (sourceEl) {
					 d = sourceEl.cloneNode(true);
					 d.id = Ext.id();
					 return {
					 ddel: d,
					 sourceEl: sourceEl//,
					 // sourceStore: srcComp.store,
					 //draggedRecord: srcComp.getRecord(sourceEl)
					 }
					 }
					 },
					 getRepairXY: function() {
					 return this.dragData.repairXY;
					 },
					 onMouseUp : function(e){
					 var currDom = Ext.fly(this.dragData.sourceEl);
					 var oldXY = currDom.getXY();
					 var newXY = e.getXY();
					 var width = currDom.getWidth();
					 var height = currDom.getHeight();
					 if(Math.abs(oldXY[0]-newXY[0]) > width || Math.abs(oldXY[1]-newXY[1]) > height){
					 currDom.setXY(newXY);
					 Ext.get(this.dragData.sourceEl).frame('#8db2e3', 1);
					 }

					 }
					 });
					
					 **/
					//	addNewPanelProxy(srcComp);
					//};
				});

				var designDD = new Ext.dd.DDTarget(trucks.getEl(), 'designDD');
				Ext.each(trucks.items.items, function(comp) {
					//   var dd = new Ext.dd.DDTarget(comp.getEl(), 'dd');
				});

			});
</script>
</head>
<body>
</body>
