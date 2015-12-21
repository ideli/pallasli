Ext.define('Pallas.activitiDesigner.BaseAttributePanel', {
	extend : "Ext.grid.property.Grid",
	requires : [],
	title : '常用属性',
	sortableColumns : false,
	initComponent : function() {
		var me = this;
		me.source = {};
		me.callParent();
		me.columns[0].setText("属性名");
		me.columns[1].setText("值");
	}
});
