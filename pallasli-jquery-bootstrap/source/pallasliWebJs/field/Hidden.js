Pallasli.field.Hidden =  function(cfg){
	this.tmpl =  '<div class="row form-group">'
		+ '<div class="col-xs-7 col-md-9">'
		+ '<input   type="hidden" '
		+ 'class="{3}" id="{0}" value="{1}" /></div></div>';
		this.dataOrder = [ "id", "text"  ,"cls"];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["hidden"]=Pallasli.field.Hidden;
$.extend( Pallasli.field.Hidden.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	},
	afterLayout : function() {
		var me = this;
		$("#" + me.id).click(function() {
			me.handler();
		});
	}
});