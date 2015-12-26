Pallasli.action.Split =  function(cfg){
	Pallasli.action.Button.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["split"]=Pallasli.action.Split;
$.extend( Pallasli.action.Split.prototype, Pallasli.action.Button.prototype, {
	initComponent : function(cfg) {
		this.tmpl =  '<span >|</span>';
			this.dataOrder = [ ];
	}
});