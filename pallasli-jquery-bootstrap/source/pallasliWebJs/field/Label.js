/**
 * default,primary,success,info,warning,danger 
 */

Pallasli.field.Label =  function(cfg){
	this.tmpl = '<div class="row form-group "><div class="col-xs-12 col-md-12">'
		+ '<label  '
		+ 'id="{0}" class="{2}" >{1}</label>'
		+ '</div></div>';
this.dataOrder = [ "id",  "text","lableCls"];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["label"]=Pallasli.field.Label;
$.extend( Pallasli.field.Label.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) { 
		if(cfg.cls){
			cfg.lableCls="lable-"+cls;
		}
	}
});