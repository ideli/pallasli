Ext.define('Pallas.portal.SysMenu', {
	extend : "Ext.menu.Item",
	
	handler:function(){
		var me=this;
		if(me.attributes.isActionMenu){
			var appKey=me.attributes.appKey;
			var moduleKey=me.attributes.moduleKey;
			var params={title:me.text};
			Pallas.portal.SinglePortal.openModule(appKey,moduleKey,params);
		}
	},

	initComponent : function() {
		var me=this;
        me.callParent();
	}
	
	
});