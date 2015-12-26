Pallasli.tree.Item =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["treeitem"]=Pallasli.tree.Item ;
$.extend( Pallasli.tree.Item.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.initCfg=cfg;
		me.leaf=cfg.leaf;
		me.target=cfg.target;
		me.url=cfg.url;
		if(me.leaf){
			me.tmpl = '<li><a href="{0}" target="{2}" > {1} </a></li>';
			me.dataOrder=["url","text","target"];
		}else{
			me.tmpl = '<li><a  href="{3}" target="{2}"  > {1} </a><ul style="padding-left:20px;" id="{0}"></ul></li>';
			me.dataOrder=["id","text","target","url"];
		}
		me.items = me.initCfg.items;

		me.defaultChildType="treeitem";
	},
	addItems : function(){

		var me=this;
		for(var index=0;  me.items&&index< me.items.length;index++){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasli.tree.Item(menu);
			}
			me.add(menu);
		}
	}
});
