Ext.define('Pallas.activitiDesigner.advanceAttributePanel.Handler', {
	extend : "Pallas.activitiDesigner.advanceAttributePanel.AbstractPanel",
	title : "参与者",
	simpleName : "handler",
	alias : [ 'advanceattributepanel.handler' ],
	initComponent : function() {
		var me = this;
		Ext.create('Ext.data.Store', {
			storeId : 'simpsonsStore',
			fields : [ 'name', 'email', 'phone' ],
			data : {
				'items' : [ {
					'name' : 'Lisa',
					"email" : "lisa@simpsons.com",
					"phone" : "555-111-1224"
				}, {
					'name' : 'Bart',
					"email" : "bart@simpsons.com",
					"phone" : "555-222-1234"
				}, {
					'name' : 'Homer',
					"email" : "home@simpsons.com",
					"phone" : "555-222-1244"
				}, {
					'name' : 'Marge',
					"email" : "marge@simpsons.com",
					"phone" : "555-222-1254"
				} ]
			},
			proxy : {
				type : 'memory',
				reader : {
					type : 'json',
					root : 'items'
				}
			}
		});

		var chulirenGrid = Ext.create('Ext.grid.Panel', {
			title : "处理人",
			columnWidth : 1,
			store : Ext.data.StoreManager.lookup('simpsonsStore'),
			columns : [ {
				text : 'Name',
				dataIndex : 'name'
			}, {
				text : 'Email',
				dataIndex : 'email',
				flex : 1
			}, {
				text : 'Phone',
				dataIndex : 'phone'
			} ],
			autoHeight : true
		});
		var chuanyuerenGrid = Ext.create('Ext.grid.Panel', {
			title : "传阅人",
			columnWidth : 1,
			store : Ext.data.StoreManager.lookup('simpsonsStore'),
			columns : [ {
				text : 'Name',
				dataIndex : 'name'
			}, {
				text : 'Email',
				dataIndex : 'email',
				flex : 1
			}, {
				text : 'Phone',
				dataIndex : 'phone'
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
		}, chulirenGrid, chuanyuerenGrid ];
		me.callParent();
	}
});
