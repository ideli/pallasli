Pallasli.window.ViewPort = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
	$(this.getHtml()).appendTo($(document.body));
	this.doLayout();
}; 
$.extend( Pallasli.window.ViewPort.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div id="{0}" class="panel"></div>'; 
		me.dataOrder=["id"]; 
	}
});