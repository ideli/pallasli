Ext.define('Pallas.design.designWin.ComponentSelectPanel', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.design.designWin.Components'],
	border : false,
	autoHeight : true,
	collapsible : true,
	initComponent : function() {
		var me = this;
		Pallas.design.designWin.ComponentSelectPanel.superclass.initComponent.call(this);
	}
});
