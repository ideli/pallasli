Pallasli.bar.ToolBar = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [
	             '<div class="btn-toolbar" role="toolbar" aria-label="...">',
	             '<div class="btn-group" role="group" aria-label="...">...</div>',
	             '<div class="btn-group" role="group" aria-label="...">...</div>',
	             '<div class="btn-group" role="group" aria-label="...">...</div>',
	             '</div>'
        ];
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["toolbar"] = Pallasli.bar.ToolBar;
$.extend(Pallasli.bar.ToolBar.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.baseCls="btn";
	},
	afterLayout : function() {
		var me = this;
		$("#" + me.id).click(function(e) {
			me.handler(e);
		});
		me._initTypeCls();
		me._initIconCls();
	},
	_initIconCls : function() {
		var me = this;
		if (me.icon) {
			me.iconCls = "glyphicon glyphicon-" + me.icon;
		} else {
			me.iconCls="";
		}
	},
	_initTypeCls : function() {
		var me = this; 
		if (me.type) {
			if(me.typeCls=="error"){
				me.typeCls="btn-danger";
			}else{
				me.typeCls = "btn-" + me.type;
			} 
		} else {
			me.typeCls = ""; 
		}
	}
});