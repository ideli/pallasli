Pallasli.tree.Tree = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["tree"]=Pallasli.tree.Tree ;
$.extend( Pallasli.tree.Tree.prototype, Pallasli.Component.prototype, {
	initComponent : function() {
		var me = this;
		me.tmpl =  '<div style="width:28%; {1} {2}">'
			+ '<ul id="{0}" style="{1}">'
			+ '</ul>' + '</div>' ;
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left;":"right;");
		}
		var widthCfg="";
		if(me.initCfg.width){
			widthCfg="width:"+me.initCfg.width+"px;";
		}
		me.defaultChildType="treeitem";
		me.initCfg.widthCfg=widthCfg;
		me.initCfg.floatTo=floatTo; 
		me.dataOrder=["id","floatTo","widthCfg"];
		me.items = me.initCfg.items;
		//$(me.getHtml()).appendTo($(document.body));
		//for ( var index in me.items) {
			//console.log(me.items[index]);
			//me.add(me.items[index]);
		//}
	},
	addItems : function(){

		var me=this;
		for(var index=0; me.items&&index< me.items.length;index++){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasl.tree.Item(menu);
			}
			me.add(menu);
		}
	}
});