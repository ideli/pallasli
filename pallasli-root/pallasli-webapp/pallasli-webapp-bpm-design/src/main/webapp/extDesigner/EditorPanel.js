Ext.define('Pallas.extDesigner.EditorPanel', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.extDesigner.shape.field.*',
			'Pallas.extDesigner.shape.panel.*' ],
	layout : "border",
	border : false,
	autoScroll : true,
	initComponent : function() {
		var me = this;
		var basicEditorPanel = Ext.create('Ext.panel.Panel', {
			layout : {
				type : 'absolute'
			},
			id : 'trucks',
			height : 1000,
			width : 1376,
			resizable : true
		});
		me.items = [ basicEditorPanel ];
		me.callParent();
	}
});