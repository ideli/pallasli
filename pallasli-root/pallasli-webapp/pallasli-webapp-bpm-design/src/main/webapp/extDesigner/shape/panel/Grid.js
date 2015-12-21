Ext.define('Pallas.extDesigner.shape.panel.Grid', {
	extend : "Pallas.extDesigner.shape.AbstractShape",
	requires : [],
	alias : [ 'shape.text' ],
	width : 331,
	height : 176,
	resizable : true,
	drawDragIcon : function() {
		var me = this;
		me.context.lineWidth = 2;
		me.context.strokeStyle = 'skyblue';

		var hg = 20;
		var hs = parseInt((me.height - 36) / hg);
		var ls = 5;
		var lk = (me.width - 16) / 5;

		me.context.strokeRect(1, 20, me.width - 16, hg * hs);
		for (var i = 0; i < hs - 1; i++) {
			me.context.moveTo(1, 20 + hg * (i + 1));
			me.context.lineTo(me.width - 16, 20 + hg * (i + 1));
		}
		for (var i = 0; i < ls - 1; i++) {
			me.context.moveTo(lk * (i + 1), 20);
			me.context.lineTo(lk * (i + 1), 20 + hg * hs);
		}
		me.context.stroke();

		me.context.strokeStyle = 'rgb(0,0,0)';
		me.context.font = "11px sans-serif";
		me.context.textBaseline = 'top';
		me.context.textAlign = 'left';
		me.context.fillText("表格:", 5, 5);
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