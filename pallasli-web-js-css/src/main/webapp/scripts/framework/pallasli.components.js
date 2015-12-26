(function($) {
	var chars = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ];

	$.generateMixed = function(n) {
		var res = "";
		for (var i = 0; i < n; i++) {
			var id = Math.ceil(Math.random() * 35);
			res += chars[id];
		}
		return res;
	};
	$.format = function(source, params) {
		if (arguments.length == 1)
			return function() {
				var args = $.makeArray(arguments);
				args.unshift(source);
				return $.format.apply(this, args);
			};
		if (arguments.length > 2 && params.constructor != Array) {
			params = $.makeArray(arguments).slice(1);
		}
		if (params.constructor != Array) {
			params = [ params ];
		}
		$.each(params, function(i, n) {
			source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
		});
		return source;
	};
	
	
	
	
	$.ViewPort = function(cfg){
		this.tmpl="<div id='pallasli-viewPort'><div>";
		this.init(cfg);
	};
	$.extend($.ViewPort.prototype, {
		init : function(cfg) {
			var me=this;
			me.items=cfg.items;
			$(me.tmpl).appendTo($(document.body));
			for(var index in me.items){
				me.addItem(me.items[index]);
				me.items[index].doLayout();
			}
		},
		getHtml : function() {
			return this.tmpl;
		},
		addItem : function(obj) { 
			$(obj.getHtml()).appendTo($("#pallasli-viewPort"));
		}
	});
	$.Form = function(cfg){
		this.tmpl='<form id="tmp_form" name="tmp_form" style="{1}"> </form>';
		this.init(cfg);
	};
	$.extend($.Form.prototype, {
		init : function(cfg) {
			this.initCfg = cfg;
			var me=this;
		 	//$(me.tmpl).appendTo($("#pallasli-viewPort"));
		 	me.items=cfg.items;
		},
		doLayout : function(){

			var me=this;
			for(var index in me.items){
				me.addItem(me.items[index]);
			}
		},
		
		getHtml : function() {
			var floatTo="";
			if(this.initCfg.region){
				floatTo="float:"+(this.initCfg.region=="west"?"left":"right")
			}
			var html = $.format(this.tmpl, "", floatTo);
			return html;
		},
		addItem : function(obj) { 
			if(obj.appendTo)obj.appendTo(this);
			else $(obj.getHtml()).appendTo($("#tmp_form"));
		}
	});

	$.Field = function(cfg){
		this.init();
	};
	$.extend($.Field.prototype,{
		init : function(cfg) {
			this.initCfg = cfg;
		},
		getHtml : function() {
			
		}
	});
	
	
	
	$.TreePanel = function(cfg){
		this.tmpl='<div class="navbar" style="{1} {2}">'
			+ '<div class="navbar-inner">'
			+ '<div class="container">'
			+ '<div class="nav-collapse">'
			+ '<ul id="{0}" class="nav" style="{1}">'
			+ '</ul>' + '</div>' + '</div>' + '</div>' + '</div>'; 
		this.init(cfg);
	};
	$.extend($.TreePanel.prototype,{
		init : function(cfg) {
			this.id=$.generateMixed(20);
			this.initCfg = cfg;
			var me=this;
		 	me.items=cfg.items;
		},
		getHtml : function() {
			var floatTo="";
			if(this.initCfg.region){
				floatTo="float:"+(this.initCfg.region=="west"?"left;":"right;")
			}
			var width="";
			if(this.initCfg.width){
				width="width:"+this.initCfg.width+"px;";
			}
			var html = $.format(this.tmpl, this.id, floatTo,width);
			return html;
		},
		doLayout : function(){

			var me=this;
			for(var index in me.items){
				var menu=me.items[index];
				if(!menu.getHtml){
					menu=new $.Menu(menu);
				}
				
				me.addItem(menu);
			}
		},
		addItem : function(obj) { 
			console.log(obj.getHtml(),this.id)
			 $(obj.getHtml()).appendTo($("#"+this.id));
		}
	});
	

	
	$.Menu = function(cfg) {
		this.tmpl = '<li><a href="{0}" target="main"'
			+ ' class="list-group-item" style="float: left"> {1} </a></li>';
		this.init(cfg);
	};
	$.extend($.Menu.prototype, {
		init : function(cfg) {
			this.initCfg = cfg; 
		},
		getHtml : function() {
			var params = this.initCfg; 
			var obj = this;
			

			var html = $.format(obj.tmpl, params.url, params.text);
			return html;
		}
	});

	
	$.TextField = function(cfg) {
		this.tmpl = '<div class="col-xs-12 col-md-6"><div class="row form-group">'
				+ '<div class="col-xs-5 col-md-3">'
				+ '<label for="{0}">{1}</label>'
				+ '</div><div class="col-xs-7 col-md-9">'
				+ '<input name="{4}" type="{5}" class="form-control" id="{0}" placeholder="{2}"  {6}>'
				+ '<label class="tipinfo"></label></div></div></div>';
		this.init(cfg);
	};
	$.extend($.TextField.prototype, {
		init : function(cfg) {
			this.initCfg = cfg; 
		},
		getHtml : function() {
			var params = this.initCfg; 
			var obj = this;
			var id = "pallali-gen-" + $.generateMixed(32);
			var otherParam = "";
			if (params.required)
				otherParam += " required";
			if (params.zipCode)
				otherParam += " zipCode=true";
			if (params.minlength)
				otherParam += " minlength=" + params.minlength;
			if (params.maxlength)
				otherParam += " maxlength=" + params.maxlength;

			var html = $.format(obj.tmpl, id, params.fieldLabel, params.emptyText?params.emptyText:"",
					params.cls, params.name,
					params.type ? params.type : "text", otherParam);
			return html;
		}
	});

	$.Button = function(cfg) {
		this.tmpl = '<button  type="button" class="btn btn-default">{0}</button>';
		this.init(cfg);
	};
	$.extend($.Button.prototype, {
		init : function(cfg) {
			this.initCfg = cfg;
			this.handler=cfg.handler; 
		},
		getHtml : function() {
			var params = this.initCfg; 
			var obj = this;
			var html = $.format(obj.tmpl, params.text); 
			
			return html;
		},
		appendTo : function(panel){
			var me=this;
			$(this.getHtml()).appendTo($("#tmp_form")).click(function() {
				me.handler();
			});
		}
	});

	$.fn.addItem = function(obj) { 
		$(obj.getHtml()).appendTo(this);
	};
})(jQuery);