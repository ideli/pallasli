Ext.define('Pallas.portal.waapp.desktop.webpage.TaskBar', {
	extend : "Ext.button.Button",

	text : '活动页面',
	hidden : false,
	// scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-livepage',
	menu : new Ext.menu.Menu({
		items : [ {
			iconCls : "icon-portal-menu-livepage-closeall",
			text : "关闭所有",
			handler : function() {
				MixkyApp.desktop.closeAllWindow();
			}
		}, '-' ]
	}),
	appendButton : function(item) {
		this.menu.add(item);
	},
	removeButton : function(btntype, appkey, key) {
		var item = this.getQuickStartCmp(btntype, appkey, key);
		if (Ext.isDefined(item)) {
			this.menu.remove(item);
		}
	},
	getQuickStartCmp : function(btntype, appkey, key) {
		for (var i = 0; i < this.menu.items.length; i++) {
			var item = this.menu.items.get(i);
			if (item && item.btntype == btntype
					&& item.applicationkey == appkey && item.key == key) {
				return item;
			}
		}
	}

});