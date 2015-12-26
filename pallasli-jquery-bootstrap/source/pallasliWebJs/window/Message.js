/**
四类消息
<div class="alert alert-success" role="alert">...</div>
<div class="alert alert-info" role="alert">...</div>
<div class="alert alert-warning" role="alert">...</div>
<div class="alert alert-danger" role="alert">...</div>


可带链接
<div class="alert alert-danger" role="alert">
  <a href="#" class="alert-link">...</a>
</div>
可关闭
<div class="alert alert-warning alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Warning!</strong> Better check yourself, you're not looking too good.
</div>
*/

Pallasli.window.Message =  function(cfg){
	Pallasli.window.Window.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["message"]=Pallasli.window.Message ;
$.extend( Pallasli.window.Message.prototype, Pallasli.window.Window.prototype, {
	initWindow : function(cfg) {
		var me = this;
		me.tmpl = '<div class="alert {0} {1}" role="alert">'
			  +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
			  +'<strong>{2}</strong> {3}'
			  +'</div>';  
		/**
		
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
		</div>*/
		me.initCfg=cfg;  
	},
	alert:function(params){
		//params.closable;
		//params.href;
		//params.closeOnClick;
		this.initCfg.cls="alert-success";
		this.initCfg.closeOnClickCls="alert-dismissible";
		this.initCfg.title="alert";
		this.initCfg.msg="alert msg";
		this.dataOrder = [ "cls", "closeOnClickCls", "title", "msg"  ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||100,params.width||200).modal("show"); 
		
	},
	error:function(params){ 
		this.initCfg.cls="alert-danger";
		this.initCfg.closeOnClickCls="alert-dismissible";
		this.initCfg.title="alert";
		this.initCfg.msg="alert msg";
		this.dataOrder = [ "cls", "closeOnClickCls", "title", "msg"  ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||100,params.width||200).modal("show"); 
		
	},
	warn:function(params){
		this.initCfg.cls="alert-warning";
		this.initCfg.closeOnClickCls="alert-dismissible";
		this.initCfg.title="alert";
		this.initCfg.msg="alert msg";
		this.dataOrder = [ "cls", "closeOnClickCls", "title", "msg"  ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||100,params.width||200).modal("show"); 
		
	},
	info:function(params){
		this.initCfg.cls="alert-info";
		this.initCfg.closeOnClickCls="alert-dismissible";
		this.initCfg.title="alert";
		this.initCfg.msg="alert msg";
		this.dataOrder = [ "cls", "closeOnClickCls", "title", "msg"  ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||100,params.width||200).modal("show"); 
		
	}
});
Pallasli.Message=new Pallasli.window.Message();
	