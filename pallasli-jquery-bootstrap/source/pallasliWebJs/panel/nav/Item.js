Pallasli.panel.nav.Item =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["navmenu"]=Pallasli.panel.nav.Item ;
$.extend( Pallasli.panel.nav.Item.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.initCfg=cfg;
		me.tmpl = '<li><a href="{0}" target="{2}"'
			+ ' class="list-group-item" style="float: left"> {1} </a></li>';
		me.dataOrder=["url","text","target"];
	}
});
