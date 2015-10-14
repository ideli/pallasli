Ext.define('Pallas.design.portal.ModuleMenuPage', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.design.portal.ModuleTreePanel',
	             'Pallas.design.portal.ModuleMain'
	             ],
	border : false,
	autoHeight : true,
	collapsible : true,
	moduleTree : null,
	moduleMain : null,
	initComponent : function() {
		var me = this;
		if(!me.moduleTree){
			me.moduleTree=Ext.create('Pallas.design.portal.ModuleTreePanel',{
				region:'north',
				height:30
				
			});
		}
 
		if(!me.moduleMain){
			me.moduleMain=Ext.create('Pallas.design.portal.ModuleMain',{
				region:'center'
				
			});
		}
		me.items=[me.moduleTree,me.moduleMain];
        me.callParent();
	}
});
