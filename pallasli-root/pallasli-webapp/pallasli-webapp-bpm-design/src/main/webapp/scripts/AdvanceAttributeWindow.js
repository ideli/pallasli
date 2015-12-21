Ext.define('Pallas.activitiDesigner.AdvanceAttributeWindow', {
	extend : "Ext.window.Window",
	requires : [ 'Pallas.activitiDesigner.utils.AllComponents',
			'Pallas.activitiDesigner.advanceAttributePanel.Basic',
			'Pallas.activitiDesigner.advanceAttributePanel.Form',
			'Pallas.activitiDesigner.advanceAttributePanel.Handler',
			'Pallas.activitiDesigner.advanceAttributePanel.Message',
			'Pallas.activitiDesigner.advanceAttributePanel.Time',
			'Pallas.activitiDesigner.advanceAttributePanel.Index'

	],
	border : false,
	layout : "fit",
	width : 800,
	height : 500,
	constrain : true,
	collapsible : false,
	initComponent : function() {
		var me = this;
		var panel = Ext.create('Ext.tab.Panel', {
			items : []
		});

		var shapes = Pallas.activitiDesigner.utils.AllComponents.shapes;
		var pallasName = "";
		for ( var key in shapes) {
			if (shapes[key].isActived) {
				pallasName = shapes[key].pallasName;
				break;
			}
		}
		var atrributeConfig = Pallas.activitiDesigner.config.AtrributeConfig;
		var atrribute_shape = atrributeConfig[pallasName];

		for ( var key in atrribute_shape.advanceSource) {
			var tab1 = Ext.create('advanceattributepanel.' + key);
			panel.add(tab1);
		}

		me.items = [ panel ];
		me.callParent();
	}
});
