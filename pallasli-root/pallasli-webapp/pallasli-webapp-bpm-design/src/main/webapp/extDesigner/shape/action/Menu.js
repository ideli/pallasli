Ext.define('Pallas.extDesigner.shape.action.Menu', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.button' ],
	width : 83,
	height : 35,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 0.5;
		me.context.fillStyle = 'skyblue';
		me.context.fillRect(0, 0, 70, 20);

		me.context.fillStyle = 'rgb(0,0,0)';
		me.context.strokeStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("按   钮", 15, 5);
	},
	initComponent : function() {
		var me = this;
		me.attributeData = {
			name : "",
			text : "",
			width : "",
			height : "",
			handler : ""
		};
		me.callParent();
	}
});