Ext.define('Pallas.activitiDesigner.advanceAttributePanel.Time', {
	extend : "Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel",
	title : "时间",
	simpleName : "time",
	alias : [ 'advanceattributepanel.time' ],
	initComponent : function() {
		var me = this;

		me.items = [ {
			fieldLabel : "是否延期执行",
			value : " "
		}, {
			fieldLabel : "延期时间",
			value : " "
		}, {
			fieldLabel : "是否设置过期",
			value : " "
		}, {
			fieldLabel : "过期时间",
			value : " "
		}, {
			fieldLabel : "流程版本",
			value : " "
		} ];
		me.callParent();
	}
});
