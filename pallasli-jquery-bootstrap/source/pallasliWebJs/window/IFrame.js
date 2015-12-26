Pallasli.window.IFrame =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["iframe"]=Pallasli.window.IFrame ;
$.extend( Pallasli.window.IFrame.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div class="embed-responsive embed-responsive-16by9">'
			+'<iframe name="{0}" id="{1}" class="embed-responsive-item" src=""></iframe>'
			+'</div>';
		
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left":"right");
		}
		me.initCfg.floatTo=floatTo;
		me.initCfg.dataOrder=["name","id"]; 
	}
});
	