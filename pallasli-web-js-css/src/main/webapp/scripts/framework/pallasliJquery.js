Pallasli = {
	isEmpty : function(value, allowEmptyString) {
		return (value == null) || (!allowEmptyString ? value === '' : false)
				|| (Ext.isArray(value) && value.length === 0);
	},

	isArray : ('isArray' in Array) ? Array.isArray : function(value) {
		return toString.call(value) === '[object Array]';
	},

	isDate : function(value) {
		return toString.call(value) === '[object Date]';
	},

	isMSDate : function(value) {
		if (!Ext.isString(value)) {
			return false;
		}
		return MSDateRe.test(value);
	},

	isObject : (toString.call(null) === '[object Object]') ? function(value) {

		return value !== null && value !== undefined
				&& toString.call(value) === '[object Object]'
				&& value.ownerDocument === undefined;
	} : function(value) {
		return toString.call(value) === '[object Object]';
	},

	isSimpleObject : function(value) {
		return value instanceof Object && value.constructor === Object;
	},

	isPrimitive : function(value) {
		var type = typeof value;
		return type === 'string' || type === 'number' || type === 'boolean';
	},

	isFunction :

	(typeof document !== 'undefined' && typeof document
			.getElementsByTagName('body') === 'function') ? function(value) {
		return !!value && toString.call(value) === '[object Function]';
	} : function(value) {
		return !!value && typeof value === 'function';
	},

	isNumber : function(value) {
		return typeof value === 'number' && isFinite(value);
	},

	isNumeric : function(value) {
		return !isNaN(parseFloat(value)) && isFinite(value);
	},

	isString : function(value) {
		return typeof value === 'string';
	},

	isBoolean : function(value) {
		return typeof value === 'boolean';
	},

	isElement : function(value) {
		return value ? value.nodeType === 1 : false;
	},

	isTextNode : function(value) {
		return value ? value.nodeName === "#text" : false;
	},

	isDefined : function(value) {
		return typeof value !== 'undefined';
	},
	trim : function(str) {
		return str.replace(/^\s+|\s+$/g, "");
	},
	toCaption : function(word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
};

Pallasli.Namespace = {
	Register : function(_Name) {
		var chk = false;
		var cob = "";
		var spc = _Name.split(".");
		for (var i = 0; i < spc.length; i++) {
			if (cob != "") {
				cob += ".";
			}
			cob += spc[i];
			chk = this.Exists(cob);
			if (!chk) {
				this.Create(cob);
			}
		}
		if (chk) {
			throw "Namespace: " + _Name + " is already defined.";
		}
	},

	Create : function(_Src) {
		eval("window." + _Src + " = new Object();");
	},

	Exists : function(_Src) {
		eval("var NE = false; try{if(" + _Src
				+ "){NE = true;}else{NE = false;}}catch(err){NE=false;}");
		return NE;
	}
};
Pallasli.createNamespaces = function() {
	for (i = 0, ln = arguments.length; i < ln; i++) {
		Pallasli.Namespace.Register(arguments[i]);
	}
};

Pallasli.generateMixed = function(n) {
	var chars = [ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' ];
	var res = "";
	for (var i = 0; i < n; i++) {
		var id = Math.ceil(Math.random() * 35);
		res += chars[id];
	}
	return res;
};

Pallasli.Class = function(properties) {
	var _class = function() {
		return (arguments[0] !== null && this.initialize && typeof (this.initialize) == 'function') ? this.initialize
				.apply(this, arguments)
				: this;
	};
	_class.prototype = properties;
	return _class;
};

Pallasli.createNamespaces("Pallasli.field", "Pallasli.panel.tree",
		"Pallasli.panel.nav", "Pallasli.tree", "Pallasli.window",
		"Pallasli.action", "Pallasli.bar", "Pallasli.alias", "Pallasli.layout");

/**
 * 可分为三部分
 * 
 * event 事件处理 dom dom处理 lang 函数工具类
 * 
 */

var $ready;

(function($) {
	$ready = $(document).ready;
	$.fn.setForm = function(jsonValue) {
		var obj = this;
		$
				.each(
						jsonValue,
						function(name, ival) {
							var $oinput = obj.find(":input[name=" + name + "]");
							if ($oinput.attr("type") == "radio"
									|| $oinput.attr("type") == "checkbox") {
								$oinput
										.each(function() {
											if (Object.prototype.toString
													.apply(ival) == '[object Array]') {
												// 是复选框，并且是数组
												for (var i = 0; i < ival.length; i++) {
													if ($(this).val() == ival[i])
														$(this).attr("checked",
																"checked");
												}
											} else {
												if ($(this).val() == ival)
													$(this).attr("checked",
															"checked");

											}
										});
							} else if ($oinput.attr("type") == "textarea") {
								// 多行文本框
								obj.find("[name=" + name + "]").html(ival);
							} else {
								obj.find("[name=" + name + "]").val(ival);
							}

						});
	};

	Pallasli.getScript = function(url, fn) {
		$.ajax({
			url : url,
			dataType : "script",
			success : function(data, success) {
				var result = {
					success : true,
					script : data
				};
				fn(result);
			},
			error : function(me, errorType, error) {
				var result = {
					success : false,
					msg : errorType + ":" + error.message,
					error : error
				};
				fn(result);
			}
		});
	};

	$.extend($.validator, {
		messages : {
			required : "<span style='color:red'>必输项</span>",
			email : "<span style='color:red'>邮箱格式无效</span>",
			url : "<span style='color:red'>URL无效</span>",
			date : "<span style='color:red'>日期格式无效</span>",
			number : "<span style='color:red'>无效数值</span>",
			digits : "<span style='color:red'>必须是数字</span>",
			creditcard : "<span style='color:red'>银行卡号无效</span>",
			equalTo : $.validator
					.format("<span style='color:red'>需与{1}保持一致</span>"),
			maxlength : $.validator
					.format("<span style='color:red'>长度不能超过 {0}个字符</span>"),
			minlength : $.validator
					.format("<span style='color:red'>长度不能少于 {0}个字符</span>"),
			rangelength : $.validator
					.format("<span style='color:red'>数值长度应在{0}和{1}之间</span>"),
			range : $.validator
					.format("<span style='color:red'>数值应在 {0}和{1}之间</span>"),
			max : $.validator
					.format("<span style='color:red'>数值不能大于{0}</span>"),
			min : $.validator
					.format("<span style='color:red'>数值不能小于{0}</span>")
		}
	});
	// 手机号码验证
	$.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "<span style='color:red'>手机号码格式错误</span>");

	// 电话号码验证
	$.validator.addMethod("phone", function(value, element) {
		var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>电话号码格式错误</span>");

	// 邮政编码验证
	$.validator.addMethod("zipcode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>邮政编码格式错误</span>");

	// QQ号码验证
	$.validator.addMethod("qq", function(value, element) {
		var tel = /^[1-9]\d{4,9}$/;
		return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>qq号码格式错误</span>");

	// IP地址验证
	$.validator
			.addMethod(
					"ip",
					function(value, element) {
						var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
						return this.optional(element)
								|| (ip.test(value) && (RegExp.$1 < 256
										&& RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
					}, "<span style='color:red'>Ip地址格式错误</span>");

	// 字母和数字的验证
	$.validator.addMethod("chrnum", function(value, element) {
		var chrnum = /^([a-zA-Z0-9]+)$/;
		return this.optional(element) || (chrnum.test(value));
	}, "<span style='color:red'>只能输入数字和字母(字符A-Z, a-z, 0-9)</span>");

	// 中文的验证
	$.validator.addMethod("chinese", function(value, element) {
		var chinese = /^[\u4e00-\u9fa5]+$/;
		return this.optional(element) || (chinese.test(value));
	}, "<span style='color:red'>只能输入中文</span>");

	// 下拉框验证
	$.validator.addMethod("selectnone", function(value, element) {
		return value == "请选择";
	}, "<span style='color:red'>必须选择一项</span>");

	// 字节长度验证
	$.validator
			.addMethod(
					"byterangelength",
					function(value, element, param) {
						var length = value.length;
						for (var i = 0; i < value.length; i++) {
							if (value.charCodeAt(i) > 127) {
								length++;
							}
						}
						return this.optional(element)
								|| (length >= param[0] && length <= param[1]);
					},
					$.validator
							.format("<span style='color:red'>请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)</span>"));

})(jQuery);
var $ready;

(function($) {
	$ready = $(document).ready;
	$.fn.setForm = function(jsonValue) {
		var obj = this;
		$
				.each(
						jsonValue,
						function(name, ival) {
							var $oinput = obj.find(":input[name=" + name + "]");
							if ($oinput.attr("type") == "radio"
									|| $oinput.attr("type") == "checkbox") {
								$oinput
										.each(function() {
											if (Object.prototype.toString
													.apply(ival) == '[object Array]') {
												// 是复选框，并且是数组
												for (var i = 0; i < ival.length; i++) {
													if ($(this).val() == ival[i])
														$(this).attr("checked",
																"checked");
												}
											} else {
												if ($(this).val() == ival)
													$(this).attr("checked",
															"checked");

											}
										});
							} else if ($oinput.attr("type") == "textarea") {
								// 多行文本框
								obj.find("[name=" + name + "]").html(ival);
							} else {
								obj.find("[name=" + name + "]").val(ival);
							}

						});
	};

	Pallasli.getScript = function(url, fn) {
		$.ajax({
			url : url,
			dataType : "script",
			success : function(data, success) {
				var result = {
					success : true,
					script : data
				};
				fn(result);
			},
			error : function(me, errorType, error) {
				var result = {
					success : false,
					msg : errorType + ":" + error.message,
					error : error
				};
				fn(result);
			}
		});
	};

	$.extend($.validator, {
		messages : {
			required : "<span style='color:red'>必输项</span>",
			email : "<span style='color:red'>邮箱格式无效</span>",
			url : "<span style='color:red'>URL无效</span>",
			date : "<span style='color:red'>日期格式无效</span>",
			number : "<span style='color:red'>无效数值</span>",
			digits : "<span style='color:red'>必须是数字</span>",
			creditcard : "<span style='color:red'>银行卡号无效</span>",
			equalTo : $.validator
					.format("<span style='color:red'>需与{1}保持一致</span>"),
			maxlength : $.validator
					.format("<span style='color:red'>长度不能超过 {0}个字符</span>"),
			minlength : $.validator
					.format("<span style='color:red'>长度不能少于 {0}个字符</span>"),
			rangelength : $.validator
					.format("<span style='color:red'>数值长度应在{0}和{1}之间</span>"),
			range : $.validator
					.format("<span style='color:red'>数值应在 {0}和{1}之间</span>"),
			max : $.validator
					.format("<span style='color:red'>数值不能大于{0}</span>"),
			min : $.validator
					.format("<span style='color:red'>数值不能小于{0}</span>")
		}
	});
	// 手机号码验证
	$.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "<span style='color:red'>手机号码格式错误</span>");

	// 电话号码验证
	$.validator.addMethod("phone", function(value, element) {
		var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>电话号码格式错误</span>");

	// 邮政编码验证
	$.validator.addMethod("zipcode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>邮政编码格式错误</span>");

	// QQ号码验证
	$.validator.addMethod("qq", function(value, element) {
		var tel = /^[1-9]\d{4,9}$/;
		return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>qq号码格式错误</span>");

	// IP地址验证
	$.validator
			.addMethod(
					"ip",
					function(value, element) {
						var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
						return this.optional(element)
								|| (ip.test(value) && (RegExp.$1 < 256
										&& RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
					}, "<span style='color:red'>Ip地址格式错误</span>");

	// 字母和数字的验证
	$.validator.addMethod("chrnum", function(value, element) {
		var chrnum = /^([a-zA-Z0-9]+)$/;
		return this.optional(element) || (chrnum.test(value));
	}, "<span style='color:red'>只能输入数字和字母(字符A-Z, a-z, 0-9)</span>");

	// 中文的验证
	$.validator.addMethod("chinese", function(value, element) {
		var chinese = /^[\u4e00-\u9fa5]+$/;
		return this.optional(element) || (chinese.test(value));
	}, "<span style='color:red'>只能输入中文</span>");

	// 下拉框验证
	$.validator.addMethod("selectnone", function(value, element) {
		return value == "请选择";
	}, "<span style='color:red'>必须选择一项</span>");

	// 字节长度验证
	$.validator
			.addMethod(
					"byterangelength",
					function(value, element, param) {
						var length = value.length;
						for (var i = 0; i < value.length; i++) {
							if (value.charCodeAt(i) > 127) {
								length++;
							}
						}
						return this.optional(element)
								|| (length >= param[0] && length <= param[1]);
					},
					$.validator
							.format("<span style='color:red'>请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)</span>"));

})(jQuery);
Pallasli.Component = function(cfg) {
	this.initialize(cfg);
};
$.extend(Pallasli.Component.prototype, {

	initGetterSetter : function(props) {
		var me = this;
		for ( var prop in props) {
			if(prop=="layout")continue;
			prop=Pallasli.toCaption(prop);
			// 这里的currentObj由this传过来的。this 为 User
			(function(currentObj) {
				// 创建此属性的一个新的getter(读取器)
				currentObj["get" + prop] = function() {
					return props[prop];
				}
				// 创建此属性的一个新的setter(设置器)
				currentObj["set" + prop] = function(val) {
					props[prop] = val;
				};
			})(me);
		}
	},

	initialize : function(cfg) {
		cfg=cfg||{};
		var me = this;
		if (!cfg.id)
			cfg.id = "pallali-gen-" + Pallasli.generateMixed(32);
		me.initCfg = cfg;
		me.initComponent(cfg);
		$.extend(me, me.initCfg);
		me.initGetterSetter(cfg);
	},
	getHtml : function(superLayout) {
		var me = this;
		me.data = [];
		for (var i = 0; i < me.dataOrder.length; i++) {
			me.data[i] = me[me.dataOrder[i]] || "";
		}

		if (Pallasli.isArray(me.tmpl)) {
			var tmp_tmpl = "";
			for (var i = 0; i < me.tmpl.length; i++) {
				tmp_tmpl += me.tmpl[i];
			}
			me.tmpl = tmp_tmpl;
		}

		var html = $.format(me.tmpl, me.data);

		if (superLayout) {
			var mainWrapperHtml = superLayout.getMainWrapperHtml();
			if (mainWrapperHtml) {
				html = $(html).append(mainWrapperHtml);
			}
		}

		return html;

	},
	appendTo : function(parent, superLayout) {
		var layoutHtml = this.getHtml(superLayout);
		var childWrapperHtml = superLayout.getChildWrapperHtml();
		if (childWrapperHtml && childWrapperHtml.length > 0) {
			$(childWrapperHtml).append(layoutHtml) .appendTo($("#" + (parent.layoutId||parent.id)));
		} else {
			$(layoutHtml).appendTo($("#" + (parent.layoutId||parent.id)));
		}
	},
	add : function(child) {
		var me = this;
		if (child.appendTo) {
			child.appendTo(me, me.getLayout());
		}
		if (child.addItems) {
			child.addItems();
		}

	},
	getLayout : function() {
		var me = this;
		me.layout = me.layout || "auto";
		return new Pallasli.alias[me.layout]();
	},
	afterLayout : function() {
	},
	doLayout : function() {
		var me = this;
		var layout = me.getLayout();
		if (layout&&me.items) {
			for ( var index=0; index< me.items.length;index++) {
				var item = me.items[index];
				if (!item.appendTo) {
					item.xtype = item.xtype || me.defaultChildType;
					item = new Pallasli.alias[item.xtype](item);
				}
				item.appendTo(this, layout);
				item.doLayout();
				item.afterLayout();
			}
		}
	},
	_initSizeCls : function(cfg) {
		if (cfg.size) {
			cfg.sizeCls = me.baseCls + "-" + cfg.size;
		} else {
			cfg.sizeCls = "";
		}
	}
});/**
 *
 * 
 */
Pallasli.field.Field = function(cfg) {
	this.baseCls = "input-group";
	this.tmpl = '<div id={0} class="input-group">'
			+ '<span class="input-group-addon" id="basic-addon1">@</span>'
			+ '<input name={1} type="text" class="form-control" placeholder="{2}">'
			+ '</div>';
	this.dataOrder = [ "id", "name", "emptyText" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
$.extend(Pallasli.field.Field.prototype, Pallasli.Component.prototype, {
	initialize : function(cfg) {
		Pallasli.Component.prototype.initialize.call(this, cfg);
	},
	initComponent : function(cfg) {

		me._initSizeCls(cfg);
	}
});/**
 * size: xs(超小) ,sm(小), lg(大) 
 * 
 * type: info（一般信息）,success（成功）,warning（警告） ,error（错误，危险）,primary（首选项）,link（链接）
 * 
 * icon: glyphicon图形
 * 
 */
Pallasli.action.Button = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = '<button id="{0}" class="btn {2} {3}"  >'
		+ '<span class="{4}" ></span>' 
		+ '{1}</button>';
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["button"] = Pallasli.action.Button;
$.extend(Pallasli.action.Button.prototype, Pallasli.Component.prototype, {
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
});/**
 * 
 

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Panel title</h3>
  </div>
  <div class="panel-body">
    Panel content
  </div>
  <div class="panel-footer">Panel footer</div>
</div>

色彩
<div class="panel panel-primary">...</div>
<div class="panel panel-success">...</div>
<div class="panel panel-info">...</div>
<div class="panel panel-warning">...</div>
<div class="panel panel-danger">...</div>
 */

Pallasli.panel.Panel =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["panel"]=Pallasli.panel.Panel;
$.extend( Pallasli.panel.Panel.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div id="{0}" class="panel panel-default">' 
			+ '<div class="panel-title">{1}</div>'
			+ '<div class="panel-heading"> </div>'
			+ '<div class="panel-body" id="{2}">'
			+ '</div> '
			+ '<div class="panel-footer"> </div>' + 
			+'</div>';
		
		var floatTo="";
		if(this.initCfg.region){
			floatTo="float:"+(this.initCfg.region=="west"?"left":"right");
		}
		me.layoutId= "pallali-gen-" + Pallasli.generateMixed(32);
		cfg.floatTo=floatTo;
		me.initCfg=cfg;
		me.items = me.initCfg.items;
		me.layout = me.initCfg.layout;
		me.dataOrder=["id","title","layoutId"];  
	},
	addBar : function($posistion,$data) {
		var barhtml="";
		for(var i in $data){
			var d=$data[i];
			var btn;
			if(d=="->"){
				btn = new Pallasli.alias["split"]();
			}else	
				if(d=="-"){
					btn = new Pallasli.alias["split"]();
				}else	if(d.xtype){
					btn = new Pallasli.alias[d.xtype](d);
			}else{
				btn=new Pallasli.action.Button(d);
			}
			barhtml=btn.getHtml();
			$(barhtml).appendTo($posistion);
			btn.afterLayout();
		}

	},
	addTbar : function() {
		var me = this;
		if(me.tbar){
			me.addBar($("#"+me.id).find(".panel-heading"),me.tbar);
		}

	},
	addFbar : function() {
		var me = this;
		if(me.fbar){
			me.addBar($("#"+me.id).find(".panel-footer"),me.fbar);
		}
	},
	addItems : function(){ 
		var me=this; 
		for(var index in me.items){
			var menu=me.items[index]; 
			if(!menu.getHtml){
				menu.xtype=menu.xtype||"fieldset"; 
					menu=new Pallasli.alias[menu.xtype](menu);
			} 
			me.add(menu); 
		} 
	} ,
	doLayout : function() {
		Pallasli.Component.prototype.doLayout.call(this); 
		var me=this;
		me.addTbar();
		me.addFbar();
	}
});
	
Pallasli.layout.Layout =  function(cfg){
	this.initialize(cfg);
}; 
$.extend( Pallasli.layout.Layout.prototype, {
	initialize: function(cfg) { 
		this.initLayout(cfg);
	},
	initLayout : function(cfg) { 
	},
	getChildWrapperHtml:function(){
		return this.childWrapperHtml;
	},
	getMainWrapperHtml:function(){
		return this.mainWrapperHtml;
	}
});
	Pallasli.window.Window =  function(cfg){
	this.initialize(cfg); 
}; 
Pallasli.alias["window"]=Pallasli.window.Window ;
$.extend( Pallasli.window.Window.prototype, Pallasli.Component.prototype, {
	initialize: function(cfg){
		var me=this;
		cfg=cfg||{}; 
		if (!cfg.id)
			cfg.id = "pallali-gen-" + Pallasli.generateMixed(32);
		me.initCfg = cfg;
		$.extend(me, me.initCfg);
		me.initWindow(cfg);
		me.initGetterSetter(cfg);
	
	},
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
	center:function($win,height,width){
		return $win.height(height).width(width).css("position","absolute")
		.css("top",top.innerHeight/2-height/2+ top.scrollY)
		.css("left",top.innerWidth/2-width/2+ top.scrollX);
	},
	show:function(params){ 
		params=params||{};
		this.dataOrder = [   ];
		this.center($(this.getHtml()).appendTo($(top.document.body)),params.height||400,params.width||800).show().modal('show'); 
	},
	hide:function(){
		
	},
	close:function(){
		
	}
});
	/**
 * <div class="row"> <div class="col-lg-6"> <div class="input-group"> <span
 * class="input-group-addon"> <input type="checkbox" aria-label="..."> </span>
 * <input type="text" class="form-control" aria-label="..."> </div><!--
 * /input-group --> </div><!-- /.col-lg-6 --> <div class="col-lg-6"> <div
 * class="input-group"> <span class="input-group-addon"> <input type="radio"
 * aria-label="..."> </span> <input type="text" class="form-control"
 * aria-label="..."> </div><!-- /input-group --> </div><!-- /.col-lg-6 -->
 * </div><!-- /.row -->
 */

Pallasli.field.Checkbox = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input id={1} name={2} type="checkbox" value="{6}" {5} >{4}'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "text",
			"otherParam", "value" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["checkbox"] = Pallasli.field.Checkbox;
$.extend(Pallasli.field.Checkbox.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
var me=this;
		var otherParam = "";
		if(cfg.boxLabel)me.text=cfg.boxLabel;
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});/**
 * <div class="checkbox">
  <label>
    <input type="checkbox" value="">
    Option one is this and that&mdash;be sure to include why it's great
  </label>
</div>
<div class="checkbox disabled">
  <label>
    <input type="checkbox" value="" disabled>
    Option two is disabled
  </label>
</div>

<div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
    Option one is this and that&mdash;be sure to include why it's great
  </label>
</div>
<div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
    Option two can be something else and selecting it will deselect option one
  </label>
</div>
<div class="radio disabled">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3" disabled>
    Option three is disabled
  </label>
</div>
 */
Pallasli.field.CheckboxGroup =  function(cfg){
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["checkboxgroup"]=Pallasli.field.CheckboxGroup;
$.extend( Pallasli.field.CheckboxGroup.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		this.tmpl = '<div class="col-xs-12 col-md-6">'
				+ '<div class="row form-group">'
				+ '<div class="col-xs-5 col-md-3">'
				+ '<label for="{0}">{1}</label>'
				+ '</div><div class="col-xs-7 col-md-9">'
				+ '<input name="{4}" type="{5}" '
				+ 'class="form-control" id="{0}" placeholder="{2}"  {6}>'
				+ '<label class="tipinfo"></label></div></div></div>';
		this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
				"type", "otherParam" ];
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.zipCode)
			otherParam += " zipCode=true";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Color =  function(cfg){
	this.tmpl =  '<div class="row form-group">'
		+ '<div class="col-xs-7 col-md-9">'
		+ '<input   type="color" '
		+ 'class="{3}" id="{0}" value="{1}" /></div></div>';
		this.dataOrder = [ "id", "text","cls"];
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["color"]=Pallasli.field.Color;
$.extend( Pallasli.field.Color.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
	},
	afterLayout : function() {
		var me = this;
		$("#" + me.id).click(function() {
			me.handler();
		});
	}
});/**
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
});Pallasli.field.Date = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="date" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  >'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["date"] = Pallasli.field.Date;
$.extend(Pallasli.field.Date.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
	}
});Pallasli.field.Datetime = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="datetime" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  >'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["datetime"] = Pallasli.field.Datetime;
$.extend(Pallasli.field.Datetime.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Email =  function(cfg){	
	this.tmpl = '<div id={0} class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{1}">{3}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+'<div class="input-group {5}">'
		+  '<span class="input-group-addon" id="basic-addon1">@</span>'
		+ '<input id={1} name={2} type="mail" class="form-control" placeholder="{4}" {6}>'
		+ '<label class="tipinfo"></label></div></div>' + '</div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "emptyText","sizeCls",
		"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["email"]=Pallasli.field.Email;
$.extend( Pallasli.field.Email.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required";
	if (cfg.minlength)
		otherParam += " minlength=" + cfg.minlength;
	if (cfg.maxlength)
		otherParam += " maxlength=" + cfg.maxlength;
	cfg.otherParam = otherParam;
	
}
});
/**
 * accept="image/*"
 */
Pallasli.field.FileUpload = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<div class="form-control" style="overflow: hidden;">'
			+ '<input type="button"  value="上传" class="btn"  style="position:absolute; top:0px;left:0px" />'
			+ '<input name="{4}" type="file" accept="{6}"  style="posistion:absolute;  cursor:pointer; right:-55px;font-size:100px; top:0px;left:0px;filter:alpha(opacity:0);opacity: 0;" ' 
			+ 'id="{0}" placeholder="{2}"  {5}>'
			+ '</div>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["fileupload"] = Pallasli.field.FileUpload;
$.extend(Pallasli.field.FileUpload.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Hidden =  function(cfg){
	this.tmpl =  '<div class="row form-group">'
		+ '<div class="col-xs-7 col-md-9">'
		+ '<input   type="hidden" '
		+ 'class="{3}" id="{0}" value="{1}" /></div></div>';
		this.dataOrder = [ "id", "text"  ,"cls"];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["hidden"]=Pallasli.field.Hidden;
$.extend( Pallasli.field.Hidden.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	},
	afterLayout : function() {
		var me = this;
		$("#" + me.id).click(function() {
			me.handler();
		});
	}
});Pallasli.field.HtmlEditor = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="text" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  {6}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["htmleditor"] = Pallasli.field.HtmlEditor;
$.extend(Pallasli.field.HtmlEditor.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});/** 
cls: rounded,circle,thumbnail
**/

Pallasli.field.Image = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [ '<div id="{0}" class="thumbnail ">',
			'<img src="{1}" alt="{2}" class="{5}">', '<div class="caption">',
			'<p class="title" hidden>{3}</p>',
			'<p class="description" hidden>{4}</p>',
			'<p class="toolbar" hidden> </p>', '</div>', '</div>' ];
	this.dataOrder = [ "id", "src", "alt", "title", "description", "cls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["img"] = Pallasli.field.Image;
$.extend(Pallasli.field.Image.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.baseCls = "img"; 
		//me._initIconCls(cfg);
	}
});/**
 * default,primary,success,info,warning,danger 
 */

Pallasli.field.Label =  function(cfg){
	this.tmpl = '<div class="row form-group "><div class="col-xs-12 col-md-12">'
		+ '<label  '
		+ 'id="{0}" class="{2}" >{1}</label>'
		+ '</div></div>';
this.dataOrder = [ "id",  "text","lableCls"];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["label"]=Pallasli.field.Label;
$.extend( Pallasli.field.Label.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) { 
		if(cfg.cls){
			cfg.lableCls="lable-"+cls;
		}
	}
});/**
 * <ul class="list-group">
 * <li class="list-group-item">Cras justo odio</li>
 * <li class="list-group-item">Dapibus ac facilisis in</li>
 * <li class="list-group-item">Morbi leo risus</li>
 * <li class="list-group-item">Porta ac consectetur ac</li>
 * <li class="list-group-item">Vestibulum at eros</li>
 * </ul>
 * 徽章
 * <ul class="list-group">
 * <li class="list-group-item"> <span class="badge">14</span> Cras justo odio
 * </li>
 * </ul>
 * 链接 当前 禁用 <div class="list-group"> <a href="#" class="list-group-item active">
 * Cras justo odio </a> <a href="#" class="list-group-item disabled">Dapibus ac
 * facilisis in</a> <a href="#" class="list-group-item">Morbi leo risus</a> <a
 * href="#" class="list-group-item">Porta ac consectetur ac</a> <a href="#"
 * class="list-group-item">Vestibulum at eros</a> </div>
 * 
 * 
 * 色彩
 * 
 * list-group-item-success list-group-item-info list-group-item-warning
 * list-group-item-danger
 * 
 * 
 * 定制 <div class="list-group"> <a href="#" class="list-group-item active">
 * <h4 class="list-group-item-heading">List group item heading</h4>
 * <p class="list-group-item-text">
 * ...
 * </p>
 * </a> </div>
 */
Pallasli.field.List = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="text" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["list"] = Pallasli.field.List;
$.extend(Pallasli.field.List.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required"; 
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Month = function(cfg) {
	this.tmpl = '<div class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{0}">{1}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input name="{4}" type="month" '
			+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["month"] = Pallasli.field.Month;
$.extend(Pallasli.field.Month.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;

	}
});Pallasli.field.Number = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+'<div class="input-group {5}">'
			+  '<span class="input-group-addon" id="basic-addon1">@</span>'
			+ '<input id={1} name={2} type="number" class="form-control" placeholder="{4}" {6}>'
			+ '<label class="tipinfo"></label></div></div>' + '</div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "emptyText","sizeCls",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["numberfield"] = Pallasli.field.Number;
$.extend(Pallasli.field.Number.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.type)
			cfg.type = "numberfield";
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Password = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+ '<input id={1} name={2} type="password" class="form-control" {5}>'
			+ '<label class="tipinfo"></label></div></div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "sizeCls",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["password"] = Pallasli.field.Password;
$.extend(Pallasli.field.Password.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;

	}
});Pallasli.field.Radio =  function(cfg){
	this.tmpl = '<div id={0} class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{1}">{3}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input id={1} name={2} type="radio" value="{6}" {5} >{4}'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "text",
		"otherParam", "value" ];
	Pallasli.field.Checkbox.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["radio"]=Pallasli.field.Radio;
$.extend( Pallasli.field.Radio.prototype, Pallasli.field.Checkbox.prototype, {
	initComponent : function(cfg) {
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		cfg.otherParam = otherParam;
	}
});Pallasli.field.RadioGroup =  function(cfg){
	Pallasli.field.CheckboxGroup.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["radiogroup"]=Pallasli.field.RadioGroup;
$.extend( Pallasli.field.RadioGroup.prototype, Pallasli.field.CheckboxGroup.prototype, {
	initComponent : function(cfg) {
		this.tmpl = '<div class="col-xs-12 col-md-6">'
				+ '<div class="row form-group">'
				+ '<div class="col-xs-5 col-md-3">'
				+ '<label for="{0}">{1}</label>'
				+ '</div><div class="col-xs-7 col-md-9">'
				+ '<input name="{4}" type="{5}" '
				+ 'class="form-control" id="{0}" placeholder="{2}"  {6}>'
				+ '<label class="tipinfo"></label></div></div></div>';
		this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
				"type", "otherParam" ];
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.zipCode)
			otherParam += " zipCode=true";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Range =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="range" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		  "otherParam" ]; 
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["range"]=Pallasli.field.Range;
$.extend( Pallasli.field.Range.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required"; 
	if (cfg.minlength)
		otherParam += " minlength=" + cfg.minlength;
	if (cfg.maxlength)
		otherParam += " maxlength=" + cfg.maxlength;
	cfg.otherParam = otherParam;
	
}
});/**
 * <div class="row">
  <div class="col-lg-6">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
      <input type="text" class="form-control" placeholder="Search for...">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search for...">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
 */

Pallasli.field.Search =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="search" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		 "otherParam" ]; 
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["search"]=Pallasli.field.Search;
$.extend( Pallasli.field.Search.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required"; 
	if (cfg.minlength)
		otherParam += " minlength=" + cfg.minlength;
	if (cfg.maxlength)
		otherParam += " maxlength=" + cfg.maxlength;
	cfg.otherParam = otherParam;
	
}
});Pallasli.field.Tel =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="tel" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
	  "otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["tel"]=Pallasli.field.Tel;
$.extend( Pallasli.field.Tel.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) { 
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required";  
	cfg.otherParam = otherParam;
	
}
});Pallasli.field.TextField = function(cfg) {
	this.tmpl = '<div id={0} class="row form-group">'
			+ '<div class="col-xs-5 col-md-3">'
			+ '<label for="{1}">{3}</label>'
			+ '</div><div class="col-xs-7 col-md-9">'
			+'<div class="input-group {5}">'
			+  '<span class="input-group-addon" id="basic-addon1">@</span>'
			+ '<input id={1} name={2} type="text" class="form-control" placeholder="{4}" {6}>'
			+ '<label class="tipinfo"></label></div></div>' + '</div>';
	this.dataOrder = [ "id", "inputId", "name", "fieldLabel", "emptyText","sizeCls",
			"otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this, cfg);
};
Pallasli.alias["textfield"] = Pallasli.field.TextField;
$.extend(Pallasli.field.TextField.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.type)
			cfg.type = "text";
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required";
		if (cfg.zipCode)
			otherParam += " zipCode=true";
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});/**
 * textarea的row属性不能被很好的支持，避免使用
 */
