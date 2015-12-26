/**
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
})

静态实例
<div class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

动态实例
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
*/
 

Pallasli.window.Dialog =  function(cfg){
	Pallasli.window.Window.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["dialog"]=Pallasli.window.Dialog ;
$.extend( Pallasli.window.Dialog.prototype, Pallasli.window.Window.prototype, {
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
	showDialog:function(params){ 
		params=params||{};
		this.dataOrder = [   ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||400,params.width||800).show().modal('show'); 
		
	} 
}); 
	

