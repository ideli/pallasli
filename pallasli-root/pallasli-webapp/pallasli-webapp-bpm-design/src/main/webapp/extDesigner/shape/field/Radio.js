Ext.define('Pallas.extDesigner.shape.field.Radio', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.radio' ],
	width : 120,
	height : 35,
	drawDragIcon : function() {
		var me = this;
		me.context.beginPath();
		me.context.lineWidth = 0.5;
		me.context.strokeStyle = 'skyblue';
		me.context.arc(86, 11, 6, 0, Math.PI * 2, false);
		me.context.stroke();
		me.context.closePath();
		me.context.beginPath();
		me.context.lineWidth = 1.5;
		me.context.arc(86, 11, 9, 0, Math.PI * 2, false);
		me.context.stroke();
		me.context.closePath();

		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("复选框:", 5, 5);
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