Pallasli.field.Textarea =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="textarea" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		  "otherParam" ]; 
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["textarea"]=Pallasli.field.Textarea;
$.extend( Pallasli.field.Textarea.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
		if (!cfg.emptyText)
			cfg.emptyText = "";
		var otherParam = "";
		if (cfg.required)
			otherParam += " required"; 
		if (cfg.minlength)
			otherParam += " minlength=" + cfg.minlength;
		if (cfg.maxlength)
			otherParam += " maxlength=" + cfg.maxlength;
		cfg.otherParam = otherParam;
	}
});Pallasli.field.Time =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="time" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		 "otherParam" ]; 
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["time"]=Pallasli.field.Time;
$.extend( Pallasli.field.Time.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required"; 
	if (cfg.minlength)
		otherParam += " minlength=" + cfg.minlength;
	if (cfg.maxlength)
		otherParam += " maxlength=" + cfg.maxlength;
	cfg.otherParam = otherParam;
	
}
});Pallasli.field.Url =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="url" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		"type", "otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["url"]=Pallasli.field.Url;
$.extend( Pallasli.field.Url.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) { 
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required"; 
	if (cfg.minlength)
		otherParam += " minlength=" + cfg.minlength;
	if (cfg.maxlength)
		otherParam += " maxlength=" + cfg.maxlength;
	cfg.otherParam = otherParam;
	
}
});Pallasli.field.Week =  function(cfg){
	this.tmpl =  '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="week" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		  "otherParam" ];
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["week"]=Pallasli.field.Week;
$.extend( Pallasli.field.Week.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) { 
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required";  
	cfg.otherParam = otherParam;
	
}
});/**
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
});/**
<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
**/

