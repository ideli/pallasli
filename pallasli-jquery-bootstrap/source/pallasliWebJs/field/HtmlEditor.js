Pallasli.field.HtmlEditor = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="text" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  {6}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["htmleditor"] = Pallasli.field.HtmlEditor;
$.extend(Pallasli.field.HtmlEditor.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
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