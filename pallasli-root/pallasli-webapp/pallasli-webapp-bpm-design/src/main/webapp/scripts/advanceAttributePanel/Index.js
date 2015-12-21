Ext.define('Pallas.activitiDesigner.advanceAttributePanel.Index', {
	extend : "Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel",
	title : "指标",
	simpleName : "index",
	alias : [ 'advanceattributepanel.index' ],
	initComponent : function() {
		var me = this;

		me.items = [ {
			fieldLabel : "绩效指标1",
			value : " "
		}, {
			fieldLabel : "绩效指标2",
			value : " "
		}, {
			fieldLabel : "绩效指标3",
			value : " "
		}, {
			fieldLabel : "绩效指标4",
			value : " "
		}, {
			fieldLabel : "绩效指标5",
			value : " "
		} ];
		me.callParent();
	}
});
