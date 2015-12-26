
(function($){
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
});
	
})(jQuery)