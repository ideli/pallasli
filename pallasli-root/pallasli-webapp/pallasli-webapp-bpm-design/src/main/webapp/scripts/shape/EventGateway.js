Ext.define('Pallas.activitiDesigner.shape.EventGateway', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.eventgateway' ],
	pallasName : "eventGateway",
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
				+ elementsConfig.eventGateway.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(0, 20);
		context.lineTo(20, 0);
		context.lineTo(40, 20);
		context.lineTo(20, 40);
		context.lineTo(0, 20);
		context.closePath();
		context.stroke();
		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.arc(20, 20, 10, 0, Math.PI * 2, false);
		context.closePath();
		context.stroke();

		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(14, 18);
		context.lineTo(18, 18);
		context.lineTo(18, 14);
		context.lineTo(22, 14);
		context.lineTo(22, 18);
		context.lineTo(26, 18);
		context.lineTo(26, 22);
		context.lineTo(22, 22);
		context.lineTo(22, 26);
		context.lineTo(18, 26);
		context.lineTo(18, 22);
		context.lineTo(14, 22);
		context.lineTo(14, 18);
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