Pallasli.action.Close = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = '<button id="{0}" class="btn {2} {3}"  >'
		+ '<span class="{4}" ></span>' 
		+ '{1}</button>';
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.action.Button.prototype.initialize.call(this, cfg);
};
Pallasli.alias["closebtn"] = Pallasli.action.Close;
$.extend(Pallasli.action.Close.prototype, Pallasli.action.Button.prototype, {
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
	}
});/**
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
*//**
 * 
 */


Pallasli.field.ImageBar = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [ '<div id="{0}" class="row">','<div class="col-sm-6 col-md-4">','</div>','</div>' ];
	this.dataOrder = [ "id"  ];
	this.defaultChildType = "img";
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["imgbar"] = Pallasli.field.ImageBar;
$.extend(Pallasli.field.ImageBar.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;  
	}
});Pallasli.action.Menu = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [
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
        '</span>'
        ];
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["menu"] = Pallasli.action.Menu;
$.extend(Pallasli.action.Menu.prototype, Pallasli.Component.prototype, {
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
});/**
 * <nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

尺寸 pagination-lg pagination-sm
禁用和当前
<nav>
  <ul class="pagination">
    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
    ...
  </ul>
</nav>


翻页不分页
<nav>
  <ul class="pager">
    <li><a href="#">Previous</a></li>
    <li><a href="#">Next</a></li>
  </ul>
</nav>
<nav>
  <ul class="pager">
    <li class="previous disabled"><a href="#"><span aria-hidden="true">&larr;</span> Older</a></li>
    <li class="next"><a href="#">Newer <span aria-hidden="true">&rarr;</span></a></li>
  </ul>
</nav>
*/
Pallasli.action.Reset =  function(cfg){
	Pallasli.action.Button.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["reset"]=Pallasli.action.Reset;
$.extend( Pallasli.action.Reset.prototype, Pallasli.action.Button.prototype, {
	initComponent : function(cfg) {
		this.tmpl =  '<div class="col-xs-12 col-md-6">'
			+ '<div class="row form-group">'
			+ '<div class="col-xs-7 col-md-9">'
			+ '<input   type="{2}" '
			+ 'class="btn btn-default" id="{0}" value="{1}" /></div></div></div>';
			this.dataOrder = [ "id", "text","xtype" ];
	}
});Pallasli.action.Split =  function(cfg){
	Pallasli.action.Button.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["split"]=Pallasli.action.Split;
$.extend( Pallasli.action.Split.prototype, Pallasli.action.Button.prototype, {
	initComponent : function(cfg) {
		this.tmpl =  '<span >|</span>';
			this.dataOrder = [ ];
	}
});Pallasli.action.Submit =  function(cfg){
	Pallasli.action.Button.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["submit"]=Pallasli.action.Submit;
$.extend( Pallasli.action.Submit.prototype, Pallasli.action.Button.prototype, {
	initComponent : function(cfg) {
		this.tmpl =  '<div class="col-xs-12 col-md-6">'
			+ '<div class="row form-group">'
			+ '<div class="col-xs-7 col-md-9">'
			+ '<input   type="{2}" '
			+ 'class="btn btn-default" id="{0}" value="{1}" /></div></div></div>';
			this.dataOrder = [ "id", "text","xtype" ];
	}
});/**
<div class="page-header">
  <h1>Example page header <small>Subtext for header</small></h1>
</div>
**//**

<div class="btn-toolbar" role="toolbar" aria-label="...">
  <div class="btn-group" role="group" aria-label="...">...</div>
  <div class="btn-group" role="group" aria-label="...">...</div>
  <div class="btn-group" role="group" aria-label="...">...</div>
</div>
*//**
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
});/**
 * 
 */


Pallasli.bar.ImageBar = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [ '<div id="{0}" class="row">','<div class="col-sm-6 col-md-4">','</div>','</div>' ];
	this.dataOrder = [ "id"  ];
	this.defaultChildType = "img";
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["imgbar"] = Pallasli.bar.ImageBar;
$.extend(Pallasli.bar.ImageBar.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;  
	}
});/**
 * <nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

尺寸 pagination-lg pagination-sm
禁用和当前
<nav>
  <ul class="pagination">
    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
    ...
  </ul>
</nav>


翻页不分页
<nav>
  <ul class="pager">
    <li><a href="#">Previous</a></li>
    <li><a href="#">Next</a></li>
  </ul>
</nav>
<nav>
  <ul class="pager">
    <li class="previous disabled"><a href="#"><span aria-hidden="true">&larr;</span> Older</a></li>
    <li class="next"><a href="#">Newer <span aria-hidden="true">&rarr;</span></a></li>
  </ul>
</nav>
*/
/**
<div class="page-header">
  <h1>Example page header <small>Subtext for header</small></h1>
</div>
**/Pallasli.bar.ToolBar = function(cfg) {
	this.size = "";
	this.type = "";
	this.icon = "";
	this.tmpl = [
	             '<div class="btn-toolbar" role="toolbar" aria-label="...">',
	             '<div class="btn-group" role="group" aria-label="...">...</div>',
	             '<div class="btn-group" role="group" aria-label="...">...</div>',
	             '<div class="btn-group" role="group" aria-label="...">...</div>',
	             '</div>'
        ];
this.dataOrder = [ "id", "text", "sizeCls", "typeCls", "iconCls" ];
	Pallasli.Component.prototype.initialize.call(this, cfg);
};
Pallasli.alias["toolbar"] = Pallasli.bar.ToolBar;
$.extend(Pallasli.bar.ToolBar.prototype, Pallasli.Component.prototype, {
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
});/**
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
	

Pallasli.window.IFrame =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["iframe"]=Pallasli.window.IFrame ;
$.extend( Pallasli.window.IFrame.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div class="embed-responsive embed-responsive-16by9">'
			+'<iframe name="{0}" id="{1}" class="embed-responsive-item" src=""></iframe>'
			+'</div>';
		
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left":"right");
		}
		me.initCfg.floatTo=floatTo;
		me.initCfg.dataOrder=["name","id"]; 
	}
});
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
	/**

隐藏进度值
<div class="progress">
  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
    <span class="sr-only">60% Complete</span>
  </div>
</div>

最小值时显示问题
<div class="progress">
  <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;">
    0%
  </div>
</div>
<div class="progress">
  <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em; width: 2%;">
    2%
  </div>
</div>


颜色变化
<div class="progress">
  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
    <span class="sr-only">40% Complete (success)</span>
  </div>
</div>
<div class="progress">
  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
    <span class="sr-only">20% Complete</span>
  </div>
</div>
<div class="progress">
  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
    <span class="sr-only">60% Complete (warning)</span>
  </div>
</div>
<div class="progress">
  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
    <span class="sr-only">80% Complete (danger)</span>
  </div>
</div>


 渐变条纹 progress-bar-striped
 渐变条纹动画效果 progress-bar progress-bar-striped active
 
 
 堆叠效果
 <div class="progress">
  <div class="progress-bar progress-bar-success" style="width: 35%">
    <span class="sr-only">35% Complete (success)</span>
  </div>
  <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 20%">
    <span class="sr-only">20% Complete (warning)</span>
  </div>
  <div class="progress-bar progress-bar-danger" style="width: 10%">
    <span class="sr-only">10% Complete (danger)</span>
  </div>
</div>
 
 
**/Pallasli.window.ViewPort = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
	$(this.getHtml()).appendTo($(document.body));
	this.doLayout();
}; 
$.extend( Pallasli.window.ViewPort.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div id="{0}" class="panel"></div>'; 
		me.dataOrder=["id"]; 
	}
});Pallasli.panel.Fieldset =  function(cfg){
	Pallasli.panel.Panel.prototype.initialize.call(this,cfg); 
};  
Pallasli.alias["fieldset"]=Pallasli.panel.Fieldset ;
$.extend( Pallasli.panel.Fieldset.prototype, Pallasli.panel.Panel.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.tmpl = '<div class=" ">' 
			+'</div>';
		
		var floatTo="";
		if(this.initCfg.region){
			floatTo="float:"+(this.initCfg.region=="west"?"left":"right");
		}
		cfg.floatTo=floatTo;
		me.initCfg=cfg;
		me.items = me.initCfg.items;
		me.dataOrder=[]; 
	},
	addItems : function(){ 
		var me=this; 
		for(var index in me.items){
			var menu=me.items[index]; 
			if(!menu.getHtml){
				menu.xtype=menu.xtype||"textfield";
				if(menu.xtype){ 
					menu=new Pallasli.alias[menu.xtype](menu);
				} 
			} 
			me.add(menu);
		}
	}
});
	/**
 * 导航条
 * 
 * <form class="navbar-form navbar-left" role="search"> <div class="form-group">
 * <input type="text" class="form-control" placeholder="Search"> </div> <button
 * type="submit" class="btn btn-default">Submit</button> </form>
 * 
 * <form class="form-inline"> <div class="form-group"> <label
 * for="exampleInputName2">Name</label> <input type="text" class="form-control"
 * id="exampleInputName2" placeholder="Jane Doe"> </div> <div
 * class="form-group"> <label for="exampleInputEmail2">Email</label> <input
 * type="email" class="form-control" id="exampleInputEmail2"
 * placeholder="jane.doe@example.com"> </div> <button type="submit" class="btn
 * btn-default">Send invitation</button> </form>
 */

