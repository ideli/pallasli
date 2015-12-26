/**
 * <div class="row"> <div class="col-lg-6"> <div class="input-group"> <span
 * class="input-group-addon"> <input type="checkbox" aria-label="..."> </span>
 * <input type="text" class="form-control" aria-label="..."> </div><!--
 * /input-group --> </div><!-- /.col-lg-6 --> <div class="col-lg-6"> <div
 * class="input-group"> <span class="input-group-addon"> <input type="radio"
 * aria-label="..."> </span> <input type="text" class="form-control"
 * aria-label="..."> </div><!-- /input-group --> </div><!-- /.col-lg-6 -->
 * </div><!-- /.row -->
 */

Pallasli.field.Checkbox = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input id={1} name={2} type="checkbox" value="{6}" {5} >{4}'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "text",
			"otherParam", "value" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["checkbox"] = Pallasli.field.Checkbox;
$.extend(Pallasli.field.Checkbox.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
var me=this;
		var otherParam = "";
		if(cfg.boxLabel)me.text=cfg.boxLabel;
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});