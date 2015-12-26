/**
 * 导航条
 * 
 * <form class="navbar-form navbar-left" role="search"> <div class="form-group">
 * <input type="text" class="form-control" placeholder="Search"> </div> <button
 * type="submit" class="btn btn-default">Submit</button> </form>
 * 
 * <form class="form-inline"> <div class="form-group"> <label
 * for="exampleInputName2">Name</label> <input type="text" class="form-control"
 * id="exampleInputName2" placeholder="Jane Doe"> </div> <div
 * class="form-group"> <label for="exampleInputEmail2">Email</label> <input
 * type="email" class="form-control" id="exampleInputEmail2"
 * placeholder="jane.doe@example.com"> </div> <button type="submit" class="btn
 * btn-default">Send invitation</button> </form>
 */

Pallasli.panel.Form = function(cfg) {
	this.tmpl = [
	             '<div id={0} >',
	 	   '<div class="panel-heading"> </div>',
			'<form class="navbar-form " role="search" style="width:{2}">',
			'</form>',
			'<div></div>',

			'<form id="{3}" name={1} class="form-inline">',
			'</form>',

			 '<div class="panel-footer"> </div>',
			 '</div>'
	];
	this.dataOrder = [ "id", "name","width","layoutId" ];
	this.defaultChildType = "textfield";
	Pallasli.panel.Panel.prototype.initialize.call(this, cfg);
};
Pallasli.alias["form"] = Pallasli.panel.Form;
$.extend(Pallasli.panel.Form.prototype, Pallasli.panel.Panel.prototype, {
	initComponent : function(cfg) {
		var me = this;	
		me.layoutId = "pallali-gen-" + Pallasli.generateMixed(32);
		me.initCfg.name = me.initCfg.name || me.id;
	},
	addItems : function() {
		var me = this;
		for ( var index in me.items) {
			var menu = me.items[index];
			if (!menu.getHtml) {
				menu.xtype = menu.xtype || "textfield";
				menu = new Pallasli.alias[menu.xtype](menu);
			}
			me.add(menu);
			// menu.doLayout();
		}
	}
});
