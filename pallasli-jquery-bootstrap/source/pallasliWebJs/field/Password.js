Pallasli.field.Password = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input id={1} name={2} type="password" class="form-control" {5}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "sizeCls",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["password"] = Pallasli.field.Password;
$.extend(Pallasli.field.Password.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;

	}
});