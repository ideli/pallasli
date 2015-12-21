Ext.define('Pallas.activitiDesigner.shape.TextAnnotation', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.textannotation' ],
	pallasName : "textAnnotation",
	initComponent : function(config) {
		var me = this;
		me.callParent();
	},
	drawDragHtml : function() {
		var me = this;
		var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var iconPath = baseConfig.imagesDependencesPath
				+ elementsConfig.textAnnotation.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(0, 40);
		context.lineTo(4, 35);
		context.moveTo(8, 30);
		context.lineTo(12, 25);
		context.moveTo(16, 20);
		context.lineTo(20, 15);
		context.closePath();
		context.stroke();

		context.beginPath();
		context.lineWidth = 2;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(40, 0);
		context.lineTo(20, 0);
		context.lineTo(20, 20);
		context.lineTo(40, 20);
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