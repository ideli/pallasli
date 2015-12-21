Ext.define('Pallas.extDesigner.shape.field.Date', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.date' ],
	width : 210,
	height : 36,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 2;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(75, 1, 120, 20);

		me.context.fillStyle = 'lightgray';
		me.context.fillRect(176, 2, 18, 18);

		me.context.moveTo(175, 1);
		me.context.lineTo(175, 20);
		me.context.moveTo(174, 5);
		me.context.lineTo(194, 5);
		me.context.moveTo(174, 9);
		me.context.lineTo(194, 9);
		me.context.moveTo(174, 13);
		me.context.lineTo(194, 13);
		me.context.moveTo(174, 17);
		me.context.lineTo(194, 17);

		me.context.moveTo(179, 1);
		me.context.lineTo(179, 20);
		me.context.moveTo(183, 1);
		me.context.lineTo(183, 20);
		me.context.moveTo(187, 1);
		me.context.lineTo(187, 20);
		me.context.moveTo(191, 1);
		me.context.lineTo(191, 20);
		me.context.stroke();

		me.context.fillStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("日期框:", 5, 5);
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