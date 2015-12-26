Pallasli.layout.FormLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["form"]=Pallasli.layout.FormLayout ;
$.extend( Pallasli.layout.FormLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<form></form>';
	}
});