Mixky.wasoft.desktop.QuickBar = function(config){
	Mixky.wasoft.desktop.QuickBar.superclass.constructor.call( this, Ext.apply({
		text : '应用管理',
	    //scale : 'medium',
		iconAlign : 'top',
		iconCls : 'icon-portal-menu-appman',
		menu : new Ext.menu.Menu({
			items : [
			    Mixky.wasoft.lib.actions.Preferences,
			    Mixky.wasoft.lib.actions.SavePreferences,
			  //  Mixky.wasoft.lib.actions.SaveAsDefaultPreferences,
			   // '-',
			   // Mixky.wasoft.lib.actions.OpenOnlineusers,
			    Mixky.wasoft.lib.actions.OpenSysState,
			  //  '-',
			    Mixky.wasoft.lib.actions.ChangePassword,
			    Mixky.wasoft.lib.actions.OpenAdministrator,
			 //   '-',
			 //   Mixky.wasoft.lib.actions.OpenHelp,
			 //   Mixky.wasoft.lib.actions.Exit,
			    '-']
		})
	}, config));
};

Ext.extend( Mixky.wasoft.desktop.QuickBar, Ext.Button, {
	appendButton : function(item){
		this.menu.add(item);
	},
	removeButton : function(btntype, appkey, key){
		var item = this.getQuickStartCmp(btntype, appkey, key);
		if(Ext.isDefined(item)){
			this.menu.remove(item);
		}
	},
	getQuickStartCmp : function(btntype, appkey, key){
		for (var i = 0; i < this.menu.items.length; i++) {
			var item = this.menu.items.get(i);
			if(item && item.btntype == btntype && item.applicationkey == appkey && item.key == key){
				return item;
			}
		}
	}
});