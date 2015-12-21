Ext.define('Pallas.activitiDesigner.shape.EventSubProcess', {
	extend : "Pallas.activitiDesigner.shape.AbstractShape",
	requires : [ "Pallas.activitiDesigner.config.ElementsConfig",
			"Pallas.activitiDesigner.config.BaseConfig" ],
	alias : [ 'widget.eventsubprocess' ],
	pallasName : "eventSubProcess",
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
				+ elementsConfig.eventSubProcess.dragIcon;
		me.initialConfig.dragIcon = "url('" + iconPath + "')";
		me.callParent();
	},
	drawIcon : function(canvas, context) {
		var me = this;
		canvas.width = me.width;
		canvas.height = me.height;
		context.beginPath();
		context.lineWidth = 2;
		context.strokeStyle = "rgb(0,0,0)";

		var i = 1;
		while (true) {
			if (15 * i < (me.width - 12)) {
				context.moveTo(1, 15 * (i - 1));
				context.lineTo(1, 15 * (i - 1) + 10);
				i++;
			} else {
				context.moveTo(1, 15 * (i - 1));
				context.lineTo(1, me.width - 12);
				break;
			}
		}
		i = 1;
		while (true) {
			if (15 * i < (me.height - 12)) {
				context.moveTo(me.width - 12, 15 * (i - 1));
				context.lineTo(me.width - 12, 15 * (i - 1) + 10);
				i++;
			} else {
				context.moveTo(me.width - 12, 15 * (i - 1));
				context.lineTo(me.width - 12, me.height - 12);
				break;
			}
		}
		i = 1;
		while (true) {
			if (15 * i < (me.width - 12)) {
				context.moveTo(15 * (i - 1), me.height - 12);
				context.lineTo(15 * (i - 1) + 10, me.height - 12);
				i++;
			} else {
				context.moveTo(15 * (i - 1), me.height - 12);
				context.lineTo(me.width - 12, me.height - 12);
				break;
			}
		}
		i = 1;
		while (true) {
			if (15 * i < (me.height - 12)) {
				context.moveTo(15 * (i - 1), 1);
				context.lineTo(15 * (i - 1) + 10, 1);
				i++;
			} else {
				context.moveTo(15 * (i - 1), 1);
				context.lineTo(me.width - 12, 1);
				break;
			}
		}

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