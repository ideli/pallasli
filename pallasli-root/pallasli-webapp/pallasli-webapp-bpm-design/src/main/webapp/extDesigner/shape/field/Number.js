Ext.define('Pallas.extDesigner.shape.field.Number', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.number' ],
	width : 210,
	height : 36,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 2;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(75, 1, 120, 20);

		me.context.fillStyle = 'lightgray';
		me.context.fillRect(174, 0, 22, 11);
		me.context.fillRect(174, 12, 22, 11);

		me.context.moveTo(179, 8);
		me.context.lineTo(185, 3);
		me.context.lineTo(191, 8);

		me.context.moveTo(179, 14);
		me.context.lineTo(185, 19);
		me.context.lineTo(191, 14);
		me.context.stroke();

		me.context.fillStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("数值框:", 5, 5);
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