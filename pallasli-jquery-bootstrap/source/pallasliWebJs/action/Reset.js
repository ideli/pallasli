Pallasli.action.Reset =  function(cfg){
	Pallasli.action.Button.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["reset"]=Pallasli.action.Reset;
$.extend( Pallasli.action.Reset.prototype, Pallasli.action.Button.prototype, {
	initComponent : function(cfg) {
		this.tmpl =  '<div class="col-xs-12 col-md-6">'
			+ '<div class="row form-group">'
			+ '<div class="col-xs-7 col-md-9">'
			+ '<input   type="{2}" '
			+ 'class="btn btn-default" id="{0}" value="{1}" /></div></div></div>';
			this.dataOrder = [ "id", "text","xtype" ];
	}
});