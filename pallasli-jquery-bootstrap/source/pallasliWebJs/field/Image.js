/** 
cls: rounded,circle,thumbnail
**/

Pallasli.field.Image = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [ '<div id="{0}" class="thumbnail ">',
			'<img src="{1}" alt="{2}" class="{5}">', '<div class="caption">',
			'<p class="title" hidden>{3}</p>',
			'<p class="description" hidden>{4}</p>',
			'<p class="toolbar" hidden> </p>', '</div>', '</div>' ];
	this.dataOrder = [ "id", "src", "alt", "title", "description", "cls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["img"] = Pallasli.field.Image;
$.extend(Pallasli.field.Image.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.baseCls = "img"; 
		//me._initIconCls(cfg);
	}
});