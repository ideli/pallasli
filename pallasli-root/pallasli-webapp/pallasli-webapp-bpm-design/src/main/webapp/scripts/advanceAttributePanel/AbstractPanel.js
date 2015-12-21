Ext.define('Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel', {
	extend : "Ext.form.Panel",
	requires : [ 'Pallas.activitiDesigner.config.AtrributeConfig' ],
	autoScroll : true,
	buttonAlign : "center",
	bodyPadding : 10,
	defaultType : 'textfield',
	layout : {
		type : "column",
		align : 'middle'
	},
	defaults : {
		padding : 2,
		labelWidth : 100,
		columnWidth : 0.5
	},
	initComponent : function() {
		var me = this;

		me.fbar = [ {
			text : "保存"
		}, {
			text : "重置"
		} ];

		me.items = [];
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
		var basicFields = atrribute_shape.advanceSource[me.simpleName];

		var config = atrributeConfig.baseSourceConfig;
		for ( var key in basicFields) {
			var bf = basicFields[key];
			var cf = config[key];
			var displayName = bf.displayName ? bf.displayName : cf.displayName;
			var editor = atrributeConfig.baseSourceConfig[key].editor;
			var comptype = editor.comptype ? editor.comptype : editor.xtype;

			var f = {};
			Ext.apply(f, editor);
			Ext.apply(f, {
				fieldLabel : displayName,
				title : displayName,
				xtype : comptype
			});
			Ext.apply(f, basicFields[key]);

			me.items.push(f);
		}
		me.callParent();
	}
});
