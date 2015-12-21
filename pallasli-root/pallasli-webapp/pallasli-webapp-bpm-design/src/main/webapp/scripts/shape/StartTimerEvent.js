Ext.define('Pallas.activitiDesigner.shape.StartTimerEvent', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.starttimerevent' ],
	pallasName : "startTimerEvent",
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
				+ elementsConfig.startTimerEvent.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		context.lineWidth = 1;
		context.fillStyle = "white";
		context.arc(20, 20, 16, 0, Math.PI * 2, false);
		context.strokeStyle = 'rgb(0,0,0)';
		context.fill();
		context.stroke();

		context.beginPath();
		context.lineWidth = 1;
		context.strokeStyle = "rgb(0,0,0)";
		context.moveTo(4, 20);
		context.lineTo(8, 20);
		context.moveTo(20, 4);
		context.lineTo(20, 8);
		context.moveTo(20, 36);
		context.lineTo(20, 32);
		context.moveTo(36, 20);
		context.lineTo(32, 20);

		context.moveTo(28, 7);
		context.lineTo(26, 10);
		context.moveTo(33, 11);
		context.lineTo(31, 14);

		context.moveTo(28, 33);
		context.lineTo(26, 30);
		context.moveTo(33, 29);
		context.lineTo(31, 26);

		context.moveTo(12, 7);
		context.lineTo(14, 10);
		context.moveTo(7, 11);
		context.lineTo(9, 14);

		context.moveTo(12, 33);
		context.lineTo(14, 30);
		context.moveTo(7, 29);
		context.lineTo(9, 26);

		context.moveTo(20, 9);
		context.lineTo(20, 20);
		context.lineTo(28, 20);

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