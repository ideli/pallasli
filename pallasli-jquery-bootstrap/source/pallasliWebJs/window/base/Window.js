Pallasli.window.Window =  function(cfg){
	this.initialize(cfg); 
}; 
Pallasli.alias["window"]=Pallasli.window.Window ;
$.extend( Pallasli.window.Window.prototype, Pallasli.Component.prototype, {
	initialize: function(cfg){
		var me=this;
		cfg=cfg||{}; 
		if (!cfg.id)
			cfg.id = "pallali-gen-" + Pallasli.generateMixed(32);
		me.initCfg = cfg;
		$.extend(me, me.initCfg);
		me.initWindow(cfg);
		me.initGetterSetter(cfg);
	
	},
	initWindow : function(cfg) {
		var me = this;
		me.tmpl = '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">'
		  +'<div class="modal-dialog" role="document">'
		  +'<div class="modal-content">'
		  +'<div class="modal-header">'
		  +'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
		  +'<h4 class="modal-title" id="myModalLabel">Modal title</h4>'
		  +'</div>'
		  +'<div class="modal-body">'
		  +'...'
		  +'</div>'
		  +'<div class="modal-footer">'
		  +'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
		  +'<button type="button" class="btn btn-primary">Save changes</button>'
		  +'</div>'
		  +'</div>'
		  +'</div>'
		  +'</div>';
		me.initCfg=cfg;  
	
	},
	center:function($win,height,width){
		return $win.height(height).width(width).css("position","absolute")
		.css("top",top.innerHeight/2-height/2+ top.scrollY)
		.css("left",top.innerWidth/2-width/2+ top.scrollX);
	},
	getSelf:function(){},
	show:function(params){ 
		params=params||{};
		this.dataOrder = [   ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||400,params.width||800).show().modal('show'); 
	},
	hide:function(){
		
	},
	close:function(){
		
	}
});
	