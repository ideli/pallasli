Ext.define('Pallas.extDesigner.shape.field.Time', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.time' ],
	width : 166,
	height : 36,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 2;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(75, 1, 76, 20);

		me.context.fillStyle = 'lightgray';
		me.context.fillRect(130, 0, 22, 22);

		me.context.moveTo(136, 10);
		me.context.lineTo(142, 15);
		me.context.lineTo(148, 10);
		me.context.stroke();

		me.context.fillStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("时间框:", 5, 5);
		me.context.fillText("12:00 am", 78, 5);
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