Ext.define('Pallas.extDesigner.shape.field.Trigger', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.trigger' ],
	width : 210,
	height : 36,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 2;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(75, 1, 120, 20);
		me.context.moveTo(174, 1);
		me.context.lineTo(174, 20);
		me.context.stroke();

		me.context.beginPath();
		me.context.lineWidth = 0.5;
		me.context.strokeStyle = 'rgb(0,0,0)';
		me.context.arc(186, 10, 6, 0, Math.PI * 2, false);
		me.context.moveTo(180, 18);
		me.context.lineTo(183, 14);
		me.context.stroke();

		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("弹出框:", 5, 5);
	},
	initComponent : function() {
		var me = this;
		me.attributeData = {
			name : "",
			fieldLabel : "",
			labelWidth : "",
			emptyText : "",
			allowBlank : true,
			blankText : "",
			maxLength : "",
			maxLengthText : "",
			minLength : "",
			minLengthText : "",
			regex : "",
			regexText : "",
			columnWidth : ""
		};
		me.callParent();
	}
});