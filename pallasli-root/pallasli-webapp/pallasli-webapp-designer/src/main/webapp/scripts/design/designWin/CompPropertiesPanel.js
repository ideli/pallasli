Ext.define('Pallas.design.designWin.CompPropertiesPanel', {
	extend : "Ext.grid.property.Grid",
	requires : [ 'Pallas.design.designWin.CompProperties'],
	border : false,
	autoHeight : true,
	hideHeaders:true,
	collapsible : true,
	sortableColumns:false,
	saveButton:null,
	loadProperties:function(ctype){
		var me=this;
		me.source=Pallas.design.designWin.CompProperties.source[ctype];
	    me.setSource(me.source);   //修改组件属性的值
	    me.doLayout();
	},
	initComponent : function() {
		var me = this;
		me.saveButton=Ext.create('Ext.button.Button',{text:'save'});
		me.bbar=[me.saveButton];
		me.customEditors=Pallas.design.designWin.CompProperties.customEditors;
		me.propertyNames=Pallas.design.designWin.CompProperties.propertyNames;
		me.source=Pallas.design.designWin.CompProperties.source["text"];
		Pallas.design.designWin.CompPropertiesPanel.superclass.initComponent.call(this);
	}
});
