Ext.define('Pallas.extDesigner.AttributePanel', {
	extend : "Ext.grid.property.Grid",
	requires : [ 'Pallas.extDesigner.shape.field.*',
			'Pallas.extDesigner.shape.panel.*' ],

	title : '属性设置',
	sortableColumns : false,
	id : "attributePanel",
	nameColumnWidth : 100,
	columns : [ {
		header : "属性名",
		dataIndex : "name"
	}, {
		header : "值",
		dataIndex : "value"
	} ],
	initComponent : function() {
		var me = this;
		me.source = {};
		me.callParent();
		me.columns[0].setText("属性名");
		me.columns[1].setText("值");
	}
});