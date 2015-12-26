Pallasli.action.Menu = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [
        '<span class="dropdown">',
          '<li  class="btn btn-default dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></li>',
          '<ul class="dropdown-menu">',
            '<li><a href="#">Action</a></li>',
            '<li><a href="#">Another action</a></li>',
            '<li><a href="#">Something else here</a></li>',
            '<li role="separator" class="divider"></li>',
            '<li><a href="#">Separated link</a></li>',
            '<li role="separator" class="divider"></li>',
            '<li><a href="#">One more separated link</a></li>',
          '</ul>',
        '</span>'
        ];
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["menu"] = Pallasli.action.Menu;
$.extend(Pallasli.action.Menu.prototype, Pallasli.Component.prototype, {
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