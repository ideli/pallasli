/**
 * <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dLabel">
  ...
</ul>
 */

Pallasli.panel.nav.Nav = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["nav"]=Pallasli.panel.nav.Nav ;
$.extend( Pallasli.panel.nav.Nav.prototype, Pallasli.Component.prototype, {
	initComponent : function() {
		var me = this;
		me.tmpl = '<div  id="{0}" class="navbar" style="{1} {2}">'
			+ '<div class="navbar-inner">'
			+ '<div class="container">'
			+ '<div class="nav-collapse">'
			+ '<ul id="{3}" class="nav" style="{1}">'
			+ '</ul>' + '</div>' + '</div>' + '</div>' + '</div>';
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left;":"right;");
		}
		var widthCfg="";
		if(me.initCfg.width){
			widthCfg="width:"+me.initCfg.width+"px;";
		}
		me.defaultChildType="navmenu";
		me.initCfg.widthCfg=widthCfg;
		me.initCfg.floatTo=floatTo; 
		me.layoutId= "pallali-gen-" + Pallasli.generateMixed(32);
		me.dataOrder=["id","floatTo","widthCfg","layoutId"];
		me.items = me.initCfg.items;
		//$(me.getHtml()).appendTo($(document.body));
		//for ( var index in me.items) {
			//console.log(me.items[index]);
			//me.add(me.items[index]);
		//}
	},
	addItems : function(){

		var me=this;
		for(var index in me.items){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasli.panel.nav.Item(menu);
			}
			me.add(menu);
		}
	}
});