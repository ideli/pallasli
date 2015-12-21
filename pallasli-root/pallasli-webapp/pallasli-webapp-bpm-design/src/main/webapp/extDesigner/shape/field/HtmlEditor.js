Ext.define('Pallas.extDesigner.shape.field.HtmlEditor', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.htmleditor' ],
	width : 391,
	height : 156,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 2;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(76, 1, 300, 140);

		me.context.moveTo(76, 25);
		me.context.lineTo(380, 25);
		me.context.stroke();

		me.context.strokeStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("html编辑框:", 5, 5);

		me.context.font = "24px sans-serif";
		me.context.fillText("A", 80, 2);
		me.context.fillText("I", 95, 2);
		me.context.fillText("B", 110, 2);
		me.context.fillText("U", 125, 2);
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