Pallasli.panel.Form = function(cfg) {
	this.tmpl = [
	             '<div id={0} >',
	 	   '<div class="panel-heading"> </div>',
			'<form class="navbar-form " role="search" style="width:{2}">',
			'</form>',
			'<div></div>',

			'<form id="{3}" name={1} class="form-inline">',
			'</form>',

			 '<div class="panel-footer"> </div>',
			 '</div>'
	];
	this.dataOrder = [ "id", "name","width","layoutId" ];
	this.defaultChildType = "textfield";
	Pallasli.panel.Panel.prototype.initialize.call(this, cfg);
};
Pallasli.alias["form"] = Pallasli.panel.Form;
$.extend(Pallasli.panel.Form.prototype, Pallasli.panel.Panel.prototype, {
	initComponent : function(cfg) {
		var me = this;	
		me.layoutId = "pallali-gen-" + Pallasli.generateMixed(32);
		me.initCfg.name = me.initCfg.name || me.id;
	},
	addItems : function() {
		var me = this;
		for ( var index in me.items) {
			var menu = me.items[index];
			if (!menu.getHtml) {
				menu.xtype = menu.xtype || "textfield";
				menu = new Pallasli.alias[menu.xtype](menu);
			}
			me.add(menu);
			// menu.doLayout();
		}
	}
});
/**
 * 
 */
