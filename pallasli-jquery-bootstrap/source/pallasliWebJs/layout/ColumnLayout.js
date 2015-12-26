Pallasli.layout.ColumnLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["column"]=Pallasli.layout.ColumnLayout ;
$.extend( Pallasli.layout.ColumnLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.childWrapperHtml='<div class="col-xs-12 col-md-4"></div>';
	}
});