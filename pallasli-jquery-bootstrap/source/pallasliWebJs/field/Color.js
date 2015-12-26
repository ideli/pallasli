Pallasli.field.Color =  function(cfg){
	this.tmpl =  '<div class="row form-group">'
		+ '<div class="col-xs-7 col-md-9">'
		+ '<input   type="color" '
		+ 'class="{3}" id="{0}" value="{1}" /></div></div>';
		this.dataOrder = [ "id", "text","cls"];
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["color"]=Pallasli.field.Color;
$.extend( Pallasli.field.Color.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
	},
	afterLayout : function() {
		var me = this;
		$("#" + me.id).click(function() {
			me.handler();
		});
	}
});