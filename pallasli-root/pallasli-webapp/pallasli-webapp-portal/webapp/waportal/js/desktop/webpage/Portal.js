Ext.define('Pallas.portal.waapp.desktop.webpage.Portal', {
	extend : "Ext.panel.Panel",
	alias : [ "webpage.portal" ],

	requires : [ 'Pallas.portal.waapp.lib.Actions',
			'Pallas.portal.waapp.desktop.webpage.Shortcuts',
			'Pallas.portal.waapp.plugins.Portal'],
	closable : false,
	title : '工作桌面',
	border : false,
	layout : 'border',
	iconCls : 'icon-portal-portal',

	initComponent : function() {
		var me = this;
		this.buttonPanel = new Ext.Panel({
			region : 'west',
			border : false,
			bodyStyle : 'background:transparent none',
			width : 15
		});
		var panelItems;
		switch (Pallas.portal.waapp.AppUtil.MixkyApp.userConfig.columns) {
		case 2:
			panelItems = [ {
				columnWidth : .5,
				style : 'padding:10px 0 10px 10px'
			}, {
				columnWidth : .5,
				style : 'padding:10px 10px 10px 10px'
			} ];
			break;
		case 4:
			panelItems = [ {
				columnWidth : .25,
				style : 'padding:10px 0 10px 10px'
			}, {
				columnWidth : .25,
				style : 'padding:10px 0 10px 10px'
			}, {
				columnWidth : .25,
				style : 'padding:10px 0 10px 10px'
			}, {
				columnWidth : .25,
				style : 'padding:10px 10px 10px 10px'
			} ];
			break;
		default:
			panelItems = [ {
				columnWidth : .33,
				style : 'padding:10px 0 10px 10px'
			}, {
				columnWidth : .33,
				style : 'padding:10px 0 10px 10px'
			}, {
				columnWidth : .33,
				style : 'padding:10px 10px 10px 10px'
			} ];
			break;
		} 
		this.portalPanel = Ext.create("widget.uxportal",{
			region : 'center',
			//bodyStyle : 'background:transparent none',
			border : false,
			items : panelItems
		});

		this.portalPanel.on('drop', function() {
			this.setPortletsPos();
		}, this);

		this.contextmenu = new Ext.menu.Menu({
			items : [ Pallas.portal.waapp.lib.Actions.Preferences,
			// Pallas.portal.waapp.lib.Actions.SavePreferences,
			// Pallas.portal.waapp.lib.Actions.SaveAsDefaultPreferences,
			// '-',
			Pallas.portal.waapp.lib.Actions.ChangePassword,
			// '-',
			Pallas.portal.waapp.lib.Actions.OpenAdministrator,
					Pallas.portal.waapp.lib.Actions.OpenHelp,
					Pallas.portal.waapp.lib.Actions.Exit ]
		});

		me.items = [ this.buttonPanel, this.portalPanel ];
		this.callParent();

		this.buttonPanel.on('afterrender', function(bp) {
			this.shortcuts = new Pallas.portal.waapp.desktop.webpage.Shortcuts(
					{
						renderTo : bp.body
					});
			this.shortcuts.initComponent();
			this.shortcuts.on('columnsrefresh', function(width) {
				this.buttonPanel.setWidth(width);
				this.doLayout();
			}, this, {
				delay : 100
			});
		}, this);

	},
	// overwrite
	onRender : function(ct, position) {
		this.callParent(arguments);
		this.body.on('contextmenu', function(e) {
			if (e.target.id == this.buttonPanel.body.id
					|| e.target.id == this.portalPanel.body.id
					|| e.target.id == this.portalPanel.body.first().id) {
				e.stopEvent();
				if (!this.contextmenu.el) {
					this.contextmenu.render();
				}
				var xy = e.getXY();
				xy[1] -= this.contextmenu.el.getHeight();
				this.contextmenu.showAt(xy);
			}
		}, this);
	},
	getMinHeightCol : function() {
		var height = 10000;
		var index = 0;
		this.portalPanel.items.each(function(columnitem, col) {
			if (columnitem.getHeight() < height) {
				height = columnitem.getHeight();
				index = col;
			}
		}, this);
		return index;
	},
	// extend method
	addSubject : function(o) {
		var id = 'portlet-' + o.key;
		var url = 'portlet.do';
		if (Ext.isDefined(o.applicationkey)) {
			id = 'portlet-' + o.applicationkey + '.' + o.key;
			var app = Mixky.wasoft.cache.Applications[o.applicationkey];
			if (!Ext.isDefined(app)) {
				alert('未定义' + o.applicationkey + '应用');
				return;
			}
			url = app.url + '/' + 'portlet.do';
		}
		var panel = Ext.getCmp(id);
		if (!panel) {
			var col = Ext.isDefined(o.col) ? o.col : this.getMinHeightCol();
			col = col % MixkyApp.userConfig.columns;
			panel = new Ext.ux.Portlet({
				id : id,
				title : o.text,
				layout : 'fit',
				iconCls : o.iconCls,
				height : o.webheight,
				tools : [ {
					id : 'refresh',
					handler : function() {
						panel.refresh();
					},
					qtip : "刷新栏目内容"
				} ],
				autoLoad : {
					url : url,
					params : {
						key : o.key,
						appkey : o.applicationkey,
						height : o.webheight
					},
					scripts : true
				},
				refresh : function() {
					panel.doAutoLoad();
				}
			});
			this.portalPanel.items.get(col).add(panel);
			this.portalPanel.doLayout();
			panel.key = o.key;
			panel.applicationkey = o.applicationkey;
		}
		return panel;
	},
	// extend method
	removeSubject : function(appkey, key) {
		var id = 'portlet-' + key;
		if (Ext.isDefined(appkey)) {
			id = 'portlet-' + appkey + '.' + key;
		}
		var panel = Ext.getCmp(id);
		if (panel) {
			var pc = panel.findParentByType('portalcolumn');
			pc.remove(panel);
			// panel.destroy();
		}
	},
	// extend method
	addShortcut : function(o) {
		o.handler = function(b, e) {
			Pallas.portal.waapp.lib.Actions.openShortcut(this.btntype,
					this.applicationkey, this.key, e);
		};
		this.shortcuts.addShortcut(o);
	},
	// extend method
	removeShortcut : function(btntype, appkey, key) {
		var btn = this.shortcuts.getButtonCmp(btntype, appkey, key);
		if (btn) {
			this.shortcuts.removeShortcut(btn);
		}
	},
	getButtonCmp : function(btntype, appkey, key) {
		return this.shortcuts.getButtonCmp(btntype, appkey, key);
	},
	setPortletsPos : function() {
		this.portalPanel.items.each(function(columnitem, col) {
			columnitem.items.each(function(panel, row) {
				var subject = MixkyApp.getSubject(panel.applicationkey,
						panel.key);
				subject.col = col;
				subject.row = row;
			}, this);
		}, this);
	}

});