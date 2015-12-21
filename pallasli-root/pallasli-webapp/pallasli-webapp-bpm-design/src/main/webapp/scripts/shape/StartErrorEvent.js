Ext.define('Pallas.activitiDesigner.shape.StartErrorEvent', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.starterrorevent' ],
	pallasName : "startErrorEvent",
	width : 50,
	height : 50,
	initComponent : function(config) {
		var me = this;
		me.callParent();
	},
	drawDragHtml : function() {
		var me = this;
		var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var iconPath = baseConfig.imagesDependencesPath
				+ elementsConfig.startErrorEvent.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.beginPath();
		context.lineWidth = 1;
		context.fillStyle = "white";
		context.arc(20, 20, 16, 0, Math.PI * 2, false);
		context.strokeStyle = 'rgb(0,0,0)';
		context.closePath();
		context.stroke();

		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(5, 30);
		context.lineTo(14, 6);
		context.lineTo(28, 24);
		context.lineTo(35, 10);
		context.lineTo(28, 34);
		context.lineTo(14, 16);
		context.lineTo(5, 30);
		context.closePath();
		context.stroke();
	},
	drawGhostIcon : function() {
		var me = this;
		me.drawIcon(me.ghostcanvas, me.ghostcontext);
	},
	drawDragIcon : function() {
		var me = this;
		me.drawIcon(me.canvas, me.context);
	},
	initDropCanvas : function() {
		var me = this;
		me.callParent();
	}
});