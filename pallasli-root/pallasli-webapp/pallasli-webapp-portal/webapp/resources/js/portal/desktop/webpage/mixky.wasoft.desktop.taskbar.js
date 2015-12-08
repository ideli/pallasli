Mixky.wasoft.desktop.TaskBar = function(config){
	Mixky.wasoft.desktop.TaskBar.superclass.constructor.call( this, Ext.apply({
		text : '活动页面',
		hidden:false,
	    //scale : 'medium',
		iconAlign : 'top',
		iconCls : 'icon-portal-menu-livepage',
		menu : new Ext.menu.Menu({
			items : [{
				iconCls : "icon-portal-menu-livepage-closeall",
				text : "关闭所有",
				handler : function(){
					MixkyApp.desktop.closeAllWindow();
				}
			}, '-']
		})
	}, config));
};

Ext.extend( Mixky.wasoft.desktop.TaskBar, Ext.Button, {
	appendWindow : function(win){
		var item = this.menu.addMenuItem({
			iconCls: win.iconCls,
			checked: true,
			text : Ext.util.Format.ellipsis(win.title, 12),
			tooltip: win.taskbuttonTooltip || win.title,
			handler: function() {
				win.show();
				win.toFront();
			}
		});
		item.win = win;
	},
	removeWindow : function(win){
		var item = this.findWindowItem(win);
		if(Ext.isDefined(item)){
			this.menu.remove(item);
		}
	},
	markWindowShow : function(win){
		var item = this.findWindowItem(win);
		if(Ext.isDefined(item)){
			item.setChecked(true);
		}
	},
	markWindowHide : function(win){
		var item = this.findWindowItem(win);
		if(Ext.isDefined(item)){
			item.setChecked(false);
		}
	},
	findWindowItem : function(win){
		for (var i = 0; i < this.menu.items.length; i++) {
			if (this.menu.items.get(i).win == win) {
				return this.menu.items.get(i);
			}
		}
	}
});