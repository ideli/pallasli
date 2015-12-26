/**
这是一个轻量、灵活的组件，它能延伸至整个浏览器视口来展示网站上的关键内容。帐幕
**/
Pallasli.layout.JumbotronLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["form"]=Pallasli.layout.JumbotronLayout ;
$.extend( Pallasli.layout.JumbotronLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<div class="jumbotron"><div class="container"></div></div>';
	}
});