Pallasli.panel.Grid = function(cfg) {
	this.tmpl = '<div id={0} class="table-padding" style="{2}" >'
			+ '<div class="panel-title">{1}</div>'
			+ '<div class="panel-heading"> </div>'
			+ '<div class="panel-body hidden">'
			+ '</div> '
			+ '<div class="table-responsive">'
			+ '<table class="table table-condensed table-hover table-bordered table-striped"> '
			+ '<thead> ' + '<tr class="group table-header hidden"></tr> '
			+ '<tr class="header table-header"></tr> ' + '</thead> '
			+ '<tbody> ' + '</tbody>' + '</table>' + '</div> '
			+ '<div class="panel-footer"> </div>' + '</div>';
	this.dataOrder = [ "id", "title", "style" ];
	this.columns = [];
	Pallasli.panel.Panel.prototype.initialize.call(this, cfg);
};
Pallasli.alias["grid"] = Pallasli.panel.Grid;
$
		.extend(
				Pallasli.panel.Grid.prototype,
				Pallasli.panel.Panel.prototype,
				{
					initComponent : function(cfg) {
						var me = this;
						if (cfg.width)
							me.style = "width:" + cfg.width + "px";
					},
					load : function(url) {
						var me=this;
						$.ajax({
							url : url,
							dataType : "json",
							// params:{},
							success : function(data, success) {
								console.log(data);
								me.setGridData(data);
							},
							error : function(me, errorType, error) {
								var result = {
									success : false,
									msg : errorType + ":" + error.message,
									error : error
								};
								console.log(result);
							}
						});
					},
					setColumns : function() {
						return this.columns;

					},
					createRow : function() {

					},
					getColumns : function() {
						return this.columns;

					},
					addGridRow : function(row) {
						columns = this.getColumns();
						var $tr = $("<tr></tr>");
						for ( var index in columns) {
							var cname = columns[index];
							var $td = $('<td class="font12">' + row[cname]
									+ '</td>');
							$td.appendTo($tr);
						}
						$tr.appendTo($("#" + this.id + " tbody"));
						console.log($tr)
					},
					setGridData : function(resultdata) {
						for ( var index in resultdata) {
							var row = resultdata[index];
							this.addGridRow(row);
						}
					},

					addRow : function(row) {
						var me = this;
						var $tr = me.addTr({
							cls : "success"
						});
						for ( var index in row) {
							var td = row[index];
							var tdHtml = "<td>" + td.text + "</td>";
							$(tdHtml).appendTo($tr).removeClass().addClass(
									td.cls || "default");
						}
					},
					alterRow : function(row) {
						var me = this;
						var $tr = me.addTr({
							cls : "success"
						});
						for ( var index in row) {
							var td = row[index];
							var tdHtml = "<td>" + td.text + "</td>";
							$(tdHtml).appendTo($tr).removeClass().addClass(
									td.cls || "default");
						}
					},
					deleteRow : function(row) {
						var me = this;
						var $tr = me.addTr({
							cls : "success"
						});
						for ( var index in row) {
							var td = row[index];
							var tdHtml = "<td>" + td.text + "</td>";
							$(tdHtml).appendTo($tr).removeClass().addClass(
									td.cls || "default");
						}
					},
					addTr : function(tr) {
						var me = this;
						var trHtml = "<tr></tr>";
						$(trHtml).addClass(tr.cls || "default");
						return $(trHtml).appendTo($("#" + me.id).find("tbody"));
					},
					addTh : function(th) {
						var me = this;
						console.log(th)
						var thHtml = "<th>" + (th.text || th.header) + "</th>";
						console.log(thHtml)
						$(thHtml)
								.appendTo($("#" + me.id).find("table .header"));
						this.columns.push(th.title || th.dataIndex);
					},
					addThGroup : function(thg) {
						var me = this;
						$("#" + me.id).find("table .group").removeClass(
								"hidden");
						var thgHtml = "<th colspan=" + (thg.colspan || 1) + ">"
								+ thg.text + "</th>";
						$(thgHtml)
								.appendTo($("#" + me.id).find("table .group"));
					},
					addPager : function(totalcount, pageSize) {
						var $tfoot = $("<tfoot></tfoot>");
						var $tr = $("<tr></tr>");
						var $td = $('<td colspan="21">'
								+ '   <ul class="col-sm-3 pagination pagination-sm" style="margin:0px;">'
								+ '     <li><a href="#">&laquo;</a></li>'
								+ '     <li><a href="javascript:void(0)">1</a></li> '
								+ '  <li><a href="#">...</a></li>'
								+ '     <li><a href="javascript:void(0)">100</a></li> '
								+ '    <li><a href="#">&raquo;</a></li>'
								+ '   </ul>'
								+ '   <div class="col-sm-3"> <span class="left" style="width:20%; float:left;">'
								+ '    <input class="form-control" type="text"/>'
								+ '   </span> <span class="left space-mleft10">'
								+ '   <button type="button" class="btn-sm btn-go">GO</button>'
								+ '   </span> <span class="left space-mleft10">每页显示'
								+ '   <select name="pagesize">'
								+ '   <option>10</option>'
								+ '  <option>20</option>'
								+ '  <option>30</option>'
								+ '  <option>50</option>'
								+ '  <option selected="selected">100</option>'
								+ '  <option>500</option>' + '</select>'
								+ '</span> </span> '
								+ '<span class="left space-mleft10">'
								+ '共100条记录' + '</span></div>' + '</td>');
						$td.appendTo($tr);
						$tr.appendTo($tfoot);
						$tfoot.appendTo($("#" + this.id + " table"));
					},
					doLayout : function() {
						var me = this;
						for ( var index in me.thead) {
							me.addTh(me.thead[index]);
						}
						for ( var index in me.theadGroup) {
							me.addThGroup(me.theadGroup[index]);
						}
						for ( var index in me.datastore) {
							me.addRow(me.datastore[index]);
						}
						me.addTbar();
						me.addFbar();
						if (me.pager !== false)
							me.addPager();
					}
				});
