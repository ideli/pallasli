Ext.define('Pallas.portal.waapp.desktop.webpage.QuickBar', {
	extend : "Ext.button.Button",
	requires : [ 'Pallas.portal.waapp.lib.Actions' ],
	text : '应用管理',
	// scale : 'medium',
	iconAlign : 'top',
	iconCls : 'icon-portal-menu-appman',
	menu : new Ext.menu.Menu({
		items : [ Pallas.portal.waapp.lib.Actions.Preferences,
				Pallas.portal.waapp.lib.Actions.SavePreferences,
				// Pallas.portal.waapp.lib.Actions.SaveAsDefaultPreferences,
				// '-',
				Pallas.portal.waapp.lib.Actions.OpenOnlineusers,
				// '-',
				Pallas.portal.waapp.lib.Actions.ChangePassword,
				Pallas.portal.waapp.lib.Actions.OpenAdministrator,
				// '-',
				// Pallas.portal.waapp.lib.Actions.OpenHelp,
				// Pallas.portal.waapp.lib.Actions.Exit,
				'-' ]
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