Ext.define('Pallas.extDesigner.shape.field.Display', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.display' ],
	width : 210,
	height : 36,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 0.5;
		me.context.strokeStyle = 'skyblue';
		me.context.strokeRect(75, 1, 120, 20);

		me.context.strokeStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.fillStyle = 'gray';
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("显示框:", 5, 5);
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