Pallasli.panel.Fieldset =  function(cfg){
	Pallasli.panel.Panel.prototype.initialize.call(this,cfg); 
};  
Pallasli.alias["fieldset"]=Pallasli.panel.Fieldset ;
$.extend( Pallasli.panel.Fieldset.prototype, Pallasli.panel.Panel.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div class=" ">' 
			+'</div>';
		
		var floatTo="";
		if(this.initCfg.region){
			floatTo="float:"+(this.initCfg.region=="west"?"left":"right");
		}
		cfg.floatTo=floatTo;
		me.initCfg=cfg;
		me.items = me.initCfg.items;
		me.dataOrder=[]; 
	},
	addItems : function(){ 
		var me=this; 
		for(var index in me.items){
			var menu=me.items[index]; 
			if(!menu.getHtml){
				menu.xtype=menu.xtype||"textfield";
				if(menu.xtype){ 
					menu=new Pallasli.alias[menu.xtype](menu);
				} 
			} 
			me.add(menu);
		}
	}
});
	