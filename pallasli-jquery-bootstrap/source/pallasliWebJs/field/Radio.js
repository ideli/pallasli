Pallasli.field.Radio =  function(cfg){
	this.tmpl = '<div id={0} class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{1}">{3}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input id={1} name={2} type="radio" value="{6}" {5} >{4}'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "text",
		"otherParam", "value" ];
	Pallasli.field.Checkbox.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["radio"]=Pallasli.field.Radio;
$.extend( Pallasli.field.Radio.prototype, Pallasli.field.Checkbox.prototype, {
	initComponent : function(cfg) {
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});