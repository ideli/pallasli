/**
 * <div class="row">
  <div class="col-lg-6">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
      <input type="text" class="form-control" placeholder="Search for...">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search for...">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
 */

Pallasli.field.Search =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="search" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		 "otherParam" ]; 
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["search"]=Pallasli.field.Search;
$.extend( Pallasli.field.Search.prototype, Pallasli.field.Field.prototype, {
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