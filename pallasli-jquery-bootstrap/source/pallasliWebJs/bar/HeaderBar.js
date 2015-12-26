/**
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



图标
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img alt="Brand" src="...">
      </a>
    </div>
  </div>
</nav>

form外的导航navbar-btn input a btn
  <button type="button" class="btn btn-default navbar-btn">Sign in</button>
  
  
  form导航条
 <form class="navbar-form navbar-left" role="search">
  <div class="form-group">
    <input type="text" class="form-control" placeholder="Search">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>

固定在顶部 底部navbar-fixed-top navbar-fixed-bottom
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    ...
  </div>
</nav>

反色导航
<nav class="navbar navbar-inverse">
  ...
</nav>

路径导航
<ol class="breadcrumb">
  <li><a href="#">Home</a></li>
  <li><a href="#">Library</a></li>
  <li class="active">Data</li>
</ol>
*/

Pallasli.bar.HeaderBar = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [
	             '<nav class="navbar navbar-default">',
	             ' <div class="container-fluid">', 
	             '<div class="navbar-header">', 

	             '<span class="dropdown">',
	               '<li  class="btn btn-default dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></li>',
	               '<ul class="dropdown-menu">',
	                 '<li><a href="#">Action</a></li>',
	                 '<li><a href="#">Another action</a></li>',
	                 '<li><a href="#">Something else here</a></li>',
	                 '<li role="separator" class="divider"></li>',
	                 '<li><a href="#">Separated link</a></li>',
	                 '<li role="separator" class="divider"></li>',
	                 '<li><a href="#">One more separated link</a></li>',
	               '</ul>',
	             '</span>', 
	             '<span class="dropdown">',
	               '<li  class="btn btn-default dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></li>',
	               '<ul class="dropdown-menu">',
	                 '<li><a href="#">Action</a></li>',
	                 '<li><a href="#">Another action</a></li>',
	                 '<li><a href="#">Something else here</a></li>',
	                 '<li role="separator" class="divider"></li>',
	                 '<li><a href="#">Separated link</a></li>',
	                 '<li role="separator" class="divider"></li>',
	                 '<li><a href="#">One more separated link</a></li>',
	               '</ul>',
	             '</span>', 
		       '  </div>', 
		       '  </div>', 
	             '', 
	             '', 
	             '', 
	             '</nav>', 
	             '<ol class="breadcrumb">', 
	             ' <li><a href="#">Home</a></li>', 
	             ' <li><a href="#">Library</a></li>', 
	             '  <li class="active">Data</li>', 
	             ' </ol>'
        ];
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["headerbar"] = Pallasli.bar.HeaderBar;
$.extend(Pallasli.bar.HeaderBar.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.baseCls="btn";
	},
	afterLayout : function() {
		var me = this;
		$("#" + me.id).click(function(e) {
			me.handler(e);
		});
		me._initTypeCls();
		me._initIconCls();
	},
	_initIconCls : function() {
		var me = this;
		if (me.icon) {
			me.iconCls = "glyphicon glyphicon-" + me.icon;
		} else {
			me.iconCls="";
		}
	},
	_initTypeCls : function() {
		var me = this; 
		if (me.type) {
			if(me.typeCls=="error"){
				me.typeCls="btn-danger";
			}else{
				me.typeCls = "btn-" + me.type;
			} 
		} else {
			me.typeCls = ""; 
		}
	}
});