/**
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Panel heading</div>
  <div class="panel-body">
    <p>...</p>
  </div>

  <!-- List group -->
  <ul class="list-group">
    <li class="list-group-item">Cras justo odio</li>
    <li class="list-group-item">Dapibus ac facilisis in</li>
    <li class="list-group-item">Morbi leo risus</li>
    <li class="list-group-item">Porta ac consectetur ac</li>
    <li class="list-group-item">Vestibulum at eros</li>
  </ul>
</div>
**//**
 * 
 图文并茂
<div class="media">
  <div class="media-left media-middle">
    <a href="#">
      <img class="media-object" src="..." alt="...">
    </a>
  </div>
  <div class="media-body">
    <h4 class="media-heading">Middle aligned media</h4>
    ...
  </div>
</div>

图文分级列表
<ul class="media-list">
  <li class="media">
    <div class="media-left">
      <a href="#">
        <img class="media-object" src="..." alt="...">
      </a>
    </div>
    <div class="media-body">
      <h4 class="media-heading">Media heading</h4>
      ...
    </div>
  </li>
</ul>
*//**
 * 
普通标签
<ul class="nav nav-tabs nav-justified">
  <li role="presentation" class="active" disabled><a tabindex="-1" href="#">Home</a></li> 
</ul>

普通标签 tabs 胶囊式标签页 pills
纵向 stacked 两端对齐 justified
禁用标签 disabled

折叠标签
<ul class="nav nav-tabs">
  ...
  <li role="presentation" class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
      Dropdown <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
      ...
    </ul>
  </li>
  ...
</ul>

*/Pallasli.tree.Item =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["treeitem"]=Pallasli.tree.Item ;
$.extend( Pallasli.tree.Item.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.initCfg=cfg;
		me.leaf=cfg.leaf;
		me.target=cfg.target;
		me.url=cfg.url;
		if(me.leaf){
			me.tmpl = '<li><a href="{0}" target="{2}" > {1} </a></li>';
			me.dataOrder=["url","text","target"];
		}else{
			me.tmpl = '<li><a  href="{3}" target="{2}"  > {1} </a><ul style="padding-left:20px;" id="{0}"></ul></li>';
			me.dataOrder=["id","text","target","url"];
		}
		me.items = me.initCfg.items;

		me.defaultChildType="treeitem";
	},
	addItems : function(){

		var me=this;
		for(var index=0;  me.items&&index< me.items.length;index++){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasli.tree.Item(menu);
			}
			me.add(menu);
		}
	}
});
Pallasli.tree.Tree = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["tree"]=Pallasli.tree.Tree ;
$.extend( Pallasli.tree.Tree.prototype, Pallasli.Component.prototype, {
	initComponent : function() {
		var me = this;
		me.tmpl =  '<div style="width:28%; {1} {2}">'
			+ '<ul id="{0}" style="{1}">'
			+ '</ul>' + '</div>' ;
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left;":"right;");
		}
		var widthCfg="";
		if(me.initCfg.width){
			widthCfg="width:"+me.initCfg.width+"px;";
		}
		me.defaultChildType="treeitem";
		me.initCfg.widthCfg=widthCfg;
		me.initCfg.floatTo=floatTo; 
		me.dataOrder=["id","floatTo","widthCfg"];
		me.items = me.initCfg.items;
		//$(me.getHtml()).appendTo($(document.body));
		//for ( var index in me.items) {
			//console.log(me.items[index]);
			//me.add(me.items[index]);
		//}
	},
	addItems : function(){

		var me=this;
		for(var index=0; me.items&&index< me.items.length;index++){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasl.tree.Item(menu);
			}
			me.add(menu);
		}
	}
});/**



<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          每项标题
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
		每项组件
      </div>
    </div>
  </div> 
</div>

*/


