Ext.define('Pallas.activitiDesigner.shape.StartNoneEvent', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.startnoneevent' ],
	pallasName : "startNoneEvent",
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
				+ elementsConfig.startNoneEvent.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		var h = canvas.height;
		var w = canvas.width;

		canvas.width = w > h ? w : h;
		canvas.height = h > w ? h : w;
		context.lineWidth = 1;
		context.fillStyle = "white";
		context.arc(canvas.width / 2, canvas.height / 2, canvas.width * 0.4, 0,
				Math.PI * 2, false);
		context.strokeStyle = 'rgb(0,0,0)';
		context.fill();
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