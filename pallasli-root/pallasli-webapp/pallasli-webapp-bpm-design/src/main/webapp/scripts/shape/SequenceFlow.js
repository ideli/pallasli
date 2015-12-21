Ext
		.define(
				'Pallas.activitiDesigner.shape.SequenceFlow',
				{
					extend : "Pallas.activitiDesigner.shape.AbstractShape",
					requires : [
							"Pallas.activitiDesigner.config.ElementsConfig",
							"Pallas.activitiDesigner.config.BaseConfig" ],
					alias : [ 'widget.sequenceflow' ],
					pallasName : "sequenceFlow",
					width : 1500,
					height : 1500,
					padding : 0,
					style : "z-index:10;",
					border : false,
					draggable : false,
					resizable : false,
					initComponent : function(config) {
						var me = this;
						me.callParent();
					},
					drawDragHtml : function() {
						var me = this;
						var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
						var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
						var iconPath = baseConfig.imagesDependencesPath
								+ elementsConfig.sequenceFlow.dragIcon;
						me.initialConfig.dragIcon = "url('" + iconPath + "')";
						me.callParent();
					},
					refreshShape : function() {
						var me = this;
						me.drawIcon(me.canvas, me.context);
						me.drawEditIcons();
					},
					initEditEvent : function() {
						var me = this;

						me.editIcons[0].onmousedown = function() {
							document.body.onmousemove = function(e) {
								me.refreshShape();

								var allComponents = Pallas.activitiDesigner.utils.AllComponents;
								var editorPanel = allComponents.editorPanel;
								var designer = allComponents.designer;
								var offsetT = designer.el.dom.offsetTop;
								var offsetL = designer.el.dom.offsetLeft;
								var scrollT = editorPanel.ownerCt.body.dom.scrollTop;
								var scrollL = editorPanel.ownerCt.body.dom.scrollLeft;
								var x = e.x;
								var y = e.y;
								me.start = {
									x : x,
									y : y
								};
								me.refreshShape();
							};
							document.body.onmouseup = function(e) {
								document.body.onmousemove = null;
								document.body.onmouseup = null;
							};
						};
						me.editIcons[1].onmousedown = function() {
							document.body.onmousemove = function(e) {
								me.refreshShape();

								var allComponents = Pallas.activitiDesigner.utils.AllComponents;
								var editorPanel = allComponents.editorPanel;
								var designer = allComponents.designer;
								var offsetT = designer.el.dom.offsetTop;
								var offsetL = designer.el.dom.offsetLeft;
								var scrollT = editorPanel.ownerCt.body.dom.scrollTop;
								var scrollL = editorPanel.ownerCt.body.dom.scrollLeft;
								var x = e.x;
								var y = e.y;
								me.middle = {
									x : x,
									y : y
								};
								me.refreshShape();
							};
							document.body.onmouseup = function(e) {
								document.body.onmousemove = null;
								document.body.onmouseup = null;
							};
						};

						me.editIcons[2].onmousedown = function() {
							document.body.onmousemove = function(e) {
								me.refreshShape();

								var allComponents = Pallas.activitiDesigner.utils.AllComponents;
								var editorPanel = allComponents.editorPanel;
								var designer = allComponents.designer;
								var offsetT = designer.el.dom.offsetTop;
								var offsetL = designer.el.dom.offsetLeft;
								var scrollT = editorPanel.ownerCt.body.dom.scrollTop;
								var scrollL = editorPanel.ownerCt.body.dom.scrollLeft;
								var x = e.x;
								var y = e.y;
								me.end = {
									x : x,
									y : y
								};
								me.refreshShape();
							};
							document.body.onmouseup = function(e) {
								document.body.onmousemove = null;
								document.body.onmouseup = null;
							};
						};
					},
					drawEditIcons : function() {
						var me = this;
						me.editIcons = me.editIcons || [];
						for (var i = 0; i < 3; i++) {
							var canvas;
							if (me.editIcons[i]) {
								canvas = me.editIcons[i];
							} else {
								canvas = document.createElement("canvas");
								me.editIcons.push(canvas);
							}
							canvas.width = 8;
							canvas.height = 8;
							canvas.style.position = "absolute";
							canvas.style["z-index"] = 200000;
							canvas.style.display = "block";
							if (i == 0) {
								canvas.style.left = me.start.x - 4 + "px";
								canvas.style.top = me.start.y - 4 + "px";
							} else if (i == 1) {
								canvas.style.left = me.middle.x - 4 + "px";
								canvas.style.top = me.middle.y - 4 + "px";

							} else {
								canvas.style.left = me.end.x + "px";
								canvas.style.top = me.end.y - 4 + "px";
							}

							var context = canvas.getContext("2d");
							context.beginPath();
							context.fillStyle = 'green';
							context.arc(4, 4, 4, 0, Math.PI * 2, false);
							context.fill();
							me.items.items[0].el.dom.appendChild(canvas);
						}
						me.initEditEvent();
					},
					drawIcon : function(canvas, context) {
						var me = this;
						/*******************************************************
						 * var x = window.event.clientX; var y =
						 * window.event.clientY; //
						 * console.log(Ext.EventManager.getPageX()); //
						 * console.log(Ext.EventManager.getPageY()); //
						 * console.log(x); // console.log(y); var width = 100;
						 * canvas.width = 500; canvas.height = 500;
						 * context.beginPath(); context.lineWidth = 2;
						 * context.moveTo(x, y + 4); context.lineTo(x + width, y +
						 * 4); context.moveTo(x + width - 10, y);
						 * context.lineTo(x + width, y + 4); context.moveTo(x +
						 * width - 10, y + 0); context.lineTo(x + width, y + 4);
						 ******************************************************/
						canvas.width = me.width - 2;
						canvas.height = me.height - 2;
						context.beginPath();
						context.lineWidth = 2;
						context.moveTo(me.start.x, me.start.y);
						context.lineTo(me.middle.x, me.middle.y);
						context.lineTo(me.end.x, me.end.y);
						context.stroke();

						context.restore();
						context.save();
						context.beginPath();
						context.translate(me.end.x, me.end.y);

						var argel = Math.atan((me.end.y - me.middle.y)
								/ (me.end.x - me.middle.x));
						if (me.end.x - me.middle.x < 0) {
							context.rotate(Math.PI + argel);
						} else {
							context.rotate(Math.PI * 2 + argel);
						}

						context.moveTo(0, 0);
						context.lineTo(-10, 5);
						context.moveTo(0, 0);
						context.lineTo(-10, -5);
						context.stroke();
					},
					drawDropHtml : function() {
						var me = this;
						me.initDropCanvas();
						me.x = 0;
						me.y = 0;
						me.initCanvas();
					},
					drawGhostIcon : function() {
						var me = this;
						me.drawIcon(me.ghostcanvas, me.ghostcontext);
					},
					drawDragIcon : function() {
						var me = this;
						var x = window.event.clientX;
						var y = window.event.clientY;
						me.start = {
							x : x,
							y : y
						};
						me.middle = {
							x : x + 50,
							y : y
						};
						me.end = {
							x : x + 100,
							y : y
						};
						me.drawIcon(me.canvas, me.context);
						me.drawEditIcons();
					},
					initDropCanvas : function() {
						var me = this;
						var context = me.canvas.getContext("2d");
						me.drawDragIcon();
						context.restore();
						context.save();
						me.callParent();
					}
				});