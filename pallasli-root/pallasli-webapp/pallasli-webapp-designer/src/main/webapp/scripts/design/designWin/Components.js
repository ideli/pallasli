Ext.define('Pallas.design.designWin.Components', {
	statics : {

		containerItems : [ {
			xtype : "button",
			border : false,
			text : 'form',
			cType : 'Ext.form.Panel',
			iconCls : 'icon-form',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : 'panel',
			cType : 'Ext.Panel',
			iconCls : 'icon-panel',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : 'grid',
			cType : 'Ext.grid.Panel',
			iconCls : 'icon-grid',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : 'tree',
			cType : 'Ext.tree.Panel',
			iconCls : 'icon-tree',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}

		],

		formItems : [ {
			xtype : "button",
			border : false,
			text : '文本框',
			cType : 'Ext.form.field.Text',
			iconCls : 'icon-field-text',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '多行文本',
			cType : 'Ext.form.field.TextArea',
			iconCls : 'icon-field-textarea',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '单选框',
			cType : 'Ext.form.field.Radio',
			iconCls : 'icon-radio',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '复选框',
			cType : 'Ext.form.field.Checkbox',
			iconCls : 'icon-checked',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '日期框',
			cType : 'Ext.form.field.Date',
			iconCls : 'icon-field-date',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '下拉框',
			cType : 'Ext.form.field.ComboBox',
			iconCls : 'icon-combobox',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}, {
			xtype : "button",
			text : '按钮',
			cType : 'Ext.button.Button',
			iconCls : 'icon-button',
			cls : 'btn-comp',
			listeners : {
				click : function() {
				}
			}
		}

		]

	}
});