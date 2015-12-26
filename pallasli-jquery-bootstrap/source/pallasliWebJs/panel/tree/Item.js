Pallasli.panel.tree.Item =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["treemenu"]=Pallasli.panel.tree.Item ;
$.extend( Pallasli.panel.tree.Item.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.initCfg=cfg;
		me.tmpl = '<li><a href="{0}" target="{2}"'
			+ ' style="float: left"> {1} </a></li>';
		me.dataOrder=["url","text","target"];
	}
});
