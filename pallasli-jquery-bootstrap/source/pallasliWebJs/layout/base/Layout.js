
Pallasli.layout.Layout =  function(cfg){
	this.initialize(cfg);
}; 
$.extend( Pallasli.layout.Layout.prototype, {
	initialize: function(cfg) { 
		this.initLayout(cfg);
	},
	initLayout : function(cfg) { 
	},
	getChildWrapperHtml:function(){
		return this.childWrapperHtml;
	},
	getMainWrapperHtml:function(){
		return this.mainWrapperHtml;
	}
});
	