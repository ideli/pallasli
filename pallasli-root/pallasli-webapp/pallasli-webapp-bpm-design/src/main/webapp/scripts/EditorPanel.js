Ext.define('Pallas.activitiDesigner.EditorPanel', {
	extend : "Ext.panel.Panel",
	autoScroll : true,
	// bodyStyle : "background-color:lightBlue",
	requires : [ 'Pallas.activitiDesigner.AdvanceAttributeWindow',
			"Pallas.activitiDesigner.config.AtrributeConfig" ],
	initComponent : function() {
		var me = this;
		var atrributeConfig = Pallas.activitiDesigner.config.AtrributeConfig;
		me.baseSource = atrributeConfig.processInstance.baseSource;
		me.baseSourceConfig = atrributeConfig.baseSourceConfig;
		var allComponents = Pallas.activitiDesigner.utils.AllComponents;
		var shapeDatas = allComponents.shapeDatas;
		var baseAttribute = shapeDatas.processInstance.baseAttributeData;

		me.callParent();
		me.on("render", function() {

			me.el.dom.onclick = function() {
				var shapes = allComponents.shapes;
				for ( var key in shapes) {
					shapes[key].isActived = false;
				}
				var attributePanel = allComponents.attributePanel;
				attributePanel.setSource(me.baseSource, me.baseSourceConfig);
				for ( var key in baseAttribute) {
					attributePanel.setProperty(key, baseAttribute[key], true);
				}
			};

		});
	}
});
