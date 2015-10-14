Ext.define('Pallas.portal.SysNavigation', {
	extend : "Ext.panel.Panel",
	requires : [ 'Pallas.chat.UserClient'],
	border : false,
	layout : "column",
	autoHeight : true,
	collapsible : false,
	sysMenuButtons : null,
	appMenuButtons : null,
	openModule:function(appKey,moduleKey,params){
		Pallas.portal.SinglePortal.openModule(appKey,moduleKey,params);
	},
	initComponent : function() {
		var me = this;
		
		
		
		var sysToolbar = Ext.create("Ext.toolbar.Toolbar", {
			"border" : false,
			"items" : [ {
				xtype : 'buttongroup',
				columns : 4,
				defaults : {
					scale : 'small'
				},
				items : me.sysMenuButtons
			} ]
		});
		var appToolbar = Ext.create("Ext.toolbar.Toolbar", {
			"border" : false,
			"items" : [ {
				xtype : 'buttongroup',
				columns : 10,
				defaults : {
					scale : 'small'
				},
				items : me.appMenuButtons
			} ]
		});
		me.appMenuPanel = Ext.create("Ext.panel.Panel", {
			"height" : 30,
			"border" : false,
			"tbar" : appToolbar,
			"columnWidth" : me.sysMenuButtons?0.8:1
		});
		me.sysMenuPanel = Ext.create("Ext.panel.Panel", {
			"height" : 30,
			"border" : false,
			"tbar" : sysToolbar,
			"columnWidth" : me.appMenuButtons?0.2:1
		});
		me.items=[];
		if(me.appMenuButtons){
			me.items.push(me.appMenuPanel);
		}
		if(me.sysMenuButtons){
			me.items.push(me.sysMenuPanel);
		}

		me.callParent();

	}
});
