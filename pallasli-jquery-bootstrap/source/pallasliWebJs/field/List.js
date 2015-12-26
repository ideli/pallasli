/**
 * <ul class="list-group">
 * <li class="list-group-item">Cras justo odio</li>
 * <li class="list-group-item">Dapibus ac facilisis in</li>
 * <li class="list-group-item">Morbi leo risus</li>
 * <li class="list-group-item">Porta ac consectetur ac</li>
 * <li class="list-group-item">Vestibulum at eros</li>
 * </ul>
 * 徽章
 * <ul class="list-group">
 * <li class="list-group-item"> <span class="badge">14</span> Cras justo odio
 * </li>
 * </ul>
 * 链接 当前 禁用 <div class="list-group"> <a href="#" class="list-group-item active">
 * Cras justo odio </a> <a href="#" class="list-group-item disabled">Dapibus ac
 * facilisis in</a> <a href="#" class="list-group-item">Morbi leo risus</a> <a
 * href="#" class="list-group-item">Porta ac consectetur ac</a> <a href="#"
 * class="list-group-item">Vestibulum at eros</a> </div>
 * 
 * 
 * 色彩
 * 
 * list-group-item-success list-group-item-info list-group-item-warning
 * list-group-item-danger
 * 
 * 
 * 定制 <div class="list-group"> <a href="#" class="list-group-item active">
 * <h4 class="list-group-item-heading">List group item heading</h4>
 * <p class="list-group-item-text">
 * ...
 * </p>
 * </a> </div>
 */
Pallasli.field.List = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="text" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["list"] = Pallasli.field.List;
$.extend(Pallasli.field.List.prototype, Pallasli.field.Field.prototype, {
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