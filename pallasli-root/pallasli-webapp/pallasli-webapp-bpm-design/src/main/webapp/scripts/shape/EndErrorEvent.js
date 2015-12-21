Ext.define('Pallas.activitiDesigner.shape.EndErrorEvent', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.enderrorevent' ],
	pallasName : "endErrorEvent",
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
				+ elementsConfig.endErrorEvent.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.beginPath();
		context.lineWidth = 5;
		context.fillStyle = "white";
		context.arc(20, 20, 16, 0, Math.PI * 2, false);
		context.strokeStyle = 'rgb(0,0,0)';
		context.closePath();
		context.stroke();

		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.fillStyle = "rgb(0,0,0)";
		context.moveTo(9, 28);
		context.lineTo(18, 10);
		context.lineTo(24, 20);
		context.lineTo(32, 12);
		context.lineTo(24, 30);
		context.lineTo(18, 20);
		context.lineTo(9, 28);
		context.closePath();
		context.fill();
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