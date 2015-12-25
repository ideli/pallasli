/**
   - added support for submenu ("menu" has to be delivered such as "handler")
*/

Ext.namespace('Ext.ux.menu');

Ext.ux.menu.StoreMenu = function(config) {
	Ext.ux.menu.StoreMenu.superclass.constructor.call(this,config);
	if(!this.store){
        //at least url/proxy or data need to be given in config when initiating this component
		this.store = new Ext.data.SimpleStore({
			fields: ['config'],
			url: this.url,
			baseParams: this.baseParams /*,
			proxy:this.proxy,
			data: this.data*/
		});
	}
	this.on('show', this.onMenuLoad, this);
	this.store.on('beforeload', this.onBeforeLoad, this);	
	this.store.on('load', this.onLoad, this);
	
	
};
								   
Ext.extend(Ext.ux.menu.StoreMenu, Ext.menu.Menu, {
	loadingText: Ext.LoadMask.prototype.msg || 'Loading...',
	loaded:      false,

	onMenuLoad: function(){
		if(!this.loaded){
//			if(this.options) {
//				this.store.loadData(this.options);
//			}
//			else {
				this.store.load();
//			}
		}
	},

	updateMenuItems: function(loadedState,records) {
//		var visible = this.isVisible();
//		this.hide(false);
		
		this.removeAll();
//to sync the height of the shadow		
		this.el.sync();	

		if (loadedState) {
		
			for(var i=0, len=records.length; i<len; i++){
//create a real function if a handler or menu is given as a string (because a function cannot really be encoded in JSON
				if (records[i].json.handler) {
					eval("records[i].json.handler = "+records[i].json.handler);
//					records[i].json.handler = new Function(records[i].json.handler);
				}
				if (records[i].json.menu) {
					eval("records[i].json.menu = "+records[i].json.menu);
//					records[i].json.menu = new Function(records[i].json.menu);
				}

				this.add(records[i].json);
			}
//			this.hide();
//			this.show();			
		
			
		}
		else {
			this.add('<span class="loading-indicator">' + this.loadingText + '</span>');
		}

		this.loaded = loadedState;
//		if(visible && loadedState) {
//			this.show(this.getEl().getXY());
//			this.show();			
//		}		
	},

	onBeforeLoad: function(store){
		this.store.baseParams = this.baseParams;
		this.updateMenuItems(false);
	},
	
	onLoad: function(store, records){
		this.updateMenuItems(true,records);		
	}
	

});