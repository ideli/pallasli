/**
 * 
 * 
 * <div class="btn-group" role="group" aria-label="...">
  <button type="button" class="btn btn-default">Left</button>
  <button type="button" class="btn btn-default">Middle</button>
  <button type="button" class="btn btn-default">Right</button>
</div>

尺寸
<div class="btn-group btn-group-lg" role="group" aria-label="...">...</div>
<div class="btn-group" role="group" aria-label="...">...</div>
<div class="btn-group btn-group-sm" role="group" aria-label="...">...</div>
<div class="btn-group btn-group-xs" role="group" aria-label="...">...</div>



嵌套
<div class="btn-group" role="group" aria-label="...">
  <button type="button" class="btn btn-default">1</button>
  <button type="button" class="btn btn-default">2</button>

  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      Dropdown
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu">
      <li><a href="#">Dropdown link</a></li>
      <li><a href="#">Dropdown link</a></li>
    </ul>
  </div>
</div>

按钮垂直分布
<div class="btn-group-vertical" role="group" aria-label="...">
  ...
</div>

两端对齐
<div class="btn-group btn-group-justified" role="group" aria-label="...">
  ...
</div>


单按钮式下拉菜单
<!-- Single button -->
<div class="btn-group">
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Action <span class="caret"></span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>

分裂式按钮下拉菜单（不支持纵向）
<!-- Split button -->
<div class="btn-group">
  <button type="button" class="btn btn-danger">Action</button>
  <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li role="separator" class="divider"></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>

向上弹出
<div class="btn-group dropup">
  <button type="button" class="btn btn-default">Dropup</button>
  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu">
    <!-- Dropdown menu links -->
  </ul>
</div>
*/
Pallasli.action.ButtonGroup =  function(cfg){
	this.size="default";//0:default 1:xs 2:sm 3:default 4:lg
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["buttonGroup"]=Pallasli.action.ButtonGroup;
$.extend( Pallasli.action.ButtonGroup.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		this.tmpl =  '<div class="col-xs-12 col-md-6">'
			+ '<div class="row form-group">'
			+ '<div class="col-xs-7 col-md-9">'
			+ '<input   type="{2}" '
			+ 'class="btn btn-default" id="{0}" value="{1}" /></div></div></div>';
			this.dataOrder = [ "id", "text","xtype" ]; 
	} 
});