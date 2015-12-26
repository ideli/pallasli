/**
 * 
 */


Pallasli.field.ImageBar = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [ '<div id="{0}" class="row">','<div class="col-sm-6 col-md-4">','</div>','</div>' ];
	this.dataOrder = [ "id"  ];
	this.defaultChildType = "img";
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["imgbar"] = Pallasli.field.ImageBar;
$.extend(Pallasli.field.ImageBar.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;  
	}
});