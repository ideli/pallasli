Ext.define('Pallas.activitiDesigner.shape.CallActivity', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.callactivity' ],
	pallasName : "callActivity",
	width : 100,
	height : 100,
	initComponent : function(config) {
		var me = this;

		me.callParent();
	},
	drawDragHtml : function() {
		var me = this;
		var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var iconPath = baseConfig.imagesDependencesPath
				+ elementsConfig.callActivity.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		var me = this;
		canvas.width = me.width;
		canvas.height = me.height;
		context.lineWidth = 2;
		context.strokeStyle = 'rgb(0,0,0)';
		context.strokeRect(1, 1, me.width - 12, me.height - 12);

		context.beginPath();
		context.lineWidth = 2;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(15, 20);
		context.lineTo(25, 20);
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
		var context = me.canvas.getContext("2d");
		me.drawDragIcon();
		context.restore();
		context.save();
		me.callParent();
	}
});