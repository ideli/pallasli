Ext.define('Pallas.activitiDesigner.shape.Association', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.association' ],
	pallasName : "association",
	initComponent : function(config) {
		var me = this;

		me.callParent();
	},
	drawDragHtml : function() {
		var me = this;
		var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var iconPath = baseConfig.imagesDependencesPath
				+ elementsConfig.association.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(0, 40);
		context.lineTo(5, 35);
		context.moveTo(10, 30);
		context.lineTo(15, 25);
		context.moveTo(20, 20);
		context.lineTo(25, 15);
		context.moveTo(30, 10);
		context.lineTo(35, 5);
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