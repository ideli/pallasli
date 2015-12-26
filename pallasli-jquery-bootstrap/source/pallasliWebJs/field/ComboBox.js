/**
 * webkit 对select支持不好
 * 
 * 
 * 
 * <div class="dropdown">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Dropdown
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>

分组
<ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
  ...
  <li class="dropdown-header">Dropdown header</li>
  ...
</ul>


对齐
 <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dLabel">
  ...
</ul> 
分割线
<ul class="dropdown-menu" aria-labelledby="dropdownMenuDivider">
  ...
  <li role="separator" class="divider"></li>
  ...
</ul>

禁用
 <li class="disabled"><a href="#">Disabled link</a></li>

向上弹出
<div class="dropup">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Dropup
    <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
    <li><a href="#">Action</a></li>
    <li><a href="#">Another action</a></li>
    <li><a href="#">Something else here</a></li>
    <li><a href="#">Separated link</a></li>
  </ul>
</div>
 */


Pallasli.field.ComboBox =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<select name="{4}" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}></select>'
		+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		 "otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["combo"]=Pallasli.field.ComboBox;
$.extend( Pallasli.field.ComboBox.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
		
	} ,
	doLayout : function() {
		Pallasli.field.Field.prototype.doLayout.call(this); 
		var me=this;

		if(me.store){ 
			for(var i=0;i<me.store.length;i++){
				var d=me.store[i];
				if(d.value){
					var d=me.store[i];
					$("#"+this.id).append($('<option value="'+d.value+'">'+d.display+'</option>'));
				}else{
					var d=me.store[i];
					$("#"+this.id).append($('<option value="'+d[0]+'">'+d[1]+'</option>'));
				}
			}
		}
	}
});