Pallasli.layout.AccordionLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["accordion"]=Pallasli.layout.AccordionLayout ;
$.extend( Pallasli.layout.AccordionLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<div class="col-xs-12 col-md-6"></div>';
		this.childWrapperHtml='<div class="col-xs-12 col-md-6"></div>';
	}
});Pallasli.layout.AutoLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["auto"]=Pallasli.layout.AutoLayout ;
$.extend( Pallasli.layout.AutoLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.childWrapperHtml="";
	}
});Pallasli.layout.ColumnLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["column"]=Pallasli.layout.ColumnLayout ;
$.extend( Pallasli.layout.ColumnLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.childWrapperHtml='<div class="col-xs-12 col-md-4"></div>';
	}
});Pallasli.layout.FormLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["form"]=Pallasli.layout.FormLayout ;
$.extend( Pallasli.layout.FormLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<form></form>';
	}
});/**
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
});Pallasli.layout.RowLayout =  function(cfg){
	Pallasli.layout.Layout.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["row"]=Pallasli.layout.RowLayout ;
$.extend( Pallasli.layout.RowLayout.prototype, Pallasli.layout.Layout.prototype, {
	initLayout : function(cfg) {
		this.mainWrapperHtml='<div class="row"></div>';
	}
});Pallasli.panel.tree.Item =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["treemenu"]=Pallasli.panel.tree.Item ;
$.extend( Pallasli.panel.tree.Item.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.initCfg=cfg;
		me.tmpl = '<li><a href="{0}" target="{2}"'
			+ ' style="float: left"> {1} </a></li>';
		me.dataOrder=["url","text","target"];
	}
});
/**
 * <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dLabel">
  ...
</ul>
 */

