Pallasli.layout.AutoLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["auto"]=Pallasli.layout.AutoLayout ;
$.extend( Pallasli.layout.AutoLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.childWrapperHtml="";
	}
});