
Ext.namespace("Mixky.wasoft.app");

Mixky.wasoft.app.Application = function(cfg){
	
	Ext.apply(this, cfg);
	
	this.initialize();
}

Mixky.wasoft.app.Application.prototype = {
		
	key : 'key',

	name : '标识',

	caption : '名称',
	
	icon : 'icon-portal-default',
	
	menus : [],
	
	initialize : function(){
		
	},
	
	getMenuButtons : function(){
		return [];
	},
	
	loadDictionarys : function(){
		
	},
	
	loadModules : function(){
		
	},
	
	loadDocuments : function(){
		
	},
	
	loadViews : function(){
		
	}
};