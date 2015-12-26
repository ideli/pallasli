Pallasli.field.RadioGroup =  function(cfg){
	Pallasli.field.CheckboxGroup.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["radiogroup"]=Pallasli.field.RadioGroup;
$.extend( Pallasli.field.RadioGroup.prototype, Pallasli.field.CheckboxGroup.prototype, {
	initComponent : function(cfg) {
		this.tmpl = '<div class="col-xs-12 col-md-6">'
				+ '<div class="row form-group">'
				+ '<div class="col-xs-5 col-md-3">'
				+ '<label for="{0}">{1}</label>'
				+ '</div><div class="col-xs-7 col-md-9">'
				+ '<input name="{4}" type="{5}" '
				+ 'class="form-control" id="{0}" placeholder="{2}"  {6}>'
				+ '<label class="tipinfo"></label></div></div></div>';
		this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
				"type", "otherParam" ];
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.zipCode)
			otherParam += " zipCode=true";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});