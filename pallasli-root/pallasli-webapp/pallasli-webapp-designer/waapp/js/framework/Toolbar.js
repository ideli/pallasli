Ext.define('Pallas.extDesigner.waapp.framework.Toolbar', {
	extend : "Ext.toolbar.Toolbar",
	requires : [ "Pallas.extDesigner.waapp.utils.Actions",
			'Pallas.extDesigner.waapp.config.ApplicationInfo' ],
	alias : [ "waapp.toolbar" ],
	border : false,
	initComponent : function() {
		var me = this;
		var actions = Pallas.extDesigner.waapp.utils.Actions;
		var applicationinfo = Pallas.extDesigner.waapp.config.ApplicationInfo;
		me.items = [ {
			xtype : 'buttongroup',
			title : '对象操作',
			// columns : 3,
			defaults : {
				scale : 'small',
				iconCls : 'icon-administrator-button-default'
			},
			items : [ actions.openBtn, actions.refreshBtn, actions.saveBtn,
					actions.copyBtn, actions.pasteBtn, actions.deleteBtn,
					actions.appchgBtn, actions.exportBtn, actions.importBtn ]
		}
		// , '->', applicationinfo.departmentname, applicationinfo.username
		];
		me.callParent();
	}
});