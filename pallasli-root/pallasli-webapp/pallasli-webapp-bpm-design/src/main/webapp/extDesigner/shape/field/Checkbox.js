Ext.define('Pallas.extDesigner.shape.field.Checkbox', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.checkbox' ],
	width : 120,
	height : 35,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 0.5;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(75, 4, 13, 12);
		me.context.lineWidth = 1;
		me.context.strokeRect(72, 1, 19, 18);

		me.context.strokeStyle = 'rgb(0,0,0)';
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