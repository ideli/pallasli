Ext.define('Pallas.activitiDesigner.shape.Pool', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.pool' ],
	pallasName : "pool",
	width : 850,
	height : 250,
	initComponent : function(config) {
		var me = this;
		me.callParent();
	},
	drawDragHtml : function() {
		var me = this;
		var elementsConfig = Pallas.activitiDesigner.config.ElementsConfig;
		var baseConfig = Pallas.activitiDesigner.config.BaseConfig;
		var iconPath = baseConfig.imagesDependencesPath
				+ elementsConfig.pool.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawEndIcon : function(canvas, context) {
		context.strokeStyle = 'rgb(0,0,0)';
		context.strokeRect(0, 0, 10, 20);
		context.strokeRect(10, 0, 30, 20);
	},
	drawIcon : function(canvas, context) {
		var me = this;
		canvas.height = canvas.height * 0.5;
		context.lineWidth = 1;
		me.drawEndIcon(canvas, context);
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
		// context.clearRect(0, 0, 300, 300);
		me.canvas.width = me.canvas.width * 20;
		me.canvas.height = me.canvas.height * 10;
		context.scale(20, 10);
		context.lineWidth = 0.1;
		me.drawEndIcon(me.canvas, context);
		context.restore();
		context.save();
		me.callParent();
	}
});