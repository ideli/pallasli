/**
<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
**/

Pallasli.action.Close = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = '<button id="{0}" class="btn {2} {3}"  >'
		+ '<span class="{4}" ></span>' 
		+ '{1}</button>';
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.action.Button.prototype.initialize.call(this, cfg);
};
Pallasli.alias["closebtn"] = Pallasli.action.Close;
$.extend(Pallasli.action.Close.prototype, Pallasli.action.Button.prototype, {
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
	}
});