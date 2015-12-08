Ext.define('Pallas.extDesigner.waapp.framework.Framework', {
	extend : "Ext.panel.Panel",
	requires : [ "Pallas.extDesigner.waapp.framework.Titlebar",
			"Pallas.extDesigner.waapp.framework.Toolbar",
			"Pallas.extDesigner.waapp.framework.Outline",
			"Pallas.extDesigner.waapp.framework.Workspace",
			"Pallas.extDesigner.waapp.utils.AllComponents" ],
	alias : [ "waapp.framework" ],
	border : false,
	layout : "border",
	initComponent : function() {
		var me = this;
		me.titlebar = Ext.create('waapp.titlebar', {
			region : "north",
			height : 40,
			collapsible : true,
			titleCollapse : "mini",
			split : true,
			hideCollapseTool : true,
			collapseMode : 'mini'
		});
		me.toolbar = Ext.create('waapp.toolbar');
		me.outline = Ext.create('waapp.outline', {
			ownerFramework : me,
			region : 'west',
			split : true,
			width : 240,
			minSize : 150,
			maxSize : 500,
			collapsible : true,
			titleCollapse : "mini",
			split : true,
			hideCollapseTool : true,
			collapseMode : 'mini'
		});
		me.workspace = Ext.create('waapp.workspace', {
			region : 'center',
			border : false
		});
		me.items = [ me.titlebar, {
			xtype : "panel",
			layout : "border",
			region : "center",
			tbar : me.toolbar,
			items : [ me.outline, me.workspace ]
		} ];
		me.callParent();
		Pallas.extDesigner.waapp.utils.AllComponents.framwork = me;
	},
	// 设置消息窗口显示位置
	getAnimateTarget : function() {
		return document;
	},
	closeEditor : function(key) {
		return this.workspace.removeObject(key);
	},
	openObject : function(oid) {
		this.outline.selectObject(oid);
		this.workspace.openEditor(oid);
	},
	activateObject : function(oid) {
		this.outline.selectObject(oid);
		this.workspace.selectObject(oid);
	},
	deleteObject : function(key) {
		this.outline.removeObject(key);
		this.workspace.removeObject(key);
	}
});