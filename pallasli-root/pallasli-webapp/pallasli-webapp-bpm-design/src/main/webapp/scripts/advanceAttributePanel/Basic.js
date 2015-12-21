Ext.define('Pallas.activitiDesigner.advanceAttributePanel.Basic', {
	extend : "Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel",
	title : "基本",
	simpleName : "basic",
	alias : [ 'advanceattributepanel.basic' ],
	initComponent : function() {
		var me = this;

		me.items = [ {
			fieldLabel : "元素唯一标识",
			value : " "
		}, {
			fieldLabel : "名称",
			value : " "
		}, {
			fieldLabel : "描述",
			value : " "
		}, {
			fieldLabel : "命名空间",
			value : " "
		}, {
			fieldLabel : "流程作者",
			value : " "
		}, {
			fieldLabel : "流程版本",
			value : " "
		}, {
			fieldLabel : "脚本格式",
			value : " "
		}, {
			fieldLabel : "任务脚本",
			value : " "
		}, {
			fieldLabel : "规则任务",
			value : " "
		}, {
			fieldLabel : "流程版本",
			value : " "
		}, {
			fieldLabel : "任务优先级",
			value : " "
		}, {
			fieldLabel : "任务过期时间",
			value : " "
		}, {
			fieldLabel : "是否异步执行",
			value : " "
		}, {
			fieldLabel : "是否排它执行",
			value : " "
		}, {
			fieldLabel : "是否补偿",
			value : " "
		}, {
			fieldLabel : "是否缺省路由",
			value : " "
		}, {
			fieldLabel : "是否条件路由",
			value : " "
		}, {
			fieldLabel : "路由条件表达式",
			value : " "
		} ];
		me.callParent();
	}
});
