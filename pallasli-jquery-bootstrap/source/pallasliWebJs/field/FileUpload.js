
/**
 * accept="image/*"
 */
Pallasli.field.FileUpload = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<div class="form-control" style="overflow: hidden;">'
			+ '<input type="button"  value="上传" class="btn"  style="position:absolute; top:0px;left:0px" />'
			+ '<input name="{4}" type="file" accept="{6}"  style="posistion:absolute;  cursor:pointer; right:-55px;font-size:100px; top:0px;left:0px;filter:alpha(opacity:0);opacity: 0;" ' 
			+ 'id="{0}" placeholder="{2}"  {5}>'
			+ '</div>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["fileupload"] = Pallasli.field.FileUpload;
$.extend(Pallasli.field.FileUpload.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});