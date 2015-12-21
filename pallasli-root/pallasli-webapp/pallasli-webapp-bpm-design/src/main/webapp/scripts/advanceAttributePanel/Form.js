Ext.define('Pallas.activitiDesigner.advanceAttributePanel.Form', {
	extend : "Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel",
	title : "表单",
	simpleName : "form",
	alias : [ 'advanceattributepanel.form' ],
	initComponent : function() {
		var me = this;
		Ext.create('Ext.data.Store', {
			storeId : 'formPropertiesStore',
			fields : [ 'name', 'caption', 'length', 'type', 'default',
					'config', 'displayType' ],
			data : {
				'items' : []
			},
			proxy : {
				type : 'memory',
				reader : {
					type : 'json',
					root : 'items'
				}
			}
		});

		var formPropertiesGrid = Ext.create('Ext.grid.Panel', {
			title : "表单属性",
			columnWidth : 1,
			selType : 'cellmodel',
			store : Ext.data.StoreManager.lookup('formPropertiesStore'),
			plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 1
			}) ],
			tbar : [ {
				text : "增加",
				handler : function() {
					formPropertiesGrid.store.add({});
				}
			} ],
			columns : [ {
				text : '属性name',
				dataIndex : 'name',
				editor : {
					xtype : 'textfield',
				},
				flex : 1
			}, {
				text : '属性名称',
				dataIndex : 'caption',
				editor : {
					xtype : 'textfield',
				},
				flex : 1
			}, {
				text : '字段类型',
				dataIndex : 'type',
				editor : {
					xtype : 'combo',
				},
				flex : 1
			}, {
				text : '显示方式',
				dataIndex : 'displayType',
				editor : {
					xtype : 'combo',
				},
				flex : 1
			}, {
				text : '字段长度',
				dataIndex : 'length',
				editor : {
					xtype : 'numberfield',
				},
				flex : 1
			}, {
				text : '默认值',
				dataIndex : 'default',
				editor : {
					xtype : 'textfield',
				},
				flex : 1
			}, {
				text : '配置',
				dataIndex : 'config',
				editor : {
					xtype : 'combo',
				},
				flex : 1
			} ],
			autoHeight : true
		});
		me.items = [ {
			fieldLabel : "所属应用",
			value : " "
		}, {
			fieldLabel : "表单key",
			value : " "
		}, {
			fieldLabel : "表单名称",
			value : " "
		}, formPropertiesGrid ];
		me.items = [];
		me.callParent();
	}
});
