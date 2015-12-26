Pallasli.field.TextField = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+'<div class="input-group {5}">'
			+  '<span class="input-group-addon" id="basic-addon1">@</span>'
			+ '<input id={1} name={2} type="text" class="form-control" placeholder="{4}" {6}>'
			+ '<label class="tipinfo"></label></div></div>' + '</div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "emptyText","sizeCls",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["textfield"] = Pallasli.field.TextField;
$.extend(Pallasli.field.TextField.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.type)
			cfg.type = "text";
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