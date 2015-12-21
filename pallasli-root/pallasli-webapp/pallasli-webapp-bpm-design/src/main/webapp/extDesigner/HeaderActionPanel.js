Ext.define('Pallas.extDesigner.HeaderActionPanel', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.extDesigner.shape.field.*',
			'Pallas.extDesigner.shape.panel.*' ],

	autoHeight : true,
	border : false,
	bodyStyle : {
		'background-color' : 'transparent'
	},
	layout : "table",
	tbar : [ {
		xtype : "button",
		text : "新建"
	}, {
		xtype : "button",
		text : "打开"
	}, {
		xtype : "button",
		text : "保存"
	}, {
		xtype : "button",
		text : "另存"
	}, {
		xtype : "button",
		text : "导出"
	}, {
		xtype : "button",
		text : "导入"
	} ],

	initComponent : function() {
		var me = this;
		me.callParent();
	}
});