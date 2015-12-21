Ext.define('Pallas.activitiDesigner.shape.ThrowSignalEvent', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.throwsignalevent' ],
	pallasName : "throwSignalEvent",
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
				+ elementsConfig.throwSignalEvent.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.lineWidth = 1;
		context.beginPath();
		context.fillStyle = "white";
		context.arc(20, 20, 16, 0, Math.PI * 2, false);
		context.strokeStyle = 'rgb(0,0,0)';
		context.closePath();
		context.stroke();
		context.beginPath();
		context.fillStyle = "white";
		context.arc(20, 20, 12, 0, Math.PI * 2, false);
		context.strokeStyle = 'rgb(0,0,0)';
		context.closePath();
		context.stroke();

		context.beginPath();
		context.lineWidth = 1;
		context.fillStyle = "rgb(0,0,0)";
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(20, 8);
		context.lineTo(30, 27);
		context.lineTo(10, 27);
		context.lineTo(20, 8);
		context.closePath();
		context.stroke();
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