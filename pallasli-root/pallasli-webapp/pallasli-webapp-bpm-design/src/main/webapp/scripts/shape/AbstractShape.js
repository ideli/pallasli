Ext
		.define(
				'Pallas.activitiDesigner.shape.AbstractShape',
				{
					extend : "Ext.panel.Panel",
					layout : 'fit',
					border : false,
					items : [ {
						xtype : "panel",
						border : false
					} ],
					style : "z-index:20;",
					padding : 2,
					requires : [
							'Pallas.activitiDesigner.AdvanceAttributeWindow',
							'Pallas.activitiDesigner.config.AtrributeConfig' ],
					activitiName : null,
					baseSource : null,
					baseSourceConfig : null,
					canvas : null,
					resizable : true,
					draggable : true,
					ghost : function(cls) {
						var me = this, ghostPanel = me.ghostPanel, box = me
								.getBox();
						if (!ghostPanel) {
							ghostPanel = new Ext.panel.Panel({
								renderTo : Ext.getBody(),
								border : false,
								floating : {
									shadow : false
								},
								padding : 4,
								// layout : "fit",
								html : "<div></div>"
							});
							me.ghostPanel = ghostPanel;
						} else {
							ghostPanel.el.show();
						}
						me.ghostPanel.hidden = false;
						ghostPanel.floatParent = me.floatParent;
						ghostPanel.toFront();
						ghostPanel.setPagePosition(box.x, box.y);
						ghostPanel.setSize(box.width, box.height);

						var canvas = document.createElement("canvas");
						canvas.style.position = "absolute";
						if (me.pallasName != "sequenceFlow") {
							canvas.style["z-index"] = 99999;
						} else {
							canvas.style["z-index"] = 10000;
						}
						canvas.style.display = "block";
						canvas.width = 40;
						canvas.height = 40;
						me.ghostcanvas = canvas;
						ghostPanel.el.dom.childNodes[0].childNodes[0].childNodes[0].childNodes[0]
								.appendChild(me.ghostcanvas);
						me.ghostcontext = me.ghostcanvas.getContext("2d");
						me.drawGhostIcon();
						me.el.hide();
						return ghostPanel;
					},
					listeners : {
						move : function(f, x, y, c) {
							var me = this;
							var allComponents = Pallas.activitiDesigner.utils.AllComponents;
							var editorPanel = allComponents.editorPanel;
							var designer = allComponents.designer;
							var offsetT = designer.el.dom.offsetTop;
							var offsetL = designer.el.dom.offsetLeft;
							var scrollT = editorPanel.ownerCt.body.dom.scrollTop;
							var scrollL = editorPanel.ownerCt.body.dom.scrollLeft;
							me.applyShapeData({
								x : me.x + 206 + offsetL + scrollL,
								y : me.y + 53 + offsetT + scrollT
							});
						}
					},
					setCanvasListeners : function() {
						var me = this;
						if (me.pallasName == "sequenceFlow") {
							/***************************************************
							 * me.canvas.ondblclick = function(e) {
							 * me.showAdvanceAttributeWindow(); e.cancelBubble =
							 * true; }; me.canvas.onclick = function(e) {
							 * me.setBaseAttribute(); e.cancelBubble = true; };
							 **************************************************/
						} else {
							me.items.items[0].el.dom.ondblclick = function(e) {
								me.showAdvanceAttributeWindow();
								e.cancelBubble = true;
							};
							me.items.items[0].el.dom.onclick = function(e) {
								me.setBaseAttribute();
								e.cancelBubble = true;
							};
						}
					},
					drawDragIcon : function() {
					},
					initDropCanvas : function() {
					},
					initCanvas : function() {
						var me = this;
						Ext.apply(me.shapeData, {
							"pallasName" : me.pallasName
						});
						if (!me.canvas) {
							var canvas = document.createElement("canvas");
							canvas.style.position = "absolute";
							if (me.pallasName != "sequenceFlow") {
								canvas.style["z-index"] = 99999;
								canvas.width = 40;
								canvas.height = 40;
							} else {
								canvas.style["z-index"] = 10000;
								canvas.width = 1500;
								canvas.height = 1500;
							}
							canvas.style.display = "block";

							me.canvas = canvas;
							me.context = me.canvas.getContext("2d");
							me.initDropCanvas();
							me.drawDragIcon();
						}
						me.canvas.style.left = 0 + "px";
						me.canvas.style.top = 0 + "px";

						me.items.items[0].el.dom.appendChild(me.canvas);

						var allComponents = Pallas.activitiDesigner.utils.AllComponents;
						var shapes = allComponents.shapes;

						me.applyShapeData({
							x : me.x,
							y : me.y
						});
						shapes[me.activitiName] = me;

						me.setBaseAttribute();

						me.setCanvasListeners();
						me.show();
						me.setPagePosition(me.x, me.y, false);
					},
					drawDragHtml : function() {
						var me = this;
						var x = window.event.clientX;
						var y = window.event.clientY;
						var canvas = document.createElement("canvas");
						canvas.style.position = "absolute";
						if (me.pallasName != "sequenceFlow") {
							canvas.style["z-index"] = 99999;
							canvas.width = 40;
							canvas.height = 40;
							canvas.style.left = x - me.width / 2 + "px";
							canvas.style.top = y - me.height / 2 + "px";
						} else {
							canvas.style["z-index"] = 100000;
							canvas.style.left = x - 50 + "px";
							canvas.style.top = y - 8 + "px";
						}
						canvas.style.display = "block";

						document.body.appendChild(canvas);
						me.canvas = canvas;

						me.context = me.canvas.getContext("2d");
						if (me.pallasName != "sequenceFlow") {
							me.drawDragIcon();
						} else {
							me.canvas.width = 300;
							me.canvas.height = 150;
							me.context.beginPath();
							me.context.lineWidth = 2;
							me.context.moveTo(0, 8);
							me.context.lineTo(50, 8);
							me.context.lineTo(100, 8);
							me.context.stroke();

							me.context.beginPath();
							me.context.moveTo(100, 8);
							me.context.lineTo(90, 13);
							me.context.moveTo(100, 8);
							me.context.lineTo(90, 3);
							me.context.stroke();
						}

						document.body.onmousemove = function(e) {
							var x = e.x;
							var y = e.y;
							if (me.pallasName != "sequenceFlow") {
								canvas.style.left = x - me.width / 2 + "px";
								canvas.style.top = y - me.height / 2 + "px";
							} else {
								canvas.style.left = x - 50 + "px";
								canvas.style.top = y - 8 + "px";
							}
						};
						document.body.onmouseup = function(e) {
							var allComponents = Pallas.activitiDesigner.utils.AllComponents;
							var editorPanel = allComponents.editorPanel;
							var htmlUtils = Pallas.activitiDesigner.utils.HtmlUtils;
							var mouseInEditorPanel = htmlUtils
									.checkMouseIn(editorPanel.ownerCt.body.dom);
							if (!mouseInEditorPanel) {
								document.body.removeChild(canvas);
							} else {
								document.body.removeChild(canvas);
								me.drawDropHtml();
							}
							// if (document.releaseCapture) {
							// document.releaseCapture();
							// } else if (window.captureEvents) {
							// window.captureEvents(Event.MOUSEMOVE |
							// Event.MOUSEUP);
							// }
							// 清除事件
							document.body.onmousemove = null;
							document.body.onmouseup = null;
						};

					},
					showAdvanceAttributeWindow : function() {
						var me = this;
						var atrributeConfig = Pallas.activitiDesigner.config.AtrributeConfig;
						var basicFields = atrributeConfig[me.pallasName].advanceSource;
						if (basicFields) {
							Ext
									.create(
											'Pallas.activitiDesigner.AdvanceAttributeWindow',
											{
												modal : true
											}).show();
						}
					},
					setBaseAttribute : function() {
						var me = this;
						if (!me.isActived) {
							var allComponents = Pallas.activitiDesigner.utils.AllComponents;
							var attributePanel = allComponents.attributePanel;

							if (me.shapeData.baseAttributeData) {

								attributePanel
										.setSource(me.shapeData.baseAttributeData);
							} else {
								for ( var key in me.baseSource) {
									me.baseSource[key] = "";
								}
								attributePanel.setSource(me.baseSource,
										me.baseSourceConfig);
							}

							if (!me.initPropertychange) {
								attributePanel.on("propertychange", function(
										data, name, newV, oldV) {
									if (me.isActived) {
										me.applyShapeData({
											baseAttributeData : Ext.apply({},
													data)
										});

									}
								});
								me.initPropertychange = true;
							}

							var shapes = Pallas.activitiDesigner.utils.AllComponents.shapes;
							for ( var key in shapes) {
								if (key == me.activitiName) {
									shapes[key].isActived = true;
									shapes[key].canvas.style.border = "dotted 1px";
								} else {
									shapes[key].isActived = false;
									shapes[key].canvas.style.border = "none";
								}
							}
						}

					},
					applyShapeData : function(shapeData) {
						var me = this;
						me.shapeData = me.shapeData || {};
						Ext.apply(me.shapeData, shapeData);
						var allComponents = Pallas.activitiDesigner.utils.AllComponents;
						allComponents.shapeDatas[me.activitiName] = me.shapeData;
					},
					drawDropHtml : function() {
						var me = this;
						me.initDropCanvas();
						me.x = window.event.clientX - me.width / 2;
						me.y = window.event.clientY - me.height / 2;
						me.initCanvas();
					},
					initComponent : function() {
						var me = this;
						me.activitiName = me.activitiName || "shape_"
								+ Ext.id();
						me.baseSource = {};
						var atrributeConfig = Pallas.activitiDesigner.config.AtrributeConfig;
						var allComponents = Pallas.activitiDesigner.utils.AllComponents;
						me.baseSource = Ext.apply(me.baseSource,
								atrributeConfig.baseCommonSource);
						me.baseSource = Ext.apply(me.baseSource,
								atrributeConfig[me.pallasName].baseSource);
						me.baseSourceConfig = atrributeConfig.baseSourceConfig;

						me.shapeData = me.shapeData || {};
						me.hidden = true;
						Ext.apply(me.shapeData, {
							"baseAttributeData" : me.baseAttributeData
						});
						allComponents.shapeDatas[me.activitiName] = me.shapeData;
						me.callParent();
						var allComponents = Pallas.activitiDesigner.utils.AllComponents;
						var editorPanel = allComponents.editorPanel;
						editorPanel.add(me);
					}
				});
