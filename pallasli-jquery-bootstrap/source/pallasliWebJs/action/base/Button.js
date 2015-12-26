/**
 * size: xs(超小) ,sm(小), lg(大) 
 * 
 * type: info（一般信息）,success（成功）,warning（警告） ,error（错误，危险）,primary（首选项）,link（链接）
 * 
 * icon: glyphicon图形
 * 
 */
Pallasli.action.Button = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = '<button id="{0}" class="btn {2} {3}"  >'
		+ '<span class="{4}" ></span>' 
		+ '{1}</button>';
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["button"] = Pallasli.action.Button;
$.extend(Pallasli.action.Button.prototype, Pallasli.Component.prototype, {
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