Ext.define('Pallas.extDesigner.Designer', {
	extend : "Ext.window.Window",
	requires : [ 'Pallas.extDesigner.PageElementTree' ],
	border : false,
	layout : "border",
	draggable : false,
	collapsible : false,
	constrain : true,
	initComponent : function(config) {
		var me = this;
		var store = Ext.create('Ext.data.TreeStore', {
			root : {
				text : "基础面板",
				expanded : true,
				children : []
			}
		});

		var containerItems = [ {
			xtype : "button",
			border : false,
			text : 'form',
			cType : 'Pallas.extDesigner.shape.panel.FormPanel',
			iconCls : 'icon-form',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : 'panel',
			cType : 'Pallas.extDesigner.shape.panel.Panel',
			iconCls : 'icon-panel',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : 'grid',
			cType : 'Pallas.extDesigner.shape.panel.Grid',
			iconCls : 'icon-grid',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : 'tree',
			cType : 'Pallas.extDesigner.shape.panel.TreePanel',
			iconCls : 'icon-tree',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}

		];

		var formItems = [ {
			xtype : "button",
			border : false,
			text : '文本框',
			cType : 'Pallas.extDesigner.shape.field.Text',
			iconCls : 'icon-field-text',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '多行文本',
			cType : 'Pallas.extDesigner.shape.field.TextArea',
			iconCls : 'icon-field-textarea',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '单选框',
			cType : 'Pallas.extDesigner.shape.field.Radio',
			iconCls : 'icon-radio',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '复选框',
			cType : 'Pallas.extDesigner.shape.field.Checkbox',
			iconCls : 'icon-checked',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '日期框',
			cType : 'Pallas.extDesigner.shape.field.Date',
			iconCls : 'icon-field-date',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '下拉框',
			cType : 'Pallas.extDesigner.shape.field.ComboBox',
			iconCls : 'icon-combobox',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '按钮',
			cType : 'Pallas.extDesigner.shape.action.Button',
			iconCls : 'icon-button',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}

		];
		var treePanel1 = Ext.create('Ext.panel.Panel', {
			title : '容器',
			border : false,
			autoHeight : true,
			collapsible : true,
			items : containerItems

		});
		var treePanel2 = Ext.create('Ext.panel.Panel', {
			title : '数据源',
			autoHeight : true,
			collapsible : true,
			border : false,
			items : []
		});
		var treePanel3 = Ext.create('Ext.panel.Panel', {
			title : '工具条',
			autoHeight : true,
			collapsible : true,
			border : false,
			items : []
		});
		var treePanel4 = Ext.create('Ext.panel.Panel', {
			title : '表单组件',
			autoHeight : true,
			collapsible : true,
			border : false,
			items : formItems
		});
		var treePanel = Ext.create('Pallas.extDesigner.PageElementTree', {
			store : store
		});

		var compPanel = Ext.create('Ext.panel.Panel', {
			title : '组件选择',
			region : "north",
			border : false,
			height : 200,
			// collapsible :true,
			tools : [ {
				type : 'expand',
				tooltip : 'Refresh form Data',
				// hidden:true,
				handler : function(event, toolEl, panel) {
					treePanel1.expand();
					treePanel2.expand();
					treePanel3.expand();
					treePanel4.expand();

				}
			}, {
				type : 'collapse',
				tooltip : 'Get Help',
				handler : function(event, toolEl, panel) {
					treePanel1.collapse();
					treePanel2.collapse();
					treePanel3.collapse();
					treePanel4.collapse();
				}
			}, {
				type : 'right',
				tooltip : 'Get Help',
				handler : function(event, toolEl, panel) {
					compPanel.collapse();
				}
			} ],
			items : [ treePanel1, treePanel2, treePanel3, treePanel4 ],
			rootVisible : false
		});

		var attributePanel = Ext.create('Pallas.extDesigner.AttributePanel', {
			region : "center"
		});
		attributePanel.on("propertychange", function(data, name, newV, oldV) {
			// node.set("text","name");
			// basicEditorPanel.layout="absolute";
			// basicEditorPanel.getLayout();
			// basicEditorPanel.updateLayout({
			// defer : false,
			// isRoot : false
			// });
		});
		var compAttributePanel = Ext.create('Ext.panel.Panel', {
			width : 300,
			region : "east",
			layout : "border",
			items : [ attributePanel, compPanel ]
		});

		var mainPanel = Ext.create('Pallas.extDesigner.EditorPanel', {
			region : "center",
		});

		var headerPanel = Ext.create('Pallas.extDesigner.HeaderActionPanel', {
			region : "north"
		});
		me.items = [ headerPanel, compAttributePanel, treePanel, mainPanel ];
		me.callParent();
	}
});
