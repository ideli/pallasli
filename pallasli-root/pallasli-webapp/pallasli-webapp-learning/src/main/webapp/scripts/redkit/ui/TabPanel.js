/**
 * @author torworx
 */

Redkit.TabPanel = function(config) {
	this._config = {
		id : 'main-tabs',
		activeTab : 0,
		margins : '0 5 5 0',
		resizeTabs : true,
		tabWidth : 150,
		minTabWidth : 120,
		enableTabScroll : true,
		plugins : new Ext.ux.TabCloseMenu()
	};
	Ext.apply(this._config, config);
	Redkit.TabPanel.superclass.constructor.call(this, this._config);

	// this.addListener("tabchange", this.doOnTabChange);

	this.addEvents({
				opentab : true
			});

	// main view
	this.mainview = this.createTab({
				id : 'main-view',
				title : '主页',
				hideMode : 'offsets',
				useFrame : true,
				autoLoad :{
					url : 'tabFrame.jsp?url=manage/mainPage.htm?method=findMainPage',
					scripts: true
				}
	
			});
	this.setActiveTab(this.mainview);
};

Ext.extend(Redkit.TabPanel, Ext.TabPanel, {

	// doOnTabChange: function(tabPanel, tab) {
	// if (Ext.isIE && tab && tab.rendered) {
	// // var _browser = tab.browser;
	// // tab.remove(_browser);
	// // tab.add(_browser);
	// // tab.doLayout();
	// }
	// },

	createTab : function(attrs) {
		var _browser;
		if (attrs.useFrame) {
			var _arrts = {
				header : false,
				border : false,
				bodyBorder : false
				// ,
				// loadMask: {
				// msg : '正在加载页面，请稍候...'
				// }
			}
			Ext.apply(_arrts, attrs)
			_browser = new Ext.ux.ManagedIFrame.Panel(_arrts);
		} else {
			_browser = new Ext.Panel({
						border : false
					});
		}

		// var _arrts = {
		// layout: 'fit',
		// border: false,
		// items: [_browser]
		// }
		//		
		// Ext.apply(_arrts, attrs);
		//		
		// var _tab = new Ext.Panel(_arrts);
		// // Add for frame
		// _tab.browser = _browser;

		this.add(_browser);

		return _browser;
	},

	findTabById : function(id) {

		// this.findBy(function(component){
		//		
		// });
		var tab = null;
		if (url) {
			this.items.each(function(item) {
						if (item.id == id) {
							tab = item;
							return null;
						}
					});
		}
		return tab;
	},

	findTabByUrl : function(url) {
		if (url)
			return this.findTabByUrlCode(hashCode(url));
	},

	findTabByUrlCode : function(urlCode) {
		var tab = null;
		if (urlCode) {
			this.items.each(function(item) {
						if (item._urlCode == urlCode) {
							tab = item;
							return null;
						}
					});
		}
		return tab;
	},

	navigate : function(tab, url, params) {

		var urlCode = hashCode(url);

		url = urlAddParams(url, {
					uc : urlCode
				});

		if (tab.initialConfig.useFrame) {
			if (url)
				tab.setSrc(url);
			else
				tab.setSrc();
		} else {
			var updater = tab.getUpdater();
			updater.update({
						url : url
					});
		}

		tab._url = url;
		tab._urlCode = urlCode;
	},

	updateMainTab : function(cfg) {
		url = urlAddParams(cfg.url, cfg.params);
		if ((cfg) && (cfg.title))
			this.mainview.setTitle(cfg.title);
		this.setActiveTab(this.mainview);
		if (cfg)
			this.navigate(this.mainview, url);
	},

	openTab : function(cfg, update) {
		var tab = null;
		url = urlAddParams(cfg.url, cfg.params);

		if (cfg.singleton) {
			tab = this.findTabByUrlCode(cfg.urlCode);
			if (!tab)
				tab = this.findTabByUrl(url);
		}

		if (!tab) {
			tab = this.createTab({
						id : cfg.id,
						title : cfg.title,
						iconCls : cfg.iconCls,
						hideMode : 'offsets',
						closable : true,
						useFrame : true
					});
			update = true;
		}

		if (tab) {
			this.setActiveTab(tab);
			if (update)
				this.navigate(tab, url);
		}
		return tab;
	},

	onenTopWindow : function(cfg) {
		var url = urlAddParams(cfg.url, cfg.params);
		var frame = new top.Ext.ux.ManagedIFrame.Panel({
				border : false,
				loadMask : {
					msg : '正在加载页面，请稍候...'
				}
		});
		var winCfg = {
			id : 'iframeWin',
			height : 300,
			width : 400,
			layout : 'fit',
			constrain : true,
			closable : true,
			modal : true,
			items : frame
		};
		Ext.apply(winCfg, cfg.win);
		var win = new top.Ext.Window(winCfg);
		win.show();
		frame.setSrc(url);
	}
});

function hashCode(str) {
	var h = 0, off = 0;
	var len = str.length;
	for (var i = 0; i < len; i++) {
		h = 31 * h + str.charCodeAt(off++);
		h = h % 2147483648;
	}
	return h;
}

function urlAddParams(url, params) {
	var u = url;
	if (params && typeof params == 'object') {
		var query = '';
		if (u.indexOf('?') < 0)
			u += '?';
		else
			u += '&';

		for (var p in params)
			query = query + '&' + p + '=' + params[p];

		query = query.substr(1);

		u += query;
	}
	return u;
};