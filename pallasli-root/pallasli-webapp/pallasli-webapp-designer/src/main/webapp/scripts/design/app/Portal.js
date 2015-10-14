Ext.define('Pallas.design.portal.Portal', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.design.portal.SysHeader',
	             'Pallas.design.portal.SysFooter',
	             'Pallas.design.portal.SysMain'
	             ],
	border : false,
	layout : 'border',
	autoHeight : true,
	collapsible : false,
	menuHeader:null,
	statusFooter:null,
	pageMain:null,
	openviewInMain : Ext.emptyFn(),
	initComponent : function() {
		var me = this;
		
		if(!me.menuHeader){
			me.menuHeader=Ext.create('Pallas.design.portal.SysHeader',{
				region:'north',
				height:30
				
			});
		}

		if(!me.statusFooter){
			me.statusFooter=Ext.create('Pallas.design.portal.SysFooter',{
				region:'south',
				height:30
				
			});
		}
		if(!me.pageMain){
			me.pageMain=Ext.create('Pallas.design.portal.SysMain',{
				region:'center',
				openviewInMain:me.openviewInMain
				
			});
		}
		me.items=[me.menuHeader,me.statusFooter,me.pageMain];
        me.callParent();
	}
});
