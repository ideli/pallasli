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