Pallasli.panel.tree.Tree = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["treepanel"]=Pallasli.panel.tree.Tree ;
$.extend( Pallasli.panel.tree.Tree.prototype, Pallasli.Component.prototype, {
	initComponent : function() {
		var me = this;
		me.tmpl =  '<div class="container" style="width:28%; {1} {2}">'
			+ '<ul id="{0}" class="nav" style="{1}">'
			+ '</ul>' + '</div>' ;
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left;":"right;");
		}
		var widthCfg="";
		if(me.initCfg.width){
			widthCfg="width:"+me.initCfg.width+"px;";
		}
		me.defaultChildType="treemenu";
		me.initCfg.widthCfg=widthCfg;
		me.initCfg.floatTo=floatTo; 
		me.dataOrder=["id","floatTo","widthCfg"];
		me.items = me.initCfg.items;
		//$(me.getHtml()).appendTo($(document.body));
		//for ( var index in me.items) {
			//console.log(me.items[index]);
			//me.add(me.items[index]);
		//}
	},
	addItems : function(){

		var me=this;
		for(var index in me.items){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasli.panel.tree.Item(menu);
			}
			me.add(menu);
		}
	}
});Pallasli.panel.nav.Item =  function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["navmenu"]=Pallasli.panel.nav.Item ;
$.extend( Pallasli.panel.nav.Item.prototype, Pallasli.Component.prototype, {
	initComponent : function(cfg) {
		var me = this;
		me.initCfg=cfg;
		me.tmpl = '<li><a href="{0}" target="{2}"'
			+ ' class="list-group-item" style="float: left"> {1} </a></li>';
		me.dataOrder=["url","text","target"];
	}
});
/**
 * <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dLabel">
  ...
</ul>
 */

Pallasli.panel.nav.Nav = function(cfg){
	Pallasli.Component.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["nav"]=Pallasli.panel.nav.Nav ;
$.extend( Pallasli.panel.nav.Nav.prototype, Pallasli.Component.prototype, {
	initComponent : function() {
		var me = this;
		me.tmpl = '<div  id="{0}" class="navbar" style="{1} {2}">'
			+ '<div class="navbar-inner">'
			+ '<div class="container">'
			+ '<div class="nav-collapse">'
			+ '<ul id="{3}" class="nav" style="{1}">'
			+ '</ul>' + '</div>' + '</div>' + '</div>' + '</div>';
		var floatTo="";
		if(me.initCfg.region){
			floatTo="float:"+(me.initCfg.region=="west"?"left;":"right;");
		}
		var widthCfg="";
		if(me.initCfg.width){
			widthCfg="width:"+me.initCfg.width+"px;";
		}
		me.defaultChildType="navmenu";
		me.initCfg.widthCfg=widthCfg;
		me.initCfg.floatTo=floatTo; 
		me.layoutId= "pallali-gen-" + Pallasli.generateMixed(32);
		me.dataOrder=["id","floatTo","widthCfg","layoutId"];
		me.items = me.initCfg.items;
		//$(me.getHtml()).appendTo($(document.body));
		//for ( var index in me.items) {
			//console.log(me.items[index]);
			//me.add(me.items[index]);
		//}
	},
	addItems : function(){

		var me=this;
		for(var index in me.items){
			var menu=me.items[index];
			if(!menu.getHtml){
				menu=new Pallasli.panel.nav.Item(menu);
			}
			me.add(menu);
		}
	}
});