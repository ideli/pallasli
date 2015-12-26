Pallasli.layout.RowLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["row"]=Pallasli.layout.RowLayout ;
$.extend( Pallasli.layout.RowLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<div class="row"></div>';
	}
});