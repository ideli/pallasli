/**
 * <div class="checkbox">
  <label>
    <input type="checkbox" value="">
    Option one is this and that&mdash;be sure to include why it's great
  </label>
</div>
<div class="checkbox disabled">
  <label>
    <input type="checkbox" value="" disabled>
    Option two is disabled
  </label>
</div>

<div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
    Option one is this and that&mdash;be sure to include why it's great
  </label>
</div>
<div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
    Option two can be something else and selecting it will deselect option one
  </label>
</div>
<div class="radio disabled">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3" disabled>
    Option three is disabled
  </label>
</div>
 */
Pallasli.field.CheckboxGroup =  function(cfg){
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["checkboxgroup"]=Pallasli.field.CheckboxGroup;
$.extend( Pallasli.field.CheckboxGroup.prototype, Pallasli.field.Field.prototype, {
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