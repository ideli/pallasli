/**
 * 
 

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Panel title</h3>
  </div>
  <div class="panel-body">
    Panel content
  </div>
  <div class="panel-footer">Panel footer</div>
</div>

色彩
<div class="panel panel-primary">...</div>
<div class="panel panel-success">...</div>
<div class="panel panel-info">...</div>
<div class="panel panel-warning">...</div>
<div class="panel panel-danger">...</div>
 */

Pallasli.panel.Panel =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["panel"]=Pallasli.panel.Panel;
$.extend( Pallasli.panel.Panel.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div id="{0}" class="panel panel-default">' 
			+ '<div class="panel-title">{1}</div>'
			+ '<div class="panel-heading"> </div>'
			+ '<div class="panel-body" id="{2}">'
			+ '</div> '
			+ '<div class="panel-footer"> </div>' + 
			+'</div>';
		
		var floatTo="";
		if(this.initCfg.region){
			floatTo="float:"+(this.initCfg.region=="west"?"left":"right");
		}
		me.layoutId= "pallali-gen-" + Pallasli.generateMixed(32);
		cfg.floatTo=floatTo;
		me.initCfg=cfg;
		me.items = me.initCfg.items;
		me.layout = me.initCfg.layout;
		me.dataOrder=["id","title","layoutId"];  
	},
	addBar : function($posistion,$data) {
		var barhtml="";
		for(var i in $data){
			var d=$data[i];
			var btn;
			if(d=="->"){
				btn = new Pallasli.alias["split"]();
			}else	
				if(d=="-"){
					btn = new Pallasli.alias["split"]();
				}else	if(d.xtype){
					btn = new Pallasli.alias[d.xtype](d);
			}else{
				btn=new Pallasli.action.Button(d);
			}
			barhtml=btn.getHtml();
			$(barhtml).appendTo($posistion);
			btn.afterLayout();
		}

	},
	addTbar : function() {
		var me = this;
		if(me.tbar){
			me.addBar($("#"+me.id).find(".panel-heading"),me.tbar);
		}

	},
	addFbar : function() {
		var me = this;
		if(me.fbar){
			me.addBar($("#"+me.id).find(".panel-footer"),me.fbar);
		}
	},
	addItems : function(){ 
		var me=this; 
		for(var index in me.items){
			var menu=me.items[index]; 
			if(!menu.getHtml){
				menu.xtype=menu.xtype||"fieldset"; 
					menu=new Pallasli.alias[menu.xtype](menu);
			} 
			me.add(menu); 
		} 
	} ,
	doLayout : function() {
		Pallasli.Component.prototype.doLayout.call(this); 
		var me=this;
		me.addTbar();
		me.addFbar();
	}
});
	