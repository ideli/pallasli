/**
 *
 * 
 */
Pallasli.field.Field = function(cfg) {
	this.baseCls = "input-group";
	this.tmpl = '<div id={0} class="input-group">'
			+ '<span class="input-group-addon" id="basic-addon1">@</span>'
			+ '<input name={1} type="text" class="form-control" placeholder="{2}">'
			+ '</div>';
	this.dataOrder = [ "id", "name", "emptyText" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
$.extend(Pallasli.field.Field.prototype, Pallasli.Component.prototype, {
	initialize : function(cfg) {
		Pallasli.Component.prototype.initialize.call(this, cfg);
	},
	initComponent : function(cfg) {

		me._initSizeCls(cfg);
	}
});