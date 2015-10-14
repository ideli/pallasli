Ext.define('Pallas.design.portal.SysMain', {
	extend : "Ext.tab.Panel",
	requires : [ 'Pallas.design.portal.SysMenuPage'
	             ],
	border : true,
	autoHeight : true,
	openviewInMain : Ext.emptyFn(),
	collapsible : false,
	initComponent : function() {
		var me = this;
		/**var tab=Ext.create('Pallas.design.portal.SysMenuPage',{
			title:'tab',

			openviewInMain : me.openviewInMain
		});
		me.items=[tab];**/
        me.callParent();
	}
});
