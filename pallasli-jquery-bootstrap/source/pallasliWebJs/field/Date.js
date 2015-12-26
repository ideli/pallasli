Pallasli.field.Date = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="date" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  >'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["date"] = Pallasli.field.Date;
$.extend(Pallasli.field.Date.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
	}
});