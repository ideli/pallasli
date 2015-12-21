Ext.define('Pallas.activitiDesigner.shape.ParallelGateway', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.parallelgateway' ],
	pallasName : "parallelGateway",
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
				+ elementsConfig.parallelGateway.dragIcon;
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
		context.lineWidth = 5;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(10, 20);
		context.lineTo(30, 20);
		context.closePath();
		context.stroke();
		context.beginPath();
		context.lineWidth = 5;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(20, 10);
		context.lineTo(20, 30);
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