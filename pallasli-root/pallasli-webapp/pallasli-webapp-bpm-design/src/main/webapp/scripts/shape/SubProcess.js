Ext.define('Pallas.activitiDesigner.shape.SubProcess', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.subprocess' ],
	pallasName : "subProcess",
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
				+ elementsConfig.subProcess.dragIcon;
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
		context.lineWidth = 2;
		context.strokeStyle = 'rgb(0,0,0)';
		context.strokeRect((me.width - 12) * 0.2, (me.height - 12) * 0.7,
				(me.width - 12) * 0.3, (me.height - 12) * 0.3);

		context.beginPath();
		context.lineWidth = 2;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo((me.width - 12) * 0.25, (me.height - 12) * 0.85);
		context.lineTo((me.width - 12) * 0.45, (me.height - 12) * 0.85);
		context.moveTo((me.width - 12) * 0.35, (me.height - 12) * 0.75);
		context.lineTo((me.width - 12) * 0.35, (me.height - 12) * 